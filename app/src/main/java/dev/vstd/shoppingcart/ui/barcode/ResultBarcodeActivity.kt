package dev.vstd.shoppingcart.ui.barcode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.keego.shoppingcart.databinding.ActivityResultBarcodeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ResultBarcodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBarcodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViews()
    }

    private fun initViews() {
        binding.btnBack.setOnClickListener {
            GlobalScope.launch {
                Intent(this@ResultBarcodeActivity, BarcodeActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

    private fun initBinding() {
        binding = ActivityResultBarcodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}