package com.mina_mikhail.base_mvvm.presentation.home

import androidx.fragment.app.viewModels
import codes.mina_mikhail.action_chooser.ActionChooserHelper
import codes.mina_mikhail.action_chooser.ChooserAction
import codes.mina_mikhail.pretty_pop_up.PrettyPopUpHelper
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyColor
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyString
import com.mina_mikhail.base_mvvm.presentation.base.extensions.hide
import com.mina_mikhail.base_mvvm.presentation.base.extensions.showMessage
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

  private val viewModel: HomeViewModel by viewModels()

  private lateinit var actions: ArrayList<ChooserAction>

  override
  fun getLayoutId() = R.layout.fragment_home

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setUpViews() {
    setUpToolBar()

    setActionsDummyData()

    binding.btnShowActionChooserPopUp.setOnClickListener { showActionChooserPopUp() }

    binding.btnShowPrettyPopUp.setOnClickListener { showPrettyPopUp() }
  }

  private fun setUpToolBar() {
    binding.includedToolbar.toolbarTitle.text = getMyString(R.string.home)
    binding.includedToolbar.backIv.hide()
  }

  private fun setActionsDummyData() {
    actions = ArrayList()
    actions.apply {
      add(
        ChooserAction(
          1,
          "First Option",
          true
        )
      )

      add(
        ChooserAction(
          1,
          "Second Option"
        )
      )

      add(
        ChooserAction(
          1,
          "Third Option"
        )
      )
    }
  }

  private fun showActionChooserPopUp() {
    ActionChooserHelper.Builder(childFragmentManager)
      .setPopUpBackground(R.drawable.bg_round_pop_up)
      .setTitle("Choose Option")
      .setTitleColorResource(R.color.colorPrimaryDark)
      .setContent("Choose option from the following options")
      .setContentColorResource(R.color.gray)
      .setActions(actions)
      .showRadioButton(true)
      .setRadioButtonSelectionColor(getMyColor(R.color.colorAccent))
      .setCloseButton("Close Pop Up")
      .setCloseButtonBackground(R.drawable.btn_accent)
      .setCloseButtonTextColorResource(R.color.colorPrimaryDark)
      .setItemClick {
        showMessage(it.name)
      }
      .create()
  }

  private fun showPrettyPopUp() {
    PrettyPopUpHelper.Builder(childFragmentManager)
      .setStyle(PrettyPopUpHelper.Style.STYLE_1_HORIZONTAL_BUTTONS)
      .setTitle("Hello!")
      .setTitleColor(getMyColor(R.color.colorPrimaryDark))
      .setContent("Hello to my base MVVM project from my pretty pop up helper")
      .setContentColor(getMyColor(R.color.gray))
      .setPositiveButtonBackground(R.drawable.btn_accent)
      .setPositiveButtonTextColor(getMyColor(R.color.colorPrimaryDark))
      .setPositiveButton("Okay") {
        it.dismiss()
      }
      .setNegativeButtonBackground(R.drawable.btn_gray)
      .setNegativeButtonTextColor(getMyColor(R.color.white))
      .setNegativeButton(getMyString(R.string.cancel), null)
      .create()
  }
}