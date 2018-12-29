package wylaga.util

import kotlin.test.Test
import kotlin.test.assertEquals

class DirectionVectorTests {
    @Test
    fun testStaticDirectionVectors() {
        assertEquals(DirectionVector.NULL, DirectionVector(0.0, 0.0))
        assertEquals(DirectionVector.NORTH, DirectionVector(0.0, -1.0))
        assertEquals(DirectionVector.NORTHEAST, DirectionVector(1.0, -1.0))
        assertEquals(DirectionVector.NORTHWEST, DirectionVector(-1.0, -1.0))
        assertEquals(DirectionVector.EAST, DirectionVector(1.0, 0.0))
        assertEquals(DirectionVector.WEST, DirectionVector(-1.0, 0.0))
        assertEquals(DirectionVector.SOUTH, DirectionVector(0.0, 1.0))
        assertEquals(DirectionVector.SOUTHEAST, DirectionVector(1.0, 1.0))
        assertEquals(DirectionVector.SOUTHWEST, DirectionVector(-1.0, 1.0))
    }
}