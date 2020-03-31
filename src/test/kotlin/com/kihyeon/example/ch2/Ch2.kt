package com.kihyeon.example.ch2

import org.junit.Test

internal class Ch2 {
    @Test
    fun `2-1`() {
        fun fibonacci(n: Int): Int {
            return when (n) { // 0 이거나, 음수는 제외
                1, 2 -> 1
                else -> fibonacci(n - 1) + fibonacci(n - 2)
            }
        }
    }

    @Test
    fun `2-2`() {
        fun fibonacci(n: Int): Int {
            var a = 1
            var b = 1
            var c = 0

            if (n == 1 || n == 2) {
                return 1
            }

            for (cnt in 3..n) {
                c = a + b
                a = b
                b = c
            }

            return c
        }

        println(fibonacci(5))
    }

    @Test
    fun `2-3`() {
        val cost: Array<IntArray> = arrayOf(
            intArrayOf(0, 10, 75, 94),
            intArrayOf(-1, 0, 35, 50),
            intArrayOf(-1, -1, 0, 80),
            intArrayOf(-1, -1, -1, 0)
        )

        // minCost(0, N-1) = MIN{cost[0][N-1], cost[0][1] + minCost(1, N-1), minCost(0, 2) + minCost(2, N-1) ... , minCost(0, N-2) + cost[N-2][N-1]}

        // 1. 출발역과 도착역이 같은 경우
        // >> return 0
        // 2. 도착역이 출발역 바로 다음 역인 경우
        // >> cost[s][d]
        // >>>> s == d || s == d-1 return cost[s][d]

        fun minCost(s: Int, d: Int): Int {
            if (s == d || s == d - 1) {
                return cost[s][d]
            }

            var minValue = cost[s][d]

            (s + 1 until d) // s .. it .. d-1
                .forEach {
                    val temp = minCost(s, it) + minCost(it, d)
                    if (temp < minValue) {
                        minValue = temp
                    }
                }

            return minValue
        }

        println(minCost(0, 3))
    }

    @Test
    fun `2-4`() {
        val size = 10
        val memo = arrayOfNulls<Int>(size + 1)

        fun fibonacci(n: Int): Int {
            if (memo[n] != null) {
                return memo[n]!!
            }

            if (n == 1 || n == 2) {
                memo[n] = 1
            } else {
                memo[n] = fibonacci(n - 1) + fibonacci(n - 2)
            }
            return memo[n]!!
        }

        println(fibonacci(size))
    }

    @Test
    fun `2-5`() {
        val cost: Array<IntArray> = arrayOf(
            intArrayOf(0, 10, 75, 94),
            intArrayOf(-1, 0, 35, 50),
            intArrayOf(-1, -1, 0, 80),
            intArrayOf(-1, -1, -1, 0)
        )

        val memo = arrayOf(
            arrayOfNulls(4),
            arrayOfNulls(4),
            arrayOfNulls(4),
            arrayOfNulls<Int>(4)
        )

        fun minCost(s: Int, d: Int): Int {
            if (s == d || s == d - 1) {
                return cost[s][d]
            }

            // 값이 계산되지 않은 경우
            if (memo[s][d] == null) {
                var minValue = cost[s][d]

                (s + 1 until d)
                    .forEach {
                        val temp = minCost(s, it) + minCost(it, d)
                        if (temp < minValue) {
                            minValue = temp
                        }
                    }

                memo[s][d] = minValue
            }

            return memo[s][d]!!
        }

        println(minCost(0, 3))

        for (i in memo.indices) {
            for (j in memo[i].indices) {
                print(" ${memo[i][j]}")
            }
            println()
        }
    }
}