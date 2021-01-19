class Advent2(data: List<String>){
    private val input: List<Triple<Int, Int, Int>> = parseList(data)

    fun solvePart1(): Int {
        val dimensions = calculateDimensions(input)
        return dimensions.sum()
    }

    fun solvePart2(): Int {
        val lengths = calculateLengths(input)
        return lengths.sum()
    }

    fun parseList(dataList: List<String>): List<Triple<Int, Int, Int>> {
        val listOfDimensions =  mutableListOf<Triple<Int, Int, Int>>()
            for (data in dataList){
                val dimensions = data.split("x")
                listOfDimensions.add(Triple(dimensions[0].toInt(), dimensions[1].toInt(),dimensions[2].toInt()))
            }
        return listOfDimensions
    }

    fun calculateDimensions(listOfDimensions: List<Triple<Int, Int, Int>>): List<Int>{
        val listOfAreas: MutableList<Int> = mutableListOf<Int>()
        for (dimension in listOfDimensions){
            val area = calculateDimension(dimension)
            listOfAreas.add(area)
        }
        return listOfAreas
    }

    fun calculateDimension(dimension: Triple<Int, Int, Int>): Int {
        val sideOne = dimension.first*dimension.second
        val sideTwo = dimension.first*dimension.third
        val sideThree = dimension.second*dimension.third
        val minSide = minOf(sideOne, sideTwo, sideThree)
        return ((sideOne*2)+(sideTwo*2)+(sideThree*2)+minSide)
    }

    fun calculateLengths(listOfDimensions: List<Triple<Int, Int, Int>>): List<Int>{
        val listOfLengths: MutableList<Int> = mutableListOf<Int>()
        for (dimension in listOfDimensions){
            val length = calculateLength(dimension)
            listOfLengths.add(length)
        }
        return listOfLengths
    }

    fun calculateLength(dimension: Triple<Int, Int, Int>): Int {
        val sideOne = minOf(dimension.first, dimension.second, dimension.third)
        val sideTwo = maxOf(minOf(dimension.first, dimension.second), minOf(maxOf(dimension.first, dimension.second), dimension.third))
        val volume = dimension.first*dimension.second*dimension.third
        return ((sideOne*2)+(sideTwo*2)+volume)
    }

}


