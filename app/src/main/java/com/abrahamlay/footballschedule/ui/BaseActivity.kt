package com.abrahamlay.footballschedule.ui

import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.abrahamlay.footballschedule.util.MyIdlingResource


/**
 * Created by Abraham on 29/04/2018.
 */
abstract class BaseActivity: AppCompatActivity(){
    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment)}
    }

    private var idlingResource: MyIdlingResource? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIdlingResource()
    }

    @VisibleForTesting
    fun getIdlingResource(): MyIdlingResource {
        if (idlingResource == null) {
            idlingResource = MyIdlingResource(getView(), View.VISIBLE)
        }
        return idlingResource as MyIdlingResource
    }

    abstract fun getView():View?
}