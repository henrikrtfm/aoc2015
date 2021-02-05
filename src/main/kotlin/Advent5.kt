import java.util.regex.Pattern

class Advent5(data: List<String>) {
    private val listOfStrings = data

    fun solvePart1(): Int{
        var niceStrings = 0
        for(string in listOfStrings){
            when{
                countVowels(string) && !checkNotAllowedSubStrings(string) && checkTwiceInARow(string) -> niceStrings++
            }

        }
        return niceStrings
    }
    fun solvePart2(): Int{
        var niceStrings = 0
        for(string in listOfStrings){
            when{
                checkCharRepeat(string) && checkAppearTwice(string) -> niceStrings +=1
            }

        }
        return niceStrings
    }

    fun countVowels(string: String): Boolean{
        var vowels = 0
        for(char in string){
            when(char) {
                'a', 'e', 'i', 'o', 'u' -> vowels++
            }
        }
        return when{
            vowels >= 3 -> true
            else -> false
        }
    }

    fun checkNotAllowedSubStrings(string: String): Boolean{
        val subStrings = listOf("ab", "cd", "pq", "xy")
        for(subString in subStrings){
            when(subString){
                in string -> return true
            }
        }
        return false
    }

    fun checkTwiceInARow(string: String): Boolean{
       var twice = false
        for((index, char) in string.withIndex()){
            when{
                index == string.lastIndex -> break
                char == string[index+1] -> twice = true
           }
       }
        return twice
    }

    fun checkAppearTwice(string: String): Boolean{
        val substrings = mutableListOf<String>()
        var twice = false
        for((index, char) in string.withIndex()) {
            when {
                index == string.lastIndex -> break
                else -> substrings.add(char.toString() + string[index + 1])
            }
        }
        for(str in substrings){
            twice = countMatches(string, str)
            when(twice){
                true -> break
            }
        }
        return twice
    }

    fun countMatches(string: String, pattern: String): Boolean {
        val matcher = Pattern.compile(pattern).matcher(string)

        var count = 0
        while (matcher.find()) {
            count++
        }
        return (count >= 2)
    }

    fun checkCharRepeat(string: String): Boolean{
        var repeat = false
        for((index, char) in string.withIndex()){
            when{
                index+1 == string.lastIndex -> break
                char == string[index+2] -> repeat = true
            }
        }
        return repeat
    }
}