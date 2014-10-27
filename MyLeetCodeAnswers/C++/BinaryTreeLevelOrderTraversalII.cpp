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
    vector<vector<int> > levelOrderBottom(TreeNode *root) {
        vector<vector<int> > result;
        if (root == NULL) return result;
        queue<TreeNode*> que;
        TreeNode *p = root;
        que.push(p);
        while (!que.empty()) {
            int count = que.size();
            vector<int> cur;
            for (int i = 0; i < count; ++i) {
                p = que.front();
                cur.push_back(p->val);
                if (p->left!=NULL) que.push(p->left);
                if (p->right!=NULL) que.push(p->right);
                que.pop();
            }
            result.push_back(cur);
        }
        reverse(result.begin(), result.end());
        return result;
    }
};