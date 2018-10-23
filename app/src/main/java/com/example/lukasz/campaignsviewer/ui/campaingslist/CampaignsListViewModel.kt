package com.example.lukasz.campaignsviewer.ui.campaingslist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.lukasz.campaignsviewer.data.CampaignData
import com.example.lukasz.campaignsviewer.repository.CampaignsRepository
import javax.inject.Inject

class CampaignsListViewModel
@Inject constructor(campaignsRepository: CampaignsRepository)
  : ViewModel() {

  var campaings = MutableLiveData<List<CampaignData>>()

  init {
    campaings = campaignsRepository.getCampaigns()
  }
}
