package beapp.utils.system

import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

object PermissionUtils {

	/**
	 * Checks the permission passed as parameter.
	 * If not already granted, triggers the system permission request.
	 * A rationale dialog may be shown before the request if the system requires it.
	 */
	fun checkPermission(
		activity: Activity,
		permission: String,
		permissionMessage: String,
		permissionLauncher: ActivityResultLauncher<String>,
		onPermissionAlreadyGranted: () -> Unit,
	) {
		if (activity.hasPermission(permission)) {
			onPermissionAlreadyGranted()
		} else {
			if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
				AlertDialog.Builder(activity)
					.setMessage(permissionMessage)
					.setPositiveButton(android.R.string.ok) { dialog, _ ->
						dialog.dismiss()
					}
					.setOnDismissListener {
						permissionLauncher.launch(permission)
					}
					.create()
					.show()
			} else {
				permissionLauncher.launch(permission)
			}
		}
	}
}

/**
 * Get status of if permissions have been checked
 *
 * @param permissions
 * @return true if all permissions have been checked, false if one or more have not
 */
fun hasCheckedPermissions(vararg permissions: String): Boolean {
	return permissions.all { PreferenceStorage.getBoolean(it, false) }
}

/**
 * Set permission checked status in local storage
 *
 * @param permissions
 */
fun setCheckedPermissions(vararg permissions: String) {
	permissions.forEach { PreferenceStorage.putBoolean(it, true) }
}
