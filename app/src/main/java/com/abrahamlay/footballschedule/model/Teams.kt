package com.abrahamlay.footballschedule.model

import com.google.gson.annotations.SerializedName

data class Teams(

	@field:SerializedName("teams")
	val teams: List<TeamsItem?>
)