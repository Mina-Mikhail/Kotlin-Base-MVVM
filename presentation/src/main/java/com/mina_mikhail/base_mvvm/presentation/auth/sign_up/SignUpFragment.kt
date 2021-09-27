package com.mina_mikhail.base_mvvm.presentation.auth.sign_up

import androidx.fragment.app.viewModels
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.backToPreviousScreen
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

  private val viewModel: SignUpViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_sign_up

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setupObservers() {
    viewModel.backToPreviousScreen.observe(this) { backToPreviousScreen() }
  }
}