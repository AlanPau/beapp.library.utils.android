package beapp.utils.androidXMLView

import android.graphics.Rect
import android.view.View

object KeyboardUtils {
	var isKeyboardShowing = false

	/**
	 * Invoke keyboardShowing function depending on if keyboard is shown or not
	 *
	 * @param contentView
	 * @param keyboardShowing function
	 * @receiver
	 */
	fun listenToKeyboardIsShown(contentView: View, keyboardShowing: ((Boolean) -> Unit)) {
		contentView.viewTreeObserver.addOnGlobalLayoutListener {
			val r = Rect()
			contentView.getWindowVisibleDisplayFrame(r)
			val screenHeight = contentView.rootView.height;

			// r.bottom is the position above soft keypad or device button.
			// if keypad is shown, the r.bottom is smaller than that before.
			val keypadHeight = screenHeight - r.bottom;


			if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
				// keyboard is opened
				if (!isKeyboardShowing) {
					isKeyboardShowing = true
					keyboardShowing.invoke(true)
				}
			} else {
				// keyboard is closed
				if (isKeyboardShowing) {
					isKeyboardShowing = false
					keyboardShowing.invoke(false)
				}
			}
		}
	}
}