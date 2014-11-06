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
    TreeNode *buildTree(vector<int> &inorder, vector<int> &postorder) {
        int postidx = postorder.size() -1;
        return buildTree(inorder, postorder, postidx);
    }
    
    TreeNode *buildTree(vector<int> &inorder, vector<int> &postorder, int &postidx) {
        if (inorder.size() <= 0 || postorder.size() <= 0) return NULL;
        TreeNode *root = new TreeNode(postorder[postidx]);
        if (inorder.size() ==1) return root;
        int i;
        for (i = 0; i < inorder.size(); ++i) {
            if (inorder[i] == postorder[postidx]) break;
        }
        
        vector<int> v(inorder.begin()+i+1, inorder.end());
        if(v.size() > 0) {
            postidx --;
            root->right = buildTree(v, postorder, postidx);
        }
        
        v.clear();
        v.assign(inorder.begin(), inorder.begin()+i);
        if (v.size() > 0) {
            postidx --;
            root->left = buildTree(v, postorder,postidx);
        }
        return root;
    }
};