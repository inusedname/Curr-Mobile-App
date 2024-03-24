package dev.vstd.shoppingcart.utils.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import dev.keego.shoppingcart.databinding.LayoutTextInputBinding

object EditTextAlertDialog {
    @Suppress("LocalVariableName")
    fun create(_context: Context, title: String, onCreateClicked: (String) -> Unit): AlertDialog {
        val builder = AlertDialog.Builder(_context)
        val context = builder.context
        val binding = LayoutTextInputBinding.inflate(LayoutInflater.from(context))

        binding.btnScan.setOnClickListener {
            // TODO: Implement button click listener
        }

        builder
            .setTitle(title)
            .setPositiveButton("Create") { _, _ ->
                // Create new group
                val name = binding.etName.text.toString()
                if (name.isNotBlank()) {
                    onCreateClicked(name)
                } else {
                    // Show error
                    Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .setView(binding.root)
            .create()
            .show()

        return builder.create()
    }
}