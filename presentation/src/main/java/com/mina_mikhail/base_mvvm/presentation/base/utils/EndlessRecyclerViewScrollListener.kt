package com.mina_mikhail.base_mvvm.presentation.base.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class EndlessRecyclerViewScrollListener(private var visibleThreshold: Int = 5) :
  RecyclerView.OnScrollListener() {

  private var currentPage = 0
  private var previousTotalItemCount = 0
  private var loading = true
  private val startingPageIndex = 0

  private var mLayoutManager: LayoutManager? = null

  constructor(visibleThreshold: Int, layoutManager: LayoutManager) : this(visibleThreshold) {
    when (layoutManager) {
      is LinearLayoutManager -> {
        this.mLayoutManager = layoutManager
      }
      is GridLayoutManager -> {
        this.mLayoutManager = layoutManager
        this.visibleThreshold = visibleThreshold * layoutManager.spanCount
      }
      is StaggeredGridLayoutManager -> {
        this.mLayoutManager = layoutManager
        this.visibleThreshold = visibleThreshold * layoutManager.spanCount
      }
    }
  }

  fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
    var maxSize = 0
    for (i in lastVisibleItemPositions.indices) {
      if (i == 0) {
        maxSize = lastVisibleItemPositions[i]
      } else if (lastVisibleItemPositions[i] > maxSize) {
        maxSize = lastVisibleItemPositions[i]
      }
    }
    return maxSize
  }

  override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
    var lastVisibleItemPosition = 0
    val totalItemCount = mLayoutManager?.itemCount ?: 0

    if (mLayoutManager is StaggeredGridLayoutManager) {
      val lastVisibleItemPositions = (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
      lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
    } else if (mLayoutManager is GridLayoutManager) {
      lastVisibleItemPosition = (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
    } else if (mLayoutManager is LinearLayoutManager) {
      lastVisibleItemPosition = (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
    }

    if (totalItemCount < previousTotalItemCount) {
      this.currentPage = this.startingPageIndex
      this.previousTotalItemCount = totalItemCount
      if (totalItemCount == 0) {
        this.loading = true
      }
    }

    if (loading && totalItemCount > previousTotalItemCount) {
      loading = false
      previousTotalItemCount = totalItemCount
    }

    if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
      currentPage++
      onLoadMore(currentPage, totalItemCount, view)
      loading = true
    }
  }

  fun resetState() {
    this.currentPage = this.startingPageIndex
    this.previousTotalItemCount = 0
    this.loading = true
  }

  abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)
}