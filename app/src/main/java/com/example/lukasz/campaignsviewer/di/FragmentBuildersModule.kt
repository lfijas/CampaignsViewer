package com.example.lukasz.campaignsviewer.di

import com.example.lukasz.campaignsviewer.ui.campaingslist.CampaignsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeCampaignsListFragment(): CampaignsListFragment
}