package beapp.utils.androidXMLView

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Rect
import android.graphics.drawable.RippleDrawable
import android.view.TouchDelegate
import android.view.View
import androidx.core.content.ContextCompat
import beapp.utils.system.dpToPx

/**
 * Set ripple effect to View
 *
 * @param radiusTopLeft
 * @param radiusTopRight
 * @param radiusBottomRight
 * @param radiusBottomLeft
 * @param color
 * @param context
 */
fun View.setRippleEffect(
	radiusTopLeft: Int = 0,
	radiusTopRight: Int = 0,
	radiusBottomRight: Int = 0,
	radiusBottomLeft: Int = 0,
	color: Int,
	context: Context
) {
	val rippleColor = ContextCompat.getColor(context, color)
	val pressedColor = ColorStateList.valueOf(rippleColor)
	val rippleDrawable = getRippleDrawable(rippleColor, radiusTopLeft, radiusTopRight, radiusBottomRight, radiusBottomLeft)
	val ripple = RippleDrawable(pressedColor, null, rippleDrawable)
	foreground = ripple
}

/**
 * Build an accessible clickable zone
 *
 */
fun View.buildAccessibleClickableZone() {
	post {
		val minimumSize = context.resources.dpToPx(24)
		val rect = Rect()
		getHitRect(rect)
		val left = paddingLeft + width / 2 - minimumSize
		if (left < 0) rect.left += left
		val right = paddingRight + width / 2 - minimumSize
		if (right < 0) rect.right -= right
		val top = paddingTop + height / 2 - minimumSize
		if (top < 0) rect.top += top
		val bottom = paddingBottom + height / 2 - minimumSize
		if (bottom < 0) rect.bottom -= bottom
		(parent as? View)?.touchDelegate = TouchDelegate(rect, this)
	}
}