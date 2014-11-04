/*From discuss area, use O(n) space, without modifing original triangle*/
class Solution {
public:
    int minimumTotal(vector<vector<int> > &triangle) {
        if (triangle.size() == 0) return 0;
        vector<int> minLen(triangle.back());
        for (int i=triangle.size()-2; i>=0; --i) {
            for (int j=0; j<=i; ++j) {
                minLen[j] = min(minLen[j], minLen[j+1]) + triangle[i][j];
            }
        }
        return minLen[0];
    }
};

/**Mine O(n) none recursive solution, 但是修改原三角*/
class Solution {
public:
    int minimumTotal(vector<vector<int> > &triangle) {
        if (triangle.size() == 0) return 0;
        for (int i=triangle.size()-2; i>=0; --i) {
            for (int j=0; j<=i; ++j) {
                if (triangle[i+1][j]>triangle[i+1][j+1]) {
                    triangle[i][j] = triangle[i][j] + triangle[i+1][j+1];
                } else {
                    triangle[i][j] = triangle[i][j] + triangle[i+1][j];
                }
            }
        }
        return triangle[0][0];
    }
};

/*Mine Recursive Solution, TLE*/
class Solution {
public:
    int minimumTotal(vector<vector<int> > &triangle) {
        if (triangle.size() == 0) return 0;
        return minimumTotal(triangle, 0, 0);
    }
    
    int minimumTotal(vector<vector<int> > &triangle, int startLine, int startIndex) {
        if (startLine == triangle.size()-1) {
            return triangle[startLine][startIndex];
        }
        
        int leftPath = minimumTotal(triangle, startLine+1, startIndex);
        int rightPath = minimumTotal(triangle, startLine+1, startIndex+1);
        if (leftPath < rightPath) {
            return triangle[startLine][startIndex] + leftPath;
        } else {
            return triangle[startLine][startIndex] + leftPath;
        }
    }
};