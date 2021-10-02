package com.mina_mikhail.base_mvvm.data.general.repository

import com.mina_mikhail.base_mvvm.data.general.data_source.remote.GeneralRemoteDataSource
import com.mina_mikhail.base_mvvm.data.local.preferences.AppPreferences
import com.mina_mikhail.base_mvvm.domain.general.repository.GeneralRepository
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
  private val remoteDataSource: GeneralRemoteDataSource,
  private val appPreferences: AppPreferences
) : GeneralRepository