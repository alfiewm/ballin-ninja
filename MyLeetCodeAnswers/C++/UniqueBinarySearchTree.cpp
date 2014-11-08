// Source   : https://oj.leetcode.com/problems/unique-binary-search-trees-ii/
// Authoer  : Wang Meng
// Date     : 2014-11-08

/*
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * 
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 * 
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 */

//优化后的代码，通过巧妙增加NULL节点，可以缩减几个条件判断
 class Solution {
public:
    vector<TreeNode *> generateTrees(int n) {
        return generateTrees(1, n);
    }
    
    vector<TreeNode *> generateTrees(int start, int end) {
        vector<TreeNode *> result;
        if (start > end) {
            result.push_back(NULL);
            return result;
        }
        if (start == end) {
            result.push_back(new TreeNode(start));
            return result;
        }
        for (int i = start; i <= end; ++i) {
            // use i as root
            vector<TreeNode *> leftTrees = generateTrees(start, i-1);
            vector<TreeNode *> rightTrees = generateTrees(i+1, end);
            for (int j = 0; j < leftTrees.size(); ++j) {
                for (int k = 0; k < rightTrees.size(); ++k) {
                    TreeNode *root = new TreeNode(i);
                    root->left = leftTrees[j];
                    root->right = rightTrees[k];
                    result.push_back(root);
                }
            }
        }
        return result;
    }
};


// My rescursive solution， 注意n==0时返回size==1的result，节点为NULL
class Solution {
public:
    vector<TreeNode *> generateTrees(int n) {
        if (n == 0) {
            return vector<TreeNode *>(1, (TreeNode*)NULL);
        }
        return generateTrees(1, n);
    }
    
    vector<TreeNode *> generateTrees(int start, int end) {
        vector<TreeNode *> result;
        if (start > end) return result;
        if (start == end) {
            result.push_back(new TreeNode(start));
            return result;
        }
        for (int i = start; i <= end; ++i) {
            // use i as root
            vector<TreeNode *> leftTrees = generateTrees(start, i-1);
            vector<TreeNode *> rightTrees = generateTrees(i+1, end);
            if (leftTrees.empty()) {
                for (int j = 0; j < rightTrees.size(); ++j) {
                    TreeNode *root = new TreeNode(i);
                    root->right = rightTrees[j];
                    result.push_back(root);
                }
            } else if (rightTrees.empty()) {
                for (int j = 0; j < leftTrees.size(); ++j) {
                    TreeNode *root = new TreeNode(i);
                    root->left = leftTrees[j];
                    result.push_back(root);
                }
            } else {
                for (int j = 0; j < leftTrees.size(); ++j) {
                    for (int k = 0; k < rightTrees.size(); ++k) {
                        TreeNode *root = new TreeNode(i);
                        root->left = leftTrees[j];
                        root->right = rightTrees[k];
                        result.push_back(root);
                    }
                }
            }
        }
        return result;
    }
};