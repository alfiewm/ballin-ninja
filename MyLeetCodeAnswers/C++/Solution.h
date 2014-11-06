#include <iostream>
#include <vector>
#include <cstring>
#include <stack>
#include <algorithm>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

void printVector(std::vector<int> v) {
	for (int i = 0; i < v.size(); ++i) {
		cout << v[i] << " ";
	}
	cout << endl;
}