package classes.equalsAndHashcode

//class Any= in Kotly == Object in Java
class User(var age: Int, var name: String, var lastName: String) : Any() {

    override fun toString(): String {
        return "user has name $name, secondName  = $lastName, age = $age"
    }

//    override fun equals(otherObject: Any?): Boolean {  //class Any= in Kotly == Object in Java
//        if (otherObject is User) {
//            if (
//                age == otherObject.age &&
//                name == otherObject.name &&
//                lastName == otherObject.lastName
//            )
//                return true
//        }
//        return false
//    }


    override fun hashCode(): Int {
        var result = age
        result = 31 * result + name.hashCode()
        result = 31 * result + lastName.hashCode()

        return result
    }

    override fun equals(other: Any?): Boolean { // === - check link to object ( == in java)
        var hashCode = this.hashCode()
        var hashCode1 = other.hashCode()
        if (hashCode != hashCode1) return false
        if (this === other) return true
        if (other !is User) return false

        if (age != other.age) return false
        if (name != other.name) return false
        if (lastName != other.lastName) return false

        return true
    }


}