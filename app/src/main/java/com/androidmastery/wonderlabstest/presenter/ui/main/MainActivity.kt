/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.wonderlabstest.presenter.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.androidmastery.wonderlabstest.R
import com.androidmastery.wonderlabstest.base.BaseApp
import com.androidmastery.wonderlabstest.helper.convertCalendarToString
import com.androidmastery.wonderlabstest.helper.show
import com.androidmastery.wonderlabstest.network.Services
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


@Suppress("DEPRECATION")
class MainActivity : BaseApp() {
    @Inject
    lateinit var service: Services
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        moduleApp.inject(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProviders.of(this, MainViewModelFactory(service)).get(MainViewModel::class.java)
        viewModel.getMainState().observe(this, Observer { state ->
            when (state) {
                is MainState.EmptyState -> edtInput.error = getString(R.string.empty_input)
                is MainState.GetDateSuccess -> {
                    emptyLayout.show(false)
                    recyclerView.show(true)
                    adapter = MainAdapter(state.response)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
                is MainState.ShowLoading -> progressBar.show(true)
                is MainState.DismissLoading -> progressBar.show(false)
                is MainState.EmptyList -> {
                    emptyLayout.show(true)
                    recyclerView.show(false)
                }
            }
        })
        if (savedInstanceState == null) {
            viewModel.getDate(convertCalendarToString(Calendar.getInstance()))
        }
        edtInput.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DatePickerDialog.newInstance(
                    null,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            )
            now.timeInMillis = System.currentTimeMillis() - 1000
            dpd.show(fragmentManager, getString(R.string.datepickerdialog))

            dpd.setOnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                edtInput.setText(convertCalendarToString(date = dayOfMonth, month = monthOfYear, year = year))
            }
        }
        btnSubmit.setOnClickListener {
            viewModel.getDate(edtInput.text.toString())
        }
    }
}
