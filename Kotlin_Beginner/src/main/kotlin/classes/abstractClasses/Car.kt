package classes.abstractClasses

class Car : AbstractVehicle(), Stopable {
    override var wheelsCount: Int = 4
    override var speed: Int = 60

    override fun move() {
        println("${this.javaClass.name} move on $wheelsCount wheels with speed $speed")
    }

    override fun signalizing() {
        println("car do bbbiiiiipppp signal")
    }

    override fun stop() {
        print("i can stop since i have $wheelsCount wheels")
    }

}
