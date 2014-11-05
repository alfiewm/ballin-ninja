/*非递归的实现，From chen Hao*/
class Solution {
public:
    vector<vector<int> > permute(vector<int> &num) {
        vector<vector<int> > result;
        result.push_back(num);
        if (num.size() < 2) return result;

        for (int pos = 0; pos < num.size() -1; ++ pos) {
            int size = result.size();
            for (int i = 0; i < size; ++i) {
                for (int j = pos+1; j < result[i].size(); ++j) {
                    vector<int> v = result[i];
                    swap(v[pos], v[j]);
                    result.push_back(v);
                }
            }
        }
        return result;
    }
};

/*看onenote笔记，优化之后的代码*/
class Solution {
public:
    vector<vector<int> > result;
    vector<vector<int> > permute(vector<int> &num) {
        result.clear();
        permute(num, 0);
        return result;
    }
    
    void permute(vector<int> &num, int start) {
        if (num.size() == start+1) {
            // find one!
            result.push_back(num);
            return ;
        }
        for (int i = start; i < num.size(); ++i) {
            // swap
            swap(num[start], num[i]);
            //recursive
            permute(num, start+1);
            //swap back
            swap(num[start], num[i]);
        }
    }
};

/*直写，比较丑，没有使用系统swap方法，递归调用多此一举*/
class Solution {
public:
    vector<vector<int> > permute(vector<int> &num) {
        return permute(num, 0);
    }
    
    vector<vector<int> > permute(vector<int> &num, int start) {
        vector<vector<int> > result;
        if (num.size() <= 0) return result;
        if (num.size() == start+1) {
            result.push_back(vector<int>(1, num[start]));
            return result;
        }
        for (int i = start; i < num.size(); ++i) {
            // swap
            int t = num[start];
            num[start] = num[i];
            num[i] = t;
            //recursive
            vector<vector<int> > tmp = permute(num, start+1);
            for (int k=0; k < tmp.size(); ++k) {
                tmp[k].push_back(num[start]);
            }
            result.insert(result.end(), tmp.begin(), tmp.end());
            //swap back
            t = num[start];
            num[start] = num[i];
            num[i] = t;
        }
    }
};