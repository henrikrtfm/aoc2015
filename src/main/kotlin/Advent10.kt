class Advent10 {
    private var input = "1113122113"
    private val regexPattern = """((\w)\2+)|([0-9])""".toRegex()
    fun solvePart1(): Int{
        for(x in 0..39){
            val temp1 = regexPattern.findAll(input)
            input = createNewInput(temp1)
        }
        return input.length
    }
    fun solvePart2(): Int{
        for(x in 0..49){
            val temp1 = regexPattern.findAll(input)
            input = createNewInput(temp1)
        }
        return input.length
    }
    private fun createNewInput(input: Sequence<MatchResult>): String{
        var result = ""
        for(x in input){
            result= result+x.value.length+x.value[0]
        }
        return result
    }
}