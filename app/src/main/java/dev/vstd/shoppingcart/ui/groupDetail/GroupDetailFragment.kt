package dev.vstd.shoppingcart.ui.groupDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.musicplayer.R
import dev.keego.musicplayer.databinding.FragmentGroupDetailBinding
import dev.keego.musicplayer.databinding.LayoutTextInputBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GroupDetailFragment : BaseFragment<FragmentGroupDetailBinding>() {

    private val vimel by viewModels<GroupDetailVimel>()

    override fun onViewCreated(binding: FragmentGroupDetailBinding) {
        val groupId = requireArguments().getInt("groupId")
        vimel.setGroup(groupId)
        val adapter = GroupDetailAdapter {
            vimel.toggleDoneUndone(it)
        }.also { binding.rvTodoList.adapter = it }

        binding.toolbar.setupWithNavController(findNavController())
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.actionNewGroup -> {
                    showCreateNewGroupDialog()
                    true
                }

                else -> false
            }
        }

        observeData(adapter, binding.toolbar)
    }

    private fun showCreateNewGroupDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val context = builder.context
        val binding = LayoutTextInputBinding.inflate(LayoutInflater.from(context))

        builder
            .setTitle("Create New Todo")
            .setPositiveButton("Create") { _, _ ->
                // Create new group
                val name = binding.etName.text.toString()
                if (name.isNotBlank()) {
                    vimel.addTodoItem(name)
                } else {
                    // Show error
                    Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .setView(binding.root)
            .create()
            .show()
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