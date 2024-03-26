package dev.vstd.shoppingcart.ui.barcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.keego.shoppingcart.databinding.ActivityResultBarcodeBinding

class ResultBarcodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBarcodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViews()
    }

    private fun initViews() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun initBinding() {
        binding = ActivityResultBarcodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}