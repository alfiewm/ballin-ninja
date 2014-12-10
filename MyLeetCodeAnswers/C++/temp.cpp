/*已知一个字符串，求这个字符串中n个字符的排列。
 *比如： s = "abc", n=2, 结果是ab, ac, ba, bc, ca, cb.
 */
#include "Solution.h"
#include "time.h"
class Solution {
public:
vector<string> cnk(string &s, int k) {
	if (s.size() < k) return vector<string>();
	vector<string> res;
	if (k == 1) {
		for (int i = 0; i < s.size(); ++i) {
			res.push_back(string(1, s[i]));
		}
		return res;
	}

	for (int i = 0; i <= s.size() - k; ++i) {
		string substring = s.substr(i+1);
		vector<string> tmp = cnk(substring, k-1);
		for (int j = 0; j < tmp.size(); ++j) {
			res.push_back(string(1, s[i]) + tmp[j]);
			res.push_back(tmp[j] + string(1, s[i]));
		}
	}
	return res;
}
};

void reverseSentence(string &src) {
    reverse(src.begin(), src.end());
    for (int i=0; i <src.size(); ++i) {
        while(i<src.size()&&src[i]==' ') ++i;
        int st=i;
        while(i<src.size()&&src[i]!=' ') ++i;
        reverse(src.begin()+st, src.begin()+i);
    }
}

void shuffleCards(int cards[], int n) {
    if (n<=0) return ;
    srand((unsigned int)time(0));
    for (int i=1; i<n; ++i) {
        int pos = rand()%(i+1);
        swap(cards[i], cards[pos]);
    }
    cout << cards[0];
    for (int i=1; i<n; ++i) {
        cout << " " << cards[i];
    }
    cout << endl;

}
void myMemcpy(char* dst, const char* src, int nBytes) {

// Try to be fast and copy a word at a time instead of byte by byte

 int* wordDst = (int*)dst;
 int* wordSrc = (int*)src;
 int numWords = nBytes >> 2;
 for (int i=0; i < numWords; i++)
 {
 *wordDst++ = *wordSrc++;
 }

 int numRemaining = nBytes - (numWords << 2);
 dst = (char*)wordDst;
 src = (char*)wordSrc;
 for (int i=0 ; i <= numRemaining; i++);
 {
 *dst++ = *src++;
 }
}

int main() {
//    string input;
//    int k;
//    Solution s;
//    while (cin >> input >> k) {
//        vector<string> vec = s.cnk(input, k);
//        printVector<string>(vec);
//    }
string src = "Tomorrow is saturday!";
reverseSentence(src);
cout <<src <<endl;

int cards[5] = {1,2,3,4,5};
shuffleCards(cards, 5);

char* src1="abcdef";
char* dst="eeeeeeeeee";
myMemcpy(dst, src1,3);
cout << dst << endl;
}
