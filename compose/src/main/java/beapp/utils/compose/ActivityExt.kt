package beapp.utils.compose

import android.app.Activity
import android.app.KeyguardManager
import android.view.WindowManager
import androidx.core.content.ContextCompat
import beapp.utils.system.isSdk26OrAbove
import beapp.utils.system.isSdk27OrAbove

/**
 * Turn screen on and keyguard off
 *
 */
fun Activity.turnScreenOnAndKeyguardOff() {
	if (isSdk27OrAbove()) {
		setShowWhenLocked(true)
		setTurnScreenOn(true)
	}
	window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

	with(ContextCompat.getSystemService(this@turnScreenOnAndKeyguardOff, KeyguardManager::class.java)) {
		if (this != null && isSdk26OrAbove()) {
			requestDismissKeyguard(this@turnScreenOnAndKeyguardOff, null)
		}
	}
}

/**
 * Turn screen off and keyguard on
 * see https://www.giorgosneokleous.com/post/full-screen-intent-notifications-android/
 */
fun Activity.turnScreenOffAndKeyguardOn() {
	if (isSdk27OrAbove()) {
		setShowWhenLocked(false)
		setTurnScreenOn(false)
	}
	window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
}

/**
 * Keep screen on
 *
 */
fun Activity.keepScreenOn() = window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)