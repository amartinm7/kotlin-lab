package com.amm.lab.domain.collections

data class Student (
    val name: String,
    val surname: String,
    val passing: Boolean,
    val averageGrade: Double
)

fun main() {
    val generatedArray = IntArray(10) { i -> i * i }
    val studentList = Array<Student>(10){
        Student(
            name= "name$it",
            surname = "surname$it",
            passing = (it % 2 == 0),
            averageGrade = it.toDouble()
        )
    }

    matchStudentsInTheAverage(studentList)?.run(::print)
}

/**
    We get only students who are passing and with a grade point average of greater than 4.0.
    We sort by the average grade.
    We take first 10 students.
    We sort students alphanumerically. The comparator compares surnames first, and if equal then it compares names.
 */
fun matchStudentsInTheAverage(studentList: Array<Student>) =
    studentList
        .filter{ it.passing && it.averageGrade > 4 }
        .sortedBy { it.averageGrade }
        .take(10)
        .sortedWith(compareBy({it.surname}, {it.name}))

/**
We add current index to every element.
We need to destructure value and index before use.
We sort by index.
We remove index and keep only students.
 */
fun matchWithIndex(studentList: Array<Student>) =
    studentList
        .filter{ it.passing && it.averageGrade > 4}
        .withIndex()
        .sortedBy { (index, student) -> index > student.averageGrade }
        .take(10)
        .sortedBy{ (index) -> index }
        .map { (index, student) -> student }

