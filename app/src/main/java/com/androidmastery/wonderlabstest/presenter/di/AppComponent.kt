/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.wonderlabstest.presenter.di

import com.androidmastery.wonderlabstest.network.NetworkModule
import com.androidmastery.wonderlabstest.presenter.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}