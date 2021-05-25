class Advent8(input: List<String>) {
    private val strings = input
    private val regexPart1 = """(?:\\\\)|(?:\\\")|(?:\\x[a-zA-Z0-9]{2})""".toRegex()
    private val regexPart2 = """(?:\\\\)|(?:\\\")|(?:\\x[a-zA-Z0-9]{1})""".toRegex()
    fun solvePart1(): Int{
        val result1 = countStringLength(strings)
        val result2 = countMemoryLength1(strings)
        return result1.sum() - result2.sum()
    }
    fun solvePart2(): Int{
        val result1 = countStringLength(strings)
        val result2 = countMemoryLength2(strings)
        return result2.sum() - result1.sum()
    }
    private fun countMemoryLength1(input: List<String>): List<Int> {
        return input.map{regexParser(it)}
    }
    private fun countMemoryLength2(input: List<String>): List<Int> {
        return input.map{regexEncoder(it)}
    }
    private fun regexParser(input: String): Int {
        val resultAll = regexPart1.replace(input, "X")
        return resultAll.length - 2
    }
    private fun regexEncoder(input: String): Int{
        val result1 = regexPart2.replace(input, "XXXX")
        return result1.length+4
    }
    private fun countStringLength(input: List<String>): List<Int>{
        return input.map(String::length)
    }
}