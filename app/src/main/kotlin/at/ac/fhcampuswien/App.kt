/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package at.ac.fhcampuswien

import java.util.Scanner

class App {
    // Game logic for a number guessing game
    fun playNumberGame(digitsToGuess: Int = 4) {
        val scanner = Scanner(System.`in`)
        val generatedNumber = generateRandomNonRepeatingNumber(digitsToGuess)

        println("Guess the $digitsToGuess-digit number \n")
        //println("$generatedNumber")

        var guess: Int
        var numberOfGuesses: Int = 1

        do {
            println("Guess number $numberOfGuesses:")
            guess = scanner.nextInt()
            val result = checkUserInputAgainstGeneratedNumber(guess, generatedNumber)

            println(result.toString())
            println("")
            numberOfGuesses++
        } while (result.m != digitsToGuess)

        println("Wow that's the correct number! $generatedNumber \n" +
                "It took you $numberOfGuesses tries.")
    }

    /**
     * Generates a non-repeating number of a specified length between 1-9.
     *
     * Note: The function is designed to generate a number where each digit is unique and does not repeat.
     * It is important to ensure that the length parameter does not exceed the maximum possible length
     * for non-repeating digits (which is 9 excluding 0 for base-10 numbers).
     *
     * @param length The length of the non-repeating number to be generated.
     *               This dictates how many digits the generated number will have.
     * @return An integer of generated non-repeating number.
     *         The generated number will have a number of digits equal to the specified length and will
     *         contain unique, non-repeating digits.
     * @throws IllegalArgumentException if the length is more than 9 or less than 1.
     */
    val generateRandomNonRepeatingNumber: (Int) -> Int = { length ->
        if (length < 1 || length > 9) throw IllegalArgumentException("The number has to have 1 to 9 digits!")

        val digits = (1..9).toList().shuffled().take(length).joinToString("")
        digits.toInt()
    }

    /**
     * Compares the user's input integer against a generated number for a guessing game.
     * This function evaluates how many digits the user guessed correctly and how many of those
     * are in the correct position. The game generates number with non-repeating digits.
     *
     * Note: The input and the generated number must both be numbers.
     * If the inputs do not meet these criteria, an IllegalArgumentException is thrown.
     *
     * @param input The user's input integer. It should be a number with non-repeating digits.
     * @param generatedNumber The generated number with non-repeating digits to compare against.
     * @return [CompareResult] with two properties:
     *         1. `m`: The number of digits guessed correctly (regardless of their position).
     *         2. `n`: The number of digits guessed correctly and in the correct position.
     *         The result is formatted as "Output: m:n", where "m" and "n" represent the above values, respectively.
     * @throws IllegalArgumentException if the inputs do not have the same number of digits.
     */
    val checkUserInputAgainstGeneratedNumber: (Int, Int) -> CompareResult = { input, generatedNumber ->
        val inputDigits = input.toString()
        val generatedDigits = generatedNumber.toString()

        if (generatedDigits.length != inputDigits.length) {
            throw IllegalArgumentException("The numbers don't have the same length!")
        }

        var n = 0
        var m = 0
        val list = mutableListOf<Char>()

        for (i in inputDigits.indices) {
            if (inputDigits[i] == generatedDigits[i]) {
                m++
            }
            if (inputDigits[i] in generatedDigits && !list.contains(inputDigits[i])) {
                n++
                list.add(inputDigits[i])
            }
        }
        CompareResult(n, m)
    }
}

fun main() {
    val app = App()
    println("This is the Number Guessing Game! \n" +
            "You have to guess which number I chose.\n" +
            "correct digits : correct positions \n" +
            "!!! GOOD LUCK !!! \n" +
            "")
    app.playNumberGame()
    // app.playNumberGame(2)
}
