package com.example.lukasz.campaignsviewer.data

import com.google.gson.annotations.SerializedName

data class GetCampaignsResponse(
  @field:SerializedName("metadata") val metadata: CampaignMetadata
)