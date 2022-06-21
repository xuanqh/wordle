package com.restaff.wordle.di

import com.restaff.wordle.domain.MainRepository
import com.restaff.wordle.data.repositoryimpl.MainRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<MainRepository> { MainRepositoryImpl(get()) }
}