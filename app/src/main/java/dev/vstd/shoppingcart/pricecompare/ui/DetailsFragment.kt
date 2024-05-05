package dev.vstd.shoppingcart.pricecompare.ui

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import dev.keego.shoppingcart.databinding.FragmentDetailsBinding
import dev.vstd.shoppingcart.common.ui.BaseFragment
import dev.vstd.shoppingcart.pricecompare.retrofit.model.ProductResults
import dev.vstd.shoppingcart.pricecompare.retrofit.model.SerpProduct
import dev.vstd.shoppingcart.pricecompare.retrofit.model.ShoppingResult
import dev.vstd.shoppingcart.pricecompare.ui.adapter.BuyingOptionAdapter
import kotlinx.coroutines.launch
import timber.log.Timber


class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    private val comparingVimel by activityViewModels<ComparingVimel>()

    override fun onViewCreated(binding: FragmentDetailsBinding) {
        setupAdapters(binding)
        setupInformation(binding)
        observeStates(binding)
    }

    private fun setupInformation(binding: FragmentDetailsBinding) {
        val product: ProductResults? = comparingVimel.serpProductResult?.productResults

        binding.apply {
            Glide.with(fragmentDetails).load(product?.getFirstImage()?.link).into(binding.ivProduct)

            binding.tvTitle.text = product?.title
            binding.ratingText.text = product?.rating?.toString() ?: "0"
            binding.rating.rating = product?.rating?.toFloat() ?: 0f
            binding.reviews.text = product?.reviews?.toString() ?: "0"
            binding.tvDescription.text = product?.description.toString()

            val extension:String? = product?.extensions?.filterNotNull()?.joinToString(separator = "\n")
//            binding.tvExtension.visibility = if (extension.isNullOrEmpty()) View.VISIBLE else View.GONE
            binding.tvExtension.text = extension

        }

    }

    private fun setupAdapters(binding: FragmentDetailsBinding) {
        binding.rvBuyingOptions.adapter = BuyingOptionAdapter {
            var url = it.link
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://$url"
            }

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }

    private fun observeStates(binding: FragmentDetailsBinding) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                comparingVimel.serpProductResult.let {
                    Timber.d("Sellers: $it")
                    (binding.rvBuyingOptions.adapter as BuyingOptionAdapter).setData(it?.sellersResults?.onlineSellers)
                }
            }
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

}