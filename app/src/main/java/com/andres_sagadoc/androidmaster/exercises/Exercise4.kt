package com.andres_sagadoc.androidmaster.exercises

/**
 * 4. Catálogo de canciones
 * Imagina que necesitas crear una app de reproducción de música.
 *
 * Crea una clase que pueda representar la estructura de una canción. La clase Song debe incluir
 * estos elementos de código:
 *
 * Propiedades para el título, el artista, el año de publicación y el recuento de reproducciones
 *
 * Propiedad que indica si la canción es popular (si el recuento de reproducciones es inferior a
 * 1,000, considera que es poco popular)
 *
 * Un método para imprimir la descripción de una canción en este formato:
 * "[Título], interpretada por [artista], se lanzó en [año de lanzamiento]".
 */

fun main() {
    val song1 = Song("Evolve", "The warning", 2022, 5300000)
    val song2 = Song("Through the valley", "Ashley Johnson", 2021, 860)
    val song3 = Song("Future days", "Troy Baker", 2021, 1200000)

    song1.printDescription()
    song1.isPopular()
    song1.numberOfReproductions()
    println("------------------------------------------------------------")
    song2.printDescription()
    song2.isPopular()
    song2.numberOfReproductions()
    println("------------------------------------------------------------")
    song3.printDescription()
    song3.isPopular()
    song3.numberOfReproductions()

}

class Song(val title: String, val artist: String, val year: Int, val reproductions: Int) {
    var isPopular: Boolean = reproductions > 1000

    fun printDescription() {
        println("$title, interpretada por $artist, se lanzó en $year")
    }

    fun isPopular() {
        when (isPopular) {
            true -> println("$title es una canción popular")
            false -> println("$title no es una canción popular")
        }
    }

    fun numberOfReproductions() {
        println("Reproducciones: $reproductions")

    }

}