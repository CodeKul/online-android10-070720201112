package com.ani.app.webservices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ani.app.webservices.databinding.ActivityMainBinding
import com.bumptech.glide.Glide

// okhttp
// volley
// retrofit
// fuel
class MainActivity : AppCompatActivity() {

    private val viewModel : UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
        }

        viewModel.btn.observe(this, Observer{

            (application  as MyApp).q.add(
                StringRequest("https://reqres.in/api/users/2", { json ->
                    val domain = (application as MyApp).gson.fromJson(json, Domain::class.java)
                    viewModel.setData(domain)

                    Glide.with(this).load(domain.data.avatar).into(binding.imageView);
                }, {
                    Log.i("@ani" ," Http Error")
                    Log.i("@ani" ,"${it.message}")
                })
            )
        })
    }
}
