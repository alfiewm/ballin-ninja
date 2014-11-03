
/*My solution*/
class Solution {
public:
    int maxProfit(vector<int> &prices) {
    	int n = prices.size();
        int maxP(0);
        int curMax(0);
        if (n > 0) curMax = prices[n-1];
        for (int i = n-2; i >=0; --i) {
        	if (prices[i] < curMax) {
        		maxP = max(maxP, curMax - prices[i]);
        	} else {
        		curMax = prices[i];
        	}
        }
    	return maxP;
    }
};


/*MD, 又理解错题意了！，只允许买卖一次*/
class Solution {
public:
    int maxProfit(vector<int> &prices) {
        int result(0);
        for (int i=1; i < prices.size(); ++i) {
        	while (i < prices.size() && prices[i] <= prices[i-1]) {
        		++i;
        	}
        	int startRising = prices[i-1];
        	while (i < prices.size() && prices[i] >= prices[i-1]) {
        		++i;
        	}
        	int stopRising = prices[i-1];
        	result += (stopRising - startRising);
        }
    	return result;
    }
};