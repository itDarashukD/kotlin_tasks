package tasks

import java.util.*

data class MyDate2(val year: Int, val month: Int, val dayOfMonth: Int)

fun MyDate2.toMillis(): Long {
    val c = Calendar.getInstance()
    c.set(year, month, dayOfMonth)
    return c.getTimeInMillis()
}

fun Long.toDate(): MyDate2 {
    val c = Calendar.getInstance()
    c.setTimeInMillis(this)
    return MyDate2(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE))
}