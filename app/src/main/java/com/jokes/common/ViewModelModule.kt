package com.jokes.common

import com.jokes.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


object ViewModelModule : BaseViewModelModuleProvider {

    override fun loadModules() =
        lazyLoadModule

    private val lazyLoadModule by lazy {
        loadKoinModules(homeViewModel)
    }




    private val homeViewModel = module {
        viewModel {
            HomeViewModel(
                jokesDataSource = get()
            )
        }
    }




}