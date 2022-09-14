package com.jokes.common

import org.koin.core.module.Module



interface BaseModuleProvider {

    val modules: List<Module>

}