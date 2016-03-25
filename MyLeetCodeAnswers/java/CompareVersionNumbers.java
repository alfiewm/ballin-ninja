/**
 * Source   : https://leetcode.com/problems/compare-version-numbers/
 * Author   : Wang Meng
 * Date     : 2016-03-25
 * <p>
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * <p>
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 * <p>
 * Here is an example of version numbers ordering:
 * <p>
 * 0.1 < 1.1 < 1.2 < 13.37
 */
public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        String[] v1s = version1.split("\\.");
        String[] v2s = version2.split("\\.");
        int length = Math.max(v1s.length, v2s.length);
        for (int i = 0; i < length; ++i) {
            int v1i = i < v1s.length ? Integer.parseInt(v1s[i]) : 0;
            int v2i = i < v2s.length ? Integer.parseInt(v2s[i]) : 0;
            if (v1i > v2i) {
                return 1;
            } else if (v1i < v2i) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new CompareVersionNumbers().compareVersion("1.2", "1.1"));
        System.out.println(new CompareVersionNumbers().compareVersion("1.1", "1.1"));
        System.out.println(new CompareVersionNumbers().compareVersion("1.1.2", "1.1.1"));
        System.out.println(new CompareVersionNumbers().compareVersion("1.2", "1.11"));
        System.out.println(new CompareVersionNumbers().compareVersion("1.2", "1.2"));
    }
}
