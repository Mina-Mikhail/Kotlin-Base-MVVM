package com.mina_mikhail.base_mvvm.presentation.auth.forgot_password

import androidx.fragment.app.viewModels
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.backToPreviousScreen
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding>() {

  private val viewModel: ForgotPasswordViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_forgot_password

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setupObservers() {
    viewModel.backToPreviousScreen.observe(this) { backToPreviousScreen() }
  }
}