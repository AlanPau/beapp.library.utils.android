package beapp.utils.system

import android.accessibilityservice.AccessibilityServiceInfo
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.UiModeManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.accessibility.AccessibilityManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import fr.beapp.logger.BuildConfig
import fr.beapp.logger.Logger
import fr.beapp.utils.system.R

/**
 * Launch intent
 *
 * @param intent
 * @return true if intent launched, false if error
 */
fun Context.safeLaunchIntent(intent: Intent): Boolean {
	(this as? Activity)?.hideKeyboard()
	return try {
		startActivity(intent)
		true
	} catch (exception: Exception) {
		Logger.error("Fail to start activity", exception)
		false
	}
}

/**
 * Launch intents
 *
 * @param intents
 */
fun Context.safeLaunchIntents(intents: List<Intent>) {
	(this as? Activity)?.hideKeyboard()
	try {
		startActivities(intents.filter { it.resolveActivity(packageManager) != null }.toTypedArray())
	} catch (exception: Exception) {
		Logger.error("Fail to start activity", exception)
	}
}

/**
 * Check if talk back is on
 *
 * @return true if Talkback is on, false if not
 */
fun Context.isTalkBackOn(): Boolean {
	val am = getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager
	if (am != null && am.isEnabled) {
		val serviceInfoList =
			am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_SPOKEN)
		if (serviceInfoList.isNotEmpty())
			return true
	}
	return false
}

/**
 * Check if permission is granted
 *
 * @param permission
 * @return true if granted, false otherwise
 */
fun Context.hasPermission(permission: String): Boolean = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

/**
 * Check if permissions are granted
 *
 * @param permissions
 * @return true if all permissions are granted, false otherwise
 */
fun Context.hasPermissions(vararg permissions: String): Boolean = permissions.all { hasPermission(it) }

/**
 * Clear application data
 *
 */
fun Context.clearApplicationData() = getSystemService<ActivityManager>()?.clearApplicationUserData() ?: false

/**
 * Clear files from cache
 *
 */
fun Context.clearCacheAndFiles() = cacheDir.parentFile?.deleteRecursively() ?: false

/**
 * Start app or open play store
 *
 * @param packageName
 */
fun Context.startAppOrOpenPlayStore(packageName: String) {
	try {
		val intent = packageManager.getLaunchIntentForPackage(packageName) ?: throw IllegalArgumentException("No launch intent found for $packageName")
		startActivity(intent)
	} catch (e: Exception) {
		openPlayStore(packageName)
	}
}

/**
 * Open play store
 *
 * @param packageName
 */
fun Context.openPlayStore(packageName: String) {
	try {
		startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
	} catch (e: ActivityNotFoundException) {
		startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
	}
}

/**
 * Open as modal
 * Deprecated
 */
fun Context.openAsModal() {
	(this as? Activity)?.overridePendingTransition(R.anim.slide_in_to_top, android.R.anim.fade_out)
}

/**
 * Launch share link intent
 *
 * @param url
 */
fun Context.shareLink(url: String) {
	val sendIntent: Intent = Intent().apply {
		action = Intent.ACTION_SEND
		putExtra(Intent.EXTRA_TEXT, url)
		type = "text/plain"
	}

	val shareIntent = Intent.createChooser(sendIntent, null)
	startActivity(shareIntent)
}

/**
 * Build web intent
 *
 * @param url
 * @return intent
 */
fun Context.buildWebIntent(url: String): Intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
	addCategory(Intent.CATEGORY_BROWSABLE)
	if (this@buildWebIntent !is Activity) addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

}

/**
 * Launch share app intent
 *
 */
fun Context.shareApp() {
	val sendIntent = Intent().apply {
		action = Intent.ACTION_SEND
		putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app_message) + BuildConfig.APPLICATION_ID)
		flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
		type = "text/plain"
	}
	startActivity(Intent.createChooser(sendIntent, getString(R.string.share_app_with)))
}

/**
 * Vibrate
 *
 */
@SuppressLint("MissingPermission")
fun Context.vibrate() {
	ContextCompat.getSystemService(this, Vibrator::class.java)?.let {
		if (isSdk26OrAbove()) {
			it.vibrate(VibrationEffect.createOneShot(10, if (isSdk29OrAbove()) VibrationEffect.EFFECT_TICK else VibrationEffect.DEFAULT_AMPLITUDE))
		} else it.vibrate(10)

	}
}

/**
 * Open PDF
 *
 * @param url
 */
fun Context.openPDF(url: String) {

	val browserIntent = Intent(Intent.ACTION_VIEW).apply {
		setDataAndType(Uri.parse(url), "application/pdf")
	}

	val chooser = Intent.createChooser(browserIntent, null)
	chooser.flags = Intent.FLAG_ACTIVITY_NEW_TASK
	startActivity(chooser)
}

/**
 * Set dark mode
 *
 */
fun Context.setDarkMode() {
	if (isSdk31OrAbove()) applicationContext.getSystemService(UiModeManager::class.java).setApplicationNightMode(UiModeManager.MODE_NIGHT_YES)
	else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
}

/**
 * Set light mode
 *
 */
fun Context.setLightMode() {
	if (isSdk31OrAbove()) applicationContext.getSystemService(UiModeManager::class.java).setApplicationNightMode(UiModeManager.MODE_NIGHT_NO)
	else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
}

/**
 * Set system mode (light or dark theme)
 *
 */
fun Context.setSystemMode() {
	if (isSdk31OrAbove()) applicationContext.getSystemService(UiModeManager::class.java).setApplicationNightMode(UiModeManager.MODE_NIGHT_AUTO)
	else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
}

/**
 * Check if in dark mode
 *
 * @return true if in dark mode, false otherwise
 */
fun Context.isInDarkMode(): Boolean {
	if (resources.isDarkModeOn) return true
	return if (isSdk31OrAbove()) applicationContext.getSystemService(UiModeManager::class.java).nightMode == UiModeManager.MODE_NIGHT_YES
	else AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
}

/**
 * Launch mail intent to compose email
 *
 * @param recipients
 * @param subject
 * @param content
 * @param attachments
 */
fun Context.launchMail(recipients: Array<String>?, subject: String?, content: String?, vararg attachments: Uri) {
	val chooserIntent = Intent(Intent.ACTION_SENDTO).apply {
		data = Uri.parse("mailto:")
	}

	val emailIntent = Intent().apply {
		putExtra(Intent.EXTRA_EMAIL, recipients)
		putExtra(Intent.EXTRA_SUBJECT, subject)
		putExtra(Intent.EXTRA_TEXT, content)
		action = when (attachments.size) {
			0 -> {
				Intent.ACTION_SENDTO
			}

			1 -> {
				putExtra(Intent.EXTRA_STREAM, attachments.first())
				flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
				Intent.ACTION_SEND
			}

			else -> {
				putParcelableArrayListExtra(Intent.EXTRA_STREAM, ArrayList(attachments.toList()))
				flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
				Intent.ACTION_SEND_MULTIPLE
			}
		}
		selector = chooserIntent
	}

	try {
		startActivity(Intent.createChooser(emailIntent, null))
	} catch (ex: ActivityNotFoundException) {
		Logger.error("No application available to open intent", ex)
	}
}

/**
 * Delete cache
 *
 * @return
 */
fun Context.deleteCache(): Boolean = this.applicationContext.cacheDir.deleteRecursively()