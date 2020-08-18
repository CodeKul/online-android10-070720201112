package com.ani.online.fragmentviewmodel.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ani.online.fragmentviewmodel.R

//https://stackoverflow.com/questions/44272914/sharing-data-between-fragments-using-new-architecture-component-viewmodel
class MainFragment : Fragment( ) {

    companion object {
        fun newInstance(hi : String) : MainFragment{
            val frag = MainFragment()

            val bnd = Bundle()
            bnd.putString("key_hi", hi)

            frag.arguments = bnd

            return frag
        }
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}
