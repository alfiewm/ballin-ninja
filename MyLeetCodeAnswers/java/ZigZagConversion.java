/**
 * Source   : https://leetcode.com/problems/zigzag-conversion/
 * Author   : Wang Meng
 * Date     : 2016-03-16
 * <p>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if (numRows <= 1) return s;
        String[] rows = new String[numRows];
        for (int i = 0; i < numRows; ++i) {
            rows[i] = "";
        }
        int step = 0;
        int curRow = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (curRow == 0) step = 1;
            if (curRow == numRows - 1) step = -1;
            rows[curRow] += s.charAt(i);
            curRow += step;
        }
        String ret = "";
        for (int i = 0; i < numRows; ++i) {
            ret += rows[i];
        }
        return ret;
    }
}
