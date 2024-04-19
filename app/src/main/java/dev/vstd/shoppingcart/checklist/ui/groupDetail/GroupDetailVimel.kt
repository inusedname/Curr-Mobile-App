package dev.vstd.shoppingcart.checklist.ui.groupDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.vstd.shoppingcart.checklist.data.TodoGroup
import dev.vstd.shoppingcart.checklist.data.TodoItem
import dev.vstd.shoppingcart.checklist.data.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupDetailVimel @Inject constructor(private val repository: TodoRepository): ViewModel() {
    val group = MutableStateFlow<TodoGroup?>(null)
    val todos = MutableStateFlow(emptyList<TodoItem>())

    fun setGroup(groupId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todos.value = repository.getTodosByGroupId(groupId)
            group.value = repository.getGroupById(groupId)
        }
    }

    fun toggleDoneUndone(todo: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodoItem(todo.copy(isCompleted = !todo.isCompleted))
        }
        todos.value = todos.value.map {
            if (it.id == todo.id) {
                it.copy(isCompleted = !it.isCompleted)
            } else {
                it
            }
        }
    }

    fun deleteTodoItem(todo: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTodoItem(todo)
            todos.value = repository.getTodosByGroupId(group.value!!.id)
        }
    }

    fun updateTodoItem(todo: TodoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTodoItem(todo)
            todos.value = repository.getTodosByGroupId(group.value!!.id)
        }
    }

    fun addTodoItem(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val groupId = group.value!!.id

            repository.insertTodo(TodoItem(title = name, groupId = groupId))
            todos.value = repository.getTodosByGroupId(groupId)
        }
    }
}