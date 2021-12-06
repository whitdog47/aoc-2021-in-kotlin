package Day06

import readInput
import readTestInput

class Day06 {

    private fun part1(input: List<String>): Int {
        val ints = input[0].split(',').map { it.toInt()}.toMutableList()
        repeat(80) {
            var d =0
            ints.forEachIndexed { index, i ->
                ints[index]--
                if (ints[index] == -1) {
                    ints[index] = 6
                    d++
                }
            }
            repeat(d) {
                ints.add(8)
            }
        }
        return ints.size
    }

    private fun part2(input: List<String>): Long {
        val ints = input[0].split(',').map { it.toInt()}.toMutableList()
        var vals = mutableMapOf<Int, Long>()
        ints.forEach{
            val g = vals[it] ?: 0
            vals[it] =g + 1
        }
        repeat(256) {
            var d = 0L
            val newvals = mutableMapOf<Int, Long>()
            for (key in 0..8) {
                val g = vals[key] ?: 0
                if (key == 0) {
                    d = g
                } else {
                    newvals[key-1] = g
                }
            }
            newvals[6] = (newvals[6] ?: 0) + d
            newvals[8] = (newvals[8] ?: 0) + d
            vals = newvals
        }
        return vals.values.sum()
    }

    fun run() {
        val testInput = readTestInput("Day06")
        val input = readInput("Day06")
        check(part1(testInput) == 5934)
        println(part1(input))

        check(part2(testInput) == 26984457539)
        println(part2(input))
    }
}
