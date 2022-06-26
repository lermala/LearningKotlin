import java.lang.Integer.min

class YandexTasks {
    // from https://www.youtube.com/watch?v=30tchn0TjaM&ab_channel=alishev

    init {
        getMinIntersectionOfNumb(intArrayOf(1, 2 , 2, 2,2),
                                intArrayOf(2, 2, 1,4,5)
                                )
    }

    // вывод повторяющихся чисел N раз. N = минимальное количество пересечений чисел
    // [1, 2 , 2, 2,2] [2, 2, 1,4,5] -> [1, 2, 2] (порядок не важен)
    // вывод чисет столько раз, сколько они пересекаются в обоих множествах
    fun getMinIntersectionOfNumb(a1: IntArray, a2: IntArray){
        println("start")
        for (el in a1){
            var num = 0
            if (a2.contains(el)){ // if intersects
                // println(el) // out ALL intersect, but we need MIN count of intersects
                // so counting numb of count in both arrays
                num = min(a1.count { it == el }, a2.count { it == el })
                // then out result
                println("min=$num")

            }
            repeat(num, { println(el) }) // fix
        }
    }
}