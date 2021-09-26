package codes.mina_mikhail.action_chooser

import android.R.attr
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import codes.mina_mikhail.action_chooser.ActionChooserAdapter.ActionChooserViewHolder
import codes.mina_mikhail.action_chooser.databinding.ItemChooserActionBinding

internal class ActionChooserAdapter(
  private var itemClick: ((item: ChooserAction) -> Unit), private var supportSelection: Boolean,
  private var radioButtonSelectionColor: Int?
) :
  ListAdapter<ChooserAction, ActionChooserViewHolder>(DIFF_CALLBACK) {

  companion object {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ChooserAction>() {
      override
      fun areItemsTheSame(oldItem: ChooserAction, newItem: ChooserAction): Boolean =
        oldItem.type == newItem.type

      override
      fun areContentsTheSame(oldItem: ChooserAction, newItem: ChooserAction): Boolean =
        oldItem == newItem
    }
  }

  override
  fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): ActionChooserViewHolder {
    val root = LayoutInflater.from(parent.context).inflate(R.layout.item_chooser_action, parent, false)
    return ActionChooserViewHolder(ItemChooserActionBinding.bind(root))
  }

  override
  fun onBindViewHolder(holder: ActionChooserViewHolder, position: Int) {
    holder.bind(position, getItem(position))
  }

  @SuppressLint("RestrictedApi")
  inner class ActionChooserViewHolder(private val itemBinding: ItemChooserActionBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var currentItem: ChooserAction

    init {
      itemBinding.llItem.setOnClickListener {
        itemClick.invoke(currentItem)
      }

      if (supportSelection) {
        itemBinding.rb.visibility = View.VISIBLE
      } else {
        itemBinding.rb.visibility = View.GONE
      }

      val colorStateList = ColorStateList(
        arrayOf(intArrayOf(-attr.state_checked), intArrayOf(attr.state_checked)),
        intArrayOf(
          Color.DKGRAY,
          radioButtonSelectionColor ?: Color.YELLOW
        )
      )
      itemBinding.rb.supportButtonTintList = colorStateList
    }

    fun bind(position: Int, item: ChooserAction) {
      currentItem = item

      itemBinding.tvItem.text = item.name
      itemBinding.rb.isChecked = item.isSelected

      if (currentList.size - 1 != position) {
        itemBinding.divider.visibility = View.VISIBLE
      } else {
        itemBinding.divider.visibility = View.GONE
      }
    }
  }
}