package Day12

import readInput
import readTestInput

class Day12 {
    private val nodes = mutableListOf<Pair<String, String>>()
    private var paths = mutableListOf<String>()
    private var total = 0

    private fun part1(input: List<String>): Int {
        nodes.clear()
        input.forEach {
            val s = it.split("-")
            nodes.add(Pair(s[0], s[1]))
        }
        total = 0
        nodes.forEach {
            if (it.first == "start") {
                findPathStarting(it.second,listOf("start"), listOf("start",it.second) )
            } else if (it.second == "start") {
                findPathStarting(it.first, listOf("start"), listOf("start",it.first))

            }
        }
        return total
    }

    private fun findPathStarting(match: String, currentVisited: List<String>, currentPath: List<String> ) {
        if (match == "start") return
        val visited = currentVisited.toMutableList()
        val path = currentPath.toMutableList() + match
        if (match.lowercase() == match) {
            visited += match
        }

        nodes.forEach {
            if (it.first == match && !currentVisited.contains(it.second)) {
                if (it.second == "end") {
                    val p = path.joinToString(",")
                    if (!this.paths.contains(p)) {
                        this.paths += p
                        total += 1
                    }

                } else {
                    findPathStarting(it.second, visited, path)
                }
            }
            if (it.second == match && !currentVisited.contains(it.first)) {
                if (it.first == "end") {
                    val p = path.joinToString(",")
                    if (!this.paths.contains(p)) {
                        this.paths += p
                        total += 1
                    }
                } else {
                    findPathStarting(it.first, visited, path)
                }
            }

        }
    }

    private fun part2(input: List<String>): Int {
        nodes.clear()
        input.forEach {
            val s = it.split("-")
            nodes.add(Pair(s[0], s[1]))
        }
        total = 0
        nodes.forEach {
            if (it.first == "start") {
                findPathStarting2(it.second,listOf(), listOf("start") )
            }
            if (it.second == "start") {
                findPathStarting2(it.first, listOf(), listOf("start"))

            }
        }
        return total
    }

    fun noRepeatsOnPath(path: List<String>): Boolean {
        return path.filter { it.lowercase() == it }.groupingBy { it }.eachCount().filter { it.value > 1 }.isEmpty()
    }

    private fun findPathStarting2(match: String, currentVisited: List<String>, currentPath: List<String>) {
        if (match == "start") return
        val visited = currentVisited.toMutableList()
        val path = currentPath.toMutableList() + match
        if (match.lowercase() == match) {
            visited += match
        }

        nodes.forEach {
            if (it.first == match && (!currentVisited.contains(it.second) || noRepeatsOnPath(path))) {
                if (it.second == "end") {
                    val p = path.joinToString(",")
                    if (!paths.contains(p)) {
                        paths += p
                        total += 1
                    }

                } else {
                    findPathStarting2(it.second, visited, path)
                }
            }
            if (it.second == match && (!currentVisited.contains(it.first) || noRepeatsOnPath(path))) {
                if (it.first == "end") {
                    val p = path.joinToString(",")
                    if (!paths.contains(p)) {
                        paths += p
                        total += 1
                    }
                } else {
                    findPathStarting2(it.first, visited, path)
                }
            }

        }
        return
    }
    fun run() {
        val testInput = readTestInput("Day12")
        val input = readInput("Day12")
        check(part1(testInput) == 226)
        println(part1(input))

        check(part2(testInput) == 3509)
        println(part2(input))
    }
}
