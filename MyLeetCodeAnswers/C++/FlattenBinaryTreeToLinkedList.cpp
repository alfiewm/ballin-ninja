

/*None recursive solution from Chen Hao*/
class Solution {
public:
    void flatten(TreeNode *root) {
        if (root == NULL) return;
        vector<TreeNode*> stack, v;
        stack.push_back(root);
        while (!stack.empty()) {
            TreeNode *p = stack.back();
            v.push_back(p);
            stack.pop_back();
            
            if (p->right != NULL) {
                stack.push_back(p->right);
            }
            if (p->left != NULL) {
               stack.push_back(p->left); 
            }
        }
        // 精髓，加个标记位，更方便
        v.push_back(NULL);
        for (int i = 0; i < v.size(); ++i) {
            if (v[i]) {
                v[i]->left = NULL;
                v[i]->right = v[i+1];
            }
        }
    }
};

/*recursive solution by myself*/
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