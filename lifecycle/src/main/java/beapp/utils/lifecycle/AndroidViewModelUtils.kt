@file:Suppress("UNCHECKED_CAST")

package beapp.utils.lifecycle

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras

object AndroidViewModelUtils {

	val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

		private val viewModels: MutableList<ViewModel> = mutableListOf()

		override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
			val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
			viewModels.filterIsInstance(modelClass)
				.firstOrNull()
				?.let { return it }
			val viewModel =
				modelClass.getConstructor(Application::class.java)
					.newInstance(application)
			viewModels.add(viewModel)
			return viewModel
		}
	}

	@Composable
	inline fun <reified AVM : AndroidViewModel> androidViewModel(): AVM = Factory.create(
		modelClass = AVM::class.java,
		extras = MutableCreationExtras().apply {
			set(
				ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY,
				LocalContext.current.applicationContext as Application
			)
		})

	inline fun <reified AVM : AndroidViewModel> Application.androidViewModels(): Lazy<AVM> = lazy {
		Factory.create(
			modelClass = AVM::class.java,
			extras = MutableCreationExtras().apply {
				set(
					ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY,
					this@androidViewModels
				)
			})
	}
}