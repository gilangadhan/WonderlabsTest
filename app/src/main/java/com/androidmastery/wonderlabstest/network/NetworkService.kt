/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.wonderlabstest.network

import com.androidmastery.wonderlabstest.model.DateResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single

interface NetworkService {
    @GET("dates")
    fun getDate(): Single<List<DateResponse>>
}
