package com.abrahamlay.footballschedule.ui.main

import android.os.Bundle
import android.view.*
import com.abrahamlay.footballschedule.R
import com.abrahamlay.footballschedule.ui.BaseActivity
import com.abrahamlay.footballschedule.ui.event.favorite.FavoriteFragment
import com.abrahamlay.footballschedule.ui.event.last.LastEventFragment
import com.abrahamlay.footballschedule.ui.event.next.NextEventFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_event.*
import org.jetbrains.anko.contentView

class MainActivity : BaseActivity(){
    override fun getView():View? {
        return findViewById(R.id.rv_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun initNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            item -> when(item.itemId){
                R.id.action_past->{
                    replaceFragment(LastEventFragment.newInstance(),R.id.container)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_next->{
                    replaceFragment(NextEventFragment.newInstance(),R.id.container)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_menu_favorite->{
                    replaceFragment(FavoriteFragment.newInstance(),R.id.container)
                    return@setOnNavigationItemSelectedListener true
                }
            else->return@setOnNavigationItemSelectedListener true
            }
        }
        bottom_navigation.selectedItemId=R.id.action_past
    }

}
