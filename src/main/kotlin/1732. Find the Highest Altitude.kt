import kotlin.math.max

fun largestAltitude(gain: IntArray): Int {
    var highest = 0
    var totalGain = 0

    gain.forEach {
        totalGain += it
        highest = max(highest, totalGain)
    }

    return highest
}