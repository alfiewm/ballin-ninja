// Source   : https://oj.leetcode.com/problems/longest-substring-without-repeating-characters/
// Authoer  : Wang Meng
// Date     : 2014-11-20

/*
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * 
 * For example:
 * Given binary tree {1,#,2,3},
 *    1
 *     \
 *      2
 *     /
 *    3
 * return [3,2,1].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
// My none recursive solution
class Solution {
public:
    vector<int> postorderTraversal(TreeNode *root) {
        vector<int> result;
        if (root == NULL) return result;
        TreeNode *p = root;
        TreeNode *pre = NULL;
        stack<TreeNode*> sta;
        while (p != NULL || !sta.empty()) {
            while (p != NULL) {
                sta.push(p);
                p = p->left;
            }
            p = sta.top();
            if(p->right == NULL || p->right == pre) {
                result.push_back(p->val);
                sta.pop();
                pre = p;
                p = NULL;
            } else {
                p = p->right;
            }
        }
        return result;
    }
};

// My Recursive solution
class Solution {
public:
    vector<int> postorderTraversal(TreeNode *root) {
        vector<int> result;
        if (root == NULL) return result;
        vector<int> leftr = postorderTraversal(root->left);
        result.insert(result.end(), leftr.begin(), leftr.end());
        vector<int> rightr = postorderTraversal(root->right);
        result.insert(result.end(), rightr.begin(), rightr.end());
        result.push_back(root->val);
        return result;
    }
};