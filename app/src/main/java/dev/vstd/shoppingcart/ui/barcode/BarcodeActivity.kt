package dev.vstd.shoppingcart.ui.barcode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dev.keego.shoppingcart.databinding.ActivityBarcodeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class BarcodeActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            Intent(context, ResultBarcodeActivity::class.java).also {
                context.startActivity(it)
            }
        }
    }

    private lateinit var binding: ActivityBarcodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViews()
    }

    private fun initViews() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnScan.setOnClickListener {
            GlobalScope.launch {
                Intent(this@BarcodeActivity, ResultBarcodeActivity::class.java).also {
                    startActivity(it)
                }
            }

//            val options = ScanOptions()
//            options.setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
//            options.setPrompt("Scan a barcode")
//            options.setBarcodeImageEnabled(true)
//            barcodeLauncher.launch(options)
        }
    }

    private val barcodeLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(
        ScanContract()
    ) { result: ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText(baseContext, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            binding.textResult.setText(result.contents)
            binding.textFormat.setText(result.formatName)
        }
    }

    private fun initBinding() {
        binding = ActivityBarcodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}