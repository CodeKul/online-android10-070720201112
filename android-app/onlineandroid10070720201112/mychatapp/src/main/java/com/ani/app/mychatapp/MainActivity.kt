package com.ani.app.mychatapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ani.app.mychatapp.contact.ContactUtil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val conUt = ContactUtil(this)
//        val contacts = conUt.fetchContacts()

        val cur = contentResolver.query(Uri.parse("content://com.ani.sign"), null, null, null, null)
        cur?.let {
            while (it.moveToNext()) {
                val id = it.getLong(it.getColumnIndex("id"))
                val name = it.getString(it.getColumnIndex("userName"))
                Log.i("@ani", "Id $id, Name $name")
            }
            cur.close()
        }
    }
}
