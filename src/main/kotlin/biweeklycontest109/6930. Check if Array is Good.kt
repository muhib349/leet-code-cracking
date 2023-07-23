package biweeklycontest109

fun isGood(nums: IntArray): Boolean {
    nums.sort()
    val n = nums.size

    if(nums[n-2] == n-1 && nums[n-1] == n-1)
        return true

    return false
}