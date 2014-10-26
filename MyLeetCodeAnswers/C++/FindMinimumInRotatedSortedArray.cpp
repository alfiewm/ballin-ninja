class Solution {
public:
    int findMin(vector<int> &num) {
        assert(num.size() > 0);
        int low = 0;
        int high = num.size() - 1;
        while (low < high) {
            if (num[low] < num[high]) break;
            
            int mid = (low + high) /2;
            if (num[mid] < num[low]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return num[low];
    }
};