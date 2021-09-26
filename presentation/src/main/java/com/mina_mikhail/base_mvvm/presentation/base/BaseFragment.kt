package com.mina_mikhail.base_mvvm.presentation.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.mina_mikhail.base_mvvm.presentation.base.utils.hideLoadingDialog
import com.mina_mikhail.base_mvvm.presentation.base.utils.showLoadingDialog
import java.util.Locale

abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

  private var _binding: VB? = null
  open val binding get() = _binding!!
  private var mRootView: View? = null
  private var hasInitializedRootView = false
  private var progressDialog: Dialog? = null

  override
  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    if (mRootView == null) {
      initViewBinding(inflater, container)
    }

    return mRootView
  }

  private fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
    _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

    mRootView = binding.root
    binding.lifecycleOwner = this
    binding.executePendingBindings()
  }

  override
  fun onResume() {
    super.onResume()

    registerListeners()
  }

  override
  fun onPause() {
    unRegisterListeners()

    super.onPause()
  }

  override
  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    if (!hasInitializedRootView) {
      getFragmentArguments()
      setBindingVariables()
      setUpViews()
      observeAPICall()
      setupObservers()

      hasInitializedRootView = true
    }
  }

  @LayoutRes
  abstract fun getLayoutId(): Int

  open fun registerListeners() {}

  open fun unRegisterListeners() {}

  open fun getFragmentArguments() {}

  open fun setBindingVariables() {}

  open fun setUpViews() {}

  open fun observeAPICall() {}

  open fun setupObservers() {}

  fun showLoading() {
    hideLoading()
    progressDialog = showLoadingDialog(requireActivity(), null)
  }

  fun showLoading(hint: String?) {
    hideLoading()
    progressDialog = showLoadingDialog(requireActivity(), hint)
  }

  fun hideLoading() = hideLoadingDialog(progressDialog, requireActivity())

  fun setLanguage(language: String) {
    (requireActivity() as BaseActivity<*>).updateLocale(language)
  }

  val currentLanguage: Locale
    get() = Locale.getDefault()
}