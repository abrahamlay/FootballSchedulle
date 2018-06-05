package com.abrahamlay.footballschedule.model

import com.google.gson.annotations.SerializedName

data class LastEvent(

	@field:SerializedName("events")
	var events: List<LastEventsItem>?
)