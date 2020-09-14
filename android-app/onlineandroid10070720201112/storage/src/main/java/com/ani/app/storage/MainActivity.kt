package com.ani.app.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val prefs = AppPrefs(this)
//        prefs.writeData()
//        prefs.readData()

        val privateFiles = PrivateFiles(this)


        privateFiles.fileSystemInfo()
        privateFiles.writeToExternalPrivate()
        privateFiles.readFromExternalPrivate()

        //  /storage/emulated/0/Android/data/com.ani.app.storage/files/Download/my.txt -> writing file
        // /storage/emulated/0/Android/data/com.ani.app.storage/files

//        privateFiles.writePrivate()
//        privateFiles.readPrivate()

        //Home work - Signature Pad you need to build and store your signature as bitmap image.

    }
}
