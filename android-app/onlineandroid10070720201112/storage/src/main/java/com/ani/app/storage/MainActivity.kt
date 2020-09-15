package com.ani.app.storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ani.app.storage.files.PrivateFiles
import com.ani.app.storage.signature.domain.Sign
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val prefs = AppPrefs(this)
//        prefs.writeData()
//        prefs.readData()

//        val privateFiles = PrivateFiles(this)
//
//        privateFiles.fileSystemInfo()
//        privateFiles.writeToExternalPrivate()
//        privateFiles.readFromExternalPrivate()

        //  /storage/emulated/0/Android/data/com.ani.app.storage/files/Download/my.txt -> writing file
        // /storage/emulated/0/Android/data/com.ani.app.storage/files

//        privateFiles.writePrivate()
//        privateFiles.readPrivate()

        //Home work - Signature Pad you need to build and store your signature as bitmap image.


        val nm = "ani"
        val dt = System.currentTimeMillis()
        val sign = Sign(1, nm, dt, File(filesDir.absolutePath, "$nm-$dt-sign.jpg").absolutePath)

        Thread {
            (application as StorageApp).db.signDao().saveSignature(sign)

            (application as StorageApp).db.signDao().listAllSigns().forEach {
                Log.i("@ani", "Name - ${it.userName} File - ${it.signFilePath}")
            }
        }.start()
    }
}
