package com.example.lukasz.campaignsviewer.api

import com.example.lukasz.campaignsviewer.data.GetCampaignsResponse
import retrofit2.Call
import retrofit2.http.GET

interface CampaignsService {

  @GET("cms/test/data.json")
  fun getCampaigns(): Call<GetCampaignsResponse>
}