package tasks

class Luhn {
    companion object {
        fun isValid(unformattedNumber: String): Boolean {
            val number = unformattedNumber.clean()
            return number.length > 1 &&
                    number.all(Character::isDigit) &&
                    number
                        .reversed()
                        .map { Character.getNumericValue(it) }
                        .mapIndexed { index, digit -> if (index % 2 == 1) digit * 2 else digit }
                        .map { if (it > 9) it - 9 else it }
                        .sum() % 10 == 0
        }
        private fun String.clean() = this.replace(" ", "")
    }
}


//            return unformattedNumber.length > 1 &&
//                    unformattedNumber.all(Character::isDigit) && unformattedNumber
//                .trim()
//                .replace("\\s+".toRegex(), "")
//                .reversed()
//                .map { it.toInt() }
//                .mapIndexed { index, d ->
//                    {
//                        if (index % 2 != 0) {
//                            var times = d.times(2)
//                            if (times > 10) {
//                                times.minus(9)
//                            } else times
//                        } else d
//                    }
//                }.mapNotNull { it?.invoke() }.sum() % 10 == 0
//
//
//        }
//    }
//}