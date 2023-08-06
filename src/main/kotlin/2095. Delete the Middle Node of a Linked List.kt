import java.util.Stack
import kotlin.math.max

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun deleteMiddle(head: ListNode?): ListNode? {
    if(head?.next == null)
        return null

    var prev: ListNode? = null
    var slow = head
    var fast = head

    while (fast?.next != null){
        prev = slow
        slow = slow?.next
        fast = fast.next?.next
    }

    prev?.next = slow?.next

    return head
}

fun oddEvenList(head: ListNode?): ListNode? {
    if(head?.next == null)
        return head

    var oddPointer = head
    var evenHead = head.next
    var evenPointer = head.next

    while (oddPointer?.next != null && evenPointer?.next != null){
        oddPointer.next = oddPointer.next?.next
        oddPointer = oddPointer.next

        evenPointer.next = evenPointer.next?.next
        evenPointer = evenPointer.next
    }

    oddPointer?.next = evenHead

    return head
}

fun reverseList(head: ListNode?): ListNode? {

    if(head?.next == null)
        return head


    var reverseHead = ListNode(head.`val`)
    reverseHead.next = null

    var pointer = head.next

    var temp: ListNode? = null

    while (pointer != null){
        temp = ListNode(pointer.`val`)
        temp.next = reverseHead

        reverseHead = temp
        pointer = pointer.next
    }

    return reverseHead
}

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    val stack1 = Stack<Int>()
    val stack2 = Stack<Int>()
    var result: ListNode? = null

    var l1 = l1
    var l2 = l2

    while (l1 != null){
        stack1.push(l1.`val`)
        l1 = l1.next
    }

    while (l2 != null){
        stack2.push(l2.`val`)
        l2 = l2.next
    }

    var carry = 0

    while (stack1.isNotEmpty() || stack2.isNotEmpty()){
        var top1 = 0
        var top2 = 0

        if(stack1.isNotEmpty()){
            top1 = stack1.pop()
        }
        if(stack2.isNotEmpty()){
            top2 = stack2.pop()
        }
        var sum = top1 + top2 + carry
        carry = 0
        if(sum > 9){
            sum %= 10
            carry = 1
        }
        val temp = ListNode(sum)
        temp.next = result
        result = temp
    }

    if(carry != 0){
        val temp = ListNode(carry)
        temp.next = result
        result = temp
    }

    return result
}

fun pairSum(head: ListNode?): Int {
    var head = head

    var pointer = head
    var reverse: ListNode? = null

    while (pointer != null){
        val temp = ListNode(pointer.`val`)
        temp.next = reverse
        reverse = temp
        pointer = pointer.next
    }
    pointer = head
    var sum = 0
    while (pointer != null){
        sum = max(sum, ((head?.`val` ?: 0 ) + (reverse?.`val` ?: 0)))
        head = head?.next
        reverse = reverse?.next

        pointer = pointer.next?.next
    }
    return sum
}