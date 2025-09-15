package domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals;

class OrientationTest {
    @Test
    fun `clockwise from N to E`() = assertEquals(Orientation.E, Orientation.N.clockwise())
    @Test
    fun `clockwise from E to S`() = assertEquals(Orientation.S, Orientation.E.clockwise())
    @Test
    fun `clockwise from S to W`() = assertEquals(Orientation.W, Orientation.S.clockwise())
    @Test
    fun `clockwise from W to N`() = assertEquals(Orientation.N, Orientation.W.clockwise())

}