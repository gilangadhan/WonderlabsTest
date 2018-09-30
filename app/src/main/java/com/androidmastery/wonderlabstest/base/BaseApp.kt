/*
 * Copyright (c) 2018.
 * Gilang Ramadhan (gilang@imastudio.co.id)
 */

package com.androidmastery.wonderlabstest.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.androidmastery.wonderlabstest.network.NetworkModule
import com.androidmastery.wonderlabstest.presenter.di.AppComponent
import com.androidmastery.wonderlabstest.presenter.di.DaggerAppComponent
import java.io.File

@SuppressLint("Registered")
open class BaseApp : AppCompatActivity() {


    lateinit var moduleApp: AppComponent
        internal set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val cacheFile = File(cacheDir, "responses")
        moduleApp = DaggerAppComponent.builder().networkModule(NetworkModule(cacheFile)).build()
        context = applicationContext
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        @get:Synchronized
        var context: Context? = null
            private set
    }

}