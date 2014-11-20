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

// 又一个好方法，罗列了访问到一个节点时满足那些条件才应该访问，也使用了pre指针
class Solution {
public:
    vector<int> postorderTraversal(TreeNode *root) {
        vector<int> result;
        if (root == NULL) return result;
        TreeNode *p = root;
        TreeNode *pre = NULL;
        stack<TreeNode*> sta;
        sta.push(p);
        while (!sta.empty()) {
            p = sta.top();
            // if pre is from substree, then it is either from right or from left.
            if (p->right!=NULL && p->right==pre
              || p->right==NULL && p->left==pre
              || p->left==NULL && p->right==NULL) {
                sta.pop();
                result.push_back(p->val);
                pre = p;
                continue;
            }
            if (p->right != NULL) sta.push(p->right);
            if (p->left != NULL) sta.push(p->left);
        }
        return result;
    }
};

// another good idea, 使用栈，依次访问根，右子树，左子树，最终对结果逆序就可以了
class Solution {
public:
    vector<int> postorderTraversal(TreeNode *root) {
        vector<int> result;
        if (root == NULL) return result;
        TreeNode *p = root;
        stack<TreeNode*> sta;
        sta.push(p);
        while (!sta.empty()) {
            p = sta.top();
            result.push_back(p->val);
            sta.pop();
            if (p->left != NULL) sta.push(p->left);
            if (p->right != NULL) sta.push(p->right);
        }
        reverse(result.begin(), result.end());
        return result;
    }
};

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