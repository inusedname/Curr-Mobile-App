package dev.vstd.shoppingcart.pricecompare.ui

import android.app.ProgressDialog
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
import dev.vstd.shoppingcart.common.MainActivity
import dev.vstd.shoppingcart.common.ui.BaseFragment
import dev.vstd.shoppingcart.pricecompare.retrofit.model.Filter
import dev.vstd.shoppingcart.pricecompare.retrofit.model.SerpResult
import dev.vstd.shoppingcart.pricecompare.ui.adapter.ComparePriceAdapter
import dev.vstd.shoppingcart.pricecompare.ui.adapter.ItemFilterBarAdapter
import dev.vstd.shoppingcart.pricecompare.ui.adapter.ItemFilterDrawerAdapter
import dev.vstd.shoppingcart.pricecompare.ui.adapter.ItemFilterOptionAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.logging.Handler

class ComparingFragment : BaseFragment<FragmentComparingBinding>() {
    private val comparingVimel by activityViewModels<ComparingVimel>()

    private lateinit var progressDialog: ProgressDialog

    private var isDone = false
        set(value) {
            field = value
            if (value) {
                MainScope().launch { findNavController().navigate(R.id.action_comparingFragment_to_detailFragment) }
            }
        }

    override fun onViewCreated(binding: FragmentComparingBinding) {
        initAdapters(binding)
        setOnClicks(binding)
        observeStates(binding)
        isDone = false
    }

    private fun initAdapters(binding: FragmentComparingBinding) {
        binding.mainContent.comparePriceRecyclerView.adapter = ComparePriceAdapter {
            showLoadingDialog()
            Timber.d("Product link: $it")
            isDone = false
            comparingVimel.getSerpProduct(it.serpapiProductApi) {
                dismissLoadingDialog()
                isDone = true
            }
        }

        binding.filterBar.itemFilterBarRecyclerView.adapter = ItemFilterBarAdapter(
            onClickFilterOption = {
                comparingVimel.selectFilter(it)
                changeData(binding)
            },
            comparingVimel
        )

        (activity as? MainActivity)?.setFilterAdapter(ItemFilterDrawerAdapter(
            onClickFilterOption = {
                comparingVimel.selectFilter(it)
                changeData(binding)
            },
            comparingVimel
        ))
    }


    private fun changeData(binding: FragmentComparingBinding) {
        (binding.filterBar.itemFilterBarRecyclerView.adapter as ItemFilterBarAdapter)
            .setData(comparingVimel.createFilterData())

        (activity as? MainActivity)?.setFilterDrawerData(comparingVimel.filters.value)

        comparingVimel.searchProductWithFilter()
    }

    private fun setProductData(binding: FragmentComparingBinding,it: SerpResult?) {

        (binding.mainContent.comparePriceRecyclerView.adapter as ComparePriceAdapter)
            .setData(it?.shoppingResults ?: listOf())
    }

    private fun setFilterData(binding: FragmentComparingBinding, it: List<Filter>) {
        (binding.filterBar.itemFilterBarRecyclerView.adapter as ItemFilterBarAdapter)
            .setData(it)

        (activity as? MainActivity)?.setFilterDrawerData(it)
    }


    private fun observeStates(binding: FragmentComparingBinding) {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    comparingVimel.serpResult.collect() {
                        setProductData(binding,it)
                        if (comparingVimel.selectedFilters.value.isEmpty()) {
                            setFilterData(binding, it?.filters ?: listOf())
                        }
                        else {
                            (binding.filterBar.itemFilterBarRecyclerView.adapter as ItemFilterBarAdapter)
                                .setData(comparingVimel.createFilterData())

                            (activity as? MainActivity)?.setFilterDrawerData(comparingVimel.filters.value)
                        }
                    }
                }
            }

            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    comparingVimel.serpResultFilter.collect {
                        if (it != null) {
                            setProductData(binding,it)
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
                comparingVimel.searchProduct(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.filterBar.btnFilter.setOnClickListener {
            (activity as? MainActivity)?.openDrawer()
        }
    }

    private fun showLoadingDialog() {
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Loading...")
        progressDialog.show()
    }

    private fun dismissLoadingDialog() {
        if (::progressDialog.isInitialized && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentComparingBinding
        get() = FragmentComparingBinding::inflate
}