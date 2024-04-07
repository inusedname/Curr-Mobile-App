package dev.vstd.shoppingcart.ui.compare

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
import dev.vstd.shoppingcart.ui.base.BaseFragment
import dev.vstd.shoppingcart.ui.compare.adapter.SellerAdapter
import kotlinx.coroutines.launch
import timber.log.Timber


class SellersFragment : BaseFragment<FragmentSellersBinding>() {
    private val comparingVimel by activityViewModels<ComparingVimel>()

    override fun onViewCreated(binding: FragmentSellersBinding) {
        setupAdapters(binding)
        setOnClicks(binding)
        observeStates(binding)
    }

    private fun setOnClicks(binding: FragmentSellersBinding) {
        binding.toolbar.setNavigationOnClickListener { 
            findNavController().navigateUp()
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
                        Timber.d("Sellers: ${it.size}")
                        (binding.rvSellers.adapter as SellerAdapter).setData(it, comparingVimel.searchingProductImageUrl)
                    }
                }
            }
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSellersBinding
        get() = FragmentSellersBinding::inflate

}