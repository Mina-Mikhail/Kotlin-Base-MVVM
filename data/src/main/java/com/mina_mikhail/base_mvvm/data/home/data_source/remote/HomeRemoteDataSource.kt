package com.mina_mikhail.base_mvvm.data.home.data_source.remote

import com.mina_mikhail.base_mvvm.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: HomeServices) :
  BaseRemoteDataSource()