package Day04

import readInput
import readTestInput

class Day04 {

    private fun part1(input: List<String>, draw: String): Int {
        return getResult(input, draw) { boards: Array<Array<Array<Int>>>, numBoards: Int ->
            testBoards(boards, numBoards, false)
        }
    }

    private fun getResult(input: List<String>, s: String, testBoard: (Array<Array<Array<Int>>>, Int) -> Int): Int {
        val n = input.size / 5
        val boards = Array(n) {Array(5) {Array(5) {0} }}
        var numBoards = 0
        var col = 0
        input.forEach {
            if (it.isNotBlank()) {
                val pos = it.split(" ")
                var row = 0
                pos.forEach {
                    if (it.isNotBlank()) {
                        boards[numBoards][col][row] = it.toInt()
                        row++
                    }
                }
                col++
                if (col == 5) {
                    col = 0
                    numBoards++
                }
            }
        }
        val num = s.split(",")
        num.forEach{
            val numint = it.toInt()
            for (board in (0 until numBoards)) {
                for (col in (0 until 5)) {
                    for (row in (0 until 5)) {
                        if (boards[board][col][row] ==numint) {
                            boards[board][col][row] = -1
                        }
                    }
                }
            }
            val t = testBoard.invoke(boards, numBoards)
            if (t > 0) {
                return t * numint
            }
        }
        return 0
    }

    private fun testBoards(boards: Array<Array<Array<Int>>>, numBoards : Int, findLast: Boolean ): Int {
        for (board in (0 until numBoards)) {
            for (col in (0 until 5)) {
                val colProduct = boards[board][col].fold(1){acc, i -> acc * i}
                if (colProduct == -1) {
                    // winner
                    if (!findLast) {
                        return sumBoard(boards[board])
                    }
                    if (!gccSet.contains(board)) {
                        gccSet.add(board)
                        if (gccSet.size == numBoards) {
                            return sumBoard(boards[board])
                        }
                    }
                }
            }
            for (row in (0 until 5)) {
                val rowProduct = boards[board].fold(1) {acc, i -> acc * i[row]}
                if (rowProduct == -1) {
                    // winner
                    if (!findLast) {
                        return sumBoard(boards[board])
                    }
                    if (!gccSet.contains(board)) {
                        gccSet.add(board)
                        if (gccSet.size == numBoards) {
                            return sumBoard(boards[board])
                        }
                    }
                }
            }

        }
        return 0
    }

    private fun sumBoard(board: Array<Array<Int>>): Int {
        var sum = 0
        for (col in (0 until 5)) {
            for (row in (0 until 5)) {
                if (board[col][row] != -1) {
                    sum += board[col][row]
                }
            }
        }
        return sum
    }

    private var gccSet = mutableSetOf<Int>()

    private fun part2(input: List<String>, draw: String): Int {
        return getResult(input, draw) { boards: Array<Array<Array<Int>>>, numBoards: Int ->
            testBoards(boards, numBoards, true)
        }
    }

    fun run() {
        val testInput = readTestInput("Day04")
        val input = readInput("Day04")
        check(part1(testInput, "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1") == 4512)
        println(part1(input, "87,12,53,23,31,70,37,79,95,16,72,9,98,92,5,74,17,60,96,80,75,11,73,33,3,84,81,2,97,93,59,13,77,52,69,83,51,64,48,82,7,49,20,8,36,66,19,0,99,41,91,78,42,40,62,63,57,39,55,47,29,50,58,34,27,43,30,35,22,28,4,14,26,32,10,88,46,65,90,76,38,6,71,67,44,68,86,25,21,24,56,94,18,89,61,15,1,45,54,85"))

        check(part2(testInput, "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1") == 1924)
        println(part2(input, "87,12,53,23,31,70,37,79,95,16,72,9,98,92,5,74,17,60,96,80,75,11,73,33,3,84,81,2,97,93,59,13,77,52,69,83,51,64,48,82,7,49,20,8,36,66,19,0,99,41,91,78,42,40,62,63,57,39,55,47,29,50,58,34,27,43,30,35,22,28,4,14,26,32,10,88,46,65,90,76,38,6,71,67,44,68,86,25,21,24,56,94,18,89,61,15,1,45,54,85"))
    }
}
