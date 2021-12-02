package Day02

import readInput
import readTestInput

class Day02 {

    private fun part1(input: List<String>): Int {
        var h = 0
        var d = 0
        input.forEach {
            val part = it.split(" ")
            val x = part[1].toInt()
            when (part[0]) {
                "forward" -> h += x
                "down" -> d += x
                "up" -> d -= x
            }
        }
        return h * d
    }

    private fun part2(input: List<String>): Int {
        var h = 0
        var d = 0
        var aim = 0
        input.forEach {
            val part = it.split(" ")
            val x = part[1].toInt()
            when (part[0]) {
                "forward" ->{
                    h += x
                    d += aim * x
                }
                "down" -> aim += x
                "up" -> aim -= x
            }
        }
        return h * d
    }

    fun run() {
        val testInput = readTestInput("Day02")
        val input = readInput("Day02")
        check(part1(testInput) == 150)
        println(part1(input))

        check(part2(testInput) == 900)
        println(part2(input))
    }
}
