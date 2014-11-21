/*已知一个字符串，求这个字符串中n个字符的排列。
 *比如： s = "abc", n=2, 结果是ab, ac, ba, bc, ca, cb.
 */
#include "Solution.h"

class Solution {
public:
vector<string> cnk(string &s, int k) {
	if (s.size() < k) return vector<string>();
	vector<string> res;
	if (k == 1) {
		for (int i = 0; i < s.size(); ++i) {
			res.push_back(string(1, s[i]));
		}
		return res;
	}

	for (int i = 0; i <= s.size() - k; ++i) {
		string substring = s.substr(i+1);
		vector<string> tmp = cnk(substring, k-1);
		for (int j = 0; j < tmp.size(); ++j) {
			res.push_back(string(1, s[i]) + tmp[j]);
			res.push_back(tmp[j] + string(1, s[i]));
		}
	}
	return res;
}
};

int main() {
    string input;
    int k;
    Solution s;
    while (cin >> input >> k) {
        vector<string> vec = s.cnk(input, k);
        printVector<string>(vec);
    }
}
