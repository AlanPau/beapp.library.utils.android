package beapp.utils.androidXMLView

import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape

/**
 * Get ripple drawable
 *
 * @param color
 * @param radiusTopLeft
 * @param radiusTopRight
 * @param radiusBottomRight
 * @param radiusBottomLeft
 * @return drawable with ripple
 */
internal fun getRippleDrawable(color: Int, radiusTopLeft: Int, radiusTopRight: Int, radiusBottomRight: Int, radiusBottomLeft: Int): Drawable {
	val floatArray = floatArrayOf(
		radiusTopLeft.toFloat(),
		radiusTopLeft.toFloat(),
		radiusTopRight.toFloat(),
		radiusTopRight.toFloat(),
		radiusBottomRight.toFloat(),
		radiusBottomRight.toFloat(),
		radiusBottomLeft.toFloat(),
		radiusBottomLeft.toFloat()
	)
	val shapeDrawable = ShapeDrawable(RoundRectShape(floatArray, null, null))
	shapeDrawable.paint.color = color
	return shapeDrawable
}