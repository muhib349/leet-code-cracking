import kotlin.math.max

fun longestSubarray(nums: IntArray): Int {
    var mxLength = Int.MIN_VALUE
    var left = 0
    var right = 0
    var zeros = 0

    while (right < nums.size){
        if(nums[right] == 0){
            zeros++
        }

        if(zeros <= 1){
            mxLength = max(mxLength, right - left)
            right++
        }else{
            while (zeros > 1){
                if(nums[left] == 0)
                    zeros--
                left++
            }

            mxLength = max(mxLength, right - left)
            right++
        }
    }

    return mxLength
}