package com.ani.app.storage

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.lang.StringBuilder

class PrivateFiles(
    private val context : Context
) {

    fun fileSystemInfo() {
        val externalStorageVolumes: Array<out File> =
            ContextCompat.getExternalFilesDirs(context, null)
        externalStorageVolumes.forEach {
            Log.i("@ani", "Loc - ${it.name} Path - ${it.absolutePath}")
        }

        Log.i("@ani", "Internal Storage Path - ${context.filesDir.absolutePath}")
    }

    fun writePrivate() {
        val rootOfInternalStorage = File(context.filesDir, "my.txt")
        val fos = FileOutputStream(rootOfInternalStorage)
        fos.write("Hello to private internal Storage".toByteArray())
        fos.close()
    }

    fun readPrivate() {
        val fis = context.openFileInput("my.txt")
        val bl = StringBuilder()
        while(true) {
            val chr = fis.read()
            if(chr == -1) break
            else bl.append((chr.toChar()))
        }
        Log.i("@ani", "${bl.toString()}")
    }

    fun writeToExternalPrivate() {
        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val rootOfExternalStorage =
                File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "my.txt")
            Log.i("@ani", "Path - ${rootOfExternalStorage.absolutePath}")
            val fos = FileOutputStream(rootOfExternalStorage)
            fos.write("Hello to private external Storage".toByteArray())
            fos.close()
        }
    }

    fun readFromExternalPrivate() {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val rootOfExternalStorage =
                File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "my.txt")
            val fis = FileInputStream(rootOfExternalStorage)
            val bl = StringBuilder()
            while (true) {
                val chr = fis.read()
                if (chr == -1) break
                else bl.append((chr.toChar()))
            }
            Log.i("@ani", "${bl.toString()}")
        }
    }
}