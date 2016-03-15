/**
 * Source   : https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Author   : Wang Meng
 * Date     : 2016-03-15
 * <p>
 * Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int[] pos = new int[256];
        for (int i = 0; i < pos.length; ++i) {
            pos[i] = -1;
        }
        int start = 0, maxL = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (pos[s.charAt(i)] >= start) {
                start = pos[s.charAt(i)] + 1;
            }
            maxL = Math.max(maxL, i - start + 1);
            pos[s.charAt(i)] = i;
        }
        return maxL;
    }
}
