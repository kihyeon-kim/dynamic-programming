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

    @Test
    fun `1-7`() {
        class Node(
            val next: Node?,
            val data: Int
        )

        fun traverse1(head: Node?) {
            if (head != null) {
                traverse1(head.next)
                println("${head.data}")
            }
        }

        fun traverse2(head: Node?) {
            if (head != null) {
                println("${head.data}")
                traverse2(head.next)
            }
        }

        val testList = Node(
            Node(
                Node(
                    Node(
                        null,
                        4
                    ),
                    3
                ),
                2
            ),
            1
        )

        println("# traverse1")
        println(traverse1(testList))
        println("# traverse2")
        println(traverse2(testList))
    }

    @Test // 중위 순회 탐색 함수
    fun `1-8`() {
        class Node(
            val left: Node?,
            val data: Int,
            val right: Node?
        )

        // 이 코드는 선행 재귀 또는 후행 재귀 어느쪽에도 해당되지 않는다
        fun inOrder(ptr: Node?) {
            if (ptr == null) {
                return
            }

            inOrder(ptr.left)
            println("data: ${ptr.data}")
            inOrder(ptr.right)
        }

        fun inOrder2(ptr: Node?) {
            if (ptr == null) {
                return
            }

            if (ptr.left != null) {
                inOrder2(ptr.left)
            }

            println("data: ${ptr.data}")

            if (ptr.right != null) {
                inOrder2(ptr.right)
            }
        }
    }

    @Test
    fun `1-10`() {

        // void swap(int *a, int *b) {
        //     *a ^= *b;
        //     *b ^= *a;
        //     *a ^= *b;
        // }

        fun bubbleSort(arr: Array<Int?>, n: Int) {
            for (i in 0 until n - 1) { // 0 .. 6
                for (j in 0 until n - i - 1) { // 0 .. 5
                    if (arr[j]!! > arr[j + 1]!!) {
                        // swap
                        val temp = arr[j]
                        arr[j] = arr[j + 1]
                        arr[j + 1] = temp
                    }
                }
            }
        }

        val arr: Array<Int?> = arrayOf(9, 6, 2, 12, 11, 9, 3, 7)
        bubbleSort(arr, 8)
        println(arr.joinToString())
    }

    // 함수가 수행해야 하는 작업: 1회 탐색을 통해 가장 큰 원소를 제일 뒤로 보내기
    // 큰 문제를 같은 유형의 작은 문제로 정의하기: n개 원소의 정렬 = 1회의 탐색 + n - 1개 원소의 정렬
    // 종료조건: 탐색 대상의 배열의 크기가 1 이하일 때 종료

    @Test
    fun `1-11`() {
        fun bubbleSort(arr: Array<Int?>, n: Int) {
            if (n < 1) {
                return
            }

            for (j in 0 until n - 1) {
                if (arr[j]!! > arr[j + 1]!!) {
                    // swap
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }

            bubbleSort(arr, n - 1)
        }

        val arr: Array<Int?> = arrayOf(9, 6, 2, 12, 11, 9, 3, 7)
        bubbleSort(arr, 8)
        println(arr.joinToString())
    }

    @Test
    fun `Q) 1-3`() {
        fun printTable(n: Int) {
            for (i in 1..10) {
                println("$n * $i = ${n * i}")
            }
        }

        printTable(10)

        fun printTableRec(n: Int, i: Int) {
            if (i < 1) {
                return
            }

            printTableRec(n, i - 1)
            println("$n * $i = ${n * i}")
        }

        printTableRec(10, 10)
    }

    @Test
    fun `1-19`() {
        fun factorial(n: Int): Int {
            return if (n == 1 || n == 0) {
                1
            } else {
                n * factorial(n - 1)
            }
        }
    }

    @Test
    fun `1-20`() {
        fun factorial(n: Int): Int {
            var f = 1
            for (i in 2..n) {
                f *= i
            }

            return f
        }

        println(factorial(4))
    }
}