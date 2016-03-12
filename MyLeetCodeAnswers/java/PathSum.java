import java.util.Stack;

/**
 * Source   : https://leetcode.com/problems/path-sum/
 * Author   : Wang Meng
 * Date     : 2016-03-12
 * <p>
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * <p>
 * For example:
 * Given the below binary tree and sum = 22,
 *          5
 *         / \
 *        4   8
 *       /   / \
 *      11  13  4
 *     /  \      \
 *    7   2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        TreeNode pre = null;
        int curSum = 0;
        while (p != null || !stack.empty()) {
            while (p != null) {
                stack.push(p);
                curSum += p.val;
                p = p.left;
            }
            TreeNode peek = stack.peek();
            if (peek.right != null && peek.right != pre) {
                p = peek.right;
            } else {
                // leaf node or return from right node
                pre = peek;
                if (pre.left == null && pre.right == null && curSum == sum) {
                    return true;
                }
                curSum -= pre.val;
                stack.pop();
            }
        }
        return false;
    }

    // recursive solution
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == sum;
        return hasPathSum2(root.left, sum - root.val) || hasPathSum2(root.right, sum-root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        System.out.println(new PathSum().hasPathSum(root, 22));
        System.out.println(new PathSum().hasPathSum2(root, 22));
    }
}
