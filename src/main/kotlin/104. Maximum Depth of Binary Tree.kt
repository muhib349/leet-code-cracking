import tree.TreeNode
import kotlin.math.max

fun maxDepth(root: TreeNode?): Int {
    if(root == null)
        return 0
    return max(maxDepth(root.left), maxDepth(root.right)) + 1
}

fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
    val leaf1 = ArrayList<Int>()
    val leaf2 = ArrayList<Int>()
    dfs(root1, leaf1)
    dfs(root2, leaf2)

    return leaf1 == leaf2
}

fun dfs(root: TreeNode?, leafs: ArrayList<Int>){
    if(root == null)
        return

    if(root.left == null && root.right == null){
        leafs.add(root.`val`)
    }
    dfs(root.left, leafs)
    dfs(root.right, leafs)
}

fun longestZigZag(root: TreeNode?): Int {
    if(root?.right != null){
        solve(root.right, true,1)
    }

    if(root?.left != null){
        solve(root.left, false, 1)
    }


    return result
}

var result = Int.MIN_VALUE
fun solve(root: TreeNode?, isRight: Boolean, path: Int){

    if(root?.left == null && root?.right == null){
        result = max(result, path)
        return
    }
    if(isRight){
        if(root.left != null){
            solve(root.left, false, path + 1)
        }else{
            result = max(result, path)
        }

        if(root.right != null){
            solve(root.right, true, 1)
        }
    }else{
        if(root.right != null){
            solve(root.right, true, path + 1)
        }else{
            result = max(result, path)
        }

        if(root.left != null){
            solve(root.left, false, 1)
        }
    }
}