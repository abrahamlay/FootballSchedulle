package com.abrahamlay.footballschedule.ui.event.last

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.abrahamlay.footballschedule.R
import com.abrahamlay.footballschedule.model.LastEvent
import com.abrahamlay.footballschedule.model.LastEventsItem
import com.abrahamlay.footballschedule.ui.ItemClickListener
import com.abrahamlay.footballschedule.ui.detail.DetailActivity
import com.abrahamlay.footballschedule.ui.event.EventContract
import com.abrahamlay.footballschedule.ui.event.EventFragment
import kotlinx.android.synthetic.main.fragment_event.*

/**
 * A simple [Fragment] subclass.
 */
class LastEventFragment : EventFragment(), EventContract.LastEventView, ItemClickListener {

    private val presenter: LastEventPresenter = LastEventPresenter(this)

    companion object {
        fun newInstance(): LastEventFragment {
            val args= Bundle()
            val fragment = LastEventFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.loadLastResult(4328)
    }

    override fun onLastResultLoaded(lastEvent: LastEvent) {
        val itemList= lastEvent.events
        rv_list?.adapter = LastEventAdapter(context, itemList, this)
        val orientation = LinearLayoutManager.VERTICAL
        rv_list?.layoutManager = LinearLayoutManager(context, orientation, false)
    }
    override fun onItemClicked(item: Any, position: Int) {
        val lastEventsItem:LastEventsItem =item as LastEventsItem
//        toast(lastEventsItem.strHomeTeam.toString())
//        startActivity<DetailActivity>("idEvent" to {lastEventsItem.idEvent})
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("idEvent", lastEventsItem.idEvent)
        startActivity(intent)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            android.R.id.home -> {
                activity?.finish()
                return true
            }
            R.id.action_refresh -> {
                presenter.loadLastResult(4328)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}// Required empty public constructor