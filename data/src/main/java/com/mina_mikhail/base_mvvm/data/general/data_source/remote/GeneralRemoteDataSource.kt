package com.mina_mikhail.base_mvvm.data.general.data_source.remote

import com.mina_mikhail.base_mvvm.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class GeneralRemoteDataSource @Inject constructor(private val apiService: GeneralServices) : BaseRemoteDataSource()