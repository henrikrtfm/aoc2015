class advent1(data: String){

    private val floorplan = data
    fun solvePart1(): Int {
        var floor: Int = 0
        for (f in floorplan){
            if (f == '('){
                floor += 1
            }
            else {
                floor -= 1
            }
        }
        return floor
    }
    fun solvePart2(): Int {
        var floor: Int = 0
        for ((index, f) in floorplan.withIndex()){
            if (f == '('){
                floor += 1
            }
            else {
                floor -= 1
            }
            if (floor <0) return index+1
        }
        return floor
    }

}

