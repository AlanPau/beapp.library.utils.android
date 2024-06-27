package beapp.utils.system

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import fr.beapp.utils.system.R

/**
 * Copy to clipboard and show copied text in toast
 *
 * @param context
 * @param text
 */
fun copyToClipboard(context: Context, text: String) {
	val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
	val clip = ClipData.newPlainText("text", text)
	clipboard.setPrimaryClip(clip)
	Toast.makeText(context, context.getString(R.string.copied_text), Toast.LENGTH_SHORT).show()
}