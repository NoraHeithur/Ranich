package com.example.ranich.di

import com.example.ranich.domain.repository.AlertRepository
import com.example.ranich.domain.repository.AlertRepositoryImpl
import com.example.ranich.domain.repository.ContactRepository
import com.example.ranich.domain.repository.ContactRepositoryImpl
import com.example.ranich.domain.repository.LogInRepository
import com.example.ranich.domain.repository.LogInRepositoryImpl
import com.example.ranich.domain.repository.UserRepository
import com.example.ranich.domain.repository.UserRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AlertRepositoryImpl) { bind<AlertRepository>() }
    singleOf(::LogInRepositoryImpl) { bind<LogInRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::ContactRepositoryImpl) { bind<ContactRepository>() }
}