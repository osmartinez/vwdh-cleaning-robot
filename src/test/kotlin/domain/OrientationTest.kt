package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals;

class OrientationTest {
    @Test
    fun `clockwise from N to E`() = assertEquals(Orientation.E, Orientation.N.clockwise())
}