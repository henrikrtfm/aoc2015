import java.math.BigInteger
import java.security.MessageDigest

class Advent4(data: String) {
    private val input = data

    fun solvePart1(): Int{
        var counter = 0
        do {
            val key = input + counter.toString()
            val hash = md5(key)
            counter +=1
        } while (hash.toString().subSequence(0,5) != "00000")

        return counter-1
    }

    fun solvePart2(): Int{
        var counter = 0
        do {
            val key = input + counter.toString()
            val hash = md5(key)
            counter +=1
        } while (hash.toString().subSequence(0,6) != "000000")

        return counter-1
    }

    fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

}