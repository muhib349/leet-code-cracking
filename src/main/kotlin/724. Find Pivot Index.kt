fun pivotIndex(nums: IntArray): Int {
    var left = 0
    var right = nums.sum()

    nums.forEachIndexed{i, num ->
        right -= num

        if(right == left){
            return i
        }
        left += num
    }

    return -1
}