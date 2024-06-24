package beapp.utils.system

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.MediaStore
import java.io.File

/**
 * Load picture from external storage
 *
 * @param context
 * @param pictureUri
 * @return bitmap
 */
fun loadPictureFromExternalStorage(
	context: Context,
	pictureUri: Uri,
): Bitmap? {
	return try {
		if (isSdk28OrAbove()) {
			val source = ImageDecoder.createSource(context.contentResolver, pictureUri)
			ImageDecoder.decodeBitmap(source)
		} else {
			MediaStore.Images.Media.getBitmap(context.contentResolver, pictureUri)
		}
	} catch (e: Exception) {
		e.printStackTrace()
		null
	}
}

/**
 * Load video from external storage
 *
 * @param context
 * @param videoUri
 * @return video file
 */
fun loadVideoFromExternalStorage(context: Context, videoUri: Uri): File? {
	return try {
		context.contentResolver.openInputStream(videoUri)?.use { input ->
			val file = File(context.cacheDir, "mlv_tmp_video_file").apply { createNewFile() }
			file.outputStream().use { output -> input.copyTo(output) }
			file
		}
	} catch (e: Exception) {
		e.printStackTrace()
		null
	}
}