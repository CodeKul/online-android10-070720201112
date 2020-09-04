package com.ani.business.services

import android.location.Location

@FunctionalInterface
interface JustLocationChangedListener {
    fun justLocationChanged( loc : Location? ) : Unit?
}

