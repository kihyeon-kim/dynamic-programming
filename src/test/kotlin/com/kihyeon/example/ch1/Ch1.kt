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

}