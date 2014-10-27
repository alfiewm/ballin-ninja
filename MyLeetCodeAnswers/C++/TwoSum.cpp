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