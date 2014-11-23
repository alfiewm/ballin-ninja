// Source   : https://oj.leetcode.com/problems/gas-station/
// Authoer  : Wang Meng
// Date     : 2014-11-23

/*
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * 
 * Note:
 * The solution is guaranteed to be unique.
 */

// another solution
class Solution {
public:
    int canCompleteCircuit(vector<int> &gas, vector<int> &cost) {
    	int start(0), total(0), tank(0);
    	for (int i=0; i<gas.size(); ++i) {
    		tank += (gas[i]-cost[i]);
    		if (tank < 0) {
    			// from "start" station cannot reach i station, so any station between them cannot reach i too. we start from i+1
    			start = i+1;
    			total += tank;
    			tank = 0;
    		}
    	}
    	// if total gas > total cost, there must be a solution, otherwise no solution.
    	return (total+tank<0) ? -1 : start;
    }
};

// solution from discuss area, very graceful
class Solution {
public:
    int canCompleteCircuit(vector<int> &gas, vector<int> &cost) {
    	int sum = 0;
    	int forward = 0;
    	int start = gas.size(); // it's a loop, this means station 0
    	int stationToSum = 0;
    	do {
    		if (sum + gas[forward] - cost[forward] >= 0) {
    			stationToSum = forward++;
    		} else { // meas we cannot start from "start", cause to here sum<0, we have to try to start from "start-1"
    			stationToSum = --start;
    		}
    		sum += (gas[stationToSum] - cost[stationToSum]);
    	} while (forward != start);

    	if (sum < 0) return -1;
    	else return start%gas.size();
    }
};

// my solution, TLE, %>_<%
class Solution {
public:
    int canCompleteCircuit(vector<int> &gas, vector<int> &cost) {
        if (gas.empty() || gas.size()!=cost.size()) return -1;
        int n = gas.size();
        vector<int> move(n, 0);
        for (int i=0; i<n; ++i) {
            move[i] = gas[i] - cost[i];
        }
        
        for (int start=0; start<n; ++start) {
            long long rest = move[start];
            for (int j=(start+1)%n; rest>=0 && j!=start; j=(j+1)%n) {
                rest += move[j];
            }
            if (rest >= 0) return start;
        }
        return -1;
    }
};