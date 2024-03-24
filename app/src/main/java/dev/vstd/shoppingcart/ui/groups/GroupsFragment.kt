package dev.vstd.shoppingcart.ui.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentGroupsBinding
import dev.keego.shoppingcart.databinding.LayoutTextInputBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GroupsFragment : BaseFragment<FragmentGroupsBinding>() {
    private val vimel by viewModels<GroupsVimel>()
    private val parentNavController =
        Navigation.findNavController(requireActivity(), R.id.container_main)

    override fun onViewCreated(binding: FragmentGroupsBinding) {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.actionNewGroup -> {
                    showCreateNewGroupDialog()
                    true
                }

                else -> {
                    false
                }
            }
        }
        val adapter = GroupsAdapter {
            parentNavController.navigate(
                R.id.action_navBarFragment_to_groupDetailFragment,
                Bundle().apply {
                    putInt("groupId", it.id)
                }
            )
        }.also { binding.rvGroups.adapter = it }

        observeData(adapter)
        vimel.fetch()
    }

    private fun observeData(adapter: GroupsAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vimel.groups.collectLatest {
                    adapter.submitList(it)
                }
            }
        }
    }


    private fun showCreateNewGroupDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val context = builder.context
        val binding = LayoutTextInputBinding.inflate(LayoutInflater.from(context))

        builder
            .setTitle("Create New Group")
            .setPositiveButton("Create") { _, _ ->
                // Create new group
                val name = binding.etName.text.toString()
                if (name.isNotBlank()) {
                    vimel.addGroup(name)
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

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGroupsBinding
        get() = FragmentGroupsBinding::inflate
}