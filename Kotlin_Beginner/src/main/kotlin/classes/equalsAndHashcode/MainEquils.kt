package classes.equalsAndHashcode

fun main() {
    var user1 = User(23,"dima","dor")
    var user2 = User(23,"dima","dor")


    println(user1 == user2) // in Kotlin '==' == equals in Java user1.equals(user1)


}