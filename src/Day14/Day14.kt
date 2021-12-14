package Day14

import readInput
import readTestInput

class Day14 {

    private fun part1(input: List<String>): Int {
        var poly = input.first()
        val pairs = mutableListOf<Pair<String, String>>()
        input.forEachIndexed { index, line ->
            if (index > 1) {
                val rule = line.split(" -> ")
                pairs.add(Pair(rule[0], rule[1]))
            }
        }
        var tempPairs = ""
        repeat(10) {
            val inserts = mutableListOf<Pair<Int, String>>()
            pairs.forEach {
                var lastIndex = 0
                while (lastIndex >= 0) {
                    val index = poly.indexOf(it.first, lastIndex)
                    if (index >= 0) {
                        inserts.add(Pair(index, it.second))
                    } else {
                        break
                    }
                    lastIndex = index+1
                }
            }
            tempPairs = ""
            repeat(poly.length) { i->
                tempPairs += poly[i]
                inserts.forEach {
                    if (it.first == i) tempPairs += it.second
                }
            }
            poly=tempPairs
        }
        var min = Int.MAX_VALUE
        var max = 0
        tempPairs.forEach {
            val charCount = countOccurrences(tempPairs, it)
            if (charCount < min) {
                min = charCount
            }
            if (charCount > max) {
                max = charCount
            }
        }
        return max - min
    }

    private fun countOccurrences(s: String, ch: Char): Int {
        return s.filter { it == ch }.count()
    }

    private fun part2(input: List<String>): Long {
        val poly = input.first()
        val rules = mutableListOf<Pair<String, String>>()
        input.forEachIndexed { index, line ->
            if (index > 1) {
                val rule = line.split(" -> ")
                rules.add(Pair(rule[0], rule[1]))
            }
        }
        // split poly into pairs
        var pairs = mutableMapOf<String, Long>()
        val counts = mutableMapOf<Char, Long>()
        repeat(poly.length-1) { i->
            pairs[poly.substring(i, i+2)] = (pairs[poly.substring(i, i+2)] ?: 0) +1
        }
        repeat(poly.length) { i ->
            counts[poly[i]] = (counts[poly[i]] ?: 0) + 1
        }
        repeat(40) {
            val pp = pairs.toMutableMap()
            rules.forEach { rule ->
                pairs.forEach { pair ->
                    if (pair.value > 0 && rule.first == pair.key) {
                        pp[pair.key] = (pp[pair.key] ?: 0) - pair.value
                        pp[pair.key[0] + rule.second] = (pp[pair.key[0] + rule.second] ?: 0) + pair.value
                        pp[rule.second + pair.key[1]] = (pp[rule.second + pair.key[1]] ?: 0) + pair.value
                        counts[rule.second[0]] = (counts[rule.second[0]] ?: 0) + pair.value
                    }
                }
            }
            pairs = pp.toMutableMap()
        }
        var min = Long.MAX_VALUE
        var max = 0L
        counts.forEach {
            val v = it.value
            if (v < min) {
                min = v
            }
            if (v > max) {
                max = v
            }
        }
        return max - min
    }

    fun run() {
        val testInput = readTestInput("Day14")
        val input = readInput("Day14")
        check(part1(testInput) == 1588)
        println(part1(input))

        check(part2(testInput) == 2188189693529L)
        println(part2(input))
    }
}
