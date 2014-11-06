/*
Construct Binary Tree from Preorder and Inorder Traversal
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.*/
#include "Solution.h"

using namespace std;

/*优化后的Solution, From Chen hao*/
class Solution {
public:
    TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder) {
        int preidx = 0;
        buildTree(preorder, preidx, inorder);
    }
    TreeNode *buildTree(vector<int> &preorder, int& preidx, vector<int> &inorder) {
        TreeNode *root = NULL;
        if (preorder.size() <=0 || inorder.size() == 0) return root;
        root = new TreeNode(preorder[preidx]);
        if (inorder.size() == 1) return root;

        int div;
        for (div=0; div<inorder.size(); ++div) {
            if (inorder[div] == preorder[preidx]) break;
        }

        vector<int> v(inorder.begin(), inorder.begin()+div);
        if (v.size() >0) {
            preidx++;
            root->left = buildTree(preorder, preidx, v);
        }
        v.clear();
        v.assign(inorder.begin()+div+1, inorder.end());
        if (v.size() >0) {
            preidx++;
            root->right = buildTree(preorder, preidx, v);
        }
        return root;
    }
};


/*My solution, memory limit exceed*/
class Solution2 {
public:
    TreeNode *buildTree(vector<int> &preorder, vector<int> &inorder) {
        TreeNode *root = NULL;
        if (preorder.size() == 0) return root;
        root = new TreeNode(preorder[0]);
        if (preorder.size() == 1) return root;

        int div;
        for (div=0; div<inorder.size(); ++div) {
            if (inorder[div] == preorder[0]) break;
        }
        if (div == 0) {
            vector<int> rightPre(preorder.begin()+1, preorder.end());
            vector<int> rightIn(inorder.begin()+1, inorder.end());
            root->right = buildTree(rightPre, rightIn);
        } else if (div == inorder.size() -1) {
            vector<int> leftPre(preorder.begin()+1, preorder.end());
            vector<int> leftIn(inorder.begin(), inorder.end()-1);
            root->left = buildTree(leftPre, leftIn);
        } else {
            vector<int> leftPre(preorder.begin()+1, preorder.begin()+div+1);
            vector<int> leftIn(inorder.begin(), inorder.begin()+div);
            root->left = buildTree(leftPre, leftIn);
            vector<int> rightPre(preorder.begin()+div+1, preorder.end());
            vector<int> rightIn(inorder.begin()+div+1, inorder.end());
            root->right = buildTree(rightPre, rightIn);
        }
        return root;
    }
};

int main() {
	int pre[5] = {4,3,1,2,5};
	vector<int> preorder(pre, pre+5);
	int in[5] = {1,2,3,4,5};
	vector<int> inorder(in, in+5);
	//printVector(vector<int>(preorder.begin(), preorder.begin()+1));

	Solution s;
	TreeNode *root = s.buildTree(preorder, inorder);
	return 0;
}
