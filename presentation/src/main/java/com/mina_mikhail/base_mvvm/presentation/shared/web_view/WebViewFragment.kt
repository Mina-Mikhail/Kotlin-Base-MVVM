package com.mina_mikhail.base_mvvm.presentation.shared.web_view

import android.annotation.SuppressLint
import android.graphics.Color
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mina_mikhail.base_mvvm.presentation.R
import com.mina_mikhail.base_mvvm.presentation.base.BaseFragment
import com.mina_mikhail.base_mvvm.presentation.databinding.FragmentWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : BaseFragment<FragmentWebViewBinding>() {

  private val viewModel: WebViewViewModel by viewModels()

  private var htmlContent: String? = null

  override
  fun getLayoutId() = R.layout.fragment_web_view

  override
  fun setBindingVariables() {
    binding.viewModel = viewModel
  }

  override
  fun getFragmentArguments() {
    val args: WebViewFragmentArgs by navArgs()

    htmlContent = args.htmlContent
  }

  override
  fun setUpViews() {
    setUpToolbar()

    setUpWebView()

    displayHtmlContent()
  }

  private fun setUpToolbar() {
    binding.includedToolbar.toolbarTitle.text = ""
    binding.includedToolbar.backIv.setOnClickListener { backToPreviousScreen() }
  }

  @SuppressLint("SetJavaScriptEnabled")
  private fun setUpWebView() {
    binding.webView.apply {
      setBackgroundColor(Color.TRANSPARENT)
      webChromeClient = WebChromeClient()
      webViewClient = object : WebViewClient() {
        override
        fun onPageFinished(view: WebView, url: String) {
          view.loadUrl("javascript:document.body.style.setProperty(\"color\", \"#000000\");")
          hideLoading()
        }
      }
      settings.javaScriptEnabled = true
    }
  }

  private fun displayHtmlContent() {
    if (!htmlContent.isNullOrEmpty()) {
      setContent(htmlContent!!)
    }
  }

  private fun setContent(content: String) {
    binding.webView.loadData(
      content, "text/html", "utf-8"
    )
  }

  private fun backToPreviousScreen() {
    findNavController().popBackStack()
  }
}