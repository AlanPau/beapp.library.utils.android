package beapp.utils.system

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import fr.beapp.logger.Logger

/**
 * Open app settings
 *
 * @param context
 */
fun openAppSettings(context: Context) {
	try {
		val notificationSettingsIntent = Intent()
		notificationSettingsIntent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
		val uri = Uri.fromParts("package", context.packageName, null)
		notificationSettingsIntent.data = uri
		context.startActivity(notificationSettingsIntent)
	} catch (e: Exception) {
		Logger.warn("Can't open notification app settings : %s", e.message)
	}
}