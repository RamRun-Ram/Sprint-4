package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Empty",
    val age: Int = 18,
    val averageRate: Double,
    val city: String  = "Moscow",
    val specialization: String = "Empty",
    val prevEducation: String? = null,
)
