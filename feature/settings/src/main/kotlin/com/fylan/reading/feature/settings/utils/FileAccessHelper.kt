package com.fylan.reading.feature.settings.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.documentfile.provider.DocumentFile
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.RandomAccessFile

object FileAccessHelper {

    fun getRandomAccessFile(context: Context, documentFile: DocumentFile): RandomAccessFile? {
        val contentResolver = context.contentResolver

        // 获取文件的 URI
        val uri = documentFile.uri

        // 获取文件路径
        val filePath = getPathFromUri(context, uri)

        if (filePath != null) {
            // 如果文件路径不为空，则使用普通的 RandomAccessFile
            try {
                return RandomAccessFile(filePath, "rw")
            } catch (e: FileNotFoundException) {
                Log.e("FileAccessHelper", "File not found: $filePath")
            }
        } else {
            // 如果文件路径为空，则尝试使用 DocumentFile 的输入流来获取 RandomAccessFile
            try {
                val inputStream = contentResolver.openInputStream(uri)
                val tempFile = createTempFileFromInputStream(context, inputStream)
                return RandomAccessFile(tempFile, "rw")
            } catch (e: FileNotFoundException) {
                Log.e("FileAccessHelper", "File not found: $uri")
            }
        }

        return null
    }

    private fun getPathFromUri(context: Context, uri: Uri): String? {
        // 从 URI 获取文件路径
        var path: String? = null
        val projection = arrayOf(MediaStore.MediaColumns.DATA)
        val cursor = context.contentResolver.query(uri, projection, null, null, null)

        cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                path = it.getString(columnIndex)
            }
        }

        return path
    }

    private fun createTempFileFromInputStream(
        context: Context,
        inputStream: java.io.InputStream?,
    ): File {
        // 将 InputStream 写入临时文件
        val tempFile = File(context.cacheDir, "temp_file.tmp")
        tempFile.deleteOnExit()

        try {
            tempFile.outputStream().use { output ->
                inputStream?.copyTo(output)
            }
        } catch (e: IOException) {
            Log.e("FileAccessHelper", "Error creating temp file: ${e.message}")
        }

        return tempFile
    }
}
