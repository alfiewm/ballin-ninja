/**
 * Source   : https://leetcode.com/problems/excel-sheet-column-title/
 * Author   : Wang Meng
 * Date     : 2016-03-16
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * <p>
 * For example:
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 */
public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        do {
            n -= 1;
            sb.insert(0, getChar(n % 26));
            n /= 26;
        } while (n != 0);
        return sb.toString();
    }

    private char getChar(int n) {
        return (char) ('A' + n);
    }

    public String convertToTitle2(int n) {
        String ret = "";
        do {
            ret = ('A' + (n - 1) % 26) + ret;
            n = (n - 1) / 26;
        } while (n != 0);
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(29));
    }
}
