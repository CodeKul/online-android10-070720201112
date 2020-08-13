package com.ani.online.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import kotlinx.android.synthetic.main.fragment_color_bars.*


class ColorBarsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_color_bars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val skLs = MySeekListener()
        view.findViewById<SeekBar>(R.id.skRd).setOnSeekBarChangeListener(skLs)
        view.findViewById<SeekBar>(R.id.skGr).setOnSeekBarChangeListener(skLs)
        view.findViewById<SeekBar>(R.id.skBl).setOnSeekBarChangeListener(skLs)
    }

    fun emitColors(red : Int, green : Int, blue : Int) {

    }

    inner class MySeekListener : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            seekBar?.let {

                when(it.id) {
                    R.id.skRd -> emitColors(progress, skGr.progress, skBl.progress)
                    R.id.skGr -> emitColors(skRd.progress, progress, skBl.progress)
                    R.id.skBl -> emitColors(skRd.progress, skGr.progress, progress)
                    else -> {}
                }
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }
}