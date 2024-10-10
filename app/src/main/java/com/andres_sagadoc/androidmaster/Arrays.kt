package com.andres_sagadoc.androidmaster

fun main() {
    val weekDays = arrayOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")

    println(weekDays[0])

    // Tamaños
    println("Tamaño del array = ${weekDays.size}")
    if(weekDays.size >= 8) println(weekDays[7])
    else println("No hay más valores en el array")

    // Modificar valores
    weekDays[0] = "Andrés"
    println(weekDays[0])

    // Bluces
    for (position in weekDays.indices){
        print("Posicion $position -> ")
        println(weekDays[position])
    }

    for ((position, value) in weekDays.withIndex()) {
        println("Posición $position contiene $value")
    }

    for (day in weekDays){
        println("Ahora es $day")
    }
}