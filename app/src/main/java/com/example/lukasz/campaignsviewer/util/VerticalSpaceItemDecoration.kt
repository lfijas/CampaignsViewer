package com.example.lukasz.campaignsviewer.util

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.lukasz.campaignsviewer.R

class VerticalSpaceItemDecoration : RecyclerView.ItemDecoration() {

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)

    val isItemLast: Boolean = parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1
    if (!isItemLast) {
      val margin = parent.resources.getDimensionPixelSize(R.dimen.campaigns_list_divider_size)
      outRect.bottom = margin
    }
  }
}