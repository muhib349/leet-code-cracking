import kotlin.math.max

/**
 * Maximum Distance Between a Pair of Values
 */
fun maxDistance(nums1: IntArray, nums2: IntArray): Int {
    var result = 0
    var i = 0
    var j = 0
    while (i<nums1.size && j < nums2.size){
        if(nums1[i] > nums2[j]){
            i++
        }else{
            result = max(result, (j - i))
            j++
        }
    }

    return result
}