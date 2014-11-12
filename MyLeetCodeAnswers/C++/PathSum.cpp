// Source   : https://oj.leetcode.com/problems/path-sum/
// Authoer  : Wang Meng
// Date     : 2014-11-12(modified)

/*
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * 
 * For example:
 * Given the below binary tree and sum = 22,
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */

// None Recursive Solution
class Solution {
public:
    bool hasPathSum(TreeNode *root, int sum) {
        if (root == NULL) return false;
        TreeNode *p = root;
        stack<TreeNode*> sta;
        TreeNode *pre = NULL;
        int curSum = 0;
        while (p != NULL || !sta.empty()) {
            while (p != NULL) {
                sta.push(p);
                curSum += p->val;
                p = p->left;
            }
            if (sta.top()->right != NULL && sta.top()->right != pre) {
                p = sta.top()->right;
            } else {
                // return from right node or leaf node
                pre = sta.top();
                if (pre->left == NULL && pre->right == NULL && curSum == sum) {// leaf node
                    return true;
                }
                sta.pop();
                curSum -= pre->val;
            }
        }
        return false;
    }
};

// Recursive solution
class Solution {
public:
    bool hasPathSum(TreeNode *root, int sum) {
        if (root == NULL) return false;
        if (root->left == NULL && root->right == NULL) return root->val == sum;
        return hasPathSum(root->left, sum - root->val) || hasPathSum(root->right, sum - root->val);
    }
};