package com.example.data.local.manager

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.util.UUID
import javax.inject.Inject
import androidx.core.net.toUri

class ImageFileManager @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    private val imageDir: File = context.filesDir

    suspend fun copyImageToInternalStorage(url: String): String {
        val fileName = "IMG_${UUID.randomUUID()}.jpg"
        val file = File(imageDir, fileName)

        withContext(Dispatchers.IO) {
            context.contentResolver.openInputStream(url.toUri())
                ?.use { inputStream ->
                    file.outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
        }

        return file.absolutePath
    }

    suspend fun deleteImage(url: String) {
        withContext(Dispatchers.IO) {
            val file = File(url)
            if (file.exists() && isInternal(file.absolutePath)) {
                file.delete()
            }
        }
    }

    fun isInternal(url: String): Boolean = url.startsWith(imageDir.absolutePath)
}