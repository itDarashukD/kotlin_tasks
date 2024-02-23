package classes.abstractClasses

fun main() {

    var car = Car()
    var bicycle = Bicycle()


    car.move()
    car.signalizing()
    car.stop()

    bicycle.move()
    bicycle.stop()


    if (car is Stopable) {      //is  == " instance of " , !is - not a Type
        println(" the car has Stopable type")
    }
}