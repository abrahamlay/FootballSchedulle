package com.abrahamlay.footballschedule.repo

/**
 * Created by Abraham on 20/04/2018.
 */
interface APICallback{
    fun onSuccess(data: Any)
    fun onFailed(data: Any)
    fun onEmpty(data: Any)
}