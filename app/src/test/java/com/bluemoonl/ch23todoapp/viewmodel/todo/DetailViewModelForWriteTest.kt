package com.bluemoonl.ch23todoapp.viewmodel.todo

import com.bluemoonl.ch23todoapp.viewmodel.ViewModelTest
import com.bluemoonl.ch23todoapp.data.entity.ToDoEntity
import com.bluemoonl.ch23todoapp.presentation.detail.DetailMode
import com.bluemoonl.ch23todoapp.presentation.detail.DetailViewModel
import com.bluemoonl.ch23todoapp.presentation.detail.ToDoDetailState
import com.bluemoonl.ch23todoapp.presentation.list.ListViewModel
import com.bluemoonl.ch23todoapp.presentation.list.ToDoListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

/**
 * [DetailViewModel]을 테스트하기 위한 Test Class
 *
 * 1. test viewModel fetch
 * 2. test insert todo
 */

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class DetailViewModelForWriteTest : ViewModelTest() {

    private val detailViewModel: DetailViewModel by inject { parametersOf(DetailMode.WRITE, id) }
    private val listViewModel: ListViewModel by inject()

    val id = 0L

    private val todo = ToDoEntity(
        id,
        title = "title 1",
        description = "description 1",
        hasCompleted = false
    )

    @Test
    fun `test viewModel fetch`() = runBlockingTest {
        val testObservable = detailViewModel.toDoDetailLiveData.test()

        detailViewModel.fetchData()

        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Write
            )
        )
    }

    @Test
    fun `test insert todo`() = runBlockingTest {
        val detailTestObservable = detailViewModel.toDoDetailLiveData.test()
        val listTestObservable = listViewModel.toDoListLiveData.test()

        detailViewModel.writeToDo(
            title = todo.title,
            description = todo.description
        )

        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(todo)
            )
        )

        assert(detailViewModel.detailMode == DetailMode.DETAIL)
        assert(detailViewModel.id == id)

        listViewModel.fetchData()
        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf(todo))
            )
        )
    }

}