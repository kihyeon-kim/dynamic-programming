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

    @Test
    fun `3-3`() {
        fun maxSubStringLength(str: String): Int {
            val n = str.length
            var maxLen = 0

            for (i in 0 until n) {
                for (j in i + 1 until n step 2) { // 짝수 길이
                    var len = j - i + 1

                    if (maxLen >= len) {
                        continue
                    }

                    var (lSum, rSum) = Pair(0, 0)
                    for (k in 0 until len / 2) {
                        lSum += str[i + k].toInt()
                        rSum += str[i + k + len / 2].toInt()
                    }

                    if (lSum == rSum) {
                        maxLen = len
                    }
                }
            }

            return maxLen
        }

        println(maxSubStringLength("142124"))
        println(maxSubStringLength("9430723"))
    }

    @Test
    fun `3-4`() {
        fun maxSubStringLength(str: String): Int {
            val n = str.length
            var maxLen = 0

            val sum = (0..n).map { arrayOfNulls<Int>(n) }.toTypedArray()

            for (i in 0 until n) {
                sum[i][i] = str[i].toInt()
            }

            for (len in 2..n) {
                for (i in 0 until n - len + 1) { // 6 - 2 + 1
                    val j = i + len - 1
                    val k = len / 2

                    // 4, 5 = 4, 4 + 5, 5
                    // 2, 3 = 2, 2 + 3, 3
                    sum[i][j] = sum[i][j - k]!! + sum[j - k + 1][j]!! // 짝수 상관없이 부분 합 계산. i .. k .. j

                    if (len % 2 == 0 && sum[i][j - k] == sum[j - k + 1][j] && len > maxLen) {
                        maxLen = len
                    }
                }
            }

            return maxLen
        }

        println(maxSubStringLength("142124"))
        println(maxSubStringLength("9430723"))
    }

    @Test
    fun `3-5`() {
        // 하향식
        fun factorial(n: Int): Int {
            if (n == 1 || n == 0) {
                return 1
            }

            return n * factorial(n - 1)
        }
    }

    @Test
    fun `3-6`() {
        // 상향식
        fun factorial(n: Int): Int {
            var f = 1

            for (i in 2..n) {
                f *= i
            }

            return f
        }

        println(factorial(4))
    }

    @Test
    fun `3-7`() {
        class Node(
            val left: Node?,
            var data: Int,
            val right: Node?
        )

        fun addChildSum(root: Node?) {
            if (root == null)
                return

            addChildSum(root.left)

            addChildSum(root.right)

            var finalSum = root.data

            if (root.left != null) {
                finalSum += root.left.data
            }

            if (root.right != null) {
                finalSum += root.right.data
            }

            root.data = finalSum
        }
    }

    @Test
    fun `3-8`() {
        // 수학 조합 설명: https://m.blog.naver.com/2gumin14/221350308142
        fun combination(n: Int, m: Int): Int {
            return if (n == 0 || m == 0 || n == m) {
                1
            } else {
                // 첫번째 선택하고 나머지 n - 1 에서 m - 1 개를 선택하는경우 +
                // 첫번째를 선택하지 않는 경우 n - 1 에서 m 개를 선택하는경우
                combination(n - 1, m - 1) + combination(n - 1, m)
            }
        }
    }

}