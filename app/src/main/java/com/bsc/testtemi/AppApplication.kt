package com.bsc.testtemi

import android.app.Application
import com.bsc.testtemi.data.api.Repository
import com.bsc.testtemi.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(setorVMModule)
            modules(subSetorVMModulo)
            modules(produtosVMModulo)
            modules(repositoryModule)
        }
    }
}