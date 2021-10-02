package com.mina_mikhail.base_mvvm.presentation.shared.web_view

import com.mina_mikhail.base_mvvm.domain.general.repository.GeneralRepository
import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(private val generalRepository: GeneralRepository) :
  BaseViewModel()