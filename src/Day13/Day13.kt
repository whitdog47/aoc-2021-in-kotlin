package Day13

import readInput
import readTestInput

class Day13 {

    private fun runFolds(input: List<String>, part1: Boolean): Int {
        var pairs = mutableSetOf<Pair<Int, Int>>()
        val folds = mutableListOf<Pair<String, Int>>()
        var switchToFolds = false
        input.forEach {
            if (it.isBlank()) {
                switchToFolds = true
            } else {
                if (switchToFolds) {
                    val foldText = it.split(" ")
                    val alongText = foldText[2].split("=")
                    folds.add(Pair(alongText[0],alongText[1].toInt()))
                } else {
                    val intPairs = it.split(",")
                    pairs.add(Pair(intPairs[0].toInt(), intPairs[1].toInt()))
                }
            }
        }
        var count =0
        folds.forEach {
            val axis = it.first
            val value = it.second
            val currentPairs = mutableSetOf<Pair<Int, Int>>()
            when (axis) {
                "x" -> {
                    pairs.forEach {
                        if (it.first >= value) {
                            currentPairs.add(Pair(value - (it.first - value) ,it.second))
                        } else {
                            currentPairs.add(Pair(it.first,it.second))
                        }
                    }
                }
                "y" -> {
                    pairs.forEach {
                        if (it.second >= value) {
                            currentPairs.add(Pair(it.first,value - (it.second - value)))
                        } else {
                            currentPairs.add(Pair(it.first,it.second))
                        }
                    }
                }
            }
            count++
            pairs = currentPairs.toMutableSet()
            if (count == 1 && part1) {
                return pairs.size
            }
        }
        var maxX = 0
        var maxY = 0
        pairs.forEach {
            if (it.first > maxX) {
                maxX = it.first
            }
            if (it.second > maxY) {
                maxY = it.second
            }
        }

        val m = Array(maxX+1) {Array(maxY+1) {" "} }
        pairs.forEach {
            m[it.first][it.second] = "#"
        }
        for (y in 0..maxY) {
            for (x in 0..maxX) {
                print(m[x][y])
            }
            println()
        }
        return 0
    }

    fun run() {
        val testInput = readTestInput("Day13")
        val input = readInput("Day13")
        check(runFolds(testInput, true) == 17)
        println(runFolds(input, true))

        runFolds(input, false)
    }
}
