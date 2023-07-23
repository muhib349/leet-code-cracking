package biweeklycontest109

fun sortVowels(s: String): String {
    val vowels = charArrayOf('A', 'E','I', 'O','U','a','e','i','o','u')
    var s = s.toCharArray()
    val vowelsFromS = mutableListOf<Char>()

    for (i in s.indices){
        if(vowels.contains(s[i])){
            vowelsFromS.add(s[i])
        }
    }
    vowelsFromS.sort()
    var i =0
    var j = 0

    while (i < s.size){
        if(vowels.contains(s[i])){
            s[i] = vowelsFromS[j]
            j++
        }
        i++
    }
    return s.joinToString("")
}