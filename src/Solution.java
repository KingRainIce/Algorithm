import java.io.ObjectInputStream;
import java.util.*;


/**
 * @title:Solution
 * @Author Ice
 * @Date: 2022/3/31 22:41
 * @Version 1.0
 */
class Solution {
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;
        int maxEnd = 0;
        int maxLen = 1;

        boolean[][] dp = new boolean[strLen][strLen];


        return s.substring(maxStart, maxEnd + 1);

    }
}