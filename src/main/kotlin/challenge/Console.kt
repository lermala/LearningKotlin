package challenge

import java.util.*
import kotlin.Comparator
import kotlin.random.Random

class Console {
    // https://smartprogress.do/programming

    init {
        // getDataTypesInfo()
        //startCatchingConsoleErrors()
        // getCalculator()
        startArrayTasks()
    }


    /**
     * 1) Постановка задачи: красиво вывести информацию о типах данных (целочисленные, строки)
     * и их переменных (int, string) и других существующих в вашем языке типов данных.
     * Оформить всё аккуратно и красиво.
     * info abt types from https://metanit.com/kotlin/tutorial/2.2.php
     * info abt formatted output from https://metapx.org/kotlin-string-format/
     */
    fun getDataTypesInfo(){
        val formatHeader = "%-8s | %11s | %28s | %28s |"
        val tableHeader = String.format(formatHeader, "type", "size (bits)", "min value", "max value")
        println(tableHeader)

        // этот класс импользуется только в этой задаче, поэтому решила оставить его внутри
        class TypeInfo(val name: String, val size: Any, val min: Any, val max: Any) {
            fun toString(format: String): String {
                return String.format(format, name, size, min, max)
            }
        }

        val allNumberTypes: MutableList<TypeInfo> = mutableListOf() // исключая  ULong
        allNumberTypes.add(TypeInfo("Byte", 8, -128, 127))
        allNumberTypes.add(TypeInfo("Short", 16, -32768, 32767))
        allNumberTypes.add(TypeInfo("Int", 32, -2147483648, 2147483647))
        allNumberTypes.add(TypeInfo("Long", Long.SIZE_BITS, Long.MIN_VALUE, Long.MAX_VALUE))
        allNumberTypes.add(TypeInfo("UByte", UByte.SIZE_BITS, UByte.MIN_VALUE.toShort(), UByte.MAX_VALUE.toShort()))
        allNumberTypes.add(TypeInfo("UShort", UShort.SIZE_BITS, UShort.MIN_VALUE.toInt(), UShort.MAX_VALUE.toInt()))
        allNumberTypes.add(TypeInfo("UInt", UInt.SIZE_BITS, UInt.MIN_VALUE.toLong(), UInt.MAX_VALUE.toLong()))

        val formatNumberTypes = "%-8s | %11d | %,28d | %,28d |"
        allNumberTypes.forEach { e -> println(e.toString(formatNumberTypes)) }

        val otherTypes: MutableList<TypeInfo> = mutableListOf()
        otherTypes.add(TypeInfo("ULong", ULong.SIZE_BITS, ULong.MIN_VALUE.toLong(), "2^64-1"))
        otherTypes.add(TypeInfo("Boolean", 1, "0 - false", "1 - true"))
        otherTypes.add(TypeInfo("Char", Char.SIZE_BITS, Char.MIN_VALUE, Char.MAX_VALUE))
        otherTypes.add(TypeInfo("String", "-", "-", "-"))
        otherTypes.add(TypeInfo("Any", "-", "-", "-"))
        otherTypes.add(TypeInfo("Float", Float.SIZE_BITS, "-3.4*10^38", "3.4*10^38"))
        otherTypes.add(TypeInfo("Double", Double.SIZE_BITS, "+-5.0*10^-324", "+-1.7*10^-308"))

        val formatOtherTypes = "%-8s | %11s | %28s | %28s |"
        otherTypes.forEach { e -> println(e.toString(formatOtherTypes)) }
    }

    /**
     * 2. Консольный перехват ошибки
     * Постановка задачи: написать программу, в которой может случиться 5 случаев,
     * в случае которых может возникнуть ошибка.
     * Научиться эту ошибку перехватывать и не давать программе выключаться
     * (оповещать об ошибке, но не давать выключаться программе, продолжая её работу).
     */
    fun startCatchingConsoleErrors(){
        // 1. division by zero
        try {
            val list = listOf(10, 0 ,5, 6)
            val x = 10 / list[1]
            println("x = $x")
        } catch (e: Exception) {
            println("1) Error ${e.message}")
            println("1) Error ${e.cause}")
        }

        // 2. fail to convert string to number
        var number = -1
        try {
            number = "68 lera".toInt()
        } catch (e: Exception) {
            println("2) Error ${e.message}")
            number = 0
        } finally {
            println("number = $number")
        }

        // 3. null
        try {
            val list: MutableList<String?> = mutableListOf()
            list.add(null)
            val x = list[0]!! // !! - allow get NPE exception
            println(x)
        } catch (e: NullPointerException){
            println("3) Error ${e.message}")
        }

        // 4. ArrayIndexOutOfBoundsException
        try {
            val array = arrayOf(1, 2, 3, 4)
            println(array[10])
        } catch (e: ArrayIndexOutOfBoundsException){
            println("4) Error ${e.message}")
        }

        // 5. own exception
        fun checkAge(age: Int){
            if (age < 0){
                throw Exception("Invalid value $age. Age must be greater than 0")
            } else if (age > 115){
                throw Exception("Invalid value $age. Age must be less than 115")
            }
        }

        try {
            val age = listOf(0, -1, 150).get(2)
            checkAge(age)
        } catch (e: Exception) {
            println("5) Error ${e.message}")
        }

        println("END of the startCatchingConsoleErrors()")
    }

    /**
     * 3. Консольный калькулятор
     * Постановка задачи: написать программу, которая умеет выполнять следующие действия:
     * сложение, вычитание, умножение, деление и возведение в степень.
     * Количество чисел выбирайте произвольное
     * (простая задача - два числа, сложная задача - количество чисел вводит пользователь).
     */
    fun getCalculator(){
        class Calculator{
            fun sum(vararg numbers: Double): Double {
                return numbers.sum()
            }

            fun sub(vararg numbers: Double): Double {
                return numbers.reduce{sub, e -> sub - e}
            }

            fun multiply(vararg numbers: Double): Double {
                return numbers.reduce{mult, e -> mult * e}
            }
            fun divide(vararg numbers: Double): Double {
                if (numbers.any { it == 0.0 }) throw NullPointerException("/ by zero")
                return numbers.reduce{div, e -> div / e}
            }

            fun pow(exp: Double, vararg numbers: Double): List<Double> {
                return numbers.map{e -> Math.pow(e, exp)}
            }
        }

        val calc = Calculator()
        val nums = doubleArrayOf(10.0, 13.0, 40.0)
        val exp = 0.0
        println("nums = ${nums.joinToString(prefix = "[", postfix = "]")}") // more info abt joinToStr https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/join-to-string.html
        println("sum = ${calc.sum(*nums)}") // * - is spread operator. it needs for passing array to vararg. More info: https://www.baeldung.com/kotlin/varargs-spread-operator
        println("sub = ${calc.sub(*nums)}")
        println("multiply = ${calc.multiply(*nums)}")
        println("pow to $exp = ${calc.pow(exp, *nums)}")

        try {
            println("div = ${calc.divide(*nums)}")
        } catch (npe: NullPointerException){
            println("Error ${npe.message} ${npe.stackTrace}")
        }

    }

    /**
     * 4-8. Консольный массив
     * Постановка задачи: Разработать программу которая умеет выводить массив M x N.
     * Усложнение задачи: Сделать так, чтобы можно было производить какие-либо операции над массивами.
     * (К примеру чисел в массиве, нахождение общего знаменателя и так далее - подобные задачи можно найти в интернете).
     * Для того чтобы зачесть себе в карму усложнение задачи, решите как минимум 5 задач с матрицами.
     */
    fun startArrayTasks(){
        // val N = inputToConsole("N = ")
        // val M = inputToConsole("M = ")
        val N = 5 // строки
        val M = 4 // столбцы

        val table: Array<Array<Int>> = Array(N, { Array(M, {it -> Random.nextInt(0, 100) }) }) // randoms
        // val table: Array<Array<Int>> = Array(N, { Array(M, {it -> 10 * (it - 1)}) }) // it + 1 (from 1 to ...)
        // val table: Array<Array<Int>> = Array(N, { Array(M, {0}) }) // all is zero

        // println(table.contentToString()) // для одномерного массива
        for(row in table){
            for(cell in row){
                print("$cell \t")
            }
            println()
        }

        // 4. find min
        println("min = ${findMinInArray(table)}")

    }

    // 4. минимальное значение массива
    fun findMinInArray(array: Array<Array<Int>>): Int {
        var min = array[0][0]
        for (row in array){
            for(cell in row){
                if (cell <= min){
                    min = cell
                }
            }
        }
        return min
    }

    fun inputToConsole(msg: String): Int?{
        var res: Int? = null
        try {
            print(msg)
            res = readLine()?.toInt()
        } catch (e: Exception){
            println("Invalid value. Enter the integer number")
            inputToConsole(msg)
        }
        return res
    }
}