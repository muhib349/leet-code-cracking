fun compress(chars: CharArray): Int {
    var count = 1
    var subStr = ""

    var i = 0

    while (i < chars.size - 1){
        if(chars[i] == chars[i+1]){
            count++
        }else{
            subStr += if(count > 1){
                "${chars[i]}${count}"
            }else{
                "${chars[i]}"
            }
            count = 1
        }

        i++
    }

    subStr += if(count > 1){
        "${chars[i]}${count}"
    }else{
        "${chars[i]}"
    }
    i = 0

    subStr.forEach {
        chars[i++] = it
    }

    return subStr.length
}