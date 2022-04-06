package com.bluemoonl.ch23todoapp.di

import com.bluemoonl.ch23todoapp.data.repository.TestToDoRepository
import com.bluemoonl.ch23todoapp.data.repository.ToDoRepository
import com.bluemoonl.ch23todoapp.domain.todo.*
import com.bluemoonl.ch23todoapp.presentation.detail.DetailMode
import com.bluemoonl.ch23todoapp.presentation.detail.DetailViewModel
import com.bluemoonl.ch23todoapp.presentation.list.ListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    factory { GetToDoListUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { InsertToDoListUseCase(get()) }
    factory { InsertToDoUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }

    single<ToDoRepository> { TestToDoRepository() }

    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) -> DetailViewModel(detailMode, id, get(), get(), get(), get()) }

}