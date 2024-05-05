package dev.vstd.shoppingcart.pricecompare.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentComparingBinding
import dev.vstd.shoppingcart.common.UiStatus
import dev.vstd.shoppingcart.common.ui.BaseFragment
import dev.vstd.shoppingcart.common.utils.beGone
import dev.vstd.shoppingcart.common.utils.beVisible
import dev.vstd.shoppingcart.common.utils.hideSoftKeyboard
import dev.vstd.shoppingcart.pricecompare.data.model.ComparingProduct
import dev.vstd.shoppingcart.pricecompare.ui.adapter.ComparePriceAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ComparingFragment : BaseFragment<FragmentComparingBinding>() {
    private val comparingVimel by activityViewModels<ComparingVimel>()
    private val uiStatus = MutableStateFlow<UiStatus<List<ComparingProduct>>>(UiStatus.Initial())

    override fun onViewCreated(binding: FragmentComparingBinding) {
        initAdapters(binding)
        setOnClicks(binding)
        observeStates(binding)
    }

    private fun initAdapters(binding: FragmentComparingBinding) {
        binding.mainContent.comparePriceRecyclerView.adapter = ComparePriceAdapter {
            comparingVimel.getSellers(it)
            findNavController().navigate(R.id.action_comparingFragment_to_detailFragment)
        }
    }

    private fun observeStates(binding: FragmentComparingBinding) {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    comparingVimel.products.collect {
                        uiStatus.value = UiStatus.Success(it)
                    }
                }
            }

            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    uiStatus.collect {
                        when (it) {
                            is UiStatus.Initial -> {
                                binding.mainContent.root.beGone()
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
                                    binding.mainContent.root.beGone()
                                } else {
                                    binding.layoutEmpty.root.beGone()
                                    binding.mainContent.root.beVisible()
                                    val adapter =
                                        binding.mainContent.comparePriceRecyclerView.adapter as ComparePriceAdapter
                                    adapter.setData(it.data)
                                }
                            }

                            is UiStatus.Error -> {
                                binding.layoutLoading.root.beGone()
                                binding.mainContent.root.beGone()
                                binding.layoutError.root.beVisible()
                                binding.layoutError.tvError.text = it.message
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setOnClicks(binding: FragmentComparingBinding) {
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank()) return false
                uiStatus.value = UiStatus.Loading()
                binding.searchView.hideSoftKeyboard()
                comparingVimel.searchProduct(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComparingBinding
        get() = FragmentComparingBinding::inflate
}