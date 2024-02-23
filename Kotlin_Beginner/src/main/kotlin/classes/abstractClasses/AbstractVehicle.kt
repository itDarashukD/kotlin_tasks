package classes.abstractClasses

abstract class AbstractVehicle {

    abstract var wheelsCount : Int;
    abstract var speed : Int;

    abstract fun move()

    open fun signalizing(){

    }

}