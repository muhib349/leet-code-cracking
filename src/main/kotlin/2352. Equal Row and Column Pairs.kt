fun equalPairs(grid: Array<IntArray>): Int {
    var count = 0
    val rowMap = mutableMapOf<List<Int>, Int>()

    grid.forEach{rowArr ->
        rowMap[rowArr.asList()] = rowMap.getOrDefault(rowArr.asList(), 0) + 1
    }

    for(i in grid.indices){
        val temp = mutableListOf<Int>()
        for (j in grid.indices){
            temp.add(grid[j][i])
        }

        count += rowMap.getOrDefault(temp, 0)
    }

    return count
}