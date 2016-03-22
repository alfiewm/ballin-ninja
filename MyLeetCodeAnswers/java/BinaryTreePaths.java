import java.util.ArrayList;
import java.util.List;

/**
 * Source   : https://leetcode.com/problems/binary-tree-paths/
 * Author   : Wang Meng
 * Date     : 2016-03-22
 * <p>
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * For example, given the following binary tree:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * All root-to-leaf paths are:
 * <p>
 * ["1->2->5", "1->3"]
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        String cur = "";
        binaryTreePaths(ret, cur, root);
        return ret;
    }

    public void binaryTreePaths(List<String> ret, String cur, TreeNode root) {
        if (root == null) return;
        String tmp = (cur.isEmpty()) ? String.valueOf(root.val) : (cur + "->" + root.val);
        if (root.left == null && root.right == null) {
            ret.add(tmp);
        } else {
            binaryTreePaths(ret, tmp, root.left);
            binaryTreePaths(ret, tmp, root.right);
        }
    }
}
