/**
 * Source   : https://leetcode.com/problems/implement-strstr/
 * Author   : Wang Meng
 * Date     : 2016-03-19
 * <p>
 * Implement strStr().
 * <p>
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
public class ImplementStrstr {
    public int strStr(String haystack, String needle) {
        if (haystack == null) return -1;
        if (needle == null || needle.isEmpty()) return 0;
        for (int i = 0; i < haystack.length(); ++i) {
            if (haystack.substring(i).startsWith(needle)) {
                return i;
            }
        }
        return -1;
    }
}
