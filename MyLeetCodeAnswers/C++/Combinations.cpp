class Solution {
public:
    vector<vector<int> > combine(int n, int k) {
        vector<vector<int> > result;
        if (k <= 0) return result;
        if (k == 1) {
        	while (n!=0) {
        		result.push_back(vector<int>(1, n--));
        	}
        	return result;
        }
        for (int i = n; i >= k; --i) {
        	vector<vector<int> > tmp;
        	tmp = combine(i-1, k-1);
        	for (int j = 0; j < tmp.size(); ++j) {
        		tmp[j].push_back(i);
        		result.push_back(tmp[j]);
        	}
        }
        return result;
    }
};