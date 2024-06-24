package beapp.utils.kotlin

import java.text.Normalizer
import java.util.Locale

/**
 * Capitalize string
 *
 * @return string with first letter capitalized
 */
fun String.capitalize(): String {
	val locale = Locale.getDefault()
	return lowercase(locale).replaceFirstChar { it.titlecase(locale) }
}

/**
 * Remove accents
 *
 * @return string without accents
 */
fun String.removeAccents() = Normalizer.normalize(this, Normalizer.Form.NFD).replace("\\p{Mn}+".toRegex(), "")

/**
 * Clear white space
 *
 * @return string with no with space
 */
fun String.clearWhiteSpace(): String = replace("\\s".toRegex(), "")

/**
 * Is numeric
 *
 * @return true if string can be formatted to a number, false otherwise
 */
fun String.isNumeric(): Boolean {
	return try {
		toInt()
		true
	} catch (ex: NumberFormatException) {
		false
	}
}

/**
 * Has a lowercase
 *
 * @return true if string contains lowercase, false if not
 */
fun String.hasALowercase() = InputUtils.lowerCasePattern.containsMatchIn(this)

/**
 * Has a number
 *
 * @return true if string contains a number, false if not
 */
fun String.hasANumber() = InputUtils.numberPattern.containsMatchIn(this)

/**
 * Has no space
 *
 * @return true if string contains no whitespace, false if it does
 */
fun String.hasNoSpace() = InputUtils.noSpacePattern.containsMatchIn(this).not()

/**
 * Has a capital letter
 *
 * @return true if string contains a capital letter, false if not
 */
fun String.hasACapital() = InputUtils.capitalPattern.containsMatchIn(this)