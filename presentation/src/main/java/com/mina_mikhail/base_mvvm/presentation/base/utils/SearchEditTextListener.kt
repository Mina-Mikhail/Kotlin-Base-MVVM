package com.mina_mikhail.base_mvvm.presentation.base.utils

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class SearchEditTextListener(
  private val customDebouncePeriod: Long? = null,
  lifecycle: Lifecycle,
  private val onSearchQueryChange: (String?) -> Unit
) : TextWatcher, LifecycleObserver {

  companion object {
    const val debouncePeriod: Long = 600
  }

  private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
  private var currentSearchJob: Job? = null

  init {
    lifecycle.addObserver(this)
  }

  override
  fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
  }

  override
  fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
    currentSearchJob?.cancel()

    currentSearchJob = coroutineScope.launch(Dispatchers.IO) {
      charSequence?.let {
        customDebouncePeriod?.let {
          delay(customDebouncePeriod)
        } ?: delay(debouncePeriod)

        withContext(Dispatchers.Main) {
          onSearchQueryChange(charSequence.toString())
        }
      }
    }
  }

  override
  fun afterTextChanged(p0: Editable?) {
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  private fun destroy() {
    currentSearchJob?.cancel()
  }
}