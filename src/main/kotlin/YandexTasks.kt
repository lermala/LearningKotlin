import java.lang.Integer.min

class YandexTasks {
    // from https://www.youtube.com/watch?v=30tchn0TjaM&ab_channel=alishev

    init {
        println(getMinIntersectionOfNumb(intArrayOf(1, 2 , 2, 2, 2), intArrayOf(2, 2, 1, 4, 5)))
    }

    // 1. вывод повторяющихся чисел N раз. N = минимальное количество пересечений чисел
    // [1, 2 , 2, 2,2] [2, 2, 1,4, 5] -> [1, 2, 2] (порядок не важен)
    // вывод чисет столько раз, сколько они пересекаются в обоих множествах
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
}