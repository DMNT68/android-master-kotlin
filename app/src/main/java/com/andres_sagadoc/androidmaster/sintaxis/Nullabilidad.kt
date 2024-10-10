package com.andres_sagadoc.androidmaster.sintaxis

fun main() {
    var name: String? = "Andrés"
    var name2: String? = null

    println(name!![3])
    println(name.get(3))
    println(name2?.get(3) ?: "Es nulo")

    if (name != null) {
        if (name.lowercase() == "andres") {
            println("Es andres")
        }
    }
}