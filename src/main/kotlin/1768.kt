
fun mergeAlternately(word1: String, word2: String): String {
    var result = ""
    var i = 0
    var j = 0

    while (i < word1.length || j < word2.length){
        if(i < word1.length){
            result += word1[i++]
        }

        if(j < word2.length){
            result += word2[j++]
        }
    }
    return result
}