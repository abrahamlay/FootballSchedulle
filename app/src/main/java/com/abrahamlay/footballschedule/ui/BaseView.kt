package com.abrahamlay.footballschedule.ui

/**
 * Created by Abraham on 21/04/2018.
 */
interface BaseView{
    fun showLoading(active: Boolean)
    fun showEmpty(message:String)
}