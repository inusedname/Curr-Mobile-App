package dev.vstd.shoppingcart.data.local

class TodoRepository(private val todoGroupDao: TodoGroupDao, private val todoDao: TodoItemDao) {
    suspend fun getAllGroups() = todoGroupDao.getAll()
    suspend fun insertGroup(todoGroup: TodoGroup) = todoGroupDao.insert(todoGroup)
    suspend fun getAllTodos() = todoDao.getAll()
    suspend fun insertTodo(todoItem: TodoItem) = todoDao.insert(todoItem)
    suspend fun getTodosByGroupId(groupId: Int) = todoDao.getByGroupId(groupId)
    suspend fun getGroupById(groupId: Int) = todoGroupDao.getById(groupId)
    suspend fun updateTodoItem(todoItem: TodoItem) = todoDao.update(todoItem)
}