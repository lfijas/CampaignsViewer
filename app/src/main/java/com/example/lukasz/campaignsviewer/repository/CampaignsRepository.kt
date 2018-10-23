package com.example.lukasz.campaignsviewer.repository

import android.arch.lifecycle.MutableLiveData
import com.example.lukasz.campaignsviewer.api.CampaignsService
import com.example.lukasz.campaignsviewer.data.GetCampaignsResponse
import com.example.lukasz.campaignsviewer.data.CampaignData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CampaignsRepository @Inject constructor(private val campaignsService: CampaignsService) {

  fun getCampaigns(): MutableLiveData<List<CampaignData>> {

    val campaigns = MutableLiveData<List<CampaignData>>()

    campaignsService.getCampaigns().enqueue(object : Callback<GetCampaignsResponse> {

      override fun onResponse(call: Call<GetCampaignsResponse>, response: Response<GetCampaignsResponse>) {
        Timber.d("getCampaigns response: %s", response.body())

        campaigns.value = response.body()?.metadata?.data
      }

      override fun onFailure(call: Call<GetCampaignsResponse>, t: Throwable) {
        Timber.d("Request failed: %s", t.localizedMessage)
      }
    })

    return campaigns
  }
}