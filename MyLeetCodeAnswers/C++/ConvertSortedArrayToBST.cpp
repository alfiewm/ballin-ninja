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
    TreeNode *sortedArrayToBST(vector<int> &num) {
        TreeNode *root = NULL;
        if (num.size() < 1) return root;
        int low = 0, high = num.size() -1;
        return convert(num, low, high);
    }

    TreeNode *convert(vector<int> &num, int low, int high) {
        if (low > high) {
            return NULL;
        } else if (low == high) {
            return new TreeNode(num[low]);
        } else {
            int mid = (low+high) /2;
            TreeNode *root = new TreeNode(num[mid]);
            root->left = convert(num, low, mid -1);
            root->right = convert(num, mid+1, high);
            return root;
        }
    }
};
