package com.mina_mikhail.base_mvvm.presentation.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.mina_mikhail.base_mvvm.presentation.R.style

abstract class BaseDialog<VB : ViewDataBinding> : DialogFragment() {

  private var _binding: VB? = null
  open val binding get() = _binding!!
  private var mRootView: View? = null
  private var hasInitializedRootView = false

  override
  fun getTheme(): Int {
    return style.CustomDialogAnimation
  }

  override
  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    if (mRootView == null) {
      initViewBinding(inflater, container)
    }

    val params = dialog!!.window!!.attributes
    params.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
    dialog!!.window!!.attributes = params
    dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
    dialog!!.setCanceledOnTouchOutside(true)
    dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    return mRootView
  }

  private fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
    _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

    mRootView = binding.root
    binding.lifecycleOwner = this
    binding.executePendingBindings()
  }

  override
  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    if (!hasInitializedRootView) {
      setUpViews()

      hasInitializedRootView = true
    }
  }

  @LayoutRes
  abstract fun getLayoutId(): Int

  open fun setUpViews() {}
}