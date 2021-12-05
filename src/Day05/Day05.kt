package Day05

import readInput
import readTestInput
import java.lang.Integer.max
import java.lang.Math.min

class Day05 {

    private fun part1(input: List<String>): Int {
        var maxx = 0
        var maxy = 0
        input.forEach{
            val g = it.split("->")
            val xy = g[0].trim().split(",")
            val x1 = xy[0].toInt()
            val y1 = xy[1].toInt()
            val xy2 = g[1].trim().split(",")
            val x2 = xy2[0].toInt()
            val y2 = xy2[1].toInt()

            if (x1 > maxx) maxx = x1
            if (x2 > maxx) maxx = x2
            if (y1 > maxy) maxy = y1
            if (y2 > maxy) maxy = y2
        }
        val mm = Array(maxx+2) {Array(maxy+2) {0} }
        input.forEach{
            val g = it.split("->")
            val xy = g[0].trim().split(",")
            val x1 = xy[0].toInt()
            val y1 = xy[1].toInt()
            val xy2 = g[1].trim().split(",")
            val x2 = xy2[0].toInt()
            val y2 = xy2[1].toInt()
            if (x1 == x2) {
                for (y in min(y1,y2)..max(y1,y2)) {
                    mm[x1][y]++
                }
            } else if (y1 == y2) {
                for (x in min(x1,x2)..max(x1,x2)) {
                    mm[x][y1]++
                }
            }


        }
        var c = 0
        for (x  in 0 until maxx+1) {
            for (y in 0 until maxy + 1) {
                if (mm[x][y] > 1) {
                    c++
                }
            }
        }
        return c
    }

    private fun part2(input: List<String>): Int {
        var maxx = 0
        var maxy = 0
        input.forEach{
            val g = it.split("->")
            val xy = g[0].trim().split(",")
            val x1 = xy[0].toInt()
            val y1 = xy[1].toInt()
            val xy2 = g[1].trim().split(",")
            val x2 = xy2[0].toInt()
            val y2 = xy2[1].toInt()

            if (x1 > maxx) maxx = x1
            if (x2 > maxx) maxx = x2
            if (y1 > maxy) maxy = y1
            if (y2 > maxy) maxy = y2
        }
        val mm = Array(maxx+2) {Array(maxy+2) {0} }
        input.forEach{
            val g = it.split("->")
            val xy = g[0].trim().split(",")
            val x1 = xy[0].toInt()
            val y1 = xy[1].toInt()
            val xy2 = g[1].trim().split(",")
            val x2 = xy2[0].toInt()
            val y2 = xy2[1].toInt()
            if (x1 == x2) {
                for (y in min(y1,y2)..max(y1,y2)) {
                    mm[x1][y]++
                }
            } else if (y1 == y2) {
                for (x in min(x1,x2)..max(x1,x2)) {
                    mm[x][y1]++
                }
            } else {
                val m = (1.0*y2-1.0*y1)/(1.0*x2-1.0*x1)
                val b = y1 - m * x1
                for (x in min(x1,x2)..max(x1,x2)) {
                    val y = m * x + b
                    mm[x][y.toInt()]++
                }
            }


        }
        var c = 0
        for (x  in 0 until maxx+1) {
            for (y in 0 until maxy + 1) {
                if (mm[x][y] > 1) {
                    c++
                }
            }
        }
        return c
    }

    fun run() {
        val testInput = readTestInput("Day05")
        val input = readInput("Day05")
        check(part1(testInput) == 5)
        println(part1(input))

        check(part2(testInput) == 12)
        println(part2(input))
    }
}
