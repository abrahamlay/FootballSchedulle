package com.abrahamlay.footballschedule.ui.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.*
import com.abrahamlay.footballschedule.R
import com.abrahamlay.footballschedule.model.DetailEvent
import com.abrahamlay.footballschedule.model.Teams
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.view_loading.*
import org.jetbrains.anko.design.snackbar
import com.abrahamlay.footballschedule.db.database
import com.abrahamlay.footballschedule.db.Favorite
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment(), DetailContract.DetailView {


    private val presenter: DetailPresenter = DetailPresenter(this)

    private var  badgeHome:String?=""
    private var badgeAway:String?=""
    private var idHome: String?=""
    private var idAway: String?=""
    private var idEvent: String?=""
    private var isFavorite: Boolean=false
    private var menuInflater: Menu?=null


    companion object {
        fun newInstance(idEvent: String, isLocal:Boolean): DetailFragment {
            val args = Bundle()
            args.putString("idEvent", idEvent )
            args.putBoolean("isLocal",isLocal)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        idEvent=arguments?.getString("idEvent")

//        val isLocal=arguments?.getBoolean("isLocal")
//
//        isLocal?.let {
//            if(isLocal){
//                idEvent?.let {
//                    favoriteState(idEvent?.toLong()?:0)
//                    presenter.loadDetail(idEvent?.toInt())
//                    setFavorite()
//                }
//            }else{
//                idEvent=arguments?.getString("idEvent")
//                idEvent?.let {
//                    favoriteState(idEvent?.toLong()?:0)
//                    presenter.loadLocalDetail(idEvent?.toLong(),context)
//                }
//            }
//        }

        favoriteState(idEvent?.toLong()?:0)
        presenter.loadDetail(idEvent?.toInt())
        if (isFavorite) {presenter.loadLocalDetail(idEvent?.toLong()?:0,context)}
        setHasOptionsMenu(true)
    }

    override fun onLocalDetailLoaded(favorite: Favorite) {
        tv_home_team?.text=favorite.homeTeamName
        tv_away_team?.text=favorite.awayTeamName

        Glide.with(this).load(favorite.homeTeamBadge).into(iv_home_team)
        Glide.with(this).load(favorite.awayTeamBadge).into(iv_away_team)

    }

    override fun onDetailLoaded(data: DetailEvent) {
        tv_home_team?.text=data.events.get(0).strHomeTeam
        tv_away_team?.text=data.events.get(0).strAwayTeam

        tv_score_home_team?.text=data.events.get(0).intHomeScore
        tv_score_away_team?.text=data.events.get(0).intAwayScore

        tv_goal_home_team?.text=data.events.get(0).strHomeGoalDetails?.replace(";","\n")
        tv_goal_away_team?.text=data.events.get(0).strAwayGoalDetails?.replace(";","\n")

        tv_yellow_card_home_team?.text=data.events.get(0).strHomeYellowCards?.replace(";","\n")
        tv_yellow_card_away_team?.text=data.events.get(0).strAwayYellowCards?.replace(";","\n")

        tv_red_card_home_team?.text=data.events.get(0).strHomeRedCards?.replace(";","\n")
        tv_red_card_away_team?.text=data.events.get(0).strAwayRedCards?.replace(";","\n")

        tv_shots_home_team?.text=data.events.get(0).intHomeShots
        tv_shots_away_team?.text=data.events.get(0).intAwayShots

        tv_formation_home_team?.text=data.events.get(0).strHomeFormation
        tv_formation_away_team?.text=data.events.get(0).strAwayFormation

        tv_goalkeeper_home_team?.text=data.events.get(0).strHomeLineupGoalkeeper?.replace(";","\n")
        tv_goalkeeper_away_team?.text=data.events.get(0).strAwayLineupGoalkeeper?.replace(";","\n")

        tv_defender_home_team?.text=data.events.get(0).strHomeLineupDefense?.replace(";","\n")
        tv_defender_away_team?.text=data.events.get(0).strAwayLineupDefense?.replace(";","\n")

        tv_midfielder_home_team?.text=data.events.get(0).strHomeLineupMidfield?.replace(";","\n")
        tv_midfielder_away_team?.text=data.events.get(0).strAwayLineupMidfield?.replace(";","\n")

        tv_forward_home_team?.text=data.events.get(0).strHomeLineupForward?.replace(";","\n")
        tv_forward_away_team?.text=data.events.get(0).strAwayLineupForward?.replace(";","\n")

        tv_sum_spectator?.text=data.events.get(0).intSpectators
        tv_date?.text=data.events.get(0).dateEvent


        idHome= data.events[0].idHomeTeam
        idAway= data.events[0].idAwayTeam
        presenter.lookupTeam(idHome!!.toInt())
        presenter.lookupTeam(idAway!!.toInt())
    }

    override fun onTeamLoaded(teams: Teams) {
        if(activity!=null){
            if(teams.teams[0]?.idTeam==idHome){
                Glide.with(this).load(teams.teams[0]?.strTeamBadge).into(iv_home_team)
                badgeHome= teams.teams[0]?.strTeamBadge
            }else if(teams.teams[0]?.idTeam==idAway){
                Glide.with(this).load(teams.teams[0]?.strTeamBadge).into(iv_away_team)
                badgeAway= teams.teams[0]?.strTeamBadge
            }
        }
    }


    override fun showLoading(active: Boolean) {
        when(active){
            true-> {
            pb_loading?.visibility= View.VISIBLE}
            false->{
            pb_loading?.visibility= View.GONE}
        }
    }

    override fun showEmpty(message: String) {
        tv_message?.visibility= View.VISIBLE
        tv_message?.text=message
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_item_detail,menu)
        menuInflater=menu
        setFavorite()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            android.R.id.home -> {
                activity?.finish()
            }
            R.id.action_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun favoriteState(idEvent: Long){
        context?.database?.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(ID_EVENT = {id})",
                            "id" to idEvent)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite(){
        try {
            context?.database?.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.ID_EVENT to idEvent,
                        Favorite.HOME_TEAM_ID to idHome,
                        Favorite.HOME_TEAM_NAME to tv_home_team?.text,
                        Favorite.HOME_TEAM_BADGE to badgeHome,
                        Favorite.AWAY_TEAM_ID to idAway,
                        Favorite.AWAY_TEAM_NAME to tv_away_team?.text,
                        Favorite.AWAY_TEAM_BADGE to badgeAway
                        )
            }
            snackbar(view!!,"Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(view!!, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            context?.database?.use {
                idEvent?.let {
                    delete(Favorite.TABLE_FAVORITE, "(ID_EVENT = {id})",
                        "id" to idEvent!!.toLong())
                }

            }
            snackbar(view!!, "Removed from favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(view!!, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuInflater?.getItem(0)?.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_menu_favorite_fill_white)
        else
            menuInflater?.getItem(0)?.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_menu_favorite_blank_white)
    }
}// Required empty public constructor