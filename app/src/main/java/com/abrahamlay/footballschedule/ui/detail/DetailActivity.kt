package com.abrahamlay.footballschedule.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.abrahamlay.footballschedule.R
import com.abrahamlay.footballschedule.ui.BaseActivity
import org.jetbrains.anko.contentView

class DetailActivity : BaseActivity() {
    override fun getView(): View? {
        return findViewById(android.support.design.R.id.snackbar_text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val idEvent=intent.getStringExtra("idEvent")

        idEvent?.let {
            replaceFragment(DetailFragment.newInstance(idEvent,true),R.id.container)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }


}
