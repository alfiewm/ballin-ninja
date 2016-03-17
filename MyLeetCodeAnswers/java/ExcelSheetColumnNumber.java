/**
 * Source   : https://leetcode.com/problems/excel-sheet-column-number/
 * Author   : Wang Meng
 * Date     : 2016-03-16
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * <p>
 * For example:
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 */
public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) return 0;
        int ret = 0;
        for (int i = 0; i < s.length(); ++i) {
            ret = ret * 26 + getInt(s.charAt(i));
        }
        return ret;
    }

    private int getInt(char c) {
        return c - 'A' + 1;
    }
}
