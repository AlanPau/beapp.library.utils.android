package beapp.utils.system

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

/**
 * Open browser
 *
 * @param context
 * @param url
 */
fun openBrowser(context: Context, url: String) {
	val uri: Uri = Uri.parse(url)
	val intent = Intent(Intent.ACTION_VIEW, uri)
	context.startActivity(intent)
}

/**
 * Restart application
 *
 * @param context
 */
fun restartApplication(context: Context) {
	val componentName = context.packageManager.getLaunchIntentForPackage(context.packageName)?.component ?: return
	val mainIntent = Intent.makeRestartActivityTask(componentName)
	context.safeLaunchIntent(mainIntent)
	Runtime.getRuntime().exit(0)
}

/**
 * Open app notification settings
 *
 * @param context
 */
fun openAppNotificationSettings(context: Context) {
	val intent = if (isSdk26OrAbove()) Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
		.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
		.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
	else Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
		.addCategory(Intent.CATEGORY_DEFAULT)
		.setData(Uri.parse("package:${context.packageName}"))
	context.safeLaunchIntent(intent)
}
