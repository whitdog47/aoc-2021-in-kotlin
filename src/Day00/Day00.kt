package Day00

import readInput
import readTestInput

class Day00 {

    private fun part1(input: List<String>): Int {
        return input.size
    }

    private fun part2(input: List<String>): Int {
        return input.size
    }

    fun run() {
        val testInput = readTestInput("Day00")
        val input = readInput("Day00")
        check(part1(testInput) == 1)
        //println(part1(input))

        //check(part2(testInput) == 1)
        //println(part2(input))
    }
}
