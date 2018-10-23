package com.example.lukasz.campaignsviewer.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.lukasz.campaignsviewer.ViewModelsFactory
import com.example.lukasz.campaignsviewer.ui.campaingslist.CampaignsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(CampaignsListViewModel::class)
  abstract fun bindCampaignsListViewModel(campaignsListViewModel: CampaignsListViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelsFactory): ViewModelProvider.Factory
}