class Advent3(data: String) {
    private val locationList = data

    fun solvePart1(): Int{
        val houses = mutableMapOf<Pair<Int, Int>, Int>()
        var (x,y) = Pair(0,0)
        houses[Pair(x,y)] = 1
        for(location in locationList){
            when(location){
                '^' -> x +=1
                'v' -> x -=1
                '<' -> y -=1
                '>' -> y +=1
            }
            when {
                houses.containsKey(Pair(x,y)) -> houses[Pair(x,y)] = houses[Pair(x,y)]!! + 1
                else -> houses[Pair(x,y)] = 1
            }
        }
        return houses.count()
    }

    fun solvePart2(): Int{
        val houses = mutableMapOf<Pair<Int, Int>, Int>()
        var x1 = 0
        var y1 = 0
        var x2 = 0
        var y2 = 0
        houses[Pair(0,0)] = 1
        for((index, location) in locationList.withIndex()){
            if(index % 2 == 0){
                when(location){
                    '^' -> x1 +=1
                    'v' -> x1 -=1
                    '<' -> y1 -=1
                    '>' -> y1 +=1
                }
                when {
                    houses.containsKey(Pair(x1,y1)) -> houses[Pair(x1,y1)] = houses[Pair(x1,y1)]!! + 1
                    else -> houses[Pair(x1,y1)] = 1
                }
            }
            else{
                when(location){
                    '^' -> x2 +=1
                    'v' -> x2 -=1
                    '<' -> y2 -=1
                    '>' -> y2 +=1
                }
                when {
                    houses.containsKey(Pair(x2,y2)) -> houses[Pair(x2,y2)] = houses[Pair(x2,y2)]!! + 1
                    else -> houses[Pair(x2,y2)] = 1
                }
            }
        }
        return houses.count()
    }
}