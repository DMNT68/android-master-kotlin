package com.andres_sagadoc.androidmaster.sintaxis

fun main() {
    // inmutableList()
    mutableList()
}

fun mutableList() {
    var weekDays: MutableList<String> =
        mutableListOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
    println(weekDays)

    // añadir elementos en la ultima posición por defecto
    weekDays.add("Andrés day")
    println(weekDays)

    // añadir elementos en una posición específica en este caso 0
    weekDays.add(0, "Andrés day")
    println(weekDays)

    if (weekDays.isEmpty()) println("Está vacío")
    else weekDays.forEach { println(it) }

    if (weekDays.isNotEmpty()) println("No está vacío")
    else println("Está vacío")
}

fun inmutableList() {
    val readOnly: List<String> =
        listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
    println("Tamaño de la lista ${readOnly.size}")
    println(readOnly)
    println("Valor de la posición 0 ${readOnly[0]}")
    println("Última posición ${readOnly.last()}")
    println("Primera posición ${readOnly.first()}")

    val example = readOnly.filter { it.contains("a") }
    println("Lista filtrada con valores que contengan la letra a $example")

    readOnly.forEach { weekDay -> println(weekDay) }
}
