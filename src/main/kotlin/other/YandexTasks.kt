package other

import java.lang.Integer.min

class YandexTasks {
    // from https://www.youtube.com/watch?v=30tchn0TjaM&ab_channel=alishev

    init {
        val task1 = "Вывод повторяющихся чисел N раз. N = минимальное количество пересечений чисел"
        val task2 = "Вывести букву и рядом количество ее встреч подряд"
        val task3 = "Группировка по общим буквам"

        val result1 = getMinIntersectionOfNumb(intArrayOf(1, 2 , 4, 2, 7), intArrayOf(2, 2, 1, 4, 5))
        val result2 = getLettersAndAmountsInARow("AAAABBBCCXYZAAAAABBBBBBBBBBB")
        val result3 = groupByCommonLetters(listOf("eat", "tea", "ate", "tan", "nat", "bat"))

        val beauty = BeautifulOutput()
        println(beauty.getConditionalAndResult(task1, result1))
        println(beauty.getConditionalAndResult(task2, result2))
        println(beauty.getConditionalAndResult(task3, result3))
    }

    // 1. вывод повторяющихся чисел N раз. N = минимальное количество пересечений чисел
    // [1, 2 , 2, 2,2] [2, 2, 1,4, 5] -> [1, 2, 2] (порядок не важен)
    // вывод чисел столько раз, сколько они пересекаются в обоих множествах
    fun getMinIntersectionOfNumb(a1: IntArray, a2: IntArray): MutableList<Int>{
        var tempA1: List<Int> = a1.toMutableList()
        var tempA2: List<Int> = a2.toMutableList()

        val result: MutableList<Int> = mutableListOf()

        for (el in tempA1){
            if (tempA2.contains(el)){ // if intersects
                // println(el) // out ALL intersect, but we need MIN count of intersects
                // so counting numb of count in both arrays
                val num = min(tempA1.count { it == el }, tempA2.count { it == el })

                // remove duplicates of this numb
                tempA1 = tempA1.filter { it != el }
                tempA2 = tempA2.filter { it != el }

                repeat(num, { result.add(el) }) // then out result
            }
        }
        return result
    }

    // вывести букву и рядом количество ее встреч подряд (in a row)
    // AAAABBBCCXYZAAAAABBBBBBBBBBB -> A4B3C2XYZA5B8
    fun getLettersAndAmountsInARow(string: String): String {
        var res = ""
        var lastChar = string[0]
        var numbOfRepet = 0 // the numb of char repet in string in a row
        for (ch in string){
            if (ch == lastChar){
                numbOfRepet++
            }
            else {
                if (numbOfRepet == 1) { // if no repets (only one char)
                    res += "$lastChar"
                } else {
                    res += "$lastChar$numbOfRepet"
                    numbOfRepet = 1 //
                }
            }
            lastChar = ch
        }

        // last char handling (outputting)
        if (numbOfRepet == 1) { // if no repets (only one char)
            res += "$lastChar"
        } else {
            res += "$lastChar$numbOfRepet"
        }

        return res
    }

    // группировка по общим буквам
    // [eat tea ate tan nat bat] -> [[ate, tea, eat], [bat], [tan, nat]]
    fun groupByCommonLetters(sArray: List<String>): Collection<List<String>> {
        return sArray.groupBy { it.toCharArray().sorted() }.values
    }
}