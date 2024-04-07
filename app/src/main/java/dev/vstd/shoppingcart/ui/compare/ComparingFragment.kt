package dev.vstd.shoppingcart.ui.compare

import android.content.Intent
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
import dev.vstd.shoppingcart.ui.base.BaseFragment
import dev.vstd.shoppingcart.ui.compare.adapter.ComparePriceAdapter
import dev.vstd.shoppingcart.ui.payment.PaymentActivity
import kotlinx.coroutines.launch

class ComparingFragment : BaseFragment<FragmentComparingBinding>() {
    private val comparingVimel by activityViewModels<ComparingVimel>()

    override fun onViewCreated(binding: FragmentComparingBinding) {
        initAdapters(binding)
        setOnClicks(binding)
        observeStates(binding)
    }

    private fun initAdapters(binding: FragmentComparingBinding) {
        binding.mainContent.comparePriceRecyclerView.adapter = ComparePriceAdapter {
            comparingVimel.getSellers(it.id)
            findNavController().navigate(R.id.action_comparingFragment_to_detailFragment)
        }
    }

    private fun observeStates(binding: FragmentComparingBinding) {
        viewLifecycleOwner.lifecycleScope.launch {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    comparingVimel.products.collect {
                        (binding.mainContent.comparePriceRecyclerView.adapter as ComparePriceAdapter).setData(it)
                    }
                }
            }
        }
    }

    private fun setOnClicks(binding: FragmentComparingBinding) {
        binding.fabCheckout.setOnClickListener {
            context?.let {
                Intent(it, PaymentActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
        binding.searchView.setOnSearchClickListener {
            comparingVimel.searchProduct(binding.searchView.query.toString())
        }
        binding.searchView.setOnQueryTextListener(object: OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank()) return false
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