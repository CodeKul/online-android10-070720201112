package com.ani.app.webservices

data class Domain (
    val data : Data,
    val ad : Ad = Ad(
        company = "",
        url = "",
        text = ""
    )
)

data class Data (
    val id : Int,
    val email : String,
    val first_name : String,
    val last_name : String,
    val avatar : String
)

data class Ad (
    val company : String,
    val url : String,
    val text : String
)

