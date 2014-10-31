class Solution {
public:
    int lengthOfLastWord(const char *s) {
        int count = 0;
        char *p = (char*)s;
        while (*p) {
            if (isalpha(*p)) {
                count = 0;
                while (isalpha(*p)) {
                    count++;
                    p++;
                }
            } else {
                p++;
            }
        }
        return count;
    }
};