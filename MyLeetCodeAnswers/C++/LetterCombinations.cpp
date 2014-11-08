class Solution {
public:
    string dm[10] = { " ", "@", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    vector<string> letterCombinations(string digits) {
        vector<string> result;
        if (digits.size() == 0) {
            result.push_back("");
            return result;
        }
        string first = dm[digits[0] - '0'];
        vector<string> rest = letterCombinations(digits.substr(1, digits.size()-1));
        for (int i = 0; i < first.size(); ++i) {
            for (int j = 0; j < rest.size(); ++j) {
                result.push_back(first[i] + rest[j]);
            }
        }
        return result;
    }
};