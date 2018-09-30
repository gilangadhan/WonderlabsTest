/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.wonderlabstest.helper

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.androidmastery.wonderlabstest.model.DateResponse
import java.text.SimpleDateFormat
import java.util.*

fun sortDates(list: List<DateResponse>) {
    Collections.sort(list, object : Comparator<DateResponse> {
        @SuppressLint("SimpleDateFormat")
        var format = SimpleDateFormat("dd/MM/yyyy")

        override fun compare(p0: DateResponse?, p1: DateResponse?): Int {
            return format.parse(p1?.date).compareTo(format.parse(p0?.date))
        }
    })
}

@SuppressLint("SimpleDateFormat")
fun checkDate(lists: List<DateResponse>, string: String): List<DateResponse> {
    val list: MutableList<DateResponse> = mutableListOf();
    val format = SimpleDateFormat("dd/MM/yyyy")
    for (item: DateResponse in lists) {
        if (format.parse(item.date).before(format.parse(string))) {
            list.add(item)
        }
    }
    return list
}

fun convertCalendarToString(date: Int, month: Int, year: Int): String {
    return String.format("%s/%s/%s", date.toString(), (month + 1).toString(), year.toString())
}

fun convertCalendarToString(date: Calendar): String {
    return String.format("%s/%s/%s", date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.MONTH) + 1, date.get(Calendar.YEAR))
}

fun View.show(show: Boolean, animated: Boolean = false) {
    when (show) {
        true -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && animated) {
                alpha = 0f
                animate().alpha(1f).duration = 200
            }
            visibility = View.VISIBLE
        }
        false -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && animated) {
                alpha = 1f
                animate().alpha(0f).duration = 200
            }
            visibility = View.GONE
        }
    }
}

fun ViewGroup.show(show: Boolean, animated: Boolean = false) {
    when (show) {
        true -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && animated) {
                alpha = 0f
                animate().alpha(1f).duration = 200
            }
            visibility = View.VISIBLE
        }
        false -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && animated) {
                alpha = 1f
                animate().alpha(0f).duration = 200
            }
            visibility = View.GONE
        }
    }
}

fun EditText.clear(show: Boolean, animated: Boolean = false) {
    when (show) {
        true -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && animated) {
                alpha = 0f
                animate().alpha(1f).duration = 200
            }
            visibility = View.VISIBLE
        }
        false -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && animated) {
                alpha = 1f
                animate().alpha(0f).duration = 200
            }
            visibility = View.GONE
            setText("")
        }
    }
}