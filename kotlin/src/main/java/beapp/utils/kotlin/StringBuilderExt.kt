package beapp.utils.kotlin

/**
 * Replace a part of a string with a new one
 *
 * @param target part of the string which need to be replaced
 * @param replacement char-sequence which will be replace the previous one
 */
fun StringBuilder.replace(target: CharSequence, replacement: CharSequence) {
	if (isEmpty()) {
		return
	}
	require(target.isNotEmpty()) { "Target can't be empty" }

	val newLenHint = length - target.length + replacement.length
	if (newLenHint < 0) {
		throw OutOfMemoryError()
	}

	val tgtStr = target.toString()

	var index = indexOf(tgtStr)
	while (index != -1) {
		replace(index, index + tgtStr.length, replacement.toString())
		index = index.plus(replacement.length) // Move to the end of the replacement
		index = indexOf(tgtStr, index)
	}
}