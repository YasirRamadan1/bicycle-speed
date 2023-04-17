import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

// TODO: add all test cases for example when user enters invalid inputs and tests foreach function
class BikeSpeedTest {
    @Test
    fun `calculateBikeSpeed with valid inputs`() {
        val frontTeeth = 36
        val rearTeeth = 12
        val rpm = 60
        val wheelDiameter = 0.6

        val expectedSpeed = 20.357520395261858
        val actualSpeed = calculateBikeSpeed(frontTeeth, rearTeeth, rpm, wheelDiameter)

        assertEquals(expectedSpeed, actualSpeed)
    }
}