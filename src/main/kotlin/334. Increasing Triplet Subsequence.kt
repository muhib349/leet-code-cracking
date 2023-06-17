fun increasingTriplet(nums: IntArray): Boolean {
    val len = nums.size

    if(len < 3){
        return false
    }

    var left = Int.MAX_VALUE
    var mid = Int.MAX_VALUE


    for (i in nums.indices){
        if(nums[i] > mid)
            return true
        else if(nums[i] > left && nums[i] < mid){
            mid = nums[i]
        }else if(nums[i] < left){
            left = nums[i]
        }
    }
    return false
}