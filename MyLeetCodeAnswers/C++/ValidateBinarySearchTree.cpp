// Source   : https://oj.leetcode.com/problems/validate-binary-search-tree/
// Authoer  : Wang Meng
// Date     : 2014-11-16

/*
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */

// recursive solution, from Discuss area
class Solution {
public:
    bool isValidBST(TreeNode *root) {
        TreeNode *pre = NULL;
        return validate(root, pre);
    }
    
    // 注意形参pre类型，为指针的引用，即方法内做的修改是针对pre指针的修改，而不仅仅是pre指向数据的修改。类似逆序链表时，传入ListNode **head返回void一样
    bool validate(TreeNode *root, TreeNode *&pre) {
        if (root == NULL) return true;
        if (!validate(root->left, pre)) return false;
        if (pre!=NULL && pre->val>=root->val) return false;
        pre = root;
        return validate(root->right, pre);
    }
};


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