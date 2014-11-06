/*
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
注意数组可能无序！
*/

class Solution {
public:
    vector<int> twoSum(vector<int> &numbers, int target) {
        unordered_map<int, int> map;
        vector<int> result;
        for (int i = 0; i < numbers.size(); ++i) {
            const int rest = target - numbers[i];
            if (map.find(rest) != map.end()) {
                result.push_back(map[rest]+1);// 先放查找池中的数字！
                result.push_back(i+1);
                break;
            }
            // 如果找不到对应的，将其放入到查找池中！%>_<%
            map[numbers[i]] = i;
        }
        return result;
    }
};