package com.example.lukasz.campaignsviewer.ui.campaingslist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lukasz.campaignsviewer.R
import com.example.lukasz.campaignsviewer.data.CampaignData
import com.example.lukasz.campaignsviewer.ui.campaigndetails.CampaignDetailsActivity
import com.example.lukasz.campaignsviewer.util.RectangleTransformation
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.campaigns_list_row.view.*
import java.lang.Exception

class CampaignsAdapter : RecyclerView.Adapter<CampaignsAdapter.ViewHolder>() {

  private val data = mutableListOf<CampaignData>()

  fun refreshData(listItems: List<CampaignData>?) {
    if (listItems != null) {
      data.clear()
      data.addAll(listItems)
      notifyDataSetChanged()
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val row = LayoutInflater.from(parent.context)
      .inflate(R.layout.campaigns_list_row, parent, false)

    return ViewHolder(row)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.view.name_text_view.text = data[position].name

    setupPhoto(position, holder)

    holder.view.setOnClickListener {
      showCampaignDetails(
        holder.view.context,
        data[position].image.url,
        data[position].name,
        data[position].description) }
  }

  private fun setupPhoto(position: Int, holder: ViewHolder) {
    val transformation = RectangleTransformation()
    val photoUrl = data[position].image.url
    Picasso.get()
      .load(photoUrl)
      .transform(transformation)
      .into(holder.view.photo_image_view, object : Callback {
        override fun onSuccess() {
          holder.view.photo_placeholder.visibility = View.GONE
        }

        override fun onError(e: Exception?) {}
      })
  }

  private fun showCampaignDetails(context: Context, photoUrl: String, campaignName: String, campaignDescription: String) {
    val intent = CampaignDetailsActivity.intent(context, photoUrl, campaignName, campaignDescription)
    context.startActivity(intent)
  }

  override fun getItemCount() = data.size

  class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}