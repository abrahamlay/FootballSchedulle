package com.abrahamlay.footballschedule.model

import com.google.gson.annotations.SerializedName

data class NextEvent(

	@field:SerializedName("events")
	var events: List<NextEventItem>?
)