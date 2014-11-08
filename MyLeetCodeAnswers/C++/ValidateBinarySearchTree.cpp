/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */

/*早起神清气爽，递归一下就出来了，果然休息足才有动力啊，毕竟不是学霸*/
class Solution {
public:
    bool isValidBST(TreeNode *root) {
        if (root == NULL) return true;
        int mx, mn;
        return isValidBST(root, mx, mn);
    }
    
    bool isValidBST(TreeNode *root, int &mx, int &mn) {
        mx = mn = root->val;
        if (root->left != NULL) {
            int lmx(0), lmn(0);
            if (!isValidBST(root->left, lmx, lmn) || root->val<=lmx) return false;
            mn = lmn;
        }
        
        if (root->right != NULL) {
            int rmx(0), rmn(0);
            if (!isValidBST(root->right, rmx, rmn) || root->val>=rmn) return false;
            mx = rmx;
        }
        return true;
    }
};
/*使用递归失败，换用中序遍历的方法，明天检查是否有别的方法*/
class Solution {
public:
    bool isValidBST(TreeNode *root) {
        if (root == NULL) return true;
        if (root->left == NULL && root->right == NULL) return true;
        vector<int> inorder;
        stack<TreeNode*> sta;
        TreeNode *p = root;
        do {
            while(p) {
                sta.push(p);
                p = p->left;
            }
            p = sta.top();
            inorder.push_back(p->val);
            p = p->right;
            sta.pop();
        } while (p != NULL || !sta.empty());
        for (int i=1; i < inorder.size(); ++i) {
            if (inorder[i] <= inorder[i-1]) return false;
        }
        return true;
    }
};