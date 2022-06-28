package challenge

class Console {
    // https://smartprogress.do/programming

    init {
        // getDataTypesInfo()
        startCatchingConsoleErrors()
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
            println(e.message)
        }

        // 2. fail to convert string to number
        var number = -1
        try {
            number = "68 lera".toInt()
        } catch (e: Exception) {
            println(e.message)
            number = 0
        } finally {
            println("number = $number")
        }

        // 3. null


        println("END of the startCatchingConsoleErrors()")
    }
}