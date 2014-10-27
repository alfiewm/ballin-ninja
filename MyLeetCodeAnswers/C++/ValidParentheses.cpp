class Solution {
public:
    char getRight(char left) {
        switch (left) {
            case '(': return ')';
            case '{': return '}';
            case '[': return ']';
            default: return '\0';
        }
    }
    
    inline bool isLeft(char left) {
        return (left == '(' || left == '{' || left == '[');
    }
    
    bool isValid(string s) {
        stack<char> sta;
        for (int i = 0; i < s.size(); ++i) {
            if (isLeft(s[i])) {
                sta.push(s[i]);
            } else {
                if (sta.empty()) return false;
                else if (getRight(sta.top()) != s[i]) {
                    return false;
                } else {
                    sta.pop();
                }
            }
        }
        return sta.empty();
    }
};

/*Improved Version*/
class Solution {
public:
    char getRight(char left) {
        switch (left) {
            case '(': return ')';
            case '{': return '}';
            case '[': return ']';
            default: return '\0';
        }
    }
    
    inline bool isLeft(char left) {
        return (left == '(' || left == '{' || left == '[');
    }
    
    bool isValid(string s) {
        stack<char> sta;
        for (int i = 0; i < s.size(); ++i) {
            if (isLeft(s[i])) {
                sta.push(s[i]);
            } else {
                if (sta.empty() || getRight(sta.top()) != s[i]) return false;
                else sta.pop();
            }
        }
        return sta.empty();
    }
};