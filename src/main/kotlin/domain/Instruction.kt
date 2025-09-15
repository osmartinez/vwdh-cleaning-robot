package domain

import domain.exceptions.DomainException

sealed interface Instruction {

    data object L: Instruction
    data object R: Instruction
    data object M: Instruction
    companion object{
        fun parse(c: Char): Instruction = when(c){
            'L' -> L
            'R' -> R
            'M' -> M
            else -> throw DomainException("Invalid instruction $c")
        }
    }
}