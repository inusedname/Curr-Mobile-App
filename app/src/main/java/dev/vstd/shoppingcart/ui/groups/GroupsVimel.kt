package dev.vstd.shoppingcart.ui.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vstd.shoppingcart.data.local.TodoGroup
import dev.vstd.shoppingcart.data.local.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsVimel @Inject constructor(private val repository: TodoRepository) : ViewModel() {
    val groups = MutableStateFlow(emptyList<TodoGroup>())

    fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            groups.value = repository.getAllGroups()
        }
    }

    fun addGroup(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertGroup(TodoGroup(title = name))
            fetch()
        }
    }
}