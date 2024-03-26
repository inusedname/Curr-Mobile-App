package dev.vstd.shoppingcart.ui.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.R
import dev.keego.shoppingcart.databinding.FragmentGroupsBinding
import dev.vstd.shoppingcart.ui.base.BaseFragment
import dev.vstd.shoppingcart.utils.dialogs.EditTextAlertDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GroupsFragment : BaseFragment<FragmentGroupsBinding>() {
    private val vimel by viewModels<GroupsVimel>()

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
            findNavController().navigate(
                R.id.action_groupsFragment_to_groupDetailFragment,
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
        val context = requireContext()
        EditTextAlertDialog.create(
            _context = context,
            title = context.getString(R.string.create_new_group),
            scanable = false,
            onCreateClicked = vimel::addGroup
        ).show()
    }

    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGroupsBinding
        get() = FragmentGroupsBinding::inflate
}