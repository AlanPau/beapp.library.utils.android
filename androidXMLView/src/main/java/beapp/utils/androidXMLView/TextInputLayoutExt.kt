package beapp.utils.androidXMLView

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout

object TextInputLayoutExt {
	const val REQUIRED_SUFFIX = "*"

	/**
	 * Mark text input as required
	 *
	 * @param requiredHint
	 * @param colorRes
	 */
	fun TextInputLayout.markAsRequired(@StringRes requiredHint: Int?, colorRes: Int) {
		val rawHint = hint ?: return
		if (requiredHint != null) contentDescription = "$rawHint. ${context.getString(requiredHint)}"
		hint = hint?.markAsRequired(context, colorRes)
	}

	/**
	 * Mark char sequence as required
	 *
	 * @param context
	 * @param colorRes
	 * @param requiredText
	 * @return styled as required Spannable
	 */
	private fun CharSequence.markAsRequired(context: Context, colorRes: Int, requiredText: String = REQUIRED_SUFFIX): Spannable {
		val requiredSpan = SpannableString(requiredText)
		requiredSpan.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, colorRes)), 0, REQUIRED_SUFFIX.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
		return SpannableStringBuilder(this)
			.append(requiredSpan)
	}
}