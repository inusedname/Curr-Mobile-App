package dev.vstd.shoppingcart.ui.groupDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentGroupDetailBinding
import dev.keego.shoppingcart.databinding.LayoutTextInputBinding
import dev.vstd.shoppingcart.data.local.TodoItem
import dev.vstd.shoppingcart.ui.base.BaseFragment
import dev.vstd.shoppingcart.utils.dialogs.EditTextAlertDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GroupDetailFragment : BaseFragment<FragmentGroupDetailBinding>() {

    private val vimel by viewModels<GroupDetailVimel>()

    override fun onViewCreated(binding: FragmentGroupDetailBinding) {
        val groupId = requireArguments().getInt("groupId")
        vimel.setGroup(groupId)
        val adapter = GroupDetailAdapter(
            onLongClickItem = { todoItem ->
                showModifyTodoDialog(
                    todoItem,
                    onSave = { vimel.updateTodoItem(it) },
                    onMoveToTrash = { vimel.deleteTodoItem(todoItem) }
                )
            },
            onCheck = {
                vimel.toggleDoneUndone(it)
            }
        ).also { binding.rvTodoList.adapter = it }

        binding.toolbar.setupWithNavController(findNavController())
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.actionNewGroup -> {
                    showCreateNewTodoDialog()
                    true
                }

                else -> false
            }
        }

        observeData(adapter, binding.toolbar)
    }

    private fun showModifyTodoDialog(
        todoItem: TodoItem,
        onSave: (TodoItem) -> Unit,
        onMoveToTrash: () -> Unit,
    ) {
        val builder = AlertDialog.Builder(requireContext())
        val context = builder.context
        val binding = LayoutTextInputBinding.inflate(LayoutInflater.from(context))

        binding.etName.setText(todoItem.title)

        builder
            .setTitle("Update Todo")
            .setPositiveButton("Update") { _, _ ->
                // Create new group
                val name = binding.etName.text.toString()
                if (name.isNotBlank() && name != todoItem.title) {
                    onSave(todoItem.copy(title = name))
                } else {
                    // Show error
                    Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .setNeutralButton("Move to trash") { _, _ ->
                onMoveToTrash()
            }
            .setNeutralButtonIcon(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_delete,
                    null
                )
            )
            .setView(binding.root)
            .create()
            .show()
    }

    private fun showCreateNewTodoDialog() {
        val context = requireContext()
        EditTextAlertDialog.create(
            _context = context,
            title = context.getString(R.string.create_new_todo),
            onCreateClicked = vimel::addTodoItem
        ).show()
    }

    private fun observeData(
        adapter: GroupDetailAdapter,
        toolbar: androidx.appcompat.widget.Toolbar,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    vimel.todos.collectLatest { todos ->
                        adapter.submitList(todos)
                    }
                }
                launch {
                    vimel.group.collectLatest { group ->
                        group?.let {
                            toolbar.title = it.title
                        }
                    }
                }
            }
        }
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGroupDetailBinding
        get() = FragmentGroupDetailBinding::inflate
}