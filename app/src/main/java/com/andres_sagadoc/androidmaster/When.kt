package com.andres_sagadoc.androidmaster

fun main() {
    getMonthWithIF(125)
    getMonthsWithWhen(12)
    getTrimester(6)
    printSemester(1)
    println(getSemester(12))
    getNumberBetween(867)
    getResult(2)
}

fun getResult(value: Any) {
    when (value) {
        is Int -> {
            println("El valor ingresado $value es un Int")
            println("$value * 2 = ${value * 2}")
        }

        is String -> println("El valor ingresado $value es un String")
        is Boolean -> {
            println("El valor ingresado $value es un Boolean")
            if (value) println("Hola")
            else println("Adios")
        }
    }
}

fun getNumberBetween(number: Int) {
    when (number) {
        in 1..866 -> println("El número está entre 1 y 866")
        in 867..2342 -> println("El número está entre 867 y 2342")
        else -> println("El número no está entre 1 y 2342")
    }
}

fun getSemester(month: Int): String =
    when (month) {
        1, 2, 3, 4, 5, 6 -> ("Primer semestre")
        7, 8, 9, 10, 11, 12 -> ("Segundo semestre")
        else -> ("No es un mes válido")
    }


fun printSemester(month: Int) {
    when (month) {
        1, 2, 3, 4, 5, 6 -> println("Primer semestre")
        7, 8, 9, 10, 11, 12 -> println("Segundo semestre")
        else -> println("No es un mes válido")
    }
}

fun getTrimester(month: Int) {
    when (month) {
        1, 2, 3 -> println("Primer trimestre")
        4, 5, 6 -> println("Segundo trimestre")
        7, 8, 9 -> println("Tercer trimestre")
        10, 11, 12 -> println("Cuarto trimestre")
        else -> println("No es un mes válido")
    }
}

fun getMonthsWithWhen(month: Int) {
    print("Con when:  ")
    when (month) {
        1 -> println("Enero")
        2 -> println("Febrero")
        3 -> println("Marzo")
        4 -> println("Abril")
        5 -> println("Mayo")
        6 -> println("Junio")
        7 -> println("Julio")
        8 -> println("Agosto")
        9 -> println("Septiembre")
        10 -> println("Octubre")
        11 -> {
            println("Noviembre")
            println("así para mas cosas")
        }

        12 -> println("Diciembre")
        else -> println("No es un mes válido")
    }
}

fun getMonthWithIF(month: Int) {
    print("Con if else:  ")
    if (month == 1) println("Enero")
    else if (month == 2) println("Febrero")
    else if (month == 3) println("Marzo")
    else if (month == 4) println("Abril")
    else if (month == 5) println("Mayo")
    else if (month == 6) println("Junio")
    else if (month == 7) println("Julio")
    else if (month == 8) println("Agosto")
    else if (month == 9) println("Septiembre")
    else if (month == 10) println("Octubre")
    else if (month == 11) println("Noviembre")
    else if (month == 12) println("Diciembre")
    else println("No es un mes válido")
}