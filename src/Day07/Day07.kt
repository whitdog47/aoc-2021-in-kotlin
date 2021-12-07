package Day07

import readInput
import readTestInput
import kotlin.math.abs

class Day07 {

    private fun part1(input: List<String>): Long {
        val ints = input.first().split(",").map { it.toInt() }
        var min = Integer.MAX_VALUE
        var max = 0
        ints.forEach {
            if (it < min) min = it
            if (it > max) max = it
        }
        var minfuel = Long.MAX_VALUE

        for (p in min..max) {
            var fuel = 0L
            ints.forEach {
                fuel += abs(it-p)
            }
            if (fuel < minfuel) {
                minfuel = fuel
            }
        }
        return minfuel
    }

    private fun part2(input: List<String>): Long {
        val ints = input.first().split(",").map { it.toInt() }
        var min = Integer.MAX_VALUE
        var max = 0
        ints.forEach {
            if (it < min) min = it
            if (it > max) max = it
        }
        var minfuel = Long.MAX_VALUE

        for (p in min..max) {
            var fuel = 0L
            ints.forEach {
                val n = abs(it-p)
                fuel += n*(n+1)/2 // thank you, Gauss
            }
            if (fuel < minfuel) {
                minfuel = fuel
            }
        }
        return minfuel
    }

    fun run() {
        val testInput = readTestInput("Day07")
        val input = readInput("Day07")
        check(part1(testInput) == 37L)
        println(part1(input))

        check(part2(testInput) == 168L)
        println(part2(input))
    }
}
