package com.ani.online.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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

        val lvDs : MutableLiveData<ArrayList<PhoneItem>> = MutableLiveData()
        lvDs.value = ds

        val adapter = PhoneAdapter( this, lvDs.value ?: arrayListOf() )
        phoneList.layoutManager = LinearLayoutManager(this)
        phoneList.adapter = adapter

        lvDs.observe(this, Observer {
            Log.i("@ani", it.toString())
            adapter.freshData(it)
        })

        btSv.setOnClickListener {
            Log.i("@ani", "${lvDs?.value}")
            val itm = PhoneItem(
                etNum.text.toString(),
                etNm.text.toString(),
                "${System.currentTimeMillis()}"
            )
           lvDs.value?.add(itm)
        }
    }
}
