package Day08

import readInput
import readTestInput

class Day08 {

    private fun part1(input: List<String>): Int {
        // 1 has two unique segments
        // 4 has 4
        // 7 has 3
        // 8 has 7
        var d = 0
        input.forEach{
            it.split("|").last().trim().split(" ").forEach{ str ->
                if (str.length == 2 || str.length == 4 || str.length == 3 || str.length == 7) {
                    d++
                }
            }
        }
        return d
    }

    private fun part2(input: List<String>): Long {
        // 0 has 6*
        // 1 has 2
        // 2 has 5+
        // 3 has 5+
        // 4 has 4
        // 5 has 5+
        // 6 has 6*
        // 7 has 3
        // 8 has 7
        // 9 has 6*
        var one = ""
        var four = ""
        var seven = ""
        var sum = 0L
        input.forEach {
            it.split("|").first().trim().split(" ").forEach{
                when (it.length) {
                    2 -> one = it // it's a one - so those letters go R1 and R2
                    4 -> four = it   // it's a 4 -- so R1, R2 , and L1, M
                    3 -> seven = it
                }
            }
            // need four minus one
            var fourLeft = ""
            four.forEach{
                if (it == one[0] || it == one[1]) {
                    // don't add
                } else {
                    fourLeft += it
                }
            }

            var place = 1000
            var row = 0
            it.split("|").last().trim().split(" ").forEach{
                when (it.length) {
                    2 -> row += 1 * place
                    3 -> row += 7 * place
                    4 -> row += 4 * place
                    5 -> { // 2, 3, or 5
                        if (it.contains(one[0]) && it.contains(one[1])) {
                            row += 3 * place
                        } else if (it.contains(fourLeft[0]) && it.contains(fourLeft[1])){
                            row += 5 * place
                        } else {
                            row += 2 * place
                        }
                    }
                    6 -> { // 0,6 or 9 (0 & 9 have both from 1)
                        if (it.contains(one[0]) && it.contains(one[1])) {
                            // either 0 or 9 (only 9 has both from 4)
                            if (it.contains(fourLeft[0]) && it.contains(fourLeft[1])) {
                                row += 9 * place
                            } else {
                                row += 0 * place
                            }
                        } else {
                            row += 6 * place
                        }
                    }
                    7 -> row += 8 * place
                }
                place /= 10
            }
            sum += row
        }
        return sum
    }

    fun run() {
        val testInput = readTestInput("Day08")
        val input = readInput("Day08")
        check(part1(testInput) == 26)
        println(part1(input))

       check(part2(testInput) == 61229L)
        println(part2(input))
    }
}
