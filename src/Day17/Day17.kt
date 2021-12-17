package Day17

class Day17 {

    private fun part1(targetX: Pair<Int, Int>, targetY: Pair<Int, Int>): Int {
        var point: Pair<Int, Int>
        var vel: Pair<Int, Int>
        var maxY = Integer.MIN_VALUE
        for (x in -800..800) {
            for (y in -800..800) {
                var steps = 0
                point = Pair(0, 0)
                vel = Pair(x, y)
                var localMax = Integer.MIN_VALUE
                while (steps < 550) {
                    point = Pair(point.first + vel.first, point.second + vel.second)
                    vel = if (vel.first > 0) {
                        Pair(vel.first - 1, vel.second - 1)
                    } else if (vel.first < 0) {
                        Pair(vel.first + 1, vel.second - 1)
                    } else {
                        Pair(vel.first, vel.second - 1)
                    }
                    if (point.second > localMax) {
                        localMax = point.second
                    }
                    if (point.first >= targetX.first && point.first <= targetX.second) {
                        if (point.second >= targetY.first && point.second <= targetY.second) {
                            if (localMax > maxY) {
                                maxY = localMax
                            }
                            break
                        }
                    }
                    steps++
                }
            }
        }
        return maxY
    }

    private fun part2(targetX: Pair<Int, Int>, targetY: Pair<Int, Int>): Int {
        var point: Pair<Int, Int>
        var vel: Pair<Int, Int>
        var count = 0
        for (x in -800..800) {
            for (y in -800..800) {
                var steps = 0
                point = Pair(0, 0)
                vel = Pair(x, y)
                while (steps < 550) {
                    point = Pair(point.first + vel.first, point.second + vel.second)
                    vel = if (vel.first > 0) {
                        Pair(vel.first - 1, vel.second - 1)
                    } else if (vel.first < 0) {
                        Pair(vel.first + 1, vel.second - 1)
                    } else {
                        Pair(vel.first, vel.second - 1)
                    }
                    if (point.first >= targetX.first && point.first <= targetX.second) {
                        if (point.second >= targetY.first && point.second <= targetY.second) {
                            count++
                            break
                        }
                    }
                    steps++
                }
            }
        }
        return count
    }

    fun run() {
        //target area: x=128..160, y=-142..-88
        check(part1(Pair(20, 30), Pair(-10, -5)) == 45)
        println(part1(Pair(128, 160), Pair(-142, -88)))
        println(part2(Pair(128, 160), Pair(-142, -88)))
    }
}
