package com.mina_mikhail.base_mvvm.data.search.data_source.remote

import com.mina_mikhail.base_mvvm.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(private val apiService: SearchServices) :
  BaseRemoteDataSource()