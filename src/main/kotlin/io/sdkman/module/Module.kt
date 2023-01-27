package io.sdkman.di

import io.sdkman.service.TwitterService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val module = module {
    singleOf(::TwitterService)
}
