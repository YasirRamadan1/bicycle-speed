import java.lang.IllegalArgumentException
import kotlin.math.PI

fun main(args: Array<String>) {
    val wheelDiameter = 0.6 // meters
    val frontTeeth = 36
    val rearTeeth = 12
    val rpm = 60

    try {
        val speed = calculateBikeSpeed(
            frontGearTeeth = frontTeeth,
            rearGearTeeth = rearTeeth,
            rpm = rpm,
            wheelDiameter = wheelDiameter
        )
        println("The speed of the bicycle is $speed km/h")
    } catch (e: IllegalArgumentException) {
        print("couldn't calculate the speed, reason: ${e.message}")
    }
}

/**
 * Calculates bicycle speed
 * it first calculates gear ratio of the bike by calling [calculateBikeSpeed]
 * and then calculates traveled distance of the bike per minute by calling [calculateDistanceMeterPerMinute]
 * and then calculates the speed in km/h by calling [calculateBikeSpeedInKmPerHour]
 *
 * @param frontGearTeeth the number of the front teeth of the bike gear
 * @param rearGearTeeth the number of the rear teeth of the bike gear
 * @param rpm the number of revolutions per minute of the wheel
 * @param wheelDiameter of the bike wheel
 * @return [Double] of bike speed
 * @throws [IllegalArgumentException]
 */
@Throws(IllegalArgumentException::class)
fun calculateBikeSpeed(
    frontGearTeeth: Int,
    rearGearTeeth: Int,
    rpm: Int,
    wheelDiameter: Double,
): Double {
    val gearRatio = calculateGearRatio(frontGearTeeth = frontGearTeeth, rearGearTeeth = rearGearTeeth)
    val traveledDistancePerMinute = calculateDistanceMeterPerMinute(
        rpm = rpm,
        wheelDiameter = wheelDiameter
    )

    return calculateBikeSpeedInKmPerHour(
        distancePerMinute = traveledDistancePerMinute,
        gearRatio = gearRatio
    )
}

/**
 * Calculate gear ratio of a bike
 *
 * @param frontGearTeeth the number of the front teeth of the bike gear
 * @param rearGearTeeth the number of the rear teeth of the bike gear
 * @return [Double] value of gear ratio or throws [IllegalArgumentException] if [rearGearTeeth] is less than 0
 * @throws [IllegalArgumentException]
 */
@Throws(IllegalArgumentException::class)
fun calculateGearRatio(
    frontGearTeeth: Int,
    rearGearTeeth: Int
): Double {
    require(rearGearTeeth > 0) { "rearTeeth cannot be $rearGearTeeth" }
    return frontGearTeeth.divideAsDouble(rearGearTeeth)
}

/**
 * Calculates the distance traveled by bike per minute
 *
 * @param rpm the number of revolutions per minute of the wheel
 * @param wheelDiameter of the bike wheel
 * @return [Double] value of the traveled distance
 */
fun calculateDistanceMeterPerMinute(
    rpm: Int,
    wheelDiameter: Double
): Double = (rpm * wheelDiameter * PI)


/**
 * Calculates the speed of a bike in km/h
 *
 * @param distancePerMinute traveled distance per minute
 * @param gearRatio of the bike
 * @return [Double] value of bike speed or throws [IllegalArgumentException] of gear ratio is less than 0
 * @throws [IllegalArgumentException]
 */
@Throws(IllegalArgumentException::class)
fun calculateBikeSpeedInKmPerHour(
    distancePerMinute: Double,
    gearRatio: Double
): Double {
    require(gearRatio > 0) { "gearRatio cannot be $gearRatio" }
    return distancePerMinute * gearRatio * METERS_PER_MINUTE_TO_KILOMETERS_PER_HOUR_FACTOR
}

fun Int.divideAsDouble(other: Int): Double = this.toDouble() / other

const val METERS_PER_MINUTE_TO_KILOMETERS_PER_HOUR_FACTOR = 0.06
