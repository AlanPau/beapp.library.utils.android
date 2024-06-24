package beapp.utils.system

import android.content.Context
import android.content.SharedPreferences

/**
 * Preference storage
 *
 * @constructor Create empty Preference storage
 */
class PreferenceStorage {
	companion object {
		private lateinit var sharedPreferences: SharedPreferences

		/**
		 * Init shared preferences
		 *
		 * @param context
		 * @param preferenceName
		 */
		internal fun init(context: Context, preferenceName: String) {
			sharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
		}

		/**
		 * Put string in shared preferences
		 *
		 * @param key
		 * @param value
		 */
		fun putString(key: String, value: String?) {
			sharedPreferences.edit()
				.putString(key, value)
				.apply()
		}

		/**
		 * Get string from shared preferences
		 *
		 * @param key
		 * @return value
		 */
		fun getString(key: String): String? {
			return sharedPreferences.getString(key, null)
		}

		/**
		 * Put boolean in shared preferences
		 *
		 * @param key
		 * @param value
		 */
		fun putBoolean(key: String, value: Boolean) {
			sharedPreferences.edit()
				.putBoolean(key, value)
				.apply()
		}

		/**
		 * Get boolean from shared preferences
		 *
		 * @param key
		 * @param defaultValue - if key does not exist
		 * @return value
		 */
		fun getBoolean(key: String, defaultValue: Boolean): Boolean {
			return sharedPreferences.getBoolean(key, defaultValue)
		}

		/**
		 * Put string set in shared preferences
		 *
		 * @param key
		 * @param values
		 */
		fun putStringSet(key: String, values: Set<String>) {
			return sharedPreferences.edit()
				.putStringSet(key, values)
				.apply()
		}

		/**
		 * Get string set from shared preferences
		 *
		 * @param key
		 * @return value
		 */
		fun getStringSet(key: String): Set<String>? {
			return sharedPreferences.getStringSet(key, null)
		}

		/**
		 * Add string to existing string set in shared preferences
		 *
		 * @param key
		 * @param value
		 */
		fun addToStringSet(key: String, value: String) {
			putStringSet(key, (getStringSet(key) ?: setOf()).plus(value))
		}
	}
}