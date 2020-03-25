package com.kihyeon.example.ch1

import kotlin.test.Test

internal class Ch1 {

    @Test
    fun `1-1`() {
        fun sum(n: Int): Int {
            return if (n == 1)
                1
            else
                n + sum(n - 1)
        }

        println(sum(10))
    }

    @Test
    fun `1-2`() {
        fun sum(n: Int): Int {
            if (n <= 0)
                return 0

            if (n == 1)
                return 1

            return n + sum(n - 1)
        }

        println(sum(0))
        println(sum(10))
    }

    // 1-3. 쉬운 코드와 복잡한 코드 중에서 선택해야 한다면, 성능이나 메모리의 이점이 있지 않은한(!) 쉬운 코드가 좋습니다.

    @Test
    fun `1-4`() {
        fun sum(n: Int): Int {
            var sum = 0

            (1..n).forEach {
                sum += it
            }
            // for (i in 1..n) { }

            return sum
        }

        println(sum(10))
    }

    @Test
    fun `Q) 1-1`() {
        fun factRec(n: Int): Int {
            if (n == 1) {
                return 1
            }

            // if (n > 1) {}
            return n * factRec(n - 1)
        }

        fun factNormal(n: Int): Int {
            var fact = 1

            (1..n).forEach {
                fact *= it
            }

            return fact
        }

        println(factRec(4))
        println(factNormal(4))
    }

    @Test
    fun `Q) 1-2`() {
        fun accNormal(inputArr: Array<Int>): Array<Int> {
            var sum = 0
            val result = arrayOfNulls<Int>(inputArr.size)

            for (i in inputArr.indices) {
                sum += inputArr[i]
                result[i] = sum
            }

            return result.requireNoNulls()
        }

        // fun accRec(inputArr: Array<Int>): Array<Int> {
        //     result[index] = inputArr[index] + inputArr[index - 1]
        // }

        println(accNormal(arrayOf(1, 2, 3, 4, 5, 6)).joinToString(","))
    }

}