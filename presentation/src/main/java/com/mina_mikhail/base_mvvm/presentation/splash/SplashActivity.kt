package com.mina_mikhail.base_mvvm.presentation.splash

import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseActivity
import com.mina_mikhail.base_mvvm.presentation.base.extensions.openActivityAndClearStack
import com.mina_mikhail.base_mvvm.presentation.databinding.ActivitySplashBinding
import com.mina_mikhail.base_mvvm.presentation.home.HomeActivity
import com.mina_mikhail.base_mvvm.presentation.intro.IntroActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

  private val viewModel: SplashViewModel by viewModels()

  override
  fun getLayoutId() = R.layout.activity_splash

  override
  fun setUpViews() {
    decideNavigationLogic()
  }

  private fun decideNavigationLogic() {
    Handler(Looper.getMainLooper()).postDelayed({
      val targetActivity = if (viewModel.isFirstTime()) {
        IntroActivity::class.java
      } else {
        HomeActivity::class.java
      }
      openActivityAndClearStack(targetActivity)
    }, 2000)
  }
}