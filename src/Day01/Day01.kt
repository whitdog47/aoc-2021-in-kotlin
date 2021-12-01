package Day01

import readInputAsInt
import readTestInputAsInt

class Day01 {

    private fun part1(input: List<Int>): Int {
        return input.windowed(2).count{(a,b) -> b>a}
    }

    private fun part2(input: List<Int>): Int {
        return part1(input.windowed(3){it.sum()})
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
