import kotlin.math.max

fun longestOnes(nums: IntArray, k: Int): Int {
    var result = Int.MIN_VALUE
    var start = 0
    var end = 0
    var count = k

    while (end < nums.size){
        if(nums[end] == 1){
            end++
            continue
        }

        if(count > 0){
            count--
            end++
        }else{
            result = max(result, (end - start))

            if(nums[start] == 0){
                count++
            }
            start++
        }
    }
    return max(result, (end - start))
}