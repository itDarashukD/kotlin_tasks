package classes.jenerics

interface Storage<T> {

    fun addItem(item: T)
    fun getOneItem(): T
}