package com.bluemoonl.ch23todoapp.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bluemoonl.ch23todoapp.data.entity.ToDoEntity
import com.bluemoonl.ch23todoapp.domain.todo.DeleteAllToDoItemUseCase
import com.bluemoonl.ch23todoapp.domain.todo.GetToDoListUseCase
import com.bluemoonl.ch23todoapp.domain.todo.UpdateToDoUseCase
import com.bluemoonl.ch23todoapp.presentation.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * 필요한 seCase
 * 1. [GetToDoListUseCase]
 * 2. [UpdateToDoUseCase]
 * 3. [DeleteAllToDoItemUseCase]
 */

internal class ListViewModel(
    private val getToDoListUseCase: GetToDoListUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val deleteAllToDoItemUseCase: DeleteAllToDoItemUseCase
): BaseViewModel() {

    private var _toDoListLiveData = MutableLiveData<ToDoListState>(ToDoListState.UnInitialized)
    val toDoListLiveData: LiveData<ToDoListState> = _toDoListLiveData

    override fun fetchData(): Job = viewModelScope.launch {
        _toDoListLiveData.postValue(ToDoListState.Loading)
        _toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
    }

    fun updateEntity(toDoEntity: ToDoEntity) = viewModelScope.launch {
        updateToDoUseCase(toDoEntity)
    }

    fun deleteAll() = viewModelScope.launch {
        _toDoListLiveData.postValue(ToDoListState.Loading)
        deleteAllToDoItemUseCase()
        _toDoListLiveData.postValue(ToDoListState.Success(getToDoListUseCase()))
    }

}