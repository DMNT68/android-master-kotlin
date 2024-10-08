package com.andres_sagadoc.androidmaster

fun main() {
    ifBasico("Andrés")

    ifAnidado("cat")

    ifBoolean(false)

    ifInt(19)

    ifMultiple(18, true, true)

    ifMultipleOR("cat")
}

fun ifBasico(name: String) {

    if (name.lowercase() == "Andrés".lowercase()) {
        println("El nombre que ingresaste es igual a Andrés")
    } else {
        println("El nombre que ingresaste $name no es igual a Andrés")
    }
}

fun ifAnidado(animal: String) {
    if (animal == "dog") {
        println("Es un perro")
    } else if (animal == "cat") {
        println("Es un gato")
    } else if (animal == "bird") {
        println("Es un pajaro")
    } else {
        println("No es uno de los animales esperados")
    }
}

fun ifBoolean(estoyFeliz: Boolean) {
    if (estoyFeliz) {
        println("En hora buena")
    } else {
        println("😔 :( Tienes que seguir adelante")
    }
}

fun ifInt(age: Int) {
    if (age >= 18) {
        println("Eres mayor de edad")
    } else {
        println("Eres menor de edad")
    }
}

fun ifMultiple(age: Int, parentPermission: Boolean, imHappy: Boolean) {
    if (age >= 18 && parentPermission && imHappy) {
        println("Eres mayor de edad y tienes permiso de tus padres y estas feliz")
    } else {
        println("Eres menor de edad")
    }
}
fun ifMultipleOR(pet: String) {
    if(pet == "dog" || pet == "cat") println("Es un gato o un perro")
    else println("No es un gato ni un perro")
}