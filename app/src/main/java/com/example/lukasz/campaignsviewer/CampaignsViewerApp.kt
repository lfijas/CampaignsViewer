package com.example.lukasz.campaignsviewer

import android.app.Activity
import android.app.Application
import com.example.lukasz.campaignsviewer.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class CampaignsViewerApp : Application(), HasActivityInjector {

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    Timber.plant(Timber.DebugTree())

    AppInjector.init(this)
  }

  override fun activityInjector() = dispatchingAndroidInjector
}