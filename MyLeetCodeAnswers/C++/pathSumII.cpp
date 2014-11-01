#include <iostream>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

/*Improved*/
class Solution {
public:
    vector<vector<int> > pathSum(TreeNode *root, int sum) {
        vector<vector<int> > result;
        if (root == NULL) return result;
        vector<int> path;
        pathSum(result, path, sum, root);
        return result;
    }
    
    void pathSum(vector<vector<int> > &result, vector<int> &path, int sum, TreeNode *root) {
        path.push_back(root->val);
        if (root->left == NULL && root->right == NULL && root->val == sum) {
            result.push_back(path);
        } else {
            if (root->left != NULL)
                pathSum(result, path, sum-root->val, root->left);
            if (root->right != NULL)
                pathSum(result, path, sum-root->val, root->right);
        }
        path.pop_back();
    }
};

/*My recursive solution*/
class Solution {
public:
    vector<vector<int> > pathSum(TreeNode *root, int sum) {
        vector<vector<int> > result;
        if (root == NULL) return result;
        vector<int> path;
        int curSum = 0;
        pathSum(result, path, sum, curSum, root);
        return result;
    }
    
    void pathSum(vector<vector<int> > &result, vector<int> &path, int sum, int &curSum, TreeNode *root) {
        path.push_back(root->val);
        curSum+= root->val;
        if (root->left == NULL && root->right == NULL) {
            if (curSum == sum) {
                result.push_back(path);
            }
        } else {
            if (root->left != NULL)
                pathSum(result, path, sum, curSum, root->left);
            if (root->right != NULL)
                pathSum(result, path, sum, curSum, root->right);
        }
        curSum -= root->val;
        path.pop_back();
    }
};
int main() {
    return 0;
}
