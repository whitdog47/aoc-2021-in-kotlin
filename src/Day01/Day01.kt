package Day01

import readInputAsInt
import readTestInputAsInt

class Day01 {

    private fun part1(input: List<Int>): Int {
        var count = 0
        var prev = input[0]
        input.forEach {
            if (it > prev) {
                count++
            }
            prev = it
        }
        return count
    }

    private fun part2(input: List<Int>): Int {
        return part1(input.windowed(3, 1).map{ it.sum() })
    }

    private fun getInputWindow(input: List<Int>, i: Int) : Int {
        return input[i] + input[i+1] + input[i+2]
    }

    fun run() {
        val testInput = readTestInputAsInt("Day01")
        check(part1(testInput) == 7)
        check(part2(testInput) == 5)

        val input = readInputAsInt("Day01")
        println(part1(input))
        println(part2(input))
    }
}
