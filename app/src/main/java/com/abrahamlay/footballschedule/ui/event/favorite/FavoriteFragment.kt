package com.abrahamlay.footballschedule.ui.event.favorite

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.abrahamlay.footballschedule.R
import com.abrahamlay.footballschedule.db.Favorite
import com.abrahamlay.footballschedule.ui.ItemClickListener
import com.abrahamlay.footballschedule.ui.detail.DetailActivity
import com.abrahamlay.footballschedule.ui.event.EventContract
import com.abrahamlay.footballschedule.ui.event.EventFragment
import com.abrahamlay.footballschedule.ui.event.last.FavoriteAdapter
import com.abrahamlay.footballschedule.ui.event.last.FavoritePresenter
import kotlinx.android.synthetic.main.fragment_event.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : EventFragment(), EventContract.FavoriteView, ItemClickListener {

    private val presenter: FavoritePresenter = FavoritePresenter(this)

    companion object {
        fun newInstance(): FavoriteFragment {
            val args: Bundle = Bundle()
//            args.putSerializable(ARG_CAUGHT, caught)
            val fragment = FavoriteFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.showFavorite(context)

    }

    override fun onFavoriteLoaded(itemList: List<Favorite>) {
        rv_list.adapter = FavoriteAdapter(context, itemList, this)
        val orientation = LinearLayoutManager.VERTICAL
        rv_list.layoutManager = LinearLayoutManager(context, orientation, false)
    }

    override fun onItemClicked(item: Any, position: Int) {
        val favorite: Favorite =item as Favorite
        toast(favorite.homeTeamName.toString())
        val idEvent=favorite.id.toString()
        startActivity<DetailActivity>("idEvent" to idEvent)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            android.R.id.home -> {
                activity?.finish()
                return true
            }
            R.id.action_refresh -> {
                presenter.showFavorite(context)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}// Required empty public constructor