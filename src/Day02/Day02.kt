package Day02

import readInput
import readTestInput

class Day02 {

    private fun part1(input: List<String>): Int {
        return input.size
    }

    private fun part2(input: List<String>): Int {
        return input.size
    }

    fun run() {
        // test if implementation meets criteria from the description, like:
        val testInput = readTestInput("Day02")
        check(part1(testInput) == 1)
        //check(part2(testInput) == 1)

        val input = readInput("Day02")
        println(part1(input))
        //println(part2(input))
    }
}
