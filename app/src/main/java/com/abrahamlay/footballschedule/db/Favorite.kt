package com.abrahamlay.footballschedule.db

import java.io.Serializable

/**
 * Created by Abraham on 24/05/2018.
 */

data class Favorite(val id: Long?,
                    val homeTeamId: String?,
                    val homeTeamName: String?,
                    val homeTeamBadge: String?,
                    val awayTeamId: String?,
                    val awayTeamName: String?,
                    val awayTeamBadge: String?):Serializable {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID_EVENT: String = "ID_EVENT"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val HOME_TEAM_NAME: String = "HOME_TEAM_NAME"
        const val HOME_TEAM_BADGE: String = "HOME_TEAM_BADGE"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val AWAY_TEAM_NAME: String = "AWAY_TEAM_NAME"
        const val AWAY_TEAM_BADGE: String = "AWAY_TEAM_BADGE"
    }
}