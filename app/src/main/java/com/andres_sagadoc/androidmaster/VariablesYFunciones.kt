package com.andres_sagadoc.androidmaster

val age: Int = 30

// Variables
fun main() {
    /*
    variableNumericas()
    variablesAlfanumericas()
    variablesBoolean()
    */
    showMyName("Andrés")
    showMyAge()
    add(1, 2)
    val mySubstract = substract(1, 2)
    println(mySubstract)
}

fun showMyName(name: String) {
    println("My name is $name")
}

fun showMyAge(currentAge: Int = 30) {
    println("My age is $currentAge")
}

fun add(firstNumber: Int, secondNumber: Int) {
    println(firstNumber + secondNumber)
}

fun substract(firstNumber: Int, secondNumber: Int): Int {
    return firstNumber - secondNumber
}

fun substractCool(firstNumber: Int, secondNumber: Int): Int = firstNumber - secondNumber

fun variableNumericas() {
    /*
       Variables númericas
    */

    // Int -2,147,483,647 a 2,147,483,647

    var age2: Int = 31

    // Long -9,223,372,036,854,775,807 a 9,223,372,036,854,775,807
    val exampleLong: Long = 30

    // Float -1.4028235E-45 a 3.4028235E38
    val floatExample: Float = 30.5f

    // Double -1.7976931348623157E308 a 1.7976931348623157E308
    val doubleExample: Double = 30.5

    // Operaciones aritmericas
    println("a: $age")
    age2 = 31
    println("b: $age2")
    println("Sumar: a+b = " + (age + age2))
    println("Restar: a-b = " + (age - age2))
    println("Multiplicar: a*b=  " + (age * age2))
    println("Dividir: a/b = " + (age / age2))
    println("Resto o Módulo: a%b = " + (age % age2))

    var example1Suma = age + exampleLong
    var example2Suma: Int = age + floatExample.toInt()
}

fun variablesAlfanumericas() {

    /*
    * Variables alfanúmericas
    */

    // Char 'a' a 'z' 'A' a 'Z'
    val charExample: Char = 'a'
    val charExample2: Char = '1'
    val charExample3: Char = '@'

    // String
    val stringExample: String = "Andrés"
    val stringExample2: String = "123"
    val stringExample3: String = "Hello World 123"
    val stringExample4: String = "3"
    val stringExample5: String = "21"
    var stringConcatenada: String = "Hola"
    val stringExample6: String = age.toString()
    // stringConcatenada = "Hola me llamo $stringExample y tengo $stringExample6 años"
    println(stringConcatenada)
    println(stringExample4 + stringExample5)
    println(stringExample4.toInt() + stringExample5.toInt())

}

fun variablesBoolean() {
    /*
    * Variables booleanas
    **/
    val booleanExample: Boolean = true
    val booleanExample2: Boolean = false

}