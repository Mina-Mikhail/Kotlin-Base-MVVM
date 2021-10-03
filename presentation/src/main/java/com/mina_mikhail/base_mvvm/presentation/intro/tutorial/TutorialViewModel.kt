package com.mina_mikhail.base_mvvm.presentation.intro.tutorial

import com.mina_mikhail.base_mvvm.presentation.base.BaseViewModel
import com.mina_mikhail.base_mvvm.presentation.base.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor() : BaseViewModel() {

  val openIntro = SingleLiveEvent<Void>()

  fun onSkipClicked() {
    openIntro.call()
  }
}