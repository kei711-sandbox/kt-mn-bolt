package com.example.app

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("com.example")
        .banner(false)
        .start()
}

