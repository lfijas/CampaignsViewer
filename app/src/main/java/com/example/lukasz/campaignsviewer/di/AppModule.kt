package com.example.lukasz.campaignsviewer.di

import com.example.lukasz.campaignsviewer.api.CampaignsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

  @Singleton
  @Provides
  fun provideCampaignsService(): CampaignsService {
    return Retrofit.Builder()
      .baseUrl("https://static.westwing.de/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(CampaignsService::class.java)
  }

}