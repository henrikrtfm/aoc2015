class Advent9(input: List<String>) {
    private val nodes = parseNodes(input)
    private val routes = permutations(nodes.toList())
    private val distances = parseDistances(input)
    private fun parseDistances(input: List<String>): MutableMap<String, Int> {
        val distances = mutableMapOf<String, Int>()
        for (x in input) {
            val tempList = x.replace(" to ", " ").replace(" = ", " ").split(" ")
            distances[tempList[0]+tempList[1]]=tempList[2].toInt()
            distances[tempList[1]+tempList[0]]=tempList[2].toInt()
        }
        return distances
    }
    fun solvePart1(): Int? {
        val result = calculateRoutes()
        return result.minOrNull()
    }
    fun solvePart2(): Int? {
        val result = calculateRoutes()
        return result.maxOrNull()
    }
    fun <T> permutations(list: List<T>): Set<List<T>> {
        if (list.isEmpty()) return setOf(emptyList())
        val result: MutableSet<List<T>> = mutableSetOf()
        for (i in list.indices) {
            permutations(list - list[i]).forEach{
                    item -> result.add(item + list[i])
            }
        }
        return result
    }
    private fun parseNodes(input: List<String>): MutableSet<String>{
        val nodes = mutableSetOf<String>()
        for (x in input) {
            val tempList = x.replace(" to ", " ").replace(" = ", " ").split(" ")
            nodes.add(tempList[0])
            nodes.add(tempList[1])
        }
        return nodes
    }
    private fun calculateRoutes(): MutableList<Int>{
        val result = mutableListOf<Int>()
        for (route in routes){
            var distance = 0
            for (x in route.indices){
                val key = when (route.getOrNull(x + 1)) {
                    null -> break
                    else -> route.getOrNull(x) + route.getOrNull(x + 1)
                }
                val keydistance = distances.getOrDefault(key, 0)
                distance += keydistance
            }
            result.add(distance)
        }
        return result
    }
}