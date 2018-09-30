/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.wonderlabstest.network

import com.androidmastery.wonderlabstest.helper.checkDate
import com.androidmastery.wonderlabstest.helper.sortDates
import com.androidmastery.wonderlabstest.model.DateResponse
import rx.Single
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class Services internal constructor(private val networkService: NetworkService) {

    fun getDate(callback: GetDateCallback, date: String): Subscription {
        return networkService.getDate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext { throwable -> Single.error(throwable) }
                .doOnSubscribe { callback.onProgress() }
                .map {
                    sortDates(it)
                    checkDate(it, date) }
                .subscribe({
                    callback.onSuccess(it)
                }, {
                    callback.onError(it.message.toString())
                })
    }

    interface GetDateCallback {
        fun onSuccess(success: List<DateResponse>)
        fun onError(error: String)
        fun onProgress()
    }
}