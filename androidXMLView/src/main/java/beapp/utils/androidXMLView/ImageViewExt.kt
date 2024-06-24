package beapp.utils.androidXMLView

import android.net.Uri
import android.view.Gravity
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.google.android.material.color.MaterialColors
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

/**
 * Determine how to process the image to load depending on the image type (svg or standard)
 *
 * @param url
 * @param scaleType
 * @param cornerRadius
 * @param gravity
 * @param placeholderResId
 * @param placeholderErrorId
 */
fun ImageView.loadAndSetImage(
	url: String?,
	scaleType: ImageView.ScaleType = ImageView.ScaleType.FIT_CENTER,
	cornerRadius: Int? = null,
	gravity: Int? = null,
	placeholderResId: Int,
	placeholderErrorId: Int
) {

	/**
	 * Load svg image with Coil.
	 *
	 * @param url
	 */
	fun ImageView.loadAndSetSvgFromUrl(url: String) {
		val imageLoader = ImageLoader.Builder(context)
			.components {
				add(SvgDecoder.Factory())
			}
			.build()
		val request = ImageRequest.Builder(context)
			.data(Uri.decode(url))
			.target(this)
			.build()
		imageLoader.enqueue(request)
	}

	/**
	 * Load standard image with Picasso.
	 *
	 * @param url
	 * @param placeholderErrorResId
	 * @param scaleType
	 * @param cornerRadius
	 * @param gravity
	 * @param placeholderResId
	 */
	fun ImageView.loadAndSetImageFromUrl(
		url: String?,
		placeholderErrorResId: Int,
		scaleType: ImageView.ScaleType = ImageView.ScaleType.FIT_CENTER,
		cornerRadius: Int? = null,
		gravity: Int? = null,
		placeholderResId: Int
	) {
		if (url?.isBlank() != false) {
			setImageResource(placeholderErrorResId)
			return
		}
		var picassoBuilder = Picasso.get().load(url)

		picassoBuilder = when (scaleType) {
			ImageView.ScaleType.CENTER,
			ImageView.ScaleType.CENTER_CROP -> picassoBuilder.fit().centerCrop(gravity ?: Gravity.CENTER)

			ImageView.ScaleType.CENTER_INSIDE -> picassoBuilder.fit().centerInside()
			ImageView.ScaleType.FIT_END,
			ImageView.ScaleType.FIT_START,
			ImageView.ScaleType.FIT_XY,
			ImageView.ScaleType.MATRIX,
			ImageView.ScaleType.FIT_CENTER -> picassoBuilder
		}
		if (cornerRadius != null) picassoBuilder = picassoBuilder.transform(RoundedCornersTransformation(cornerRadius, 0))
		picassoBuilder = picassoBuilder.placeholder(placeholderResId)
		picassoBuilder = picassoBuilder.error(placeholderErrorResId)

		picassoBuilder.into(this, object : Callback {
			override fun onSuccess() = Unit

			override fun onError(e: Exception?) {
				MaterialColors.getColor(this@loadAndSetImageFromUrl, com.google.android.material.R.attr.colorOnBackground)
			}
		})
	}

	when {
		url?.endsWith(".svg") == true -> loadAndSetSvgFromUrl(url)
		else -> loadAndSetImageFromUrl(
			url,
			placeholderErrorResId = placeholderErrorId,
			scaleType = scaleType,
			cornerRadius = cornerRadius,
			gravity = gravity,
			placeholderResId = placeholderResId
		)
	}
}