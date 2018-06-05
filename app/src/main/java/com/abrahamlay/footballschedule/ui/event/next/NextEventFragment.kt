package com.abrahamlay.footballschedule.ui.event.next

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.abrahamlay.footballschedule.R
import com.abrahamlay.footballschedule.model.NextEvent
import com.abrahamlay.footballschedule.model.NextEventItem
import com.abrahamlay.footballschedule.ui.ItemClickListener
import com.abrahamlay.footballschedule.ui.detail.DetailActivity
import com.abrahamlay.footballschedule.ui.event.EventContract
import com.abrahamlay.footballschedule.ui.event.EventFragment
import kotlinx.android.synthetic.main.fragment_event.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class NextEventFragment : EventFragment(),EventContract.NextEventView, ItemClickListener {

    private val presenter: NextEventPresenter = NextEventPresenter(this)

    companion object {
        fun newInstance(): NextEventFragment {
            val args: Bundle = Bundle()
//            args.putSerializable(ARG_CAUGHT, caught)
            val fragment = NextEventFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.loadNextFixture(4328)
    }

    override fun onNextFixtureLoaded(nextEvent: NextEvent) {
        val itemList= nextEvent.events
        rv_list.adapter = NextEventAdapter(context, itemList, this)
        val orientation = LinearLayoutManager.VERTICAL
        rv_list.layoutManager = LinearLayoutManager(context, orientation, false)
    }
    override fun onItemClicked(item: Any, position: Int) {
        val nextEventItem: NextEventItem =item as NextEventItem
        toast(nextEventItem.strHomeTeam.toString())
        startActivity<DetailActivity>("idEvent" to {nextEventItem.idEvent})
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            android.R.id.home -> {
                activity?.finish()
            }
            R.id.action_refresh -> {
                presenter.loadNextFixture(4328)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}// Required empty public constructor