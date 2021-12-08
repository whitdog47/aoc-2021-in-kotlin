package Day00

class ParkedCode {
    //    private fun part0(input: List<String>): Int {
//        var maxx = 0
//        var maxy = 0
//        input.forEach{
//            val xy = it.split(",")
//            val x = xy[0].toInt()
//            val y = xy[1].toInt()
//
//            if (x > maxx) maxx = x
//            if (y > maxy) maxy = y
//        }
//        var walls = Array(maxx) {Array(maxy) {false} }
//        var path = Array(maxx) {Array(maxy) {false} }
//        input.forEach{
//            val xy = it.split(",")
//            val x = xy[0].toInt()
//            val y = xy[1].toInt()
//
//            walls[x][y] = true
//        }
//        var sx = 0
//        var sy = 0
//        move(sx+1, sy, walls, path, 0)
//        move(sx-1, sy, walls, path, 0)
//        move(sx, sy+1, walls, path ,0)
//        move(sx, sy-1, walls, path, 0)
//
//    }
//
//    private fun move(sx: Int, sy: Int, walls: Array<Array<Boolean>>, path: Array<Array<Boolean>>, steps: Int) : Int {
//        if (walls[sx][sy] || path[sx][sy]) {
//            return steps
//        }
//        move(sx+1, sy, walls, path, steps+1)
//        move(sx-1, sy, walls, path, steps+1)
//        move(sx, sy+1, walls, path, steps+1)
//        move(sx, sy-1, walls, path, steps+1)
//    }
}