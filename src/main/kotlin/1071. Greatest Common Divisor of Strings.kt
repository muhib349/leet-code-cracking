fun gcdOfStrings(str1: String, str2: String): String {

    if(str1+str2 != str2+str1)
        return ""

    var len1 = str1.length
    var len2 = str2.length

    while (len1 != len2){
        if(len1 > len2){
            len1 -= len2
        }else{
            len2 -= len1
        }
    }


    return str1.substring(0,len1)
}