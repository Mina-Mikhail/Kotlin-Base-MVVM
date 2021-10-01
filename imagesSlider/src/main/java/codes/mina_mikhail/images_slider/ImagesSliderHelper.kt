package codes.mina_mikhail.images_slider

import android.app.Activity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import codes.mina_mikhail.images_slider.fullscreen_images_slider.FullscreenImagesSliderActivity
import kotlinx.coroutines.delay

class ImagesSliderHelper private constructor(builder: Builder) : LifecycleObserver {

  companion object {
    const val SLIDER_DELAY: Long = 2500
  }

  private var activity: Activity = builder.activity
  private var lifecycle: Lifecycle = builder.lifecycle

  private var images: List<String>
  private var sliderContainer: FrameLayout? = null

  @IdRes
  private var sliderContainerResourceID: Int = 0

  @ColorRes
  private var activeIndicatorColor: Int = R.color.black

  @ColorRes
  private var inActiveIndicatorColor: Int = R.color.white
  private var autoScrolling: Boolean = false
  private var imageClickAction: ((position: Int) -> Unit)?

  private var currentItem = 0
  private lateinit var viewPager: ViewPager2
  private lateinit var indicatorsContainer: LinearLayout
  private var viewPagerIndicators: ArrayList<TextView>
  private lateinit var imagesAdapter: ImagesSliderAdapter
  private lateinit var pageChangedCallback: ViewPager2.OnPageChangeCallback
  private var firstTimeCallListener: Boolean = true

  init {
    images = builder.images
    viewPagerIndicators = ArrayList(images.size)
    sliderContainer = builder.sliderContainer
    sliderContainerResourceID = builder.sliderContainerResourceID
    activeIndicatorColor = builder.activeIndicatorColor
    inActiveIndicatorColor = builder.inActiveIndicatorColor
    autoScrolling = builder.autoScrolling
    imageClickAction = builder.imageClickAction

    setUpSliderContainer()

    addViewPager()

    setViewPagerIndicator()

    addViewPagerIndicator()

    setUpViewPagerAdapter()

    setUpViewPagerListener()

    setUpAutoScrolling()

    lifecycle.addObserver(this)
  }

  private fun setUpSliderContainer() {
    if (sliderContainerResourceID != 0 && sliderContainer == null) {
      sliderContainer = activity.findViewById(sliderContainerResourceID)
    }

    sliderContainer?.removeAllViewsInLayout()
  }

  private fun addViewPager() {
    viewPager = ViewPager2(activity)

    viewPager.apply {
      val lp = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
      layoutParams = lp
      clipChildren = false
      clipToPadding = false
      getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    sliderContainer?.addView(viewPager)
  }

  private fun addViewPagerIndicator() {
    indicatorsContainer.apply {
      val lp = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
      lp.gravity = Gravity.CENTER or Gravity.BOTTOM
      layoutParams = lp
      orientation = LinearLayout.HORIZONTAL
      gravity = Gravity.CENTER
    }

    sliderContainer?.addView(indicatorsContainer)
  }

  private fun setViewPagerIndicator() {
    if (!::indicatorsContainer.isInitialized) {
      indicatorsContainer = LinearLayout(activity)
    }
    indicatorsContainer.removeAllViews()

    for (i in images.indices) {
      val tv = TextView(activity)
      tv.apply {
        val lp = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.setMargins(5, 0, 5, 7)
        layoutParams = lp
        setText(R.string.dot)
        textSize = if (i == currentItem) 45f else 28f
        includeFontPadding = false
        setTextColor(
          if (i == currentItem)
            ContextCompat.getColor(context, activeIndicatorColor)
          else
            ContextCompat.getColor(context, inActiveIndicatorColor)
        )
      }

      viewPagerIndicators.add(tv)
      indicatorsContainer.addView(tv)
      indicatorsContainer.bringToFront()
    }
  }

  private fun setUpViewPagerAdapter() {
    imagesAdapter = ImagesSliderAdapter { if (imageClickAction != null) imageClickAction else onImageClicked(it) }

    viewPager.adapter = imagesAdapter.apply { submitList(images) }
  }

  private fun onImageClicked(position: Int) {
    FullscreenImagesSliderActivity.open(activity, ArrayList(images), position)
  }

  private fun setUpViewPagerListener() {
    pageChangedCallback = object : ViewPager2.OnPageChangeCallback() {
      override
      fun onPageSelected(position: Int) {
        currentItem = position

        if (firstTimeCallListener) {
          firstTimeCallListener = false
          return
        }
        setViewPagerIndicator()
      }
    }
  }

  private fun setUpAutoScrolling() {
    if (autoScrolling) {
      lifecycle.coroutineScope.launchWhenResumed {
        while (true) {
          delay(SLIDER_DELAY)

          if (images.size - 1 == currentItem) {
            currentItem = 0
          } else {
            currentItem++
          }

          if (currentItem == 0) {
            viewPager.setCurrentItem(currentItem, false)
          } else {
            viewPager.setCurrentItem(currentItem, true)
          }
        }
      }
    }
  }

  class Builder(internal var activity: Activity, internal var lifecycle: Lifecycle) {

    internal lateinit var images: List<String>
    internal var sliderContainer: FrameLayout? = null

    @IdRes
    internal var sliderContainerResourceID: Int = 0

    @ColorRes
    internal var activeIndicatorColor: Int = R.color.black

    @ColorRes
    internal var inActiveIndicatorColor: Int = R.color.white
    internal var autoScrolling: Boolean = false
    internal var imageClickAction: ((position: Int) -> Unit)? = null

    fun setImages(images: List<String>): Builder {
      this.images = images
      return this
    }

    fun setSliderContainer(sliderContainer: FrameLayout): Builder {
      this.sliderContainer = sliderContainer
      return this
    }

    fun setSliderContainerResourceID(@IdRes sliderContainerResourceID: Int): Builder {
      this.sliderContainerResourceID = sliderContainerResourceID
      return this
    }

    fun setActiveIndicatorColor(@ColorRes activeIndicatorColor: Int): Builder {
      this.activeIndicatorColor = activeIndicatorColor
      return this
    }

    fun setInActiveIndicatorColor(@ColorRes inActiveIndicatorColor: Int): Builder {
      this.inActiveIndicatorColor = inActiveIndicatorColor
      return this
    }

    fun setAutoScrolling(autoScrolling: Boolean): Builder {
      this.autoScrolling = autoScrolling
      return this
    }

    fun setClickAction(imageClickAction: ((position: Int) -> Unit)?): Builder {
      this.imageClickAction = imageClickAction
      return this
    }

    fun create(): ImagesSliderHelper {
      return ImagesSliderHelper(this)
    }
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
  private fun onPause() {
    unregisterViewPagerCallback()
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  private fun onResume() {
    registerViewPagerCallback()
  }

  private fun registerViewPagerCallback() {
    viewPager.registerOnPageChangeCallback(pageChangedCallback)
  }

  private fun unregisterViewPagerCallback() {
    viewPager.unregisterOnPageChangeCallback(pageChangedCallback)
  }
}