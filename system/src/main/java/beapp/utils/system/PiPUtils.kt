package beapp.utils.system

import android.app.Activity
import android.app.PictureInPictureParams
import android.os.Build
import android.util.Rational
import androidx.annotation.RequiresApi

object PiPUtils {
	/**
	 * Build default picture in picture params
	 *
	 * @param portraitMode
	 * @return params
	 */
	@RequiresApi(Build.VERSION_CODES.O)
	private fun buildDefaultParams(portraitMode: Boolean): PictureInPictureParams {
		return PictureInPictureParams.Builder()
			.setAspectRatio(
				if (portraitMode) Rational(9, 16)
				else Rational(16, 9)
			)
			.build()
	}

	/**
	 * Enter picture in picture mode
	 * Only possible for Android SdK >= 26
	 *
	 * @param activity
	 * @param portraitMode
	 * @return true if picture in picture mode entered, false if not
	 */
	fun enterPiPMode(activity: Activity, portraitMode: Boolean): Boolean {
		return if (isSdk26OrAbove()) {
			activity.enterPictureInPictureMode(buildDefaultParams(portraitMode))
			true
		} else false
	}
}