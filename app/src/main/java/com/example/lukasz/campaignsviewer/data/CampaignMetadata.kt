package com.example.lukasz.campaignsviewer.data

import com.google.gson.annotations.SerializedName

data class CampaignMetadata(
  @field:SerializedName("data") val data: List<CampaignData>
)