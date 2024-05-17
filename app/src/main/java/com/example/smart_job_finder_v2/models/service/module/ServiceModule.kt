package com.example.smart_job_finder_v2.models.service.module

import com.example.smart_job_finder_v2.models.service.AccountService
import com.example.smart_job_finder_v2.models.service.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

}