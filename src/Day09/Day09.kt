package Day09

import readInput
import readTestInput

class Day09 {

    private fun part1(input: List<String>): Int {
        var lowPoint = 0
        input.forEachIndexed { row, s ->
            s.forEachIndexed { col, c ->
                val a = c.digitToInt()
                var less = true
                //left
                if (col > 0) {
                    if (a >= s[col-1].digitToInt()) less = false
                }
                //right
                if (col < s.length - 1) {
                    if (a >= s[col+1].digitToInt()) less = false
                }
                //up
                if (row > 0) {
                    if (a >= input[row-1][col].digitToInt()) less = false
                }
                //down
                if (row < input.size - 1) {
                    if (a >= input[row+1][col].digitToInt()) less = false
                }
                if (less) {
                    lowPoint += (a+1)
                }
            }
        }
        return lowPoint
    }

    private fun part2(input: List<String>): Int {
        val matrix = Array(input.size) {Array(input.first().length) {0} }
        val lowPoints = mutableListOf<Pair<Int, Int>>()
        input.forEachIndexed { row, s ->
            s.forEachIndexed { col, c ->
                val a = c.digitToInt()
                matrix[row][col] = a
                var less = true
                //left
                if (col > 0) {
                    if (a >= s[col-1].digitToInt()) less = false
                }
                //right
                if (col < s.length - 1) {
                    if (a >= s[col+1].digitToInt()) less = false
                }
                //up
                if (row > 0) {
                    if (a >= input[row-1][col].digitToInt()) less = false
                }
                //down
                if (row < input.size - 1) {
                    if (a >= input[row+1][col].digitToInt()) less = false
                }
                if (less) {
                    lowPoints += Pair(row,col)
                }
            }
        }
        val largest = mutableListOf<Int>()
        lowPoints.forEach {
            val visited = Array(input.size) {Array(input.first().length) {false} }
            val size = doSomeWork(it, matrix, visited,  input.size-1, input.first().length-1)
            largest.add(size)
        }
        var product = 1
        largest.sort()
        largest.reverse()
        val topThree = largest.take(3)
        topThree.forEach {
            product *= it
        }
        return product
    }

private fun doSomeWork(
    currentLocation: Pair<Int, Int>,
    matrix: Array<Array<Int>>,
    visited: Array<Array<Boolean>>,
    maxRow: Int,
    maxCol: Int
): Int {
    val row = currentLocation.first
    val col = currentLocation.second
    if (visited[row][col]) return 0
    val cur = matrix[row][col]
    if (cur == 9) return 0
    visited[row][col] =true
    var size = 1
    if (row > 0 && matrix[row - 1][col] > cur) {
        size += doSomeWork(Pair(row-1, col), matrix, visited, maxRow, maxCol)
    }
    if (row < maxRow && matrix[row+1][col] > cur) {
        size += doSomeWork(Pair(row+1, col), matrix, visited, maxRow, maxCol)
    }
    if (col > 0 && matrix[row][col-1] > cur) {
        size += doSomeWork(Pair(row, col-1), matrix, visited, maxRow, maxCol)
    }
    if (col < maxCol && matrix[row][col+1] > cur) {
        size += doSomeWork(Pair(row, col+1), matrix, visited, maxRow, maxCol)
    }
    return size
}


    fun run() {
        val testInput = readTestInput("Day09")
        val input = readInput("Day09")
        check(part1(testInput) == 15)
        println(part1(input))

        check(part2(testInput) == 1134)
        println(part2(input))
    }
}
