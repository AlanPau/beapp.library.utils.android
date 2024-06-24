package beapp.utils.compose

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.os.PowerManager
import android.provider.Settings

/**
 * Find activity
 *
 * @return
 */
fun Context.findActivity(): Activity? = when (this) {
	is Activity -> this
	is ContextWrapper -> baseContext.findActivity()
	else -> null
}

/**
 * Power manger
 */
private val Context.powerManager: PowerManager?
	get() = findActivity()?.getSystemService(Context.POWER_SERVICE) as? PowerManager

/**
 * Check if context ignores battery optimizations
 *
 * @return true if ignores, false if not
 */
val Context.ignoresBatteryOptimizations: Boolean
	get() {
		return powerManager?.isIgnoringBatteryOptimizations(this.packageName) ?: true
	}

/**
 * Build ignore battery optimization intent
 *
 * @return intent
 */
fun Context.buildIgnoreBatteryOptimizationIntent(): Intent? {
	powerManager?.let {
		return Intent().apply {
			action = if (ignoresBatteryOptimizations) Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS
			else Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
			data = Uri.parse("package:$packageName")
		}

	} ?: return null
}