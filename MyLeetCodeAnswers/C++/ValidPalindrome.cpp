/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
*/

/*Super handsome*/
class Solution {
public:
    bool isPalindrome(string s) {
        auto left=s.begin(), right = prev(s.end());
        while (left < right) {
            if (!isalnum(*left)) ++left;
            else if (!isalnum(*right)) --right;
            else if (tolower(*left) != tolower(*right)) return false;
            else ++left, --right;
        }
        return true;
    }
};
/*much more handsome*/
class Solution {
public:
    bool isPalindrome(string s) {
        if (s.empty()) return true;
        for (int i=0, j=s.size()-1; i < j; ++i, --j) {
            while (i<j && !isalnum(s[i])) ++i;
            while (i<j && !isalnum(s[j])) --j;
            if (i >= j) return true;
            else if (tolower(s[i]) == tolower(s[j])) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
};
/*more handsome one*/
class Solution {
public:
    bool isPalindrome(string s) {
        if (s.empty()) return true;
        string::iterator start = s.begin();
        string::iterator end = s.end() -1;
        while (start < end) {
            while (start < end && !isalnum(*start)) ++start;
            while (start < end && !isalnum(*end)) --end;
            if (start >= end) return true;
            else if (tolower(*start) == tolower(*end)) {
                start ++;
                end --;
            } else {
                return false;
            }
        }
        return true;
    }
};

/*stupid one*/
class Solution {
public:
    bool isPalindrome(string s) {
        if (s.empty()) return true;
        string format = "";
        for (int i= 0; i < s.size(); ++i) {
            if (isalnum(s[i])) {
                format+=tolower(s[i]);
            }
        }
        string reflect = format;
        reverse(reflect.begin(), reflect.end());
        return format.compare(reflect) == 0;
    }
};