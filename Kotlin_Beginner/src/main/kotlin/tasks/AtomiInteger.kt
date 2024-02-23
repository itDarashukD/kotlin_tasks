package tasks

import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class BankAccount {

    var isBalanceClosed = AtomicBoolean(false)
    var balanceInternal = AtomicInteger()
    val balance: Int
        get() {
            if (isBalanceClosed.get() == true) {
                throw IllegalStateException()
            } else return balanceInternal.get()
        }


    fun adjustBalance(amount: Long) {
        if (isBalanceClosed.get() == true) {
            throw IllegalStateException()
        }
        balanceInternal.getAndAdd(amount.toInt())
    }

    fun close() {
        isBalanceClosed.set(true)
    }
}
