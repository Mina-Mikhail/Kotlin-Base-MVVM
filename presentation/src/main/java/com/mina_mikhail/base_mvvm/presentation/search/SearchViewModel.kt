package com.mina_mikhail.base_mvvm.presentation.search

import com.mina_mikhail.base_mvvm.domain.search.repository.SearchRepository
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val searchRepository: SearchRepository
) : BaseViewModel()