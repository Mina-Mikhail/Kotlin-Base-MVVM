package com.mina_mikhail.base_mvvm.presentation.intro

import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseActivity
import com.mina_mikhail.base_mvvm.presentation.databinding.ActivityIntroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : BaseActivity<ActivityIntroBinding>() {

  override
  fun getLayoutId() = R.layout.activity_intro
}