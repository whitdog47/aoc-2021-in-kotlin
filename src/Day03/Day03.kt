package Day03

import readInput
import readTestInput
import kotlin.math.pow

class Day03 {

    private fun part1(input: List<String>): Int {
        val h = input[0].length
        var g = 0.0
        var e = 0.0
        for (i in (0 until h)) {
            var c = 0
            input.forEach { if (it[i] == '1') c += 1 }
            if (c > input.size - c) {
                g += 2.0.pow(h-1-i.toDouble())
            } else {
                e += 2.0.pow(h-1-i.toDouble())
            }
        }
        return (g * e).toInt()
    }

    private fun part2(input: List<String>): Int {
        var oxyList = input
        var co2List = input
        val h = input[0].length
        for (i in (0 until h)) {
            var c = 0
            oxyList.forEach { if (it[i] == '1') c += 1 }
            oxyList = if (c >= oxyList.size - c) {
                oxyList.filter { it[i] == '1' }
            } else {
                oxyList.filter { it[i] == '0' }
            }
            if (oxyList.size == 1) break
        }
        for (i in (0 until h)) {
            var c = 0
            co2List.forEach { if (it[i] == '1') c += 1 }
            co2List = if (c < co2List.size - c) {
                co2List.filter { it[i] == '1' }
            } else {
                co2List.filter { it[i] == '0' }
            }
            if (co2List.size == 1) break
        }
        val oxy = Integer.parseInt(oxyList[0], 2)
        val co2 = Integer.parseInt(co2List[0], 2)
        return oxy * co2
    }

    fun run() {
        val testInput = readTestInput("Day03")
        val input = readInput("Day03")
        check(part1(testInput) == 198)
        println(part1(input))

        check(part2(testInput) == 230)
        println(part2(input))
    }
}
