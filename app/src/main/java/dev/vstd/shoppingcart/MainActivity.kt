package dev.vstd.shoppingcart

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.databinding.ActivityMainBinding

@AndroidEntryPoint
open class MainActivity : AppCompatActivity() {

    private  lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}