package com.bluemoonl.ch23todoapp.domain.todo

import com.bluemoonl.ch23todoapp.data.entity.ToDoEntity
import com.bluemoonl.ch23todoapp.data.repository.ToDoRepository
import com.bluemoonl.ch23todoapp.domain.UseCase

internal class InsertToDoListUseCase(
    private val toDoRepository: ToDoRepository
): UseCase {

    suspend operator fun invoke(toDoList: ToDoEntity) {
        return toDoRepository.insertToDoList(toDoList)
    }

}