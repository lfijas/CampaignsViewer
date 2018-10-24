package com.example.lukasz.campaignsviewer.ui.campaigndetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.lukasz.campaignsviewer.R
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
  }

  private fun setupViewModel() {
    viewModel = ViewModelProviders.of(this).get(CampaignDetailsViewModel::class.java)
    viewModel.photoUrl.observe(this, Observer { loadPhoto(it) })
    viewModel.campaignName.observe(this, Observer { loadCampaignName(it) })
    viewModel.campaignDescription.observe(this, Observer { loadCampaignDescription(it) })
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
}