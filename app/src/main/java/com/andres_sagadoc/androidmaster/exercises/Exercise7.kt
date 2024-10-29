package com.andres_sagadoc.androidmaster.exercises

/**
 * 7. Subasta especial
 * Por lo general, en una subasta, el ofertante que ofrece el importe más alto determina el precio
 * de un artículo. En esta subasta especial, si nadie oferta por un artículo, este se vende
 * automáticamente a la casa de subastas al precio mínimo.
 *
 * En el código inicial que se proporciona en el siguiente fragmento de código, se te proporciona
 * una función auctionPrice() que acepta un tipo Bid? anulable como argumento:
 *
 * Completa la función auctionPrice() para que el programa imprima estas líneas:
 *
 * Item A is sold at 5000.
 * Item B is sold at 3000.
 */

fun main() {
    val winningBid = Bid(5000, "Private Collector")

    println("Item A is sold at ${auctionPrice(winningBid, 2000)}.")
    println("Item B is sold at ${auctionPrice(null, 3000)}.")
}

class Bid(val amount: Int, val bidder: String)

fun auctionPrice(bid: Bid?, minimumPrice: Int): Int {
    val soldPrice = bid?.amount ?: minimumPrice
    return soldPrice
}