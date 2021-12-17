package Day16

import readInput
import readTestInput
import java.util.*

class Day16 {

    var ver = 0
    private fun part1(input: List<String>): Int {
        val line = input.first()
        val binary = convertToHex(line)
        var pos = 0
        while (pos < binary.length - 1) {
            println("Left to process ${binary.substring(pos)}")
            println(values)
            pos = processPacket(binary, pos)
            if (pos < 0) return ver
        }
        return ver
    }

    var values = Stack<Long>()
    private fun processPacket(binary: String, p: Int): Int {
        if (p > binary.length - 6) return p + 6

        var pos = p
        val version = (binary.substring(pos, pos + 3)).toInt(2)
        println("Got version $version - total $ver")
        println("*** $values")
        ver += version
        pos += 3
        val id = (binary.substring(pos, pos + 3)).toInt(2)
        println("Got id $id")
        pos += 3
        if (id == 4) {
            var literal = ""
            while (pos < binary.length - 1) {
                val digit = binary.substring(pos, pos + 5)
//                println("Got digit: $digit")
                literal += binary.substring(pos + 1, pos + 5)
                pos += 5
                if (digit[0] == '0') {
                    break
                }
            }
            println(literal)
            val value = literal.toLong(2)
            values.push(value)
            println("*** $values")
//            println(value)
        } else {
            val len = binary.substring(pos, pos + 1)
            pos += 1
            var c = 0
            when (len) {
                "0" -> {
                    val total = (binary.substring(pos, pos + 15)).toInt(2)
                    pos += 15
                    val sp = pos
                    while (pos < sp + total) {
                        pos = processPacket(binary, pos)
                        c++
                    }
                }
                "1" -> {
                    val totalPackets = (binary.substring(pos, pos + 11)).toInt(2)
                    println("Totalsubpackets $totalPackets")
                    pos += 11
                    // process totalPackets number of packets
                    repeat(totalPackets) {
                        pos = processPacket(binary, pos)
                        c++
                    }
                }
            }
            when (id) {
                0 -> {
                    var sum = 0L
                    repeat(c) { sum += values.pop() }
                    values.push(sum)
                }
                1 -> {
                    var prod = 1L
                    repeat(c) { prod *= values.pop() }
                    values.push(prod)
                }
                2 -> {
                    var min = Long.MAX_VALUE
                    repeat(c) {
                        val f = values.pop()
                        if (f < min) {
                            min = f
                        }
                    }
                    values.push(min)
                }
                3 -> {
                    var max = Long.MIN_VALUE
                    repeat(c) {
                        val f = values.pop()
                        if (f > max) {
                            max = f
                        }
                    }
                    values.push(max)
                }
                5 -> {
                    val first = values.pop()
                    val second = values.pop()
                    val ans = if (first < second) 1L else 0L
                    values.push(ans)
                }
                6 -> {
                    val first = values.pop()
                    val second = values.pop()
                    val ans = if (first > second) 1L else 0L
                    values.push(ans)
                }
                7 -> {
                    val second = values.pop()
                    val first = values.pop()
                    val ans = if (first == second) 1L else 0L
                    values.push(ans)
                }


            }
            println("*** $values")
        }
        return pos
    }


    private fun part2(input: List<String>): Int {
        return input.size
    }

    private fun convertToHex(s: String): String {

        var i = 0
        var binaryNum = ""
        while (i < s.length) {
            when (s[i]) {
                '0' -> binaryNum += "0000"
                '1' -> binaryNum += "0001"
                '2' -> binaryNum += "0010"
                '3' -> binaryNum += "0011"
                '4' -> binaryNum += "0100"
                '5' -> binaryNum += "0101"
                '6' -> binaryNum += "0110"
                '7' -> binaryNum += "0111"
                '8' -> binaryNum += "1000"
                '9' -> binaryNum += "1001"
                'A', 'a' -> binaryNum += "1010"
                'B', 'b' -> binaryNum += "1011"
                'C', 'c' -> binaryNum += "1100"
                'D', 'd' -> binaryNum += "1101"
                'E', 'e' -> binaryNum += "1110"
                'F', 'f' -> binaryNum += "1111"
            }
            i++
        }
        return binaryNum
    }


    fun run() {
        val input = readInput("Day16")
        println(part1(input))
    }
}
