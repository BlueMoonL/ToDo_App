package com.bluemoonl.ch23todoapp.di

import android.content.Context
import androidx.room.Room
import com.bluemoonl.ch23todoapp.data.local.db.ToDoDatabase
import com.bluemoonl.ch23todoapp.data.repository.DefaultToDoRepository
import com.bluemoonl.ch23todoapp.data.repository.ToDoRepository
import com.bluemoonl.ch23todoapp.domain.todo.DeleteAllToDoItemUseCase
import com.bluemoonl.ch23todoapp.domain.todo.DeleteToDoItemUseCase
import com.bluemoonl.ch23todoapp.domain.todo.GetToDoItemUseCase
import com.bluemoonl.ch23todoapp.domain.todo.GetToDoListUseCase
import com.bluemoonl.ch23todoapp.domain.todo.InsertToDoUseCase
import com.bluemoonl.ch23todoapp.domain.todo.UpdateToDoUseCase
import com.bluemoonl.ch23todoapp.presentation.detail.DetailMode
import com.bluemoonl.ch23todoapp.presentation.detail.DetailViewModel
import com.bluemoonl.ch23todoapp.presentation.list.ListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
internal val appModule = module {

    single { Dispatchers.Main }
    single { Dispatchers.IO }

    factory { GetToDoListUseCase(get()) }
    factory { GetToDoItemUseCase(get()) }
    factory { InsertToDoUseCase(get()) }
    factory { InsertToDoUseCase(get()) }
    factory { DeleteToDoItemUseCase(get()) }
    factory { DeleteAllToDoItemUseCase(get()) }
    factory { UpdateToDoUseCase(get()) }

    single<ToDoRepository> { DefaultToDoRepository(get(), get()) }

    single { provideDB(androidApplication()) }
    single { provideToDoDao(get()) }

    viewModel { ListViewModel(get(), get(), get()) }
    viewModel { (detailMode: DetailMode, id: Long) -> DetailViewModel(detailMode, id, get(), get(), get(), get()) }

}

internal fun provideDB(context: Context): ToDoDatabase =
    Room.databaseBuilder(context, ToDoDatabase::class.java, ToDoDatabase.DB_NAME).build()

internal fun provideToDoDao(database: ToDoDatabase) = database.toDoDao()