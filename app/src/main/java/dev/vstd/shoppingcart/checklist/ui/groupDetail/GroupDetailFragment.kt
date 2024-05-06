//package dev.vstd.shoppingcart.checklist.ui.groupDetail
//
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.appcompat.app.AlertDialog
//import androidx.core.content.res.ResourcesCompat
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.lifecycleScope
//import androidx.lifecycle.repeatOnLifecycle
//import androidx.navigation.fragment.findNavController
//import androidx.navigation.ui.setupWithNavController
//import com.google.android.material.dialog.MaterialAlertDialogBuilder
//import dagger.hilt.android.AndroidEntryPoint
//import dev.keego.shoppingcart.R
//import dev.keego.shoppingcart.databinding.FragmentGroupDetailBinding
//import dev.keego.shoppingcart.databinding.LayoutTextInputBinding
//import dev.vstd.shoppingcart.checklist.data.TodoItem
//import dev.vstd.shoppingcart.checklist.ui.barcode.BarcodeActivity
//import dev.vstd.shoppingcart.common.ui.BaseFragment
//import dev.vstd.shoppingcart.checklist.utils.dialogs.EditTextAlertDialog
//import kotlinx.coroutines.flow.collectLatest
//import kotlinx.coroutines.launch
//import java.lang.ref.WeakReference
//
//@AndroidEntryPoint
//class GroupDetailFragment : BaseFragment<FragmentGroupDetailBinding>() {
//    private var barcodeDialog: WeakReference<AlertDialog>? = null
//    private val barcodeActivityLauncher =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == BarcodeActivity.RESULT_OK) {
//                val barcode = result.data?.getStringExtra(BarcodeActivity.EXTRA_BARCODE_DESCRIPTION)
//                barcodeDialog?.run {
//                    get()?.let {
//                        it.dismiss()
//                        showCreateNewTodoDialog(editTextValue = barcode)
//                    }
//                }
//            }
//        }
//
//
//    private val vimel by viewModels<GroupDetailVimel>()
//
//    override fun onViewCreated(binding: FragmentGroupDetailBinding) {
//        val groupId = requireArguments().getInt("groupId")
//        vimel.setGroup(groupId)
//        val adapter = GroupDetailAdapter(
//            onLongClickItem = { todoItem ->
//                showModifyTodoDialog(
//                    todoItem,
//                    onSave = { vimel.updateTodoItem(it) },
//                    onMoveToTrash = { vimel.deleteTodoItem(todoItem) }
//                )
//            },
//            onCheck = {
//                vimel.toggleDoneUndone(it)
//            }
//        ).also { binding.rvTodoList.adapter = it }
//
//        binding.toolbar.setupWithNavController(findNavController())
//        binding.toolbar.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.actionNewGroup -> {
//                    showCreateNewTodoDialog()
//                    true
//                }
//
//                else -> false
//            }
//        }
//
//        observeData(adapter, binding.toolbar)
//    }
//
//    private fun showModifyTodoDialog(
//        todoItem: TodoItem,
//        onSave: (TodoItem) -> Unit,
//        onMoveToTrash: () -> Unit,
//    ) {
//        val builder = MaterialAlertDialogBuilder(requireContext())
//        val context = builder.context
//        val binding = LayoutTextInputBinding.inflate(LayoutInflater.from(context))
//
//        binding.etName.setText(todoItem.title)
//
//        builder
//            .setTitle("Update Todo")
//            .setPositiveButton("Update") { _, _ ->
//                // Create new group
//                val name = binding.etName.text.toString()
//                if (name.isNotBlank() && name != todoItem.title) {
//                    onSave(todoItem.copy(title = name))
//                } else {
//                    // Show error
//                    Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
//                }
//            }
//            .setNegativeButton("Cancel") { _, _ -> }
//            .setNeutralButton("Move to trash") { _, _ ->
//                onMoveToTrash()
//            }
//            .setNeutralButtonIcon(
//                ResourcesCompat.getDrawable(
//                    resources,
//                    R.drawable.ic_delete,
//                    null
//                )
//            )
//            .setView(binding.root)
//            .create()
//            .show()
//    }
//
//    private fun showCreateNewTodoDialog(editTextValue: String? = null) {
//        val context = requireContext()
//        val dialog = EditTextAlertDialog.create(
//            _context = context,
//            dialogTitle = context.getString(R.string.create_new_todo),
//            onCreateClicked = vimel::addTodoItem,
//            editTextValue = editTextValue,
//            onBarcodeIconClick = {
//                barcodeActivityLauncher.launch(Intent(context, BarcodeActivity::class.java))
//            }
//        )
//        barcodeDialog = WeakReference(dialog)
//        dialog.show()
//    }
//
//    private fun observeData(
//        adapter: GroupDetailAdapter,
//        toolbar: androidx.appcompat.widget.Toolbar,
//    ) {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {
//                    vimel.todos.collectLatest { todos ->
//                        adapter.submitList(todos)
//                    }
//                }
//                launch {
//                    vimel.group.collectLatest { group ->
//                        group?.let {
//                            toolbar.title = it.title
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGroupDetailBinding
//        get() = FragmentGroupDetailBinding::inflate
//}