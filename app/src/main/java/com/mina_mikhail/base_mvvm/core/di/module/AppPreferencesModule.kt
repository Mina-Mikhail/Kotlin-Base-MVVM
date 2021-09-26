package com.mina_mikhail.base_mvvm.core.di.module

import android.content.Context
import com.mina_mikhail.base_mvvm.data.local.preferences.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppPreferencesModule {

  @Provides
  @Singleton
  fun providePreferences(@ApplicationContext context: Context) = AppPreferences(context)
}