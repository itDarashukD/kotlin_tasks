package classes.abstractClasses

class Bicycle : AbstractVehicle(), Stopable {
    override var wheelsCount: Int = 2
    override var speed: Int = 25

    override fun move() {
        println("move on $wheelsCount wheels with speed $speed")
    }


    override fun stop() {
        print("i can stop since i have $wheelsCount wheels")
    }
}