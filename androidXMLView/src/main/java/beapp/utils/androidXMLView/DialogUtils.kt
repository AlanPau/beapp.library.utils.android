package beapp.utils.androidXMLView

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import beapp.utils.system.hideKeyboard
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

object DialogUtils {

	/**
	 * Show dialog with a positive button
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param buttonLabel
	 * @param onClose
	 */
	fun showDialog(
		context: Context,
		title: String,
		message: String,
		buttonLabel: String,
		onClose: (() -> Unit)? = null,
	) {
		MaterialAlertDialogBuilder(context)
			.setTitle(title)
			.setMessage(message)
			.setPositiveButton(buttonLabel) { _, _ -> onClose?.invoke() }
			.show()
	}

	/**
	 * Show dialog with a positive and a negative button
	 *
	 * @param context
	 * @param title
	 * @param message
	 * @param positiveButtonLabel
	 * @param negativeButtonLabel
	 * @param onConfirm
	 */
	fun showDialog(
		context: Context,
		title: String,
		message: String,
		positiveButtonLabel: String,
		negativeButtonLabel: String,
		onConfirm: (() -> Unit)? = null,
	) {
		MaterialAlertDialogBuilder(context)
			.setTitle(title)
			.setMessage(message)
			.setPositiveButton(positiveButtonLabel) { _, _ -> onConfirm?.invoke() }
			.setNegativeButton(negativeButtonLabel) { _, _ -> }
			.show()
	}

	/**
	 * Show message in snackbar
	 *
	 * @param activity
	 * @param view
	 * @param message
	 */
	fun showSnackbar(activity: Activity, view: View, message: String) {
		activity.hideKeyboard()
		Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
	}

	/**
	 * Show message in toast
	 *
	 * @param context
	 * @param message
	 */
	fun showToast(context: Context, message: String) {
		Toast.makeText(
			context,
			message,
			Toast.LENGTH_LONG
		).show()
	}
}