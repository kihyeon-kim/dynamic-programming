package com.kihyeon.example.ch4

import org.junit.Test
import kotlin.math.min

internal class Ch4 {

    @Test
    fun `4-1`() {
        val M = 4
        val N = 3
        val cost = (0..M).map { intArrayOf(N) }.toTypedArray()
        fun minPathCost(cost: Array<IntArray>, m: Int, n: Int): Int {
            val x = minPathCost(cost, m - 1, n)
            val y = minPathCost(cost, m, n - 1)

            return min(x, y) + cost[m][n]
        }
    }

    @Test
    fun `4-2`() {
        // 특별한경우
        // 1. m, n 모두 0인경우. 출발점.
        // 2. m = 0, n != 0 인경우. 맨위쪽.
        // 3. n = 0, m != 0 인경우. 맨왼쪽.
        val M = 4
        val N = 3
        val cost = (0..M).map { intArrayOf(N) }.toTypedArray()
        fun minPathCost(cost: Array<IntArray>, m: Int, n: Int): Int {
            if (m == 0 && n == 0) {
                return 0
            }

            if (m == 0) {
                return minPathCost(cost, 0, n - 1) + cost[0][n]
            }
            if (n == 0) {
                return minPathCost(cost, m - 1, 0) + cost[m][0]
            }

            val x = minPathCost(cost, m - 1, n)
            val y = minPathCost(cost, m, n - 1)

            return min(x, y) + cost[m][n]
        }
    }

    @Test
    fun `4-3`() {
        // 메모 전략
        val M = 4
        val N = 3
        val cost = (0..M).map { arrayOfNulls<Int>(N) }.toTypedArray()
        val mem = (0..M).map { arrayOfNulls<Int>(N) }.toTypedArray()

        fun minPathCost(cost: Array<Array<Int?>>, m: Int, n: Int): Int {
            if (mem[m][n] != null) {
                return mem[m][n]!!
            }

            if (m == 0 && n == 0) {
                return 0
            }

            if (m == 0) {
                return minPathCost(cost, 0, n - 1) + cost[0][n]!!
            }
            if (n == 0) {
                return minPathCost(cost, m - 1, 0) + cost[m][0]!!
            }

            val x = minPathCost(cost, m - 1, n)
            val y = minPathCost(cost, m, n - 1)

            return min(x, y) + cost[m][n]!!
        }
    }

    @Test
    fun `4-4`() {
        // 모든 경로의 비용을 미리 계산해둠
        // 0,0 0,1 .. 0,M.  1,0 2,0 .. N,0. 1,1 ..

        val M = 4
        val N = 3
        val cost = (0..M).map { arrayOfNulls<Int>(N) }.toTypedArray()
        val mem = (0..M).map { arrayOfNulls<Int>(N) }.toTypedArray()

        fun minPathCost(cost: Array<Array<Int?>>): Int {
            mem[0][0] = cost[0][0]

            for (j in 1 until N) {
                mem[0][j] = mem[0][j - 1]!! + cost[0][j]!!
            }

            for (i in 1 until M) {
                mem[i][0] = mem[i - 1][0]!! + cost[i][0]!!
            }

            for (i in 1 until M) {
                for (j in 1 until N) {
                    mem[i][j] = min(mem[i - 1][j]!!, mem[i][j - 1]!!) + cost[i][j]!!
                }
            }

            return mem[M - 1][N - 1]!!
        }
    }

}