/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

/*My solution*/
class Solution {
public:
    vector<vector<int> > zigzagLevelOrder(TreeNode *root) {
        vector<vector<int> > result;
        if (root == NULL) return result;
        bool ltor = true;
        vector<TreeNode*> que;
        que.push_back(root);
        while(!que.empty()) {
            vector<int> oneLv;
            int count = que.size();
            if (ltor) {
                for (int i = 0; i < count; ++i) {
                    oneLv.push_back(que[i]->val);
                }
            } else {
                for (int i = count-1; i >=0; --i) {
                    oneLv.push_back(que[i]->val);
                }
            }
            for (int i = 0; i < count; ++i) {
                if (que[i]->left != NULL) que.push_back(que[i]->left);
                if (que[i]->right != NULL) que.push_back(que[i]->right);
            }
            result.push_back(oneLv);
            ltor = !ltor;
            que.assign(que.begin()+count, que.end());
        }
        return result;
    }
};