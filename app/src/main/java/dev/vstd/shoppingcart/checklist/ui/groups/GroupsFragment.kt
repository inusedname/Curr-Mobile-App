package dev.vstd.shoppingcart.checklist.ui.groups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dev.keego.shoppingcart.databinding.FragmentGroupsBinding
import dev.vstd.shoppingcart.checklist.ui.addItem.AddItemActivity
import dev.vstd.shoppingcart.common.ui.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GroupsFragment : BaseFragment<FragmentGroupsBinding>() {
    private val vimel by viewModels<GroupsVimel>()

    override fun onViewCreated(binding: FragmentGroupsBinding) {
        binding.fabAddNewTodo.setOnClickListener {
            AddItemActivity.start(requireContext())
        }
        val adapter = GroupsAdapter {
            vimel.toggleDoneUndone(it)
        }.also { binding.rvGroups.adapter = it }

        observeData(adapter)
    }

    override fun onStart() {
        super.onStart()
        vimel.fetch()
    }

    private fun observeData(adapter: GroupsAdapter) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vimel.groupsWithTodos.collectLatest {
                    adapter.submitListt(it)
                }
            }
        }
    }
    override val viewCreator: (LayoutInflater, ViewGroup?, Boolean) -> FragmentGroupsBinding
        get() = FragmentGroupsBinding::inflate
}