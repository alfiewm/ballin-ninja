import java.util.ArrayList;
import java.util.List;

/**
 * Source   : https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * Author   : Wang Meng
 * Date     : 2016-03-18
 * Given a digit string, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * <p>
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinationsOfAPhoneNumber {
    public static String[] map = {" ", "@", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return ret;
        String first = map[digits.charAt(0) - '0'];
        List<String> rest = letterCombinations(digits.substring(1));
        if (rest.size() == 0) {
            for (int i = 0; i < first.length(); ++i) {
                ret.add(String.valueOf(first.charAt(i)));
            }
        } else {
            for (int i = 0; i < first.length(); ++i) {
                for (int j = 0; j < rest.size(); ++j) {
                    ret.add(first.charAt(i) + rest.get(j));
                }
            }
        }
        return ret;
    }
}
