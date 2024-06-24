package beapp.utils.kotlin

object InputUtils {

	/**
	 * Email address pattern
	 */
	private val emailAddressRegex = Regex(
		"[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
				"\\@" +
				"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
				"(" +
				"\\." +
				"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
				")+"
	)
	internal val capitalPattern = Regex("[A-Z]")
	internal val numberPattern = Regex("[0-9]")
	internal val lowerCasePattern = Regex("[a-z]")
	internal val noSpacePattern = Regex("(?=\\s).")

	/**
	 * Validate phone
	 * check if a phone number is valid without whitespaces, called in validatePhoneWithWhiteSpaces
	 * @param phone
	 * @return
	 */
	private fun validatePhone(phone: String): Boolean = phone.clearWhiteSpace().isNumeric()
			&& (phone.startsWith(Constants.PHONE_PREFIX_06) || phone.startsWith(Constants.PHONE_PREFIX_07))

	/**
	 * Validate phone with white spaces
	 * check if a phone number is valid with whitespaces (refers to PHONE_MAX_LENGTH_WITH_SPACES)
	 * @param phone
	 * @return
	 */
	fun validatePhoneWithWhiteSpaces(phone: String): Boolean {
		return phone.length == Constants.PHONE_MAX_LENGTH_WITH_SPACES && validatePhone(phone)
	}

	/**
	 * Validate email
	 * check if the mail is valid (based on Kotlin regex)
	 * @param email
	 * @return
	 */
	fun validateEmail(email: String): Boolean {
		return email.matches(emailAddressRegex)
	}

	/**
	 * Validate sms code
	 * check if the sms code is valid
	 * @param code
	 * @param lengthCode by default is 6
	 * @return
	 */
	fun validateSmsCode(code: String, lengthCode: Int = 6): Boolean {
		return code.length == lengthCode && code.isNumeric()
	}

	/**
	 * Format phone
	 * changes the user input to format a phone number with spaces ("0666666666" becomes "06 66 66 66 66" for example)
	 * if the format is incorrect, return null
	 * @param phone
	 */
	fun formatPhone(phone: String): String? {
		return if (phone.clearWhiteSpace().isNumeric())
			phone.clearWhiteSpace().chunked(2).joinToString(" ")
		else null
	}
}