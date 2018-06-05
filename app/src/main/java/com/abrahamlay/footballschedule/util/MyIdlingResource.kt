package com.abrahamlay.footballschedule.util

import android.support.test.espresso.IdlingResource
import android.view.View


class MyIdlingResource(var view: View?, expectedVisibility:Int) :IdlingResource{
    var mView = view
    val mExpectedVisibility = expectedVisibility
    var mIdle = false
    var mResourceCallback:IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return javaClass.name
    }

    override fun isIdleNow(): Boolean {
        mIdle = mIdle || mView?.visibility == mExpectedVisibility

        if (mIdle) {
            if (mResourceCallback != null) {
                mResourceCallback!!.onTransitionToIdle()
            }
        }

        return mIdle
    }


    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        mResourceCallback=callback
    }
}