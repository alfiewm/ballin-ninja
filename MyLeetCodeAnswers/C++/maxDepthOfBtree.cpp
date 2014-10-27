/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

/*Recursive*/
class Solution {
public:
    int maxDepth(TreeNode *root) {
        if (root == NULL) {
            return 0;
        } else {
            return 1 + max(maxDepth(root->left), maxDepth(root->right));
        }
    }
};


 /*None Recursive*/
class Solution {
public:
    int maxDepth(TreeNode *root) {
        if (root == NULL) return 0;
        TreeNode *p = root;
        queue<TreeNode*> que;
        que.push(p);
        int result(0);
        while (!que.empty()) {
            result++;
            int count = que.size();
            while(count--) {
                p = que.front();
                que.pop();
                if (p->left != NULL) que.push(p->left);
                if (p->right != NULL) que.push(p->right);
            }
        }
        return result;
    }
};