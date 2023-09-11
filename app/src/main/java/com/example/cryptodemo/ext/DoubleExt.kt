package com.example.cryptodemo.ext

fun Float.format(digits: Int = 2) = "%.${digits}f".format(this)