package com.kihyeon.example.ch3

import org.junit.Test

internal class Ch3 {

    @Test
    fun `3-1`() {
        fun fibonacci(n: Int): Int {
            val arr = arrayOfNulls<Int>(n + 1)
            arr[1] = 1
            arr[2] = 1

            for (i in 3..n) {
                arr[i] = arr[i - 1]!! + arr[i - 2]!!
            }

            return arr[n]!!
        }

        println(fibonacci(10))
    }

    // 0 .. 4
    // minCost[0] + cost[0][4]
    // minCost[1] << 이미 계산됨 + cost[1][4]. 1번에서 갈아탐
    // minCost[2] << 이미 계산됨 + cost[2][4]. 2번에서 갈아탐
    // minCost[3] << 이미 계산됨 + cost[3][4]. 3번에서 갈아탐
    @Test
    fun `3-2`() {
        val cost: Array<IntArray> = arrayOf(
            intArrayOf(0, 10, 75, 94),
            intArrayOf(-1, 0, 35, 50),
            intArrayOf(-1, -1, 0, 80),
            intArrayOf(-1, -1, -1, 0)
        )

        fun minCost(n: Int): Int {
            val minValue = arrayOfNulls<Int>(n + 1)
            minValue[0] = 0
            minValue[1] = cost[0][1]

            for (i in 2..n) {
                minValue[i] = cost[0][i]
                for (j in 1 until i) {
                    if (minValue[i]!! > minValue[j]!! + cost[j][i]) {
                        minValue[i] = minValue[j]!! + cost[j][i]
                    }
                }
            }

            return minValue[n]!!
        }

        println(minCost(3))
    }

}