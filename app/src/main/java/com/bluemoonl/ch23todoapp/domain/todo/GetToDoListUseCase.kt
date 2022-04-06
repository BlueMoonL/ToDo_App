package com.bluemoonl.ch23todoapp.domain.todo

import com.bluemoonl.ch23todoapp.data.entity.ToDoEntity
import com.bluemoonl.ch23todoapp.data.repository.ToDoRepository
import com.bluemoonl.ch23todoapp.domain.UseCase

internal class GetToDoListUseCase(
    private val toDoRepository: ToDoRepository
): UseCase {

    suspend operator fun invoke(): List<ToDoEntity> {
        return toDoRepository.getToDoList()
    }

}