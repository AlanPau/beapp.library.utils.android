package beapp.utils.system

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import fr.beapp.utils.system.R
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

enum class DateFormat(val pattern: String) {
	TIME("HH'h'mm"), DATE("dd MMMM yyyy"), DATE_MINIFY_MONTH("dd MMM yyyy"), DATE_COMPACT("dd/MM/yy"), DATE_AND_TIME("dd/MM/yyyy à HH'h'mm"), FORMAT_EXTENDED_DATE_WITH_SLASHES("dd/MM/yyyy")
}

/**
 * Is today
 *
 * @return true if date is today
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Instant.isToday() = atZone(ZoneId.systemDefault()).toLocalDate() == LocalDate.now()

/**
 * Is yesterday
 *
 * @return true if date is yesterday
 */
@RequiresApi(Build.VERSION_CODES.O)
private fun Instant.isYesterday() = atZone(ZoneId.systemDefault()).toLocalDate() == LocalDate.now().minusDays(1)

/**
 * Format date to pattern
 *
 * @param format
 * @return formatted date
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Instant.toFormat(format: DateFormat): String = format(format.pattern)

/**
 * To label
 *
 * @param context
 * @param format
 * @param labelId
 * @return
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Instant.toLabel(
	context: Context, format: DateFormat, @StringRes labelId: Int? = null
): String {
	val since = Duration.between(this, Instant.now())
	val minutes = since.toMinutes()
	val hours = since.toHours()

	val durationLabel = when {
		minutes < 60 -> context.resources.getQuantityString(R.plurals.date_format_min, minutes.toInt(), minutes)
		hours < 24 -> context.resources.getQuantityString(R.plurals.date_format_hour, hours.toInt(), hours)
		else -> null
	}
	val dateLabel = when {
		isYesterday() -> context.getString(R.string.yesterday)
		minutes <= 1 -> context.getString(R.string.date_format_now)
		durationLabel != null -> context.getString(R.string.date_format_ago, durationLabel)
		else -> format(format.pattern)
	}
	return (if (labelId != null) context.getString(labelId, dateLabel) else dateLabel)
		.replaceFirstChar {
			if (it.isLowerCase()) it.titlecase(Locale.getDefault())
			else it.toString()
		}
}

/**
 * Format date to pattern in French locale
 *
 * @param pattern
 * @return formatted date
 */
@RequiresApi(Build.VERSION_CODES.O)
private fun Instant.format(pattern: String): String = DateTimeFormatter.ofPattern(pattern).withLocale(Locale.FRENCH).withZone(ZoneId.systemDefault()).format(this)