/*
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 */

/*DP solution*/
class Solution {
public:
    bool wordBreak(string s, unordered_set<string> &dict) {
        if (s.empty()) return false;
        vector<bool> v(s.size(), false);// 表示从0到i的子串，或者这个子串拆分后的子串们是否都存在于字典中
        for (int i = 0; i < s.size(); ++i) {
        	string w(s.begin(), s.begin()+i+1);
        	if (dict.count(w)>0) v[i] = true;
        	if (v[i]) continue;
        	else {
        		// 如果当前子串在字典中，则进行拆分
        		for (int j=0; j<i; ++j) {
        			if (v[j]) {
        				w.assign(s.begin()+j+1, s.begin()+i+1);
        				if (dict.count(w)>0) {
        					v[i] = true;
        					break;
        				}
        			}
        		}
        	}
        }
        return v[s.size()-1];
    }
};

/*My Recursive Solution, TLE*/
class Solution {
public:
    bool wordBreak(string s, unordered_set<string> &dict) {
        if (s.empty()) return false;
        bool result = false;
        for (int div = 0; div < s.size(); ++div) {
            result |= (dict.count(s.substr(0, div+1))>0 && wordBreak(s.substr(div+1, s.size()-div-1), dict));
        }
        return result;
    }
};