package com.example.lukasz.campaignsviewer.di

import com.example.lukasz.campaignsviewer.ui.campaingslist.CampaignsListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CampaignsListActivityModule {
  @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
  abstract fun contributeCampaignsListActivity(): CampaignsListActivity
}