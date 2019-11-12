package com.amm.lab.domain.collections

data class Student (
    val name: String,
    val surname: String,
    val passing: Boolean,
    val averageGrade: Double
)
/*
My favorite examples of functional programming in Kotlin
https://www.freecodecamp.org/news/my-favorite-examples-of-functional-programming-in-kotlin-e69217b39112/
 */
fun main() {
    val generatedArray = IntArray(10) { i -> i * i }
    val studentList = List<Student>(10){
        Student(
            name= "name$it",
            surname = "surname$it",
            passing = (it % 2 == 0),
            averageGrade = it.toDouble()
        )
    }

    //keep in mind to create an extension function over the collection and then call to the function
    //in this way is more functional
    matchStudentsInTheAverage(studentList)?.run(::println)
    matchWithIndex(studentList)?.run(::println)
    mypowerset(listOf<Int>(1,2,3))?.run(::println)
    mypowerset(emptyList())?.run(::println)
    listOf<Int>(9,8,3,5,2,1).quickSort().run(::println)

}

/**
    We get only students who are passing and with a grade point average of greater than 4.0.
    We sort by the average grade.
    We take first 10 students.
    We sort students alphanumerically. The comparator compares surnames first, and if equal then it compares names.
 */
fun matchStudentsInTheAverage(studentList: List<Student>) =
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
fun matchWithIndex(studentList: List<Student>) =
    studentList
        .asSequence()
        .filter{ it.passing && it.averageGrade > 4}
        .withIndex()
        .sortedBy { (index, student) -> index > student.averageGrade }
        .take(10)
        .sortedBy{ (index) -> index }
        .map { (index, student) -> student }
        .toList()

private tailrec fun mypowerset(
    tail: Collection<Int>,
    acc: Set<Set<Int>> = setOf(emptySet())
): Set<Set<Int>> {
    return when {
        tail.isEmpty()-> acc
        else -> mypowerset( tail.drop(1), acc + acc.map { it + tail.first() })
    }
}

fun <T : Comparable<T>> List<T>.quickSort(): List<T> =
    if(size < 2) this
    else {
        val pivot = first()
        val (smaller, greater) = drop(1).partition { it <= pivot}
        smaller.quickSort() + pivot + greater.quickSort()
    }


