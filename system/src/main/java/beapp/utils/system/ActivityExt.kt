package beapp.utils.system

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat

/**
 * Force portrait for mobile
 *
 */
@SuppressLint("SourceLockedOrientationActivity")
fun Activity.forcePortraitForMobile() {
	if (!resources.isTablet()) requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}

/**
 * Hide keyboard
 *
 */
fun Activity.hideKeyboard() {
	val imm: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
	// Find the currently focused view, so we can grab the correct window token from it.
	var view: View? = currentFocus
	// If no view currently has focus, create a new one, just so we can grab a window token from it
	if (view == null) view = View(this)
	imm.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * Show keyboard
 *
 */
fun Activity.showKeyboard() {
	val view = currentFocus
	if (view != null) {
		val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.showSoftInput(view, 0)
	}
}

/**
 * Check if permission is denied
 *
 * @param permission
 * @return true if is denied, false otherwise
 */
fun Activity.isPermissionDenied(permission: String): Boolean = !ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

/**
 * Check if permissions are denied
 *
 * @param permissions
 * @return true if one or more is denied, false if none are denied
 */
fun Activity.arePermissionsDenied(vararg permissions: String): Boolean = permissions.all { isPermissionDenied(it) }
