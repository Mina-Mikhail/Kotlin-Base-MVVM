package com.mina_mikhail.base_mvvm.presentation.account

import androidx.fragment.app.viewModels
import codes.mina_mikhail.pretty_pop_up.PrettyPopUpHelper
import com.mina_mikhail.base_mvvm.domain.utils.Resource.Failure
import com.mina_mikhail.base_mvvm.domain.utils.Resource.Loading
import com.mina_mikhail.base_mvvm.domain.utils.Resource.Success
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.auth.AuthActivity
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyColor
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyString
import com.mina_mikhail.base_mvvm.presentation.base.extensions.handleApiError
import com.mina_mikhail.base_mvvm.presentation.base.extensions.hide
import com.mina_mikhail.base_mvvm.presentation.base.extensions.openActivityAndClearStack
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>() {

  private val viewModel: AccountViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_account

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setUpViews() {
    setUpToolBar()
  }

  private fun setUpToolBar() {
    binding.includedToolbar.toolbarTitle.text = getMyString(R.string.account)
    binding.includedToolbar.backIv.hide()
  }

  override
  fun observeAPICall() {
    sendFirebaseToken()
  }

  private fun sendFirebaseToken() {
    viewModel.sendFirebaseToken(requireContext())
  }

  private fun showLogOutPopUp() {
    PrettyPopUpHelper.Builder(childFragmentManager)
      .setTitle(R.string.log_out)
      .setContent(R.string.log_out_hint)
      .setContentColor(getMyColor(R.color.darkGray))
      .setPositiveButton(R.string.log_out) {
        it.dismiss()

        logOut()
      }
      .setNegativeButton(getMyString(R.string.cancel), null)
      .create()
  }

  private fun logOut() {
    viewModel.logOut().observe(this) {
      when (it) {
        Loading -> {
          showLoading()
        }
        is Success -> {
          viewModel.clearPreferences()
          hideLoading()
          openLogInScreen()
        }
        is Failure -> {
          hideLoading()
          handleApiError(it, retryAction = { logOut() })
        }
      }
    }
  }

  private fun openLogInScreen() {
    requireActivity().openActivityAndClearStack(AuthActivity::class.java)
  }
}