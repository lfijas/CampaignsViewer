package com.example.lukasz.campaignsviewer.util

class Event<out T>(private val data: T) {

  var hasBeenHandled = false
    private set

  fun getEventData(): T? {
    return if (hasBeenHandled) {
      null
    } else {
      hasBeenHandled = true
      data
    }
  }
}