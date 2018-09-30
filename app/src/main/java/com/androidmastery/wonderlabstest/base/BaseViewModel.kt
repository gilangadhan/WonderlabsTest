/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.wonderlabstest.base

import android.arch.lifecycle.ViewModel
import rx.Subscription
import rx.subscriptions.CompositeSubscription

open class BaseViewModel : ViewModel() {

    private val disposable by lazy {
        CompositeSubscription()
    }

    private fun dispose() {
        disposable.clear()
    }

    protected fun addDisposable(subscription: Subscription) {
        disposable.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }

}