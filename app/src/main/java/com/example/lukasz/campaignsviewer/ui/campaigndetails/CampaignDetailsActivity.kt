package com.example.lukasz.campaignsviewer.ui.campaigndetails

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.lukasz.campaignsviewer.R
import com.example.lukasz.campaignsviewer.util.Event
import com.example.lukasz.campaignsviewer.util.PERMISSIONS_REQUEST_PHONE_CALL
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.campaign_details_activity.*

private const val PHOTO_URL = "campaign_url"
private const val CAMPAIGN_NAME = "campaign_name"
private const val CAMPAIGN_DESCRIPTION = "campaign_description"

class CampaignDetailsActivity : AppCompatActivity() {

  companion object {

    fun intent(context: Context,
               photoUrl: String,
               campaignName: String,
               campaignDescription: String): Intent {
      return Intent(context, CampaignDetailsActivity::class.java)
        .apply {
          putExtra(PHOTO_URL, photoUrl)
          putExtra(CAMPAIGN_NAME, campaignName)
          putExtra(CAMPAIGN_DESCRIPTION, campaignDescription)
        }
    }
  }

  private lateinit var viewModel: CampaignDetailsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.campaign_details_activity)

    setupViewModel()

    if (savedInstanceState == null) {
      setupViewModelData()
    }

    fab.setOnClickListener { viewModel.handleFABClick() }
  }

  private fun setupViewModel() {
    viewModel = ViewModelProviders.of(this).get(CampaignDetailsViewModel::class.java)
    viewModel.photoUrl.observe(this, Observer { loadPhoto(it) })
    viewModel.campaignName.observe(this, Observer { loadCampaignName(it) })
    viewModel.campaignDescription.observe(this, Observer { loadCampaignDescription(it) })
    viewModel.permissionRequestEvent.observe(this, Observer { requestPermission(it) })
    viewModel.phoneNumberEvent.observe(this, Observer { makePhoneCall(it) })
    viewModel.snackBarMessageEvent.observe(this, Observer { showSnackbar(it) })
  }

  private fun setupViewModelData() {
    val photoUrl = intent.getStringExtra(PHOTO_URL)
    val campaignName = intent.getStringExtra(CAMPAIGN_NAME)
    val campaignDescription = intent.getStringExtra(CAMPAIGN_DESCRIPTION)

    viewModel.setupData(photoUrl, campaignName, campaignDescription)
  }

  private fun loadPhoto(url: String?) {
    Picasso.get()
      .load(url)
      .fit()
      .centerCrop()
      .into(photo_image_view, object : Callback {
        override fun onSuccess() {
          photo_placeholder.visibility = View.GONE
        }

        override fun onError(e: Exception?) {}
      })
  }

  private fun loadCampaignName(campaignName: String?) {
    name_text_view.text = campaignName
  }

  private fun loadCampaignDescription(campaignDescription: String?) {
    description_text_view.text = campaignDescription
  }

  private fun requestPermission(permissionEvent: Event<String>?) {
    permissionEvent?.getEventData()?.let {
      if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, arrayOf(it), PERMISSIONS_REQUEST_PHONE_CALL)
      } else {
        viewModel.onPermissionResult(it, true)
      }
    }

  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    when (requestCode) {
      PERMISSIONS_REQUEST_PHONE_CALL -> {
        val granted = grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        viewModel.onPermissionResult(Manifest.permission.CALL_PHONE, granted)
      }
    }
  }

  @SuppressWarnings("MissingPermission")
  private fun makePhoneCall(phoneNumberUriEvent: Event<Uri>?) {
    phoneNumberUriEvent?.getEventData()?.let {
      val intent = Intent(Intent.ACTION_CALL, it)
      startActivity(intent)
    }
  }

  private fun showSnackbar(messageEvent: Event<Int>?) {
    messageEvent?.getEventData()?.let {
      Snackbar.make(campaign_details_activity_root, it, Snackbar.LENGTH_LONG).show()
    }
  }
}