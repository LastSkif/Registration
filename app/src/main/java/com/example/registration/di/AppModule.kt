package com.example.registration.di

import com.example.registration.data.database.UserDatabase
import com.example.registration.data.database.UserDatabaseBuilder
import com.example.registration.data.datasource.UserDataSource
import com.example.registration.data.datasource.UserDataSourceImpl
import com.example.registration.data.repository.UserRepositoryImpl
import com.example.registration.domain.repository.UserRepository
import com.example.registration.domain.usecase.GetUserNamesUseCase
import com.example.registration.domain.usecase.InsertUserUseCase
import com.example.registration.presentation.MainPageFragmentViewModel
import com.example.registration.presentation.RegistrationFragmentViewModel
import com.example.registration.presentation.UserRouter
import com.example.registration.ui.buildCicerone
import com.example.registration.ui.router.UserRouterImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { buildCicerone() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }

    single { UserDatabaseBuilder().build(androidContext()) }
    factory { get<UserDatabase>().getUserDao() }
    factory<UserDataSource> { UserDataSourceImpl(get()) }
    factory<UserRepository> {
        UserRepositoryImpl(
            userDataSource = get(),
        )
    }

    factory { GetUserNamesUseCase(get()) }
    factory { InsertUserUseCase(get()) }

    factory<UserRouter> { UserRouterImpl(get()) }
    viewModel { RegistrationFragmentViewModel(get(), get()) }
    viewModel { MainPageFragmentViewModel(get()) }
}