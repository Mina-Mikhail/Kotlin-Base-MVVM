package com.mina_mikhail.base_mvvm.data.search.repository

import com.mina_mikhail.base_mvvm.data.search.data_source.remote.SearchRemoteDataSource
import com.mina_mikhail.base_mvvm.domain.search.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val remoteDataSource: SearchRemoteDataSource) :
  SearchRepository