class advent2(data: List<String>){
    private val input: List<Triple<Int, Int, Int>> = parseList(data)

    fun parseList(dataList: List<String>): List<Triple<Int, Int, Int>> {
        val listOfDimensions =  mutableListOf<Triple<Int, Int, Int>>()
            for (data in dataList){
                val dimensions = data.split("#")
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

}


