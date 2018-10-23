package com.example.lukasz.campaignsviewer.util

import android.graphics.Bitmap
import com.squareup.picasso.Transformation

private const val RATIO = 9.0 / 16

class RectangleTransformation : Transformation {

  override fun transform(source: Bitmap?): Bitmap {
    val targetWidth = source!!.width
    val targetHeight = (targetWidth * RATIO).toInt()
    val result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false)
    if (result != source) {
      source.recycle()
    }
    return result
  }

  override fun key() = "rectangle"
}