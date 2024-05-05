package dev.vstd.shoppingcart.pricecompare.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dev.keego.shoppingcart.databinding.FragmentSellersBinding
import dev.vstd.shoppingcart.common.UiStatus
import dev.vstd.shoppingcart.common.ui.BaseFragment
import dev.vstd.shoppingcart.common.utils.beGone
import dev.vstd.shoppingcart.common.utils.beVisible
import dev.vstd.shoppingcart.pricecompare.data.model.SellerInfo
import dev.vstd.shoppingcart.pricecompare.ui.adapter.SellerAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber


class SellersFragment : BaseFragment<FragmentSellersBinding>() {
    private val comparingVimel by activityViewModels<ComparingVimel>()
    private val uiStatus = MutableStateFlow<UiStatus<List<SellerInfo>>>(UiStatus.Loading())

    override fun onViewCreated(binding: FragmentSellersBinding) {
        setupAdapters(binding)
        setOnClicks(binding)
        observeStates(binding)
    }

    private fun setOnClicks(binding: FragmentSellersBinding) {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupAdapters(binding: FragmentSellersBinding) {
        binding.rvSellers.adapter = SellerAdapter {
            val url = it.url
            val intent = CustomTabsIntent.Builder()
                .build()
            intent.launchUrl(requireContext(), Uri.parse(url))
        }
    }

    private fun observeStates(binding: FragmentSellersBinding) {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    comparingVimel.sellers.collect {
                        Timber.d("Collecting sellers: $it")
                        uiStatus.value = UiStatus.Success(it)
                    }
                }
            }

            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    uiStatus.collect {
                        Timber.d("Collecting uiStatus: $it")
                        when (it) {
                            is UiStatus.Initial -> {
                                binding.mainContent.beGone()
                                binding.layoutEmpty.root.beVisible()
                            }

                            is UiStatus.Loading -> {
                                binding.layoutEmpty.root.beGone()
                                binding.layoutLoading.root.beVisible()
                            }

                            is UiStatus.Success -> {
                                binding.layoutLoading.root.beGone()
                                if (it.data.isEmpty()) {
                                    binding.layoutEmpty.root.beVisible()
                                    binding.mainContent.beGone()
                                } else {
                                    binding.layoutEmpty.root.beGone()
                                    binding.mainContent.beVisible()
                                    val adapter =
                                        binding.rvSellers.adapter as SellerAdapter
                                    adapter.setData(it.data, productLogo = comparingVimel.searchingProductImageUrl)
                                }
                            }

                            is UiStatus.Error -> {
                                binding.layoutLoading.root.beGone()
                                binding.mainContent.beGone()
                                binding.layoutError.root.beVisible()
                                binding.layoutError.tvError.text = it.message
                            }
                        }
                    }
                }
            }
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSellersBinding
        get() = FragmentSellersBinding::inflate

}