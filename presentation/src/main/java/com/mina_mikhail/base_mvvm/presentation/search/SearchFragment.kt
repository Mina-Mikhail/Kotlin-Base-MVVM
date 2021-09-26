package com.mina_mikhail.base_mvvm.presentation.search

import androidx.fragment.app.viewModels
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyString
import com.mina_mikhail.base_mvvm.presentation.base.extensions.hide
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

  private val viewModel: SearchViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_search

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setUpViews() {
    setUpToolBar()
  }

  private fun setUpToolBar() {
    binding.includedToolbar.toolbarTitle.text = getMyString(R.string.search)
    binding.includedToolbar.backIv.hide()
  }
}