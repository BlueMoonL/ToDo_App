package com.bluemoonl.ch23todoapp.data.repository

import com.bluemoonl.ch23todoapp.data.entity.ToDoEntity

/**
 * 1. insertToDoList
 * 2. getToDoList
 * 3. updateToDoitem
 */

interface ToDoRepository {

    suspend fun getToDoList(): List<ToDoEntity>

    suspend fun getToDoItem(id: Long): ToDoEntity?

    suspend fun insertToDoItem(toDoEntity: ToDoEntity): Long

    suspend fun insertToDoList(toDoList: ToDoEntity)

    suspend fun updateToDoItem(toDoEntity: ToDoEntity)

    suspend fun deleteToDoItem(id: Long)

    suspend fun deleteAll()

}