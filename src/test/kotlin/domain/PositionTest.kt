package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {
    @Test
    fun `forward N`(){
        val current = Position(1,1).forward(Orientation.N)
        assertEquals(Position(1,2), current)
    }

    @Test
    fun `forward E`(){
        val current = Position(1,1).forward(Orientation.E)
        assertEquals(Position(2,1), current)
    }

    @Test
    fun `forward S`(){
        val current = Position(1,1).forward(Orientation.S)
        assertEquals(Position(1,0), current)
    }

    @Test
    fun `forward W`(){
        val current = Position(1,1).forward(Orientation.W)
        assertEquals(Position(0,1), current)
    }
}