package beapp.utils.system

import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory

/**
 * Is tablet
 *
 * @return true if device is a tablet, false if not
 */
fun Resources.isTablet(): Boolean = getBoolean(R.bool.isTablet)

/**
 * Transform Dp size to px
 *
 * @param dp
 * @return size in px
 */
fun Resources.dpToPx(dp: Int): Int = (dp * displayMetrics.density).toInt()

/**
 * Is dark mode on
 */
internal val Resources.isDarkModeOn
	get() = (this.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

/**
 * Rounded bitmap
 *
 * @param drawableId
 * @param radiusId
 * @return
 */
fun Resources.roundedBitmap(@DrawableRes drawableId: Int, @DimenRes radiusId: Int): Drawable {
	val radius = getDimension(radiusId)
	val bitmap = BitmapFactory.decodeResource(this, drawableId)
	val drawable = RoundedBitmapDrawableFactory.create(this, bitmap)
	drawable.cornerRadius = radius
	return drawable
}

/**
 * Check if orientation is landscape
 *
 * @return true if orientation is landscape, false otherwise
 */
fun Resources.isOrientationLandscape() = this.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE