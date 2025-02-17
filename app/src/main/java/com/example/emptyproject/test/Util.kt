package com.example.emptyproject.test

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL

/*
 * Created by Sudhanshu Kumar on 19/11/24.
 */

object Util {

    fun downloadFileWithUrl(
        url: String,
        fileName: String,
        filePath: String?,
        context: Context,
        onRequestPermission: ((url: String, fileName: String) -> Unit)? = null
    ) {
        val fileExt = url.split(".").last()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            if(hasWriteStorageAccess(context) && hasReadStorageAccess(context)) {
                try {
                    val parsedUrl = URL(url)
                    val inputStream = parsedUrl.openStream()
                    val fileOutputStream = FileOutputStream(
                        "${Environment.getExternalStorageDirectory()}/$fileName.$fileExt"
                    )
                    var length: Int
                    val buffer = ByteArray(8129)
                    while ((inputStream.read(buffer).also { length = it }) > -1) {
                        fileOutputStream.write(buffer, 0, length)
                    }
                    fileOutputStream.close()
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                onRequestPermission?.let { it(url, fileName) }
                return
            }
        } else {
            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val uri = Uri.parse(url)
            val request: DownloadManager.Request = DownloadManager.Request(uri)
            val title = "$fileName.$fileExt"
            request.setTitle(title)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                title
            )
            downloadManager.enqueue(request)
        }
    }

    private fun hasReadStorageAccess(context: Context?): Boolean {
        context?.let {
            return ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        } ?: run {
            return false
        }
    }

    private fun hasWriteStorageAccess(context: Context?): Boolean {
        context?.let {
            return ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        } ?: run {
            return false
        }
    }

}