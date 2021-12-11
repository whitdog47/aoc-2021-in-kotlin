package Day11

import readInput
import readTestInput

class Day11 {
    private val listflashes = mutableListOf<Pair<Int, Int>>()
    private var flashes = 0

    private fun part1(input: List<String>): Int {
        listflashes.clear()
        flashes = 0
        val matrix = loadMatrix(input)
        repeat(100) {
            iterateMap(matrix)
            listflashes.forEach {
                matrix[it.first][it.second] = 0
            }
            listflashes.clear()
        }
        return flashes
    }

    private fun iterateMap(matrix: Array<Array<Int>>) {
        for (r in 0..9) {
            for (c in 0..9) {
                matrix[r][c]++
                if (matrix[r][c] > 9) {
                    flash(matrix, r, c)
                }
            }
        }
    }

    private fun loadMatrix(input: List<String>): Array<Array<Int>> {
        val matrix = Array(10) {Array(10) {0} }
        var r = 0
        var c = 0
        input.forEach { line ->
            line.forEach {
                val a = it.digitToInt()
                matrix[r][c] = a
                r++
            }
            r = 0
            c++
        }
        return matrix
    }

    private fun flash(matrix: Array<Array<Int>>, r: Int, c: Int) {
        if (listflashes.contains(Pair(r,c))) return
        listflashes.add(Pair(r,c))
        flashes++
        if (r > 0) {
            //left
            matrix[r-1][c]++
            if (matrix[r-1][c] == 10) {
                flash(matrix, r-1, c)
            }
            if (c > 0) {
                //upper left
                matrix[r-1][c-1]++
                if (matrix[r-1][c-1] == 10) {
                    flash(matrix, r-1, c-1)
                }
            }
            //lower left
            if (c < 9) {
                matrix[r-1][c+1]++
                if (matrix[r-1][c+1] == 10) {
                    flash(matrix, r-1, c+1)
                }
            }
        }
        if (c > 0) {
            //up
            matrix[r][c-1]++
            if (matrix[r][c-1] == 10) {
                flash(matrix, r, c-1)
            }
        }
        if (r < 9) {
            //right
            matrix[r+1][c]++
            if (matrix[r+1][c] == 10) {
                flash(matrix, r+1, c)
            }
            //lower right
            if (c < 9) {
                matrix[r+1][c+1]++
                if (matrix[r+1][c+1]== 10) {
                    flash(matrix, r+1, c+1)
                }
            }
            //lower left
            if (c > 0) {
                matrix[r+1][c-1]++
                if (matrix[r+1][c-1] == 10) {
                    flash(matrix, r+1, c-1)
                }
            }
        }
        //down
        if (c < 9) {
            matrix[r][c+1]++
            if (matrix[r][c+1] == 10) {
                flash(matrix, r, c+1)
            }
        }
    }

    private fun part2(input: List<String>): Int {
        listflashes.clear()
        flashes = 0
        val matrix = loadMatrix(input)
        var step = 0
        while (true) {
            step++
            iterateMap(matrix)
            if (listflashes.size == 100) {
                return step
            }
            listflashes.forEach { point ->
                matrix[point.first][point.second] = 0
            }
            listflashes.clear()
        }
    }

    fun run() {
        val testInput = readTestInput("Day11")
        val input = readInput("Day11")
        check(part1(testInput) == 1656)
        println(part1(input))

        check(part2(testInput) == 195)
        println(part2(input))
    }
}
