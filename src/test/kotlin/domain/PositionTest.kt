package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {
    @Test
    fun `forward N`(){
        val current = Position(1,1).forward(Orientation.N)
        assertEquals(Position(1,2), current)
    }
}