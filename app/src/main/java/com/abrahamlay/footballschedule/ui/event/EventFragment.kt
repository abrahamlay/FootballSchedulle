package com.abrahamlay.footballschedule.ui.event


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.abrahamlay.footballschedule.R
import com.abrahamlay.footballschedule.ui.BaseView
import kotlinx.android.synthetic.main.view_loading.*


/**
 * A simple [Fragment] subclass.
 */
open class EventFragment : Fragment(),BaseView {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun showLoading(active: Boolean) {
        when(active){
            true->pb_loading?.visibility=View.VISIBLE
            false->pb_loading?.visibility=View.GONE
        }
    }

    override fun showEmpty(message: String) {
        tv_message?.visibility=View.VISIBLE
        tv_message?.text=message
    }

}// Required empty public constructor
