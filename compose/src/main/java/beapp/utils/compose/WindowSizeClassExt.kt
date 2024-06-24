package beapp.utils.compose

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

/**
 * Is expended
 */
val WindowSizeClass.isExpended: Boolean
	get() = widthSizeClass == WindowWidthSizeClass.Expanded