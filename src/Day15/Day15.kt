package Day15

import readInput
import readTestInput

class Day15 {

    private fun part1(input: List<String>): Int {
        val maxX = input.first().length
        val maxY = input.size
        val matrix = Array(maxX) {Array(maxY) {0} }
        input.forEachIndexed { col, line ->
            line.forEachIndexed { row, char ->
                matrix[row][col] = char.digitToInt()
            }
        }
        return findLowestRisk(matrix, maxX, maxY)
    }

    data class Chiton(val point: Pair<Int,Int>, val risk: Int)

    private fun findLowestRisk(m: Array<Array<Int>>, maxX: Int, maxY: Int): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        val chitons = mutableListOf<Chiton>()
        chitons.add(Chiton(Pair(0, 0), 0))
        while (true) {
            val pos = chitons.removeAt(0)
            if (visited.contains(pos.point)) continue
            visited.add(pos.point)
            val x = pos.point.first
            val y = pos.point.second
            val risk = pos.risk
            if (x == maxX - 1 && y == maxY - 1) {
                return risk
            }
            if (x > 0) {
                chitons.add(Chiton(Pair(x - 1, y), risk + m[x - 1][y]))
            }
            if (x < maxX - 1) {
                chitons.add(Chiton(Pair(x + 1, y), risk + m[x + 1][y]))
            }
            if (y > 0) {
                chitons.add(Chiton(Pair(x, y - 1), risk + m[x][y - 1]))
            }
            if (y < maxY - 1) {
                chitons.add(Chiton(Pair(x, y + 1), risk + m[x][y + 1]))
            }
            chitons.sortBy { it.risk }
        }
    }

    private fun part2(input: List<String>): Int {
        val maxX = input.first().length
        val maxY = input.size
        val matrix = Array(maxX * 5) { Array(maxY * 5) { 0 } }
        input.forEachIndexed { col, line ->
            line.forEachIndexed { row, char ->
                repeat(5) { colMultiplier ->
                    repeat(5) { rowMultiplier ->
                        val digit = char.digitToInt() + colMultiplier + rowMultiplier
                        matrix[row + rowMultiplier * maxX][col + colMultiplier * maxY] = (digit - 1) % 9 + 1
                    }
                }
            }
        }
        return findLowestRisk(matrix, maxX * 5, maxY * 5)
    }

    fun run() {
        val testInput = readTestInput("Day15")
        val input = readInput("Day15")
        check(part1(testInput) == 40)
        println(part1(input))

        check(part2(testInput) == 315)
        println(part2(input))
    }
}
