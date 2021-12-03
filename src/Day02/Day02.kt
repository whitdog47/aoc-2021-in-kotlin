package Day02

import readInputStringToInt
import readTestInputStringToInt

class Day02 {

    private fun part1(input: List<Pair<String, Int>>): Int {
        var h = 0
        var d = 0
        input.forEach {
            when (it.first) {
                "forward" -> h += it.second
                "down" -> d += it.second
                "up" -> d -= it.second
            }
        }
        return h * d
    }

    private fun part2(input: List<Pair<String, Int>>): Int {
        var h = 0
        var d = 0
        var aim = 0
        input.forEach {
            when (it.first) {
                "forward" ->{
                    h += it.second
                    d += aim * it.second
                }
                "down" -> aim += it.second
                "up" -> aim -= it.second
            }
        }
        return h * d
    }

    fun run() {
        val testInput = readTestInputStringToInt("Day02")
        val input = readInputStringToInt("Day02")
        check(part1(testInput) == 150)
        println(testInput)
        println(part1(input))

        check(part2(testInput) == 900)
        println(part2(input))
    }
}
