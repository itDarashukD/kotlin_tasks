package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)


fun evaluateGuess(secret: String, guess: String): Evaluation {
//    var rightPosition = 0
//    var wrongPosition = 0
//
//    val secretOccurrences: MutableMap<Char, Int> =secret.groupBy { it }.mapValues { it.value.size } as MutableMap<Char, Int>
//    val guessOccurrences: MutableMap<Char, Int> =guess.groupBy { it }.mapValues { it.value.size } as MutableMap<Char, Int>
//
//    // Calculate right positions, if right positions then reduce values:
//    for (i in secret.indices) {
//        val secretKey = secret[i]
//        val guessKey = guess[i]
//
//        if (secretKey == guessKey) {
//            rightPosition++
//            secretOccurrences[secretKey] = secretOccurrences.getValue(secretKey) - 1
//            guessOccurrences[guessKey] = guessOccurrences.getValue(guess[i]) - 1
//        }
//    }
//
//    // Calculate wrong positions
//    for ((secretKey, secretValue) in secretOccurrences) {
//        if (guessOccurrences.containsKey(secretKey)) {
//            var guessValue = guessOccurrences.getValue(secretKey)
//            wrongPosition += minOf(secretValue, guessValue)
//        }
//    }


    val rightPositions = secret.zip(guess).count {
        it.first == it.second
//        (charFirst,charSecond)->charFirst == charSecond
    }

    val commonLetters = "ABCDEF".sumBy { ch ->

        Math.min(secret.count {it == ch }, guess.count { it == ch }) //it - first letter in secret and in guess
    }
    return Evaluation(rightPositions, commonLetters - rightPositions)
}

//    return Evaluation(rightPosition, wrongPosition)

//}
