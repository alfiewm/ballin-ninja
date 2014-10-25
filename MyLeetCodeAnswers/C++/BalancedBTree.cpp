/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool isBalanced(TreeNode *root) {
        if (root == NULL) return true;
        return isBalanced(root->left) && isBalanced(root->right) && abs(height(root->left)-height(root->right)) <= 1;
    }
    
    int height(TreeNode* root) {
        if (root == NULL) return 0;
        return 1 + max(height(root->left), height(root->right));
    }
};

/*version 2, by myself*/
class Solution {
public:
    bool isBalanced(TreeNode *root) {
        int high = 0;
        return isBalanced(root, &high);
    }
    
    bool isBalanced(TreeNode *root, int* high) {
        if (root == NULL) {
            *high = 0;
            return true;
        }
        int lh = 0;
        int rh = 0;
        bool result = isBalanced(root->left, &lh) && isBalanced(root->right, &rh);
        *high = max(rh, lh) + 1;
        return result && abs(rh-lh) <= 1;
    }
};