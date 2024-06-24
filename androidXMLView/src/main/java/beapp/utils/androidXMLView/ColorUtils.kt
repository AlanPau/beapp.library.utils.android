package beapp.utils.androidXMLView

import android.graphics.Color
import fr.beapp.logger.Logger

/**
 * Parse string as color
 *
 * @param colorCode
 * @return color if parse OK, null if error
 */
fun safeColorParse(colorCode: String?): Int? = try {
	Color.parseColor(colorCode)
} catch (e: Exception) {
	Logger.warn("Fail to parse color $colorCode", e)
	null
}