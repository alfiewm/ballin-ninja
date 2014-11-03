class Solution {
public:
    void flatten(TreeNode *root) {
        flattenR(root);
    }
    
    TreeNode *flattenR(TreeNode *root) {
        if (root == NULL) return NULL;
        if (root->left == NULL && root->right == NULL) {
            return root;
        }
        TreeNode *rightEnd = NULL;
        if (root->right != NULL) {
            rightEnd = flattenR(root->right);
        }
        TreeNode *leftEnd = NULL;
        if (root->left != NULL) {
            leftEnd = flattenR(root->left);
            leftEnd->right = root->right;
            root->right = root->left;
            root->left = NULL;
        }
        return (rightEnd==NULL) ? leftEnd : rightEnd;
    }
};