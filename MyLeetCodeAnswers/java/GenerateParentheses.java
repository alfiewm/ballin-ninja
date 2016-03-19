import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Source   : https://leetcode.com/problems/merge-two-sorted-lists/
 * Author   : Wang Meng
 * Date     : 2016-03-18
 * <p>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) return Arrays.asList("");
        List<String> ret = new ArrayList<>();
        generate(ret, "", n, 0);
        return ret;
    }

    public void generate(List<String> ret, String cur, int n, int leftCount) {
        if (cur.length() == n * 2) {
            ret.add(cur);
            return;
        }
        int rightCount = cur.length() - leftCount;
        if (leftCount == rightCount) {
            generate(ret, cur + "(", n, leftCount + 1);
        } else if (leftCount > rightCount) {
            if (leftCount < n) {
                generate(ret, cur + "(", n, leftCount + 1);
            }
            generate(ret, cur + ")", n, leftCount);
        }
    }
}
