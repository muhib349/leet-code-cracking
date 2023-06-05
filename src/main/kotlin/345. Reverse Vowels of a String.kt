fun reverseVowels(s: String): String {
    val s = s.toCharArray()

    val vowels = charArrayOf('a', 'e', 'i', 'o', 'u', 'A','E','I','O','U')
    var start = 0
    var end = s.size - 1

    while (start < end){
        if(vowels.contains(s[start]) && vowels.contains(s[end])){
            val temp = s[start]
            s[start] = s[end]
            s[end] = temp
            start++
            end--
        }else{
            if(!vowels.contains(s[start])){
                start++
            }
            if(!vowels.contains(s[end])){
                end--
            }
        }
    }
    return String(s)
}