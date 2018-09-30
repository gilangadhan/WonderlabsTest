/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.wonderlabstest.model

import com.google.gson.annotations.SerializedName

data class DateResponse(
        @SerializedName("date") var date: String = ""
)