package codes.mina_mikhail.pretty_pop_up

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
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
import codes.mina_mikhail.pretty_pop_up.PrettyPopUpHelper.Style.STYLE_1_HORIZONTAL_BUTTONS
import codes.mina_mikhail.pretty_pop_up.PrettyPopUpHelper.Style.STYLE_2_VERTICAL_BUTTONS
import codes.mina_mikhail.pretty_pop_up.databinding.PrettyPopUpBinding

class PrettyPopUpHelper private constructor(builder: Builder) {

  enum class Style {
    STYLE_1_HORIZONTAL_BUTTONS,
    STYLE_2_VERTICAL_BUTTONS
  }

  private var fragmentManager: FragmentManager = builder.fragmentManager

  // Pop Up Style
  private val style: Style?

  // Title
  private val title: String?
  private val titleResId: Int?

  // Title Color
  private val titleColorResId: Int?
  private val titleColor: Int?

  // Image
  private val image: Drawable?
  private val imageResId: Int?

  // Content
  private val content: String?
  private val contentResId: Int?

  // Content Color
  private val contentColorResId: Int?
  private val contentColor: Int?

  // Positive Button
  private val positiveButton: String?
  private val positiveButtonResId: Int?

  // Positive Button Background
  private val positiveButtonBackground: Int?

  // Positive Button Text Color
  private val positiveButtonTextColorResId: Int?
  private val positiveButtonTextColor: Int?

  // Positive Button Click
  private var positiveButtonClick: ((popUp: DialogFragment) -> Unit)?

  // Negative Button
  private val negativeButton: String?
  private val negativeButtonResId: Int?

  // Negative Button Background
  private val negativeButtonBackground: Int?

  // Negative Button Text Color
  private val negativeButtonTextColorResId: Int?
  private val negativeButtonTextColor: Int?

  // Negative Button Click
  private var negativeButtonClick: ((popUp: DialogFragment) -> Unit)?

  init {
    style = builder.style

    title = builder.title
    titleResId = builder.titleResId

    titleColorResId = builder.titleColorResId
    titleColor = builder.titleColor

    image = builder.image
    imageResId = builder.imageResId

    content = builder.content
    contentResId = builder.contentResId

    contentColorResId = builder.contentColorResId
    contentColor = builder.contentColor

    positiveButton = builder.positiveButton
    positiveButtonResId = builder.positiveButtonResId

    positiveButtonBackground = builder.positiveButtonBackground

    positiveButtonTextColorResId = builder.positiveButtonTextColorResId
    positiveButtonTextColor = builder.positiveButtonTextColor

    positiveButtonClick = builder.positiveButtonClick

    negativeButton = builder.negativeButton
    negativeButtonResId = builder.negativeButtonResId

    negativeButtonBackground = builder.negativeButtonBackground

    negativeButtonTextColorResId = builder.negativeButtonTextColorResId
    negativeButtonTextColor = builder.negativeButtonTextColor

    negativeButtonClick = builder.negativeButtonClick

    showPopUp()
  }

  private fun showPopUp() {
    val dialogFragment = PrettyPopUp()

    dialogFragment.setStyle(style)

    dialogFragment.setTitle(title)
    dialogFragment.setTitle(titleResId)

    dialogFragment.setTitleColorResource(titleColorResId)
    dialogFragment.setTitleColor(titleColor)

    dialogFragment.setImage(image)
    dialogFragment.setImage(imageResId)

    dialogFragment.setContent(content)
    dialogFragment.setContent(contentResId)

    dialogFragment.setContentColorResource(contentColorResId)
    dialogFragment.setContentColor(contentColor)

    dialogFragment.setPositiveButton(positiveButton, positiveButtonClick)
    dialogFragment.setPositiveButton(positiveButtonResId, positiveButtonClick)

    dialogFragment.setPositiveButtonBackground(positiveButtonBackground)

    dialogFragment.setPositiveButtonTextColorResource(positiveButtonTextColorResId)
    dialogFragment.setPositiveButtonTextColor(positiveButtonTextColor)

    dialogFragment.setUpNegativeButton(negativeButton, negativeButtonClick)
    dialogFragment.setUpNegativeButton(negativeButtonResId, negativeButtonClick)

    dialogFragment.setNegativeButtonBackground(negativeButtonBackground)

    dialogFragment.setNegativeButtonTextColorResource(negativeButtonTextColorResId)
    dialogFragment.setNegativeButtonTextColor(negativeButtonTextColor)

    dialogFragment.show(fragmentManager, "AnimatedDialog")
  }

  class PrettyPopUp : DialogFragment() {

    private var _binding: PrettyPopUpBinding? = null
    private val binding get() = _binding!!

    // Pop Up Style
    private var style: Style? = null

    // Title
    private var title: String? = null
    private var titleResId: Int? = null

    // Title Color
    private var titleColorResId: Int? = null
    private var titleColor: Int? = null

    // Image
    private var image: Drawable? = null
    private var imageResId: Int? = null

    // Content
    private var content: String? = null
    private var contentResId: Int? = null

    // Content Color
    private var contentColorResId: Int? = null
    private var contentColor: Int? = null

    // Positive Button
    private var positiveButton: String? = null
    private var positiveButtonResId: Int? = null

    // Positive Button Background
    private var positiveButtonBackground: Int? = null

    // Positive Button Text Color
    private var positiveButtonTextColorResId: Int? = null
    private var positiveButtonTextColor: Int? = null

    // Positive Button Click
    private var positiveButtonClick: ((popUp: DialogFragment) -> Unit)? = null

    // Negative Button
    private var negativeButton: String? = null
    private var negativeButtonResId: Int? = null

    // Negative Button Background
    private var negativeButtonBackground: Int? = null

    // Negative Button Text Color
    private var negativeButtonTextColorResId: Int? = null
    private var negativeButtonTextColor: Int? = null

    // Negative Button Click
    private var negativeButtonClick: ((popUp: DialogFragment) -> Unit)? = null

    override
    fun getTheme(): Int {
      return R.style.PrettyDialogAnimation
    }

    override
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
      _binding = PrettyPopUpBinding.inflate(inflater, container, false)

      val params = dialog!!.window!!.attributes
      params.gravity = Gravity.CENTER
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
      dialog!!.window!!.setGravity(Gravity.CENTER)

      super.onResume()
    }

    override
    fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      setUpViews()
    }

    fun setUpViews() {
      setUpPopUpTitle()

      setUpPopUpTitleColor()

      setUpPopUpImage()

      setUpPopUpContent()

      setUpPopUpContentColor()

      setUpPositiveButton()

      setUpPositiveButtonTextColor()

      setUpPositiveButtonBackground()

      setUpNegativeButton()

      setUpNegativeButtonTextColor()

      setUpNegativeButtonBackground()

      handleClickListeners()
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

    private fun setUpPopUpImage() {
      image?.let {
        binding.ivImage.setImageDrawable(it)
        binding.ivImage.visibility = View.VISIBLE
      } ?: run {
        imageResId?.let {
          binding.ivImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), it))
          binding.ivImage.visibility = View.VISIBLE
        } ?: run {
          binding.ivImage.visibility = View.GONE
        }
      }
    }

    private fun setUpPopUpContent() {
      contentResId?.let {
        binding.tvContent.text = resources.getString(it)
        binding.tvContent.visibility = View.VISIBLE
      } ?: run {
        if (content != null && content!!.isNotEmpty()) {
          binding.tvContent.text = content
          binding.tvContent.visibility = View.VISIBLE
        } else {
          binding.tvContent.visibility = View.GONE
        }
      }
    }

    private fun setUpPopUpContentColor() {
      contentColorResId?.let {
        binding.tvContent.setTextColor(ContextCompat.getColor(requireContext(), it))
      } ?: run {
        contentColor?.let {
          binding.tvContent.setTextColor(it)
        } ?: run {
          binding.tvContent.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGray))
        }
      }
    }

    private fun setUpPositiveButton() {
      positiveButton?.let {
        when (style) {
          STYLE_1_HORIZONTAL_BUTTONS -> {
            binding.positiveBtnStyle1.text = it
            binding.positiveBtnStyle1.visibility = View.VISIBLE
          }
          STYLE_2_VERTICAL_BUTTONS -> {
            binding.positiveBtnStyle2.text = it
            binding.positiveBtnStyle2.visibility = View.VISIBLE
          }
          else -> {
            binding.positiveBtnStyle1.text = it
            binding.positiveBtnStyle1.visibility = View.VISIBLE
          }
        }
      } ?: run {
        positiveButtonResId?.let {
          when (style) {
            STYLE_1_HORIZONTAL_BUTTONS -> {
              binding.positiveBtnStyle1.text = resources.getString(it)
              binding.positiveBtnStyle1.visibility = View.VISIBLE
            }
            STYLE_2_VERTICAL_BUTTONS -> {
              binding.positiveBtnStyle2.text = resources.getString(it)
              binding.positiveBtnStyle2.visibility = View.VISIBLE
            }
            else -> {
              binding.positiveBtnStyle1.text = resources.getString(it)
              binding.positiveBtnStyle1.visibility = View.VISIBLE
            }
          }
        } ?: run {
          binding.positiveBtnStyle1.visibility = View.GONE
          binding.positiveBtnStyle2.visibility = View.GONE
        }
      }
    }

    private fun setUpPositiveButtonTextColor() {
      positiveButtonTextColorResId?.let {
        when (style) {
          STYLE_1_HORIZONTAL_BUTTONS -> {
            binding.positiveBtnStyle1.setTextColor(
              ContextCompat.getColor(
                requireContext(),
                it
              )
            )
          }
          STYLE_2_VERTICAL_BUTTONS -> {
            binding.positiveBtnStyle2.setTextColor(
              ContextCompat.getColor(
                requireContext(),
                it
              )
            )
          }
          else -> {
            binding.positiveBtnStyle1.setTextColor(
              ContextCompat.getColor(
                requireContext(),
                it
              )
            )
          }
        }
      } ?: run {
        positiveButtonTextColor?.let {
          when (style) {
            STYLE_1_HORIZONTAL_BUTTONS -> {
              binding.positiveBtnStyle1.setTextColor(it)
            }
            STYLE_2_VERTICAL_BUTTONS -> {
              binding.positiveBtnStyle2.setTextColor(it)
            }
            else -> {
              binding.positiveBtnStyle1.setTextColor(it)
            }
          }
        }
      }
    }

    private fun setUpPositiveButtonBackground() {
      positiveButtonBackground?.let {
        when (style) {
          STYLE_1_HORIZONTAL_BUTTONS -> {
            binding.positiveBtnStyle1.background = ContextCompat.getDrawable(requireContext(), it)
          }
          STYLE_2_VERTICAL_BUTTONS -> {
            binding.positiveBtnStyle2.background = ContextCompat.getDrawable(requireContext(), it)
          }
          else -> {
            binding.positiveBtnStyle1.background = ContextCompat.getDrawable(requireContext(), it)
          }
        }
      }
    }

    private fun setUpNegativeButton() {
      negativeButton?.let {
        when (style) {
          STYLE_1_HORIZONTAL_BUTTONS -> {
            binding.negativeBtnStyle1.text = it
            binding.negativeBtnStyle1.visibility = View.VISIBLE
          }
          STYLE_2_VERTICAL_BUTTONS -> {
            binding.negativeBtnStyle2.text = it
            binding.negativeBtnStyle2.visibility = View.VISIBLE
          }
          else -> {
            binding.negativeBtnStyle1.text = it
            binding.negativeBtnStyle1.visibility = View.VISIBLE
          }
        }
      } ?: run {
        negativeButtonResId?.let {
          when (style) {
            STYLE_1_HORIZONTAL_BUTTONS -> {
              binding.negativeBtnStyle1.text = resources.getString(it)
              binding.negativeBtnStyle1.visibility = View.VISIBLE
            }
            STYLE_2_VERTICAL_BUTTONS -> {
              binding.negativeBtnStyle2.text = resources.getString(it)
              binding.negativeBtnStyle2.visibility = View.VISIBLE
            }
            else -> {
              binding.negativeBtnStyle1.text = resources.getString(it)
              binding.negativeBtnStyle1.visibility = View.VISIBLE
            }
          }
        } ?: run {
          binding.negativeBtnStyle1.visibility = View.GONE
          binding.negativeBtnStyle2.visibility = View.GONE
        }
      }
    }

    private fun setUpNegativeButtonTextColor() {
      negativeButtonTextColorResId?.let {
        when (style) {
          STYLE_1_HORIZONTAL_BUTTONS -> {
            binding.negativeBtnStyle1.setTextColor(
              ContextCompat.getColor(
                requireContext(),
                it
              )
            )
          }
          STYLE_2_VERTICAL_BUTTONS -> {
            binding.negativeBtnStyle2.setTextColor(
              ContextCompat.getColor(
                requireContext(),
                it
              )
            )
          }
          else -> {
            binding.negativeBtnStyle1.setTextColor(
              ContextCompat.getColor(
                requireContext(),
                it
              )
            )
          }
        }
      } ?: run {
        negativeButtonTextColor?.let {
          when (style) {
            STYLE_1_HORIZONTAL_BUTTONS -> {
              binding.negativeBtnStyle1.setTextColor(it)
            }
            STYLE_2_VERTICAL_BUTTONS -> {
              binding.negativeBtnStyle2.setTextColor(it)
            }
            else -> {
              binding.negativeBtnStyle1.setTextColor(it)
            }
          }
        }
      }
    }

    private fun setUpNegativeButtonBackground() {
      negativeButtonBackground?.let {
        when (style) {
          STYLE_1_HORIZONTAL_BUTTONS -> {
            binding.negativeBtnStyle1.background = ContextCompat.getDrawable(requireContext(), it)
          }
          STYLE_2_VERTICAL_BUTTONS -> {
            binding.negativeBtnStyle2.background = ContextCompat.getDrawable(requireContext(), it)
          }
          else -> {
            binding.negativeBtnStyle1.background = ContextCompat.getDrawable(requireContext(), it)
          }
        }
      }
    }

    private fun handleClickListeners() {
      binding.negativeBtnStyle1.setOnClickListener {
        negativeButtonClick?.invoke(this) ?: dismiss()
      }

      binding.positiveBtnStyle1.setOnClickListener {
        positiveButtonClick?.invoke(this)
      }

      binding.negativeBtnStyle2.setOnClickListener {
        negativeButtonClick?.invoke(this) ?: dismiss()
      }

      binding.positiveBtnStyle2.setOnClickListener {
        positiveButtonClick?.invoke(this)
      }
    }

    fun setStyle(style: Style?) {
      this.style = style
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

    fun setImage(image: Drawable?) {
      this.image = image
    }

    fun setImage(@DrawableRes imageResId: Int?) {
      this.imageResId = imageResId
    }

    fun setContentColorResource(@ColorRes contentColorResId: Int?) {
      this.contentColorResId = contentColorResId
    }

    fun setContentColor(contentColor: Int?) {
      this.contentColor = contentColor
    }

    fun setPositiveButton(positiveButton: String?, positiveButtonClick: ((popUp: DialogFragment) -> Unit)?) {
      this.positiveButton = positiveButton
      this.positiveButtonClick = positiveButtonClick
    }

    fun setPositiveButton(
      @StringRes positiveButtonResId: Int?,
      positiveButtonClick: ((popUp: DialogFragment) -> Unit)?
    ) {
      this.positiveButtonResId = positiveButtonResId
      this.positiveButtonClick = positiveButtonClick
    }

    fun setPositiveButtonBackground(@DrawableRes positiveButtonBackground: Int?) {
      this.positiveButtonBackground = positiveButtonBackground
    }

    fun setPositiveButtonTextColorResource(@ColorRes positiveButtonTextColorResId: Int?) {
      this.positiveButtonTextColorResId = positiveButtonTextColorResId
    }

    fun setPositiveButtonTextColor(positiveButtonTextColor: Int?) {
      this.positiveButtonTextColor = positiveButtonTextColor
    }

    fun setUpNegativeButton(negativeButton: String?, negativeButtonClick: ((popUp: DialogFragment) -> Unit)?) {
      this.negativeButton = negativeButton
      this.negativeButtonClick = negativeButtonClick
    }

    fun setUpNegativeButton(
      @StringRes negativeButtonResId: Int?,
      negativeButtonClick: ((popUp: DialogFragment) -> Unit)?
    ) {
      this.negativeButtonResId = negativeButtonResId
      this.negativeButtonClick = negativeButtonClick
    }

    fun setNegativeButtonBackground(@DrawableRes negativeButtonBackground: Int?) {
      this.negativeButtonBackground = negativeButtonBackground
    }

    fun setNegativeButtonTextColorResource(@ColorRes negativeButtonTextColorResId: Int?) {
      this.negativeButtonTextColorResId = negativeButtonTextColorResId
    }

    fun setNegativeButtonTextColor(negativeButtonTextColor: Int?) {
      this.negativeButtonTextColor = negativeButtonTextColor
    }
  }

  class Builder(internal var fragmentManager: FragmentManager) {

    // Pop Up Style
    internal var style: Style? = null

    // Title
    internal var title: String? = null
    internal var titleResId: Int? = null

    // Title Color
    internal var titleColorResId: Int? = null
    internal var titleColor: Int? = null

    // Image
    internal var image: Drawable? = null
    internal var imageResId: Int? = null

    // Content
    internal var content: String? = null
    internal var contentResId: Int? = null

    // Content Color
    internal var contentColorResId: Int? = null
    internal var contentColor: Int? = null

    // Positive Button
    internal var positiveButton: String? = null
    internal var positiveButtonResId: Int? = null

    // Positive Button Background
    internal var positiveButtonBackground: Int? = null

    // Positive Button Text Color
    internal var positiveButtonTextColorResId: Int? = null
    internal var positiveButtonTextColor: Int? = null

    // Positive Button Click
    internal var positiveButtonClick: ((popUp: DialogFragment) -> Unit)? = null

    // Negative Button
    internal var negativeButton: String? = null
    internal var negativeButtonResId: Int? = null

    // Negative Button Background
    internal var negativeButtonBackground: Int? = null

    // Negative Button Text Color
    internal var negativeButtonTextColorResId: Int? = null
    internal var negativeButtonTextColor: Int? = null

    // Negative Button Click
    internal var negativeButtonClick: ((popUp: DialogFragment) -> Unit)? = null

    fun setStyle(style: Style?): Builder {
      this.style = style!!
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

    fun setImage(image: Drawable?): Builder {
      this.image = image!!
      return this
    }

    fun setImage(@DrawableRes imageResId: Int): Builder {
      this.imageResId = imageResId
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

    fun setPositiveButton(
      positiveButton: String?,
      positiveButtonClick: ((popUp: DialogFragment) -> Unit)?
    ): Builder {
      this.positiveButton = positiveButton!!
      this.positiveButtonClick = positiveButtonClick
      return this
    }

    fun setPositiveButton(
      @StringRes positiveButtonResId: Int,
      positiveButtonClick: ((popUp: DialogFragment) -> Unit)?
    ): Builder {
      this.positiveButtonResId = positiveButtonResId
      this.positiveButtonClick = positiveButtonClick
      return this
    }

    fun setPositiveButtonBackground(
      @DrawableRes positiveButtonBackground: Int
    ): Builder {
      this.positiveButtonBackground = positiveButtonBackground
      return this
    }

    fun setPositiveButtonTextColorResource(
      @ColorRes positiveButtonTextColorResId: Int
    ): Builder {
      this.positiveButtonTextColorResId = positiveButtonTextColorResId
      return this
    }

    fun setPositiveButtonTextColor(positiveButtonTextColor: Int): Builder {
      this.positiveButtonTextColor = positiveButtonTextColor
      return this
    }

    fun setNegativeButton(
      negativeButton: String?,
      negativeButtonClick: ((popUp: DialogFragment) -> Unit)?
    ): Builder {
      this.negativeButton = negativeButton!!
      this.negativeButtonClick = negativeButtonClick
      return this
    }

    fun setNegativeButton(
      @StringRes negativeButtonResId: Int,
      negativeButtonClick: ((popUp: DialogFragment) -> Unit)?
    ): Builder {
      this.negativeButtonResId = negativeButtonResId
      this.negativeButtonClick = negativeButtonClick
      return this
    }

    fun setNegativeButtonBackground(
      @DrawableRes negativeButtonBackground: Int
    ): Builder {
      this.negativeButtonBackground = negativeButtonBackground
      return this
    }

    fun setNegativeButtonTextColorResource(
      @ColorRes negativeButtonTextColorResId: Int
    ): Builder {
      this.negativeButtonTextColorResId = negativeButtonTextColorResId
      return this
    }

    fun setNegativeButtonTextColor(negativeButtonTextColor: Int): Builder {
      this.negativeButtonTextColor = negativeButtonTextColor
      return this
    }

    fun create(): PrettyPopUpHelper {
      return PrettyPopUpHelper(this)
    }
  }
}