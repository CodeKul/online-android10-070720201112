package com.ani.online.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

class DisplayFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vw = inflater.inflate(
            R.layout.fragment_display,
            container,
            false
        )

        return vw
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i("@ani","---> ${view?.findViewById<FrameLayout>(R.id.dispBlock)}")
        setNewColor(255, 206, 100)
    }

    fun setNewColor(red : Int, green : Int, blue : Int) {
        Log.i("@ani", "Red $red, Green $green, Blue $blue")
        Log.i("@ani", "$view")

        Log.i("@ani","---> $$$ ${view?.findViewById<FrameLayout>(R.id.dispBlock)}")
        view?.setBackgroundColor(Color.argb(255, red, green , blue))
    }
}