package com.bluemoonl.ch23todoapp.domain.todo

import com.bluemoonl.ch23todoapp.data.entity.ToDoEntity
import com.bluemoonl.ch23todoapp.data.repository.ToDoRepository
import com.bluemoonl.ch23todoapp.domain.UseCase

internal class InsertToDoUseCase(
    private val toDoRepository: ToDoRepository
): UseCase {

    suspend operator fun invoke(toDoEntity: ToDoEntity): Long {
        return toDoRepository.insertToDoItem(toDoEntity)
    }

}