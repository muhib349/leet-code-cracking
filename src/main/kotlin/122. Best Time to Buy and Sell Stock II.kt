fun maxProfit(prices: IntArray): Int {
    var profit = 0

    for (i in 1 until prices.size){
        if(prices[i-1] < prices[i]){
            profit += prices[i] - prices[i - 1]
        }
    }

    return profit
}