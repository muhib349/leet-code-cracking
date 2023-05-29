import kotlin.math.max

fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {
    val result = BooleanArray(candies.size)

    var maxCount = 0

    for(candy in candies){
        maxCount = max(maxCount, candy)
    }

    candies.forEachIndexed { index, item ->
        result[index] = item + extraCandies >= maxCount
    }

    return result.toList()
}