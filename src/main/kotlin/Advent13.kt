class Advent13(input: List<String>){
    private val pairs = parsePairs(input)
    private val seatings = parseSeating(input)

    private fun parsePairs(input: List<String>): MutableMap<Pair<String, String>, Int> {
        val pairList = input.map{it.replace("would ", "").replace("happiness ", "").replace("units ", "").replace("by ", "").replace("sitting ", "").replace("next ", "").replace("to ", "").replace(".", "").split(" ")}
        val pairs = mutableMapOf<Pair<String, String>, Int>()
        pairList.forEach { pl: List<String> ->
            val tempKey = Pair(pl[0], pl[3])
            val tempValue = pl[2].toInt()
            when{
                pl.contains("gain") -> pairs[tempKey] = tempValue
                pl.contains("lose") -> pairs[tempKey] = tempValue.unaryMinus()
            }
        }
        return pairs
    }

    fun solvePart1(): Int?{
        val happinessRatings = mutableListOf<Int>()
        for (seating in seatings){
            var happiness = 0
            for (x in seating.indices){
                val key1: Pair<String, String> = when(x){
                    seating.size-1 -> Pair(seating[x], seating[0])
                    else -> Pair(seating[x],seating[x+1])
                }
                val key2: Pair<String, String> = when(x){
                    seating.size-1 -> Pair(seating[0], seating[x])
                    else -> Pair(seating[x+1],seating[x])
                }
                happiness += pairs[key1]!! + pairs[key2]!!
            }
            happinessRatings.add(happiness)
        }
        //println(happinessRatings)
        return happinessRatings.maxOrNull()
    }

    private fun parseSeating(input: List<String>): Set<List<String>> {
        val tempSeatings = input.map { it.replace(".", "").split(" ") }
        val seatings = tempSeatings.map { it.first() }.distinct()
        return permutations(seatings)
    }

    private fun <T> permutations(list: List<T>): Set<List<T>> {
        if (list.isEmpty()) return setOf(emptyList())
        val result: MutableSet<List<T>> = mutableSetOf()
        for (i in list.indices) {
            permutations(list - list[i]).forEach { item ->
                result.add(item + list[i])
            }
        }
        return result
    }
}