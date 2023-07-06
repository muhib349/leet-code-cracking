import java.util.LinkedList

fun removeStars(s: String): String {
    var result = LinkedList<Char>()
    s.forEach {
        if(it == '*'){
            result.removeLast()
        }else{
            result.add(it)
        }
    }

    return result.joinToString("")
}