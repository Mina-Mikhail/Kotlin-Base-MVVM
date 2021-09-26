package codes.mina_mikhail.action_chooser

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import codes.mina_mikhail.action_chooser.databinding.PopUpActionChooserBinding

class ActionChooserHelper private constructor(builder: Builder) {

  private var fragmentManager: FragmentManager = builder.fragmentManager

  // Pop Up Background
  private val popUpBackground: Int?

  // Title
  private val title: String?
  private val titleResId: Int?

  // Title Color
  private val titleColorResId: Int?
  private val titleColor: Int?

  // Content
  private val content: String?
  private val contentResId: Int?

  // Content Color
  private val contentColorResId: Int?
  private val contentColor: Int?

  // Actions
  private val actions: ArrayList<ChooserAction>?
  private val showRadioButton: Boolean
  private val radioButtonSelectionColor: Int?

  // Close Button
  private val closeButton: String?
  private val closeButtonResId: Int?

  // Close Button Background
  private val closeButtonBackground: Int?

  // Close Button Text Color
  private val closeButtonTextColorResId: Int?
  private val closeButtonTextColor: Int?

  // Item CLick
  private var itemClick: ((action: ChooserAction) -> Unit)?

  init {
    popUpBackground = builder.popUpBackground

    title = builder.title
    titleResId = builder.titleResId

    titleColorResId = builder.titleColorResId
    titleColor = builder.titleColor

    content = builder.content
    contentResId = builder.contentResId

    contentColorResId = builder.contentColorResId
    contentColor = builder.contentColor

    actions = builder.actions
    showRadioButton = builder.showRadioButton
    radioButtonSelectionColor = builder.radioButtonSelectionColor

    closeButton = builder.closeButton
    closeButtonResId = builder.closeButtonResId

    closeButtonBackground = builder.closeButtonBackground

    closeButtonTextColorResId = builder.closeButtonTextColorResId
    closeButtonTextColor = builder.closeButtonTextColor

    itemClick = builder.itemClick

    showPopUp()
  }

  private fun showPopUp() {
    val dialogFragment = ActionChooserPopUp()

    dialogFragment.setPopUpBackground(popUpBackground)

    dialogFragment.setTitle(title)
    dialogFragment.setTitle(titleResId)

    dialogFragment.setTitleColorResource(titleColorResId)
    dialogFragment.setTitleColor(titleColor)

    dialogFragment.setContent(content)
    dialogFragment.setContent(contentResId)

    dialogFragment.setContentColorResource(contentColorResId)
    dialogFragment.setContentColor(contentColor)

    dialogFragment.setActions(actions)
    dialogFragment.setShowRadioButton(showRadioButton)
    dialogFragment.setRadioButtonSelectionColor(radioButtonSelectionColor)

    dialogFragment.setPositiveButton(closeButton)
    dialogFragment.setPositiveButton(closeButtonResId)

    dialogFragment.setPositiveButtonBackground(closeButtonBackground)

    dialogFragment.setPositiveButtonTextColorResource(closeButtonTextColorResId)
    dialogFragment.setPositiveButtonTextColor(closeButtonTextColor)

    dialogFragment.setItemClick(itemClick)

    dialogFragment.show(fragmentManager, "AnimatedDialog")
  }

  class ActionChooserPopUp : DialogFragment() {

    private var _binding: PopUpActionChooserBinding? = null
    private val binding get() = _binding!!

    private lateinit var actionChooserAdapter: ActionChooserAdapter

    // Pop Up Background
    private var popUpBackground: Int? = null

    // Title
    private var title: String? = null
    private var titleResId: Int? = null

    // Title Color
    private var titleColorResId: Int? = null
    private var titleColor: Int? = null

    // Content
    private var content: String? = null
    private var contentResId: Int? = null

    // Content Color
    private var contentColorResId: Int? = null
    private var contentColor: Int? = null

    // Actions
    private var actions: ArrayList<ChooserAction>? = null
    private var showRadioButton: Boolean = false
    private var radioButtonSelectionColor: Int? = null

    // Close Button
    private var closeButton: String? = null
    private var closeButtonResId: Int? = null

    // Close Button Background
    private var closeButtonBackground: Int? = null

    // Close Button Text Color
    private var closeButtonTextColorResId: Int? = null
    private var closeButtonTextColor: Int? = null

    // Item CLick
    private var itemClick: ((action: ChooserAction) -> Unit)? = null

    override
    fun getTheme(): Int {
      return R.style.ChooserDialogAnimation
    }

    override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
      _binding = PopUpActionChooserBinding.inflate(inflater, container, false)

      val params = dialog!!.window!!.attributes
      params.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
      dialog!!.window!!.attributes = params
      dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
      dialog!!.setCanceledOnTouchOutside(true)
      dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

      return binding.root
    }

    override
    fun onResume() {
      dialog!!.window!!.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
      )
      dialog!!.window!!.setGravity(Gravity.BOTTOM)

      super.onResume()
    }

    override
    fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      setUpViews()
    }

    fun setUpViews() {
      setUpPopUpBackground()

      setUpPopUpTitle()

      setUpPopUpTitleColor()

      setUpPopUpContent()

      setUpPopUpContentColor()

      setUpActionsRecyclerView()

      setUpCloseButton()

      setUpCloseButtonTextColor()

      setUpCloseButtonBackground()

      handleClickListeners()
    }

    private fun setUpPopUpBackground() {
      popUpBackground?.let {
        binding.popUpContainer.background = ContextCompat.getDrawable(requireContext(), it)
      } ?: run {
        binding.popUpContainer.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chooser_pop_up)
      }
    }

    private fun setUpPopUpTitle() {
      titleResId?.let {
        binding.tvTitle.text = resources.getString(it)
        binding.tvTitle.visibility = View.VISIBLE
      } ?: run {
        if (title != null && title!!.isNotEmpty()) {
          binding.tvTitle.text = title
          binding.tvTitle.visibility = View.VISIBLE
        } else {
          binding.tvTitle.visibility = View.GONE
        }
      }
    }

    private fun setUpPopUpTitleColor() {
      titleColorResId?.let {
        binding.tvTitle.setTextColor(ContextCompat.getColor(requireContext(), it))
      } ?: run {
        titleColor?.let {
          binding.tvTitle.setTextColor(it)
        } ?: run {
          binding.tvTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorDarkGray))
        }
      }
    }

    private fun setUpPopUpContent() {
      contentResId?.let {
        binding.tvHint.text = resources.getString(it)
        binding.tvHint.visibility = View.VISIBLE
      } ?: run {
        if (content != null && content!!.isNotEmpty()) {
          binding.tvHint.text = content
          binding.tvHint.visibility = View.VISIBLE
        } else {
          binding.tvHint.visibility = View.GONE
        }
      }
    }

    private fun setUpPopUpContentColor() {
      contentColorResId?.let {
        binding.tvHint.setTextColor(ContextCompat.getColor(requireContext(), it))
      } ?: run {
        contentColor?.let {
          binding.tvHint.setTextColor(it)
        } ?: run {
          binding.tvHint.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray))
        }
      }
    }

    private fun setUpActionsRecyclerView() {
      actionChooserAdapter = ActionChooserAdapter(
        itemClick = { onActionSelected(it) },
        showRadioButton,
        radioButtonSelectionColor
      )
      binding.recyclerView.apply {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(requireContext())
        adapter = actionChooserAdapter.apply { submitList(actions) }
      }
    }

    private fun onActionSelected(action: ChooserAction) {
      actions?.forEach { it.isSelected = false }
      action.isSelected = true

      itemClick?.invoke(action)

      dismiss()
    }

    private fun setUpCloseButton() {
      closeButtonResId?.let {
        binding.btnClose.text = resources.getString(it)
      } ?: run {
        if (closeButton != null && closeButton!!.isNotEmpty()) {
          binding.btnClose.text = closeButton
        } else {
          binding.btnClose.text = resources.getString(R.string.action_close)
        }
      }
    }

    private fun setUpCloseButtonTextColor() {
      closeButtonTextColorResId?.let {
        binding.btnClose.setTextColor(ContextCompat.getColor(requireContext(), it))
      } ?: run {
        closeButtonTextColor?.let {
          binding.btnClose.setTextColor(it)
        } ?: run {
          binding.btnClose.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorDarkGray))
        }
      }
    }

    private fun setUpCloseButtonBackground() {
      closeButtonBackground?.let {
        binding.btnClose.background = ContextCompat.getDrawable(requireContext(), it)
      } ?: run {
        binding.btnClose.background = ContextCompat.getDrawable(requireContext(), R.drawable.btn_action_chooser)
      }
    }

    private fun handleClickListeners() {
      binding.btnClose.setOnClickListener { dismiss() }
    }

    fun setPopUpBackground(@DrawableRes popUpBackground: Int?) {
      this.popUpBackground = popUpBackground
    }

    fun setTitle(title: String?) {
      this.title = title
    }

    fun setTitle(@StringRes titleResId: Int?) {
      this.titleResId = titleResId
    }

    fun setTitleColorResource(@ColorRes titleColorResId: Int?) {
      this.titleColorResId = titleColorResId
    }

    fun setTitleColor(titleColor: Int?) {
      this.titleColor = titleColor
    }

    fun setContent(content: String?) {
      this.content = content
    }

    fun setContent(@StringRes contentResId: Int?) {
      this.contentResId = contentResId
    }

    fun setContentColorResource(@ColorRes contentColorResId: Int?) {
      this.contentColorResId = contentColorResId
    }

    fun setContentColor(contentColor: Int?) {
      this.contentColor = contentColor
    }

    fun setActions(actions: ArrayList<ChooserAction>?) {
      this.actions = actions
    }

    fun setShowRadioButton(showRadioButton: Boolean) {
      this.showRadioButton = showRadioButton
    }

    fun setRadioButtonSelectionColor(radioButtonSelectionColor: Int?) {
      this.radioButtonSelectionColor = radioButtonSelectionColor
    }

    fun setPositiveButton(positiveButton: String?) {
      this.closeButton = positiveButton
    }

    fun setPositiveButton(@StringRes positiveButtonResId: Int?) {
      this.closeButtonResId = positiveButtonResId
    }

    fun setPositiveButtonBackground(@DrawableRes positiveButtonBackground: Int?) {
      this.closeButtonBackground = positiveButtonBackground
    }

    fun setPositiveButtonTextColorResource(@ColorRes positiveButtonTextColorResId: Int?) {
      this.closeButtonTextColorResId = positiveButtonTextColorResId
    }

    fun setPositiveButtonTextColor(positiveButtonTextColor: Int?) {
      this.closeButtonTextColor = positiveButtonTextColor
    }

    fun setItemClick(itemClick: ((action: ChooserAction) -> Unit)?) {
      this.itemClick = itemClick
    }

    override
    fun onDestroyView() {
      super.onDestroyView()
      _binding = null
    }
  }

  class Builder(internal var fragmentManager: FragmentManager) {

    // Pop Up Background
    internal var popUpBackground: Int? = null

    // Title
    internal var title: String? = null
    internal var titleResId: Int? = null

    // Title Color
    internal var titleColorResId: Int? = null
    internal var titleColor: Int? = null

    // Content
    internal var content: String? = null
    internal var contentResId: Int? = null

    // Content Color
    internal var contentColorResId: Int? = null
    internal var contentColor: Int? = null

    // Actions
    internal var actions: ArrayList<ChooserAction>? = null
    internal var showRadioButton: Boolean = false
    internal var radioButtonSelectionColor: Int? = null

    // Close Button
    internal var closeButton: String? = null
    internal var closeButtonResId: Int? = null

    // Close Button Background
    internal var closeButtonBackground: Int? = null

    // Close Button Text Color
    internal var closeButtonTextColorResId: Int? = null
    internal var closeButtonTextColor: Int? = null

    // Item CLick
    internal var itemClick: ((action: ChooserAction) -> Unit)? = null

    fun setPopUpBackground(@DrawableRes popUpBackground: Int): Builder {
      this.popUpBackground = popUpBackground
      return this
    }

    fun setTitle(title: String?): Builder {
      this.title = title!!
      return this
    }

    fun setTitle(@StringRes titleResId: Int): Builder {
      this.titleResId = titleResId
      return this
    }

    fun setTitleColorResource(@ColorRes titleColorResId: Int): Builder {
      this.titleColorResId = titleColorResId
      return this
    }

    fun setTitleColor(titleColor: Int): Builder {
      this.titleColor = titleColor
      return this
    }

    fun setContent(content: String?): Builder {
      this.content = content!!
      return this
    }

    fun setContent(@StringRes contentResId: Int): Builder {
      this.contentResId = contentResId
      return this
    }

    fun setContentColorResource(@ColorRes contentColorResId: Int): Builder {
      this.contentColorResId = contentColorResId
      return this
    }

    fun setContentColor(contentColor: Int): Builder {
      this.contentColor = contentColor
      return this
    }

    fun setActions(actions: ArrayList<ChooserAction>): Builder {
      this.actions = actions
      return this
    }

    fun showRadioButton(showRadioButton: Boolean): Builder {
      this.showRadioButton = showRadioButton
      return this
    }

    fun setRadioButtonSelectionColor(radioButtonSelectionColor: Int): Builder {
      this.radioButtonSelectionColor = radioButtonSelectionColor
      return this
    }

    fun setCloseButton(positiveButton: String?): Builder {
      this.closeButton = positiveButton!!
      return this
    }

    fun setCloseButton(@StringRes positiveButtonResId: Int): Builder {
      this.closeButtonResId = positiveButtonResId
      return this
    }

    fun setCloseButtonBackground(@DrawableRes positiveButtonBackground: Int): Builder {
      this.closeButtonBackground = positiveButtonBackground
      return this
    }

    fun setCloseButtonTextColorResource(@ColorRes positiveButtonTextColorResId: Int): Builder {
      this.closeButtonTextColorResId = positiveButtonTextColorResId
      return this
    }

    fun setCloseButtonTextColor(positiveButtonTextColor: Int): Builder {
      this.closeButtonTextColor = positiveButtonTextColor
      return this
    }

    fun setItemClick(itemClick: ((action: ChooserAction) -> Unit)): Builder {
      this.itemClick = itemClick
      return this
    }

    fun create(): ActionChooserHelper {
      return ActionChooserHelper(this)
    }
  }
}