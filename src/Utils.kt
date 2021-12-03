import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name/main.txt").readLines()
fun readTestInput(name: String) = File("src", "$name/test.txt").readLines()

fun readInputAsInt(name: String) = File("src", "$name/main.txt").readLines().map { it.toInt() }
fun readTestInputAsInt(name: String) = File("src", "$name/test.txt").readLines().map { it.toInt() }

private fun fileToPairStringInt(name: String, ext: String) = File("src", "$name/$ext.txt").readLines().map {
    val (s, i) = it.split(" ")
    s to i.toInt()
}
fun readInputStringToInt(name: String) = fileToPairStringInt(name, "main")
fun readTestInputStringToInt(name: String) = fileToPairStringInt(name, "test")

private fun fileToTripleStringIntInt(name: String, ext: String) = File("src", "$name/$ext.txt").readLines().map {
    val (s, i, i2) = it.split(" ")
    Triple(s,i.toInt(),i2.toInt())
}
fun readInputStringToIntToInt(name: String) = fileToTripleStringIntInt(name, "main")
fun readTestInputStringToIntToInt(name: String) = fileToTripleStringIntInt(name, "test")

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
