class Solution {
public:
    vector<vector<int> > generate(int numRows) {
        vector<vector<int>> result;
        if (numRows < 1) return result;

        vector<int> pre;
        pre.push_back(1);
        result.push_back(pre);
        for (int i= 1; i < numRows; ++i) {
            vector<int> cur(1,1);
            for (int i=0; i+1 < pre.size(); ++i) {
                cur.push_back(pre[i] + pre[i+1]);
            }
            cur.push_back(1);
            pre = cur;
            result.push_back(pre);
        }
        return result;
    }
};

/*Improved version*/

class Solution {
public:
    vector<vector<int> > generate(int numRows) {
        vector<vector<int>> result;
        if (numRows < 1) return result;
        result.push_back(vector<int>(1,1));
        for (int i= 2; i <= numRows; ++i) {
            vector<int> cur(i,1);
            vector<int> pre = result[i-2];
            for (int j=0; j+1 < pre.size(); ++j) {
                cur[j+1] = pre[j] + pre[j+1];
            }
            result.push_back(cur);
        }
        return result;
    }
};