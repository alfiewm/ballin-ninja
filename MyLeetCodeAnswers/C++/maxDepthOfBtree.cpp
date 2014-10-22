#include <iostream>
#include <stack>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};


int maxDepthOfBtree(TreeNode *root) {
    if (root == NULL) {
        return 0;
    } else if (root->left == NULL) {
        return 1 + maxDepthOfBtree(root->right);
    } else if (root->right == NULL) {
        return 1 + maxDepthOfBtree(root->left);
    } else {
        return 1 + max(maxDepthOfBtree(root->left), maxDepthOfBtree(root->right));
    }
}


int main() {
    TreeNode *root = new TreeNode(0);
    TreeNode *first = new TreeNode(1);
    TreeNode *second = new TreeNode(2);
    TreeNode *third = new TreeNode(3);
    TreeNode *forth = new TreeNode(4);
    root->left = first;
    root->right = second;
    second->right = third;
    second->left = forth;
    //forth->left=second;
    cout << maxDepthOfBtree(root) << endl;
    return 0;
}
