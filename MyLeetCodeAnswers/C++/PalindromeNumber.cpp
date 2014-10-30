
/*reverse可能会导致溢出，通过计算数的总位数，不断比较前后两个数字*/

class Solution {
public:
    bool isPalindrome(int x) {
        if (x < 0 ) return false;
        int d = 1;
        while (x/d >= 10) d*=10;
        while (x > 0) {
            int q = x /d;
            int r = x %10;
            if (q!=r) return false;
            x = x%d/10;
            d/=100;
        }
        return true;
    }
};