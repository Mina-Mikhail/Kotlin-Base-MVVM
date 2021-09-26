package codes.mina_mikhail.images_slider.fullscreen_images_slider

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import codes.mina_mikhail.images_slider.databinding.ActivityFullscreenImagesSliderBinding
import codes.mina_mikhail.images_slider.utils.ZoomOutTransformation

internal class FullscreenImagesSliderActivity : AppCompatActivity() {

  companion object {
    const val KEY_IMAGES = "images"
    const val KEY_SELECTED_POSITION = "selectedPosition"

    fun open(activity: Activity, images: ArrayList<String>, selectedPosition: Int) {
      val intent = Intent(activity, FullscreenImagesSliderActivity::class.java)
      intent.putStringArrayListExtra(KEY_IMAGES, images)
      intent.putExtra(KEY_SELECTED_POSITION, selectedPosition)
      activity.startActivity(intent)
    }
  }

  private lateinit var binding: ActivityFullscreenImagesSliderBinding

  private lateinit var images: ArrayList<String>
  private var selectedPosition: Int = 0

  private lateinit var sliderAdapter: FullscreenImagesSliderAdapter
  private lateinit var pageChangedCallback: ViewPager2.OnPageChangeCallback

  private lateinit var thumbnailsAdapter: ThumbnailsAdapter

  override
  fun onPause() {
    unregisterViewPagerCallback()

    super.onPause()
  }

  override
  fun onResume() {
    super.onResume()

    registerViewPagerCallback()
  }

  override
  fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    initViewBinding()

    getIntentData()

    setUpViews()

    handleClickListeners()
  }

  private fun initViewBinding() {
    binding = ActivityFullscreenImagesSliderBinding.inflate(layoutInflater)
    setContentView(binding.root)
  }

  private fun getIntentData() {
    images = intent.getStringArrayListExtra(KEY_IMAGES) ?: ArrayList()
    selectedPosition = intent.getIntExtra(KEY_SELECTED_POSITION, 0)
  }

  private fun setUpViews() {
    setUpSliderAdapter()

    setUpSliderListener()

    setUpThumbnailsRecyclerView()
  }

  private fun setUpSliderAdapter() {
    sliderAdapter = FullscreenImagesSliderAdapter()

    binding.sliderPager.apply {
      clipChildren = false
      clipToPadding = false
      getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

      setPageTransformer(ZoomOutTransformation())

      adapter = sliderAdapter.apply { submitList(images) }

      currentItem = selectedPosition
    }
  }

  private fun setUpSliderListener() {
    pageChangedCallback = object : ViewPager2.OnPageChangeCallback() {
      override
      fun onPageSelected(position: Int) {
        scrollToSpecificThumbnail(position)
      }
    }
  }

  private fun setUpThumbnailsRecyclerView() {
    thumbnailsAdapter = ThumbnailsAdapter(selectedPosition) { onThumbnailClicked(it) }

    binding.recyclerViewThumbnails.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(
        this@FullscreenImagesSliderActivity,
        LinearLayoutManager.HORIZONTAL,
        false
      )
      adapter = thumbnailsAdapter.apply { submitList(images) }
    }
  }

  private fun onThumbnailClicked(position: Int) {
    if (position == thumbnailsAdapter.selectedItemPosition) return

    scrollToSpecificThumbnail(position)
  }

  private fun scrollToSpecificThumbnail(position: Int) {
    binding.recyclerViewThumbnails.smoothScrollToPosition(position)
    thumbnailsAdapter.selectItem(position)

    binding.sliderPager.setCurrentItem(position, true)
  }

  private fun handleClickListeners() {
    binding.btnBack.setOnClickListener { finish() }
  }

  private fun registerViewPagerCallback() {
    binding.sliderPager.registerOnPageChangeCallback(pageChangedCallback)
  }

  private fun unregisterViewPagerCallback() {
    binding.sliderPager.unregisterOnPageChangeCallback(pageChangedCallback)
  }
}