package tasks

fun main(args: Array<String>){

    fun isValidIdentifier(s: String): Boolean {
        if ( s.isEmpty() || s[0].isDigit()) {
            return false
        }

        fun isValidChar(ch: Char) =
            ch == '_' || ch.isLetterOrDigit()

        for (ch in s) {
            if (!isValidChar(ch)) {
                return false
            }
        }

        return true
    }

    println(isValidIdentifier("name")) //true
    println(isValidIdentifier("_name")) //true
    println(isValidIdentifier("_12")) //true
    println(isValidIdentifier("")) //false
    println(isValidIdentifier("012")) //false
    println(isValidIdentifier("no$")) //false


}