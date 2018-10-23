package com.example.lukasz.campaignsviewer.data

import com.google.gson.annotations.SerializedName

data class CampaignData(
  @field:SerializedName("name") val name: String,
  @field:SerializedName("description") val description: String,
  @field:SerializedName("image") val image: CampaignImage
)