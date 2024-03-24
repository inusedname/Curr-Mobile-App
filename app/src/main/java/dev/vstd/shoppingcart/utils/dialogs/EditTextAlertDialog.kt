package dev.vstd.shoppingcart.utils.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.keego.shoppingcart.databinding.LayoutTextInputBinding
import dev.vstd.shoppingcart.utils.beGone

object EditTextAlertDialog {
    @Suppress("LocalVariableName")
    fun create(
        _context: Context,
        title: String,
        scanable: Boolean = true,
        onCreateClicked: (String) -> Unit,
    ): AlertDialog {
        val builder = MaterialAlertDialogBuilder(_context)
        val context = builder.context
        val binding = LayoutTextInputBinding.inflate(LayoutInflater.from(context))

        if (!scanable) binding.btnScan.beGone()

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

        return builder.create()
    }
}