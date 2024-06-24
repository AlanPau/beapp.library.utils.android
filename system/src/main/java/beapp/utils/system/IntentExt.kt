package beapp.utils.system

import android.content.Intent
import android.os.Build
import android.os.Parcelable

/**
 * Get serializable extra
 *
 * @param T
 * @param name
 * @return
 */
@Suppress("DEPRECATION")
inline fun <reified T : java.io.Serializable> Intent.getSerializableExtraCompat(name: String): T? {
	return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
		getSerializableExtra(name, T::class.java)
	} else getSerializableExtra(name) as? T
}

/**
 * Get parcelable extra
 *
 * @param T
 * @param key
 * @return
 */
inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(key: String): T? =
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
		getParcelableExtra(key, T::class.java)
	} else {
		@Suppress("DEPRECATION") getParcelableExtra(key) as? T
	}

/**
 * Clear tasks
 *
 * @return intent
 */
fun Intent.clearTasks(): Intent = apply {
	flags = flags or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
}