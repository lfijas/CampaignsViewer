package com.example.lukasz.campaignsviewer.ui.campaigndetails

import android.Manifest
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.net.Uri
import android.support.annotation.StringRes
import com.example.lukasz.campaignsviewer.R
import com.example.lukasz.campaignsviewer.util.Event
import com.example.lukasz.campaignsviewer.util.PHONE_PROTOCOL_PREFIX

private const val PHONE_NUMBER = "+48123456789"

class CampaignDetailsViewModel : ViewModel() {

  val photoUrl = MutableLiveData<String>()

  val campaignName = MutableLiveData<String>()

  val campaignDescription = MutableLiveData<String>()

  val permissionRequestEvent = MutableLiveData<Event<String>>()

  val phoneNumberEvent = MutableLiveData<Event<Uri>>()

  val snackBarMessageEvent = MutableLiveData<Event<@StringRes Int>>()

  fun setupData(photoUrl: String, campaignName: String, campaignDescription: String) {
    this.photoUrl.value = photoUrl
    this.campaignName.value = campaignName
    this.campaignDescription.value = campaignDescription
  }

  fun handleFABClick() {
    permissionRequestEvent.value = Event(Manifest.permission.CALL_PHONE)
  }

  fun onPermissionResult(permission: String?, granted: Boolean) {
    when (permission) {
      Manifest.permission.CALL_PHONE -> handleCallPhonePermission(granted)
    }
  }

  private fun handleCallPhonePermission(granted: Boolean) {
    if (granted) {
      phoneNumberEvent.value = Event(Uri.parse(PHONE_PROTOCOL_PREFIX + PHONE_NUMBER))
    } else {
      snackBarMessageEvent.value = Event(R.string.call_phone_permission_not_granted_info)
    }
  }
}