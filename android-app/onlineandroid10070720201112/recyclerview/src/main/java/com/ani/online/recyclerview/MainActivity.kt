package com.ani.online.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ani.online.recyclerview.adpater.PhoneAdapter
import com.ani.online.recyclerview.adpater.PhoneItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ds = arrayListOf<PhoneItem>()
        ds.add(
            PhoneItem("96644654", "Android", "${System.currentTimeMillis()}")
        )
        ds.add(
            PhoneItem("64654", "iOS", "${System.currentTimeMillis()}")
        )
        ds.add(
            PhoneItem("65454768", "iOS", "${System.currentTimeMillis()}")
        )

        val adapter = PhoneAdapter( this, ds )
        phoneList.layoutManager = LinearLayoutManager(this)
        phoneList.adapter = adapter
    }
}
