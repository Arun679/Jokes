package com.jokes.common


import com.jokes.repositories.JokesDataSource
import org.koin.core.module.Module
import org.koin.dsl.module


object DataSourceModules : BaseModuleProvider {

    override val modules: List<Module>
        get() = listOf(

           jokesDataSource

        )
    private val jokesDataSource = module {
        single { JokesDataSource(get()) }
    }

}
