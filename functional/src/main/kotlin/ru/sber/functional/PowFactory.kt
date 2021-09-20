package ru.sber.functional


/**
 * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
 */
object PowFactory {

    fun buildPowFunction(pow: Int): (Int) -> Int = fun(x: Int): Int {
        var result = 1
        for (i in 1..pow) {
            result *= x
        }
        return result
    }
}
