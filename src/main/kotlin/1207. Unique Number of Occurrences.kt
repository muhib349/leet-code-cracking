fun uniqueOccurrences(arr: IntArray): Boolean {
    val map = mutableMapOf<Int, Int>()

    arr.forEach {
        map[it] = (map[it] ?: 0)  + 1
    }

    return map.values.size == map.values.toSet().size
}