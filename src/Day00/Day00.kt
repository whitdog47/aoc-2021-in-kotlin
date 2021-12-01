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
        // test if implementation meets criteria from the description, like:
        val testInput = readTestInput("Day00")
        check(part1(testInput) == 1)
        //check(part2(testInput) == 1)

        val input = readInput("Day00")
        println(part1(input))
        //println(part2(input))
    }
}
