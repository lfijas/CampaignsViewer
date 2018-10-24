package com.example.lukasz.campaignsviewer.ui.campaigndetails

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class CampaignDetailsViewModel : ViewModel() {

  val photoUrl = MutableLiveData<String>()

  val campaignName = MutableLiveData<String>()

  val campaignDescription = MutableLiveData<String>()

  fun setupData(photoUrl: String, campaignName: String, campaignDescription: String) {
    this.photoUrl.value = photoUrl
    this.campaignName.value = campaignName
    this.campaignDescription.value = campaignDescription
  }
}