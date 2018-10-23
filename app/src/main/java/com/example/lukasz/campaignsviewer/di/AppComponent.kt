package com.example.lukasz.campaignsviewer.di

import android.app.Application
import com.example.lukasz.campaignsviewer.CampaignsViewerApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    CampaignsListActivityModule::class]
)
interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(campaignsViewerApp: CampaignsViewerApp)
}