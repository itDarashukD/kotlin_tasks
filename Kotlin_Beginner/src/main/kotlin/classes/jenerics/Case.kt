package classes.jenerics

class Case<T, E>(var item: T, var item2: E) : Storage<T> {

    override fun addItem(item: T) {
        this.item = item
    }

    override fun getOneItem(): T {

        return item
    }

    fun getSecondItem(): E {
        return item2
    }


}