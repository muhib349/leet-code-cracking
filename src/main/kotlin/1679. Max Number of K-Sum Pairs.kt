fun maxOperations(nums: IntArray, k: Int): Int {
    var count = 0
    var map = mutableMapOf<Int, Int>()

    for (i in nums.indices){
        var remain = k - nums[i]

        if(map.containsKey(remain)){
            count++
            if(map[remain] == 1) map.remove(remain)
            else map[remain] = (map[remain] ?: 0) - 1
        }else{
            map[nums[i]] = (map[nums[i]] ?: 0) + 1
        }
    }

    return count
}