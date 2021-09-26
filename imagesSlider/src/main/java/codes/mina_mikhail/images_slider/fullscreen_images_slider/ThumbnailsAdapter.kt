package codes.mina_mikhail.images_slider.fullscreen_images_slider

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.GONE
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.VISIBLE
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import codes.mina_mikhail.images_slider.R
import codes.mina_mikhail.images_slider.databinding.ItemThumbnailBinding
import codes.mina_mikhail.images_slider.fullscreen_images_slider.ThumbnailsAdapter.ThumbnailsViewHolder
import codes.mina_mikhail.images_slider.utils.loadSliderImage
import codes.mina_mikhail.images_slider.utils.px

internal class ThumbnailsAdapter(var selectedItemPosition: Int = -1, private var itemClick: ((position: Int) -> Unit)) :
  ListAdapter<String, ThumbnailsViewHolder>(DIFF_CALLBACK) {

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
      override
      fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem.hashCode() == newItem.hashCode()

      override
      fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem
    }
  }

  override
  fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ThumbnailsViewHolder {
    val root = LayoutInflater.from(parent.context).inflate(R.layout.item_thumbnail, parent, false)
    return ThumbnailsViewHolder(ItemThumbnailBinding.bind(root))
  }

  override
  fun onBindViewHolder(holder: ThumbnailsViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  fun selectItem(itemPosition: Int) {
    val copyOfLastCheckedPosition = selectedItemPosition
    selectedItemPosition = itemPosition

    notifyItemChanged(copyOfLastCheckedPosition)
    notifyItemChanged(selectedItemPosition)
  }

  inner class ThumbnailsViewHolder(private val itemBinding: ItemThumbnailBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var currentItem: String

    init {
      itemBinding.item.setOnClickListener {
        itemClick.invoke(currentList.indexOf(currentItem))
      }
    }

    fun bind(item: String) {
      currentItem = item

      displayImage()

      animateSelection()
    }

    private fun displayImage() {
      itemBinding.ivThumbnail.loadSliderImage(currentItem)
    }

    private fun animateSelection() {
      if (selectedItemPosition == currentList.indexOf(currentItem)) {
        val anim = ValueAnimator.ofInt(itemBinding.thumbnailContainer.measuredHeight, 60.px)
        anim.addUpdateListener { valueAnimator: ValueAnimator ->
          val value = valueAnimator.animatedValue as Int
          val layoutParams: LayoutParams = itemBinding.thumbnailContainer.layoutParams
          layoutParams.height = value
          layoutParams.width = value
          itemBinding.thumbnailContainer.layoutParams = layoutParams

          itemBinding.alphaLayer.visibility = GONE
        }
        anim.duration = 500
        anim.start()
      } else {
        val anim = ValueAnimator.ofInt(itemBinding.thumbnailContainer.measuredHeight, 50.px)
        anim.addUpdateListener { valueAnimator: ValueAnimator ->
          val value = valueAnimator.animatedValue as Int
          val layoutParams: LayoutParams = itemBinding.thumbnailContainer.layoutParams
          layoutParams.height = value
          layoutParams.width = value
          itemBinding.thumbnailContainer.layoutParams = layoutParams

          itemBinding.alphaLayer.visibility = VISIBLE
        }
        anim.duration = 500
        anim.start()
      }
    }
  }
}