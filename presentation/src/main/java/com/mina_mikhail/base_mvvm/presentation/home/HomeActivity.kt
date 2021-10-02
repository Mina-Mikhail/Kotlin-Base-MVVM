package com.mina_mikhail.base_mvvm.presentation.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.annotation.IdRes
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseActivity
import com.mina_mikhail.base_mvvm.presentation.base.extensions.setupWithNavController
import com.mina_mikhail.base_mvvm.presentation.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

  companion object {
    const val ACTION_OPEN_SPECIFIC_PAGE = "ACTION_OPEN_SPECIFIC_PAGE"
    const val TAB_ID = "TAB_ID"
  }

  private var isReceiverRegistered = false

  private val openSpecificTabReceiver: BroadcastReceiver = object : BroadcastReceiver() {
    override
    fun onReceive(context: Context, intent: Intent) {
      navigateToSpecificTab(intent.getIntExtra(TAB_ID, 0))
    }
  }

  override
  fun getLayoutId() = R.layout.activity_home

  override
  fun onResume() {
    super.onResume()

    registerOpenSpecificTabReceiver()
  }

  private fun registerOpenSpecificTabReceiver() {
    if (!isReceiverRegistered) {
      LocalBroadcastManager.getInstance(this)
        .registerReceiver(
          openSpecificTabReceiver,
          IntentFilter(ACTION_OPEN_SPECIFIC_PAGE)
        )
      isReceiverRegistered = true
    }
  }

  override
  fun setUpBottomNavigation() {
    setUpBottomNavigationWithGraphs()
  }

  private fun setUpBottomNavigationWithGraphs() {
    val graphIds = listOf(
      R.navigation.nav_home,
      R.navigation.nav_search,
      R.navigation.nav_account
    )

    val controller = binding.bottomNavigationView.setupWithNavController(
      graphIds,
      supportFragmentManager,
      R.id.fragment_host_container,
      intent
    )

    navController = controller
  }

  private fun navigateToSpecificTab(@IdRes tabID: Int) {
    binding.bottomNavigationView.selectedItemId = tabID
  }

  override
  fun onDestroy() {
    unregisterOpenSpecificTabReceiver()

    super.onDestroy()
  }

  private fun unregisterOpenSpecificTabReceiver() {
    LocalBroadcastManager.getInstance(this).unregisterReceiver(openSpecificTabReceiver)
  }
}