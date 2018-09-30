/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.wonderlabstest.presenter.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.androidmastery.wonderlabstest.network.Services

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val service: Services) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(service) as T
    }
}