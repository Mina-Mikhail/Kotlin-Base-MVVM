package com.mina_mikhail.base_mvvm.presentation.intro.tutorial

import androidx.fragment.app.viewModels
import codes.mina_mikhail.app_tutorial.AppTutorial
import codes.mina_mikhail.app_tutorial.AppTutorialHelper
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.base.extensions.getMyDrawable
import com.mina_mikhail.base_mvvm.presentation.base.extensions.navigateSafe
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentTutorialBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TutorialFragment : BaseFragment<FragmentTutorialBinding>() {

  private val viewModel: TutorialViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.fragment_tutorial

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun setUpViews() {
    setUpAppTutorial()
  }

  private fun setUpAppTutorial() {
    val tutorialData = ArrayList<AppTutorial>()
    tutorialData.apply {
      tutorialData.add(
        AppTutorial(
          "Title - 1",
          "Some content here for first tutorial screen",
          getMyDrawable(R.drawable.bg_no_data)
        )
      )

      tutorialData.add(
        AppTutorial(
          "Title - 2",
          "Some content here for second tutorial screen",
          getMyDrawable(R.drawable.bg_no_internet)
        )
      )

      tutorialData.add(
        AppTutorial(
          "Title - 3",
          "Some content here for third tutorial screen",
          getMyDrawable(R.drawable.bg_no_image)
        )
      )
    }

    AppTutorialHelper.Builder(requireActivity(), lifecycle)
      .setTutorialData(tutorialData)
      .setTitleColor(R.color.colorPrimaryDark)
      .setContentColor(R.color.gray)
      .setSliderContainerResourceID(R.id.tutorial_container)
      .setActiveIndicatorColor(R.color.colorAccent)
      .setInActiveIndicatorColor(R.color.gray)
      .setAutoScrolling(false)
      .setNextButtonTextColor(R.color.colorPrimaryDark)
      .setNextButtonBackground(R.drawable.btn_accent)
      .setSkipTutorialClick { openIntro() }
      .create()
  }

  override
  fun setupObservers() {
    viewModel.openIntro.observe(this) { openIntro() }
  }

  private fun openIntro() {
    navigateSafe(TutorialFragmentDirections.actionOpenIntroFragment())
  }
}