package ru.sber.generics


// 1.
fun <A, B> compare(p1: Pair<out A, B>, p2: Pair<out A, B>): Boolean {
    val isEquality = if (p1 == p2) true else false
    return isEquality
}

// 2.
fun <Any : Comparable<Any>> countGreaterThan(anArray: Array<Any>, elem: Any): Int {
    var sum = 0
    anArray.filter { it > elem }.forEach { ++sum }
    return sum
}

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sortWith(compareBy { it })
    }
}

// 4.
class Stack<T> {
    val stack: MutableList<T> = mutableListOf()

    fun push(s: T) {
        stack.add(s)
    }

    fun pop(): T {
        return stack.removeLast()
    }

    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }
}