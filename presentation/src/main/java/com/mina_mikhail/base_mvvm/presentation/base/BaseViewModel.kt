package com.mina_mikhail.base_mvvm.presentation.base

import androidx.lifecycle.ViewModel
import com.mina_mikhail.base_mvvm.presentation.base.utils.SingleLiveEvent

open class BaseViewModel : ViewModel() {

  var dataLoadingEvent: SingleLiveEvent<Int> = SingleLiveEvent()
}