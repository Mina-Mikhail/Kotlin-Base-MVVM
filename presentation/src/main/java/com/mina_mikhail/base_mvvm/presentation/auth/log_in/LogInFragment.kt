package com.mina_mikhail.base_mvvm.presentation.auth.log_in

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.SingleLineTransformationMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView.BufferType.SPANNABLE
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mina_mikhail.base_mvvm.domain.auth.enums.AuthFieldsValidation
import com.mina_mikhail.base_mvvm.domain.utils.Resource
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyColor
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyDrawable
import com.mina_mikhail.base_mvvm.presentation.base.extensions.handleApiError
import com.mina_mikhail.base_mvvm.presentation.base.extensions.hideKeyboard
import com.mina_mikhail.base_mvvm.presentation.base.extensions.navigateSafe
import com.mina_mikhail.base_mvvm.presentation.base.extensions.openActivityAndClearStack
import com.mina_mikhail.base_mvvm.presentation.base.extensions.showSnackBar
import com.mina_mikhail.base_mvvm.presentation.base.utils.showSoftInput
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentLogInBinding
import com.mina_mikhail.base_mvvm.presentation.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>() {

  private val viewModel: LogInViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_log_in

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setUpViews() {
    setUpSignUpButton()
  }

  private fun setUpSignUpButton() {
    val finalMessage =
      "${resources.getString(R.string.not_have_account)} ${resources.getString(R.string.sign_up)}"

    val spanString = SpannableString(finalMessage)

    val clickableSpan = object : ClickableSpan() {
      override
      fun onClick(textView: View) {
        openSignUp()
      }
    }

    // Define my span
    spanString
      .setSpan(
        clickableSpan, finalMessage.indexOf(resources.getString(R.string.sign_up)),
        finalMessage.indexOf(resources.getString(R.string.sign_up)) + resources
          .getString(R.string.sign_up).length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
      )

    // Set span color
    spanString.setSpan(
      ForegroundColorSpan(getMyColor(R.color.blue)),
      finalMessage.indexOf(resources.getString(R.string.sign_up)),
      finalMessage.indexOf(resources.getString(R.string.sign_up)) + resources
        .getString(R.string.sign_up).length,
      Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    // Set span style
    spanString.setSpan(
      StyleSpan(Typeface.BOLD),
      finalMessage.indexOf(resources.getString(R.string.sign_up)),
      finalMessage.indexOf(resources.getString(R.string.sign_up)) + resources
        .getString(R.string.sign_up).length,
      Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    // Add underline to span
    spanString.setSpan(
      UnderlineSpan(),
      finalMessage.indexOf(resources.getString(R.string.sign_up)),
      finalMessage.indexOf(resources.getString(R.string.sign_up)) + resources
        .getString(R.string.sign_up).length,
      Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    binding.btnSignUp.movementMethod = LinkMovementMethod.getInstance()
    binding.btnSignUp.setText(spanString, SPANNABLE)
    binding.btnSignUp.isSelected = true
  }

  override
  fun setupObservers() {
    viewModel.togglePassword.observe(this) {
      if (binding.etPassword.transformationMethod.javaClass.simpleName.equals(
          "PasswordTransformationMethod"
        )
      ) {
        binding.etPassword.transformationMethod = SingleLineTransformationMethod()
        binding.ivPasswordToggle.setImageDrawable(getMyDrawable(R.drawable.ic_hide_password))
      } else {
        binding.etPassword.transformationMethod = PasswordTransformationMethod()
        binding.ivPasswordToggle.setImageDrawable(getMyDrawable(R.drawable.ic_show_password))
      }
      binding.etPassword.setSelection(binding.etPassword.text.length)
    }

    viewModel.openForgotPassword.observe(this) {
      openForgotPassword()
    }

    viewModel.validationException.observe(this) {
      when (it) {
        AuthFieldsValidation.EMPTY_EMAIL.value -> {
          binding.etEmail.requestFocus()
          showSoftInput(binding.etEmail, requireContext())
          requireView().showSnackBar(resources.getString(R.string.empty_email))
        }
        AuthFieldsValidation.INVALID_EMAIL.value -> {
          binding.etEmail.requestFocus()
          showSoftInput(binding.etEmail, requireContext())
          requireView().showSnackBar(resources.getString(R.string.invalid_email))
        }
        AuthFieldsValidation.EMPTY_PASSWORD.value -> {
          binding.etPassword.requestFocus()
          showSoftInput(binding.etPassword, requireContext())
          requireView().showSnackBar(resources.getString(R.string.empty_password))
        }
      }
    }

    lifecycleScope.launchWhenResumed {
      viewModel.logInResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            openHome()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it, retryAction = { viewModel.onLogInClicked() })
          }
        }
      }
    }
  }

  private fun openForgotPassword() {
    navigateSafe(LogInFragmentDirections.actionOpenForgotPasswordFragment())
  }

  private fun openSignUp() {
    navigateSafe(LogInFragmentDirections.actionOpenSignUpFragment())
  }

  private fun openHome() {
    requireActivity().openActivityAndClearStack(HomeActivity::class.java)
  }
}