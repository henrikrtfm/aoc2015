import java.lang.NullPointerException
import java.lang.NumberFormatException

class Advent7(input: List<String>) {
    val instructions = parseInput(input)
    private val wireSignals = mutableMapOf<String, Int>()

    fun solvePart1(): Int{
        while(instructions.isNotEmpty()){
            val instruction = instructions.removeFirst()
            try {
                handleInstruction(instruction)
            }
            catch (e: NullPointerException){
                instructions.addLast(instruction)
            }
        }
        return wireSignals["a"]!!
    }

    private fun parseInput(input: List<String>): ArrayDeque<List<String>>{
        val parsedInput = ArrayDeque<List<String>>()
        for (line in input){
            val tempString = line.replace(" ->", "").split(" ")
            parsedInput.add(tempString)
        }
        return parsedInput
    }

    private fun assignSignal(wire: String, signal: String){
        try {
            wireSignals[wire] = signal.toInt()
        } catch (e: NumberFormatException){
            wireSignals[wire] = wireSignals[signal]!!
        }

    }

    private fun handleInstruction(instruction: List<String>){
        when(instruction.size){
            2 -> assignSignal(instruction[1], instruction[0])
            3 -> bitwiseComplement(instruction[2], instruction[1])
            4 -> bitwiseLogic(instruction[3], instruction[1], instruction[0], instruction[2])
        }
    }

    private fun bitwiseLogic(wire: String, operator: String, operand1: String, operand2: String) {
        when{
            operator == "OR" -> wireSignals[wire] = wireSignals[operand1]!! or wireSignals[operand2]!!
            operator == "AND" && operand1.toIntOrNull() != null -> wireSignals[wire] = operand1.toInt() and wireSignals[operand2]!!
            operator == "AND" && operand1.toIntOrNull() == null -> wireSignals[wire] = wireSignals[operand1]!! and wireSignals[operand2]!!
            operator == "RSHIFT" -> wireSignals[wire] = wireSignals[operand1]!! shr operand2.toInt()
            operator == "LSHIFT" -> wireSignals[wire] = wireSignals[operand1]!! shl operand2.toInt()
        }
    }

    private fun bitwiseComplement(wire: String, operand: String) {
        val complement = wireSignals[operand]!!.inv()
        when {
            complement >= 0 -> wireSignals[wire] = complement
            else -> wireSignals[wire] = 65535 + complement + 1
        }
    }
}















