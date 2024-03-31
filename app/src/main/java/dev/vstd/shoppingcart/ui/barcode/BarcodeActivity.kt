package dev.vstd.shoppingcart.ui.barcode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dev.keego.shoppingcart.databinding.ActivityBarcodeBinding
import dev.vstd.shoppingcart.utils.Clipboard
import dev.vstd.shoppingcart.utils.toast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber


class BarcodeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBarcodeBinding
    private val barcode = MutableStateFlow<String?>("8938508475056")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarcodeBinding.inflate(layoutInflater)

        setOnClicks()
        observeStates()
        setContentView(binding.root)
    }

    private fun observeStates() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                barcode.collect { barcode ->
                    binding.textBarcode.text = barcode ?: "Not found in database"
                    binding.btnSearchInInternet.isEnabled = barcode != null
                }
            }
        }
    }

    private fun setOnClicks() {
        binding.apply {
            fabScanNow.setOnClickListener {
                val options = GmsBarcodeScannerOptions.Builder()
                    .setBarcodeFormats(
                        Barcode.FORMAT_ALL_FORMATS
                    )
                    .build()
                val scanner = GmsBarcodeScanning.getClient(this@BarcodeActivity, options)
                scanner.startScan()
                    .addOnSuccessListener { barcode ->
                        if (barcode.valueType != Barcode.TYPE_PRODUCT) {
                            Timber.d("Barcode not from product: value=${barcode.rawValue} valueType=${barcode.valueType}")
                            this@BarcodeActivity.toast("This barcode is not from a product")
                            return@addOnSuccessListener
                        }
                        this@BarcodeActivity.barcode.value = barcode.rawValue
                    }
                    .addOnCanceledListener {
                        Timber.d("Barcode scanning canceled")
                    }
                    .addOnFailureListener { e ->
                        Timber.e(e, "Barcode scanning failed")
                    }
            }
            btnCopy.setOnClickListener {
                barcode.value?.let {
                    Clipboard.copyToClipboard(this@BarcodeActivity, it)
                    Toast.makeText(this@BarcodeActivity, "Copied to clipboard", Toast.LENGTH_SHORT).show()
                }
            }
            btnSearchInInternet.setOnClickListener {
                barcode.value?.let {
                    FindBarcodeActivity.start(this@BarcodeActivity, it)
                }
            }
            appBar.setNavigationOnClickListener {
                finish()
            }
        }
    }

    companion object {
        fun start(context: Context) {
            Intent(context, BarcodeActivity::class.java).also {
                context.startActivity(it)
            }
        }
    }
}