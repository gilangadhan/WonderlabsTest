/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.wonderlabstest.presenter.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.androidmastery.wonderlabstest.R
import com.androidmastery.wonderlabstest.model.DateResponse

class MainAdapter(private val dates: List<DateResponse>) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_date, parent, false))
    }

    override fun getItemCount(): Int = dates.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindItem(dates[position])
    }
}

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val date: TextView = view.findViewById(R.id.date)
    fun bindItem(dates: DateResponse) {
        date.text = dates.date
    }
}