package classes.exception

class ExceptionHandling {

    var one: Int = 5;

    fun deviding() {
        try {
            one / 0
        } catch (e: ArithmeticException) {
            println("ArithmeticException")
        }catch (e: Exception) {
            println("Exception")
        }
    }

    fun throwException(){
        throw ArithmeticException()
    }



}