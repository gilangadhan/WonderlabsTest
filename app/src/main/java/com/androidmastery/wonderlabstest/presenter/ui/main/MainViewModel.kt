/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.wonderlabstest.presenter.ui.main

import android.arch.lifecycle.MutableLiveData
import com.androidmastery.wonderlabstest.base.BaseViewModel
import com.androidmastery.wonderlabstest.model.DateResponse
import com.androidmastery.wonderlabstest.network.Services

interface MainContract {
    fun getMainState(): MutableLiveData<MainState>
    fun getDate(date: String)
}

class MainViewModel(private val service: Services) : BaseViewModel(), MainContract {
    val state: MutableLiveData<MainState> = MutableLiveData()
    override fun getMainState(): MutableLiveData<MainState> = state

    override fun getDate(date: String) {
        if (date.isEmpty()){
            state.value = MainState.EmptyState
        }else {
            val subscription = service.getDate(object : Services.GetDateCallback {
                override fun onProgress() {
                    state.value = MainState.ShowLoading
                }

                override fun onSuccess(success: List<DateResponse>) {
                    state.value = MainState.DismissLoading
                    state.value = MainState.GetDateSuccess(success)
                }

                override fun onError(error: String) {
                    state.value = MainState.DismissLoading
                    state.value = MainState.DateFailed(error)
                }
            }, date)
            addDisposable(subscription)
        }
    }
}

sealed class MainState {
    object ShowLoading : MainState()
    object EmptyState : MainState()
    object DismissLoading : MainState()
    data class GetDateSuccess(val response: List<DateResponse>) : MainState()
    data class DateFailed(val error: String) : MainState()
}