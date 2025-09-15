package infrastructure.adapters.`in`.cli

import domain.Floor
import domain.Orientation
import domain.Position
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class CliParserTest {
    @Test
    fun `sample input is parsed correctly`() {
        val raw = listOf(
            "5 5",
            "1 2 N",
            "LMLMLMLMM",
            "3 3 E",
            "MMRMMRMRRM"
        )

        val (floor, pairs) = CliParser.parse(raw)
        assertEquals(floor, Floor(5, 5))
        assertEquals(pairs.first().robot.position, Position(1, 2))
        assertEquals(pairs.last().robot.position, Position(3, 3))
        assertEquals(pairs.first().robot.orientation, Orientation.N)
        assertEquals(pairs.last().robot.orientation, Orientation.E)


    }
}
