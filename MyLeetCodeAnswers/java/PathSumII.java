import java.util.ArrayList;
import java.util.List;

/**
 * Source   : https://leetcode.com/problems/path-sum-ii/
 * Author   : Wang Meng
 * Date     : 2016-03-12
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * <p>
 * For example:
 * Given the below binary tree and sum = 22,
 * return
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7   2   5  1
 */
public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        pathSum(root, sum, cur, ret);
        return ret;
    }

    private void pathSum(TreeNode root, int sum, List<Integer> cur, List<List<Integer>> ret) {
        if (root == null) {
            return;
        }
        cur.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            ret.add(new ArrayList<>(cur));
        } else {
            pathSum(root.left, sum - root.val, cur, ret);
            pathSum(root.right, sum - root.val, cur, ret);
        }
        cur.remove(cur.size() - 1);
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
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);
        System.out.println(new PathSumII().pathSum(root, 22));
    }
}
