import kotlin.math.max

fun findMaxAverage(nums: IntArray, k: Int): Double {
    var start = 0
    var end = 0
    var maxSum = Int.MIN_VALUE
    var sum = 0

    while (end < nums.size){
        val item = (end - start) + 1
        sum += nums[end]

        if(item < k){
            end++
        }else if(item == k){
            maxSum = max(maxSum, sum)
            sum -= nums[start]
            start++
            end++
        }
    }
    return maxSum/k.toDouble()
}