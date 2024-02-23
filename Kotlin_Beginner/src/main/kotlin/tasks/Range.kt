package tasks




data class MyDate1(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate1> {
    override fun compareTo(other: MyDate1): Int {

        return when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }
}
fun checkInRange(date: MyDate1, first: MyDate1, last: MyDate1): Boolean {
    return  date in first..last
}


