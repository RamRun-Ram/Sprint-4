package ru.sber.functional

import java.time.Month
import java.util.function.Predicate

fun main(){
    val studentsGroup = StudentsGroup()
    studentsGroup.students = listOf(Student("Nikola","Sergeevich",averageRate = 2.1),Student("German","Sergeevich",averageRate = 8.6),Student("Vlad","Fedorovich",averageRate = 10.2))
    print(studentsGroup.filterByPredicate{it.averageRate >= 8})
}

class StudentsGroup {
    lateinit var students: List<Student>

    fun filterByPredicate(predicate: Predicate <Student>): List<Student> = students.filter { predicate.test(it) }
}
