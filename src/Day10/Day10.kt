package Day10

import readInput
import readTestInput

class Day10 {

    private fun part1(input: List<String>): Int {

        var score = 0
        input.forEach {
            val closing = mutableListOf<Char>()
            for (c in it) {
                if (c == '(' || c == '<' || c == '{' || c == '[') {
                    closing.add(c)
                } else {
                    if (closing.last() == opposite(c)) {
                        closing.removeLast()
                    } else {
                        score += scoreTable(c)
                        break
                    }
                }
            }
        }
        return score
    }

    fun opposite(c: Char): Char {
        return when (c) {
            ')' -> '('
            ']' -> '['
            '}' -> '{'
            '>' -> '<'
            else -> ' '
        }
    }

    fun scoreTable(c:Char): Int {
        return when (c) {
            ')' -> 3
            ']' -> 57
            '}' -> 1197
            '>' -> 25137
            else -> 0
        }
    }

    fun scoreTable2(c:Char): Int {
        return when (c) {
            '(' -> 1
            '[' -> 2
            '{' -> 3
            '<' -> 4
            else -> 0
        }
    }

    private fun part2(input: List<String>): Long {
        val scores = mutableListOf<Long>()
        input.forEach {
            val closing = mutableListOf<Char>()
            var inc = true
            for (c in it) {
                if (c == '(' || c == '<' || c == '{' || c == '[') {
                    closing.add(c)
                } else {
                    if (closing.last() == opposite(c)) {
                        closing.removeLast()
                    } else {
                        inc = false
                        break
                    }
                }
            }
            if (inc) {
                closing.reverse()
                var sc = 0L
                closing.forEach {
                    sc *= 5L
                    sc += scoreTable2(it)
                }
                scores += sc
            }
        }
        scores.sort()
        return scores[scores.size/2]
    }

    fun run() {
        val testInput = readTestInput("Day10")
        val input = readInput("Day10")
        check(part1(testInput) == 26397)
        println(part1(input))

        check(part2(testInput) == 288957L)
        println(part2(input))
    }
}
