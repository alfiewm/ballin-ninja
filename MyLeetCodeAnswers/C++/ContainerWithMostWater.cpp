

/*Solution from discuss area*/
/*Here is the proof. Proved by contradiction:

Suppose the returned result is not the optimal solution. Then there must exist an optimal solution, say a container with aol and aor (left and right respectively), such that it has a greater volume than the one we got. Since our algorithm stops only if the two pointers meet. So, we must have visited one of them but not the other. WLOG, let's say we visited aol but not aor. When a pointer stops at a_ol, it won't move until

The other pointer also points to aol. In this case, iteration ends. But the other pointer must have visited aor on its way from right end to aol. Contradiction to our assumption that we didn't visit aor.

The other pointer arrives at a value, say arr, that is greater than aol before it reaches aor. In this case, we does move aol. But notice that the volume of aol and arr is already greater than aol and aor (as it is wider and heigher), which means that aol and aor is not the optimal solution -- Contradiction!

Both cases arrive at a contradiction.
*/
class Solution {
public:
    int maxArea(vector<int> &height) {
        int maxArea = 0;
        int left = 0;
        int right = height.size() - 1;
        while (left < right) {
            maxArea = max(maxArea, min(height[right], height[left])*(right-left));
            if (height[left] < height[right]) {
                left ++;
            } else {
                right --;
            }
        }
        return maxArea;
    }
};

/*Wrong answer, 貌似理解错题意了*/
class Solution {
public:
    int maxArea(vector<int> &height) {
        int maxArea = 0;
        int start = 0;
        int low = 0;
        int end = 0;
        for (int i = 1; i < height.size(); ++i) {
            if (height[i] < height[i-1]) {
                start = height[i-1];
                while (i < height.size() && height[i] <= height[i-1]) {
                    ++i;
                }
                if (i < height.size()) {
                    low = height[i-1];
                } else {
                    break;
                }
                while (i < height.size() && height[i] >= height[i-1]) {
                    ++i;
                }
                if (i < height.size()) {
                    end = height[i-1];
                } else {
                    break;
                }
                int cur = (start >= end) ? end - low : start - low;
                maxArea = (maxArea < cur) ? cur : maxArea;
                --i;
            }
        }
        return maxArea;
    }
};