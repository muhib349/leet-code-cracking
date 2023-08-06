
import com.sun.source.tree.Tree
import tree.TreeNode
import java.util.Deque
import java.util.Queue
import java.util.Stack

fun maxLevelSum(root: TreeNode?): Int {
    var result = 1
    val queue = ArrayDeque<TreeNode?>()
    queue.add(root)
    var sum = 0
    var maxSum = Int.MIN_VALUE
    var level = 1

    while (queue.isNotEmpty()){
        var len = queue.size

        sum = 0
        while (len > 0){
            val node = queue.removeFirst()
            sum += node?.`val` ?: 0

            if(node?.left != null){
                queue.add(node.left)
            }
            if(node?.right != null){
                queue.add(node.right)
            }
            len--
        }

        if(sum > maxSum){
            maxSum = sum
            result = level
        }
        level++
    }


    return result
}

fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
    if(root == null)
        return null

    if(root.`val` == `val`)
        return root

    return if(`val` < root.`val`){
        searchBST(root.left, `val`)
    }else{
        searchBST(root.right, `val`)
    }
}

fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
    val stack = Stack<TreeNode?>()
    stack.push(root)
    var prevNode: TreeNode? = null

    while (stack.isNotEmpty()){
        val node = stack.pop()
        if(node?.`val` == key){
            if (prevNode == null){

            }
        }
        if(node?.left != null){
            stack.push(node.left)
        }
        if(node?.right != null){
            stack.push(node.right)
        }
    }

    return root
}
