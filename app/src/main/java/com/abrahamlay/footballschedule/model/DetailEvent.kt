package com.abrahamlay.footballschedule.model

import com.google.gson.annotations.SerializedName

data class DetailEvent(

	@field:SerializedName("events")
	val events: List<DetailItem>
)