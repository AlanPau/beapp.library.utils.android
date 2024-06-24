package beapp.utils.compose

import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

object KeyboardUtils {

	/**
	 * Check if keyboard is visible
	 *
	 * @return true if visible, false if not
	 */
	private fun View.isKeyboardVisible(): Boolean = ViewCompat.getRootWindowInsets(this)?.isVisible(WindowInsetsCompat.Type.ime()) == true

	/**
	 * Observe keyboard visibility as state
	 *
	 * @return State with boolean of keyboard visibility
	 */
	@Composable
	fun observeKeyboardAsState(): State<Boolean> {

		val isVisible = remember { mutableStateOf(false) }
		with(LocalView.current) {
			DisposableEffect(this) {
				val listener = OnGlobalLayoutListener {
					isVisible.value = isKeyboardVisible()
				}
				viewTreeObserver.addOnGlobalLayoutListener(listener)
				onDispose {
					viewTreeObserver.removeOnGlobalLayoutListener(listener)
				}
			}
		}

		return isVisible
	}
}