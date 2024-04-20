package dev.vstd.shoppingcart.checklist.ui.addItem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.keego.shoppingcart.databinding.ActivityAddItemBinding
import dev.vstd.shoppingcart.common.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViews()
    }

    private fun initViews() {
        var groups = listOf("Group1", "Group2", "Group3")
//        var adapterGroup = ArrayAdapter(this, R.layout.list_group,groups)
//        binding.autoCompleteTxt.setAdapter(adapterGroup)

        binding.btnBack.setOnClickListener {
            GlobalScope.launch {
                Intent(this@AddItemActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        binding.btnAdd.setOnClickListener {
            GlobalScope.launch {
                Intent(this@AddItemActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

    private fun initBinding() {
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}