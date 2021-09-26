package com.mina_mikhail.base_mvvm.presentation.auth

import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseActivity
import com.mina_mikhail.base_mvvm.presentation.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>() {

  override
  fun getLayoutId() = R.layout.activity_auth
}