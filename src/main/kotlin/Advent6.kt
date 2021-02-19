class Advent6(input: List<String>) {
    var lights = Array(1000) {Array(1000) {0} }
    private val instructions = parseList(input)
    fun solvePart1(): Int{
        for(instruction in instructions){
            doInstruction(instruction)
        }
        return lights.flatten().count { it == 1 }
    }
    fun solvePart2(): Int{
        for(instruction in instructions){
            controlBrightness(instruction)
        }
        return lights.flatten().sum()
    }
    private fun controlBrightness(instruction: List<Int>){
        val status = instruction[0]
        val xFrom = instruction[1]
        val yFrom = instruction[2]
        val xTo = instruction[3]
        val yTo = instruction[4]
        for (i in xFrom until xTo+1){
            for(j in yFrom until yTo+1){
                setBrightness(status, i, j)
            }
        }
    }
    private fun doInstruction(instruction: List<Int>){
        val status = instruction[0]
        val xFrom = instruction[1]
        val yFrom = instruction[2]
        val xTo = instruction[3]
        val yTo = instruction[4]
        for (i in xFrom until xTo+1){
            for(j in yFrom until yTo+1){
                switchLight(status, i, j)
            }
        }
    }
    private fun switchLight(status: Int, x: Int, y: Int) {
        val current = lights[x][y]
        when{
            current == 0 && status == 1 -> lights[x][y]=1
            current == 1 && status == 0 -> lights[x][y]=0
            current == 0 && status == 2 -> lights[x][y]=1
            current == 1 && status == 2 -> lights[x][y]=0
        }
    }
    private fun setBrightness(status: Int, x: Int, y: Int) {
        val current = lights[x][y]
        when(status){
            0 -> if(current > 0 ) lights[x][y]-=1
            1 -> lights[x][y]+=1
            2 -> lights[x][y]+=2
        }
    }
    private fun parseList(input: List<String>): List<List<Int>>{
        val configuration = mutableListOf<List<Int>>()
        for (line in input){
            val tempstr = line.replace("toggle", "2").replace("turn on", "1").replace("turn off", "0").replace("through ", "").replace("(", "").replace(")", "").replace(",", " ")
            val templist = tempstr.split(" ").map { it.toInt() }
            configuration.add(templist)
        }
        return configuration
    }
}