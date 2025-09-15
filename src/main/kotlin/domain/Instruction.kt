package domain

import domain.exceptions.DomainException

sealed interface Instruction {

    fun run(robot: Robot): Robot


    data object L: Instruction { override fun run(robot: Robot) = robot.rotateAnticlockwise()}
    data object R: Instruction { override fun run(robot: Robot) = robot.rotateClockwise()}
    data object M: Instruction { override fun run(robot: Robot) = robot.move()}

    companion object{
        fun parse(c: Char): Instruction = when(c){
            'L' -> L
            'R' -> R
            'M' -> M
            else -> throw DomainException("Invalid instruction $c")
        }
    }
}

data class InstructionList(val instructions: List<Instruction>){
    fun run(robot: Robot): Robot = instructions.fold(robot){ acc, instruction -> instruction.run(acc)}

    companion object{
        fun parse(s: String): InstructionList = InstructionList(s.map { Instruction.parse(it) })
    }
}