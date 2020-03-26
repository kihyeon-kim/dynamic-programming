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

        //        fun accRec(inputArr: Array<Int>): Array<Int> {
        //            //            result[index] = inputArr[index] + inputArr[index - 1]
        //
        //            val size = inputArr.size
        //            if (size == 1) {
        //
        //            }
        //            val result = arrayOfNulls<Int>(1)
        //            return result.plus(accRec(inputArr.sliceArray((0 until size)))).requireNoNulls()
        //        }

        println(accNormal(arrayOf(1, 2, 3, 4, 5, 6)).joinToString(","))
    }

    @Test // 점화식이란: http://www.ktword.co.kr/word/abbr_view.php?m_temp1=6008
    fun `1-5`() {
        fun power(x: Int, n: Int): Int {
            return when {
                n == 0 -> 1
                x == 1 -> x
                else -> x * power(x, n - 1)
            }
        }

        println(power(2, 4))
    }

    @Test // 하노이
    fun `1-6`() {
        fun towerOfHanoi(s: Char, d: Char, e: Char, n: Int) {
            // 종료조건
            if (n <= 0) {
                return
            }

            towerOfHanoi(s, e, d, n - 1)
            println("$n 번 원반을 $s 에서 $d 로 옮깁니다")
            towerOfHanoi(e, d, s, n - 1)
        }

        println(towerOfHanoi('s', 'd', 'e', 3))
    }
}