package com.tom.guess3

import java.util.Random

class SceretNumber {
    var secret: Int = Random().nextInt(10) + 1
    var count = 0

    fun validate(number: Int): Int {
        count++
        return number - secret
    }


}


fun main() {
    val secretNumber = SceretNumber()
    println(secretNumber.secret)
    println("${secretNumber.validate(2)} , count:${secretNumber.count}")

}











