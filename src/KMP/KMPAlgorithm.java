package KMP;

import java.util.Arrays;

/**
 * @title:KMPAlgorithm
 * @Author Ice
 * @Date: 2022/3/21 20:34
 * @Version 1.0
 */

public class KMPAlgorithm {
    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int index = kmpSearch(str1, str2);
        System.out.println("index=" + index);
    }

    /**
     * @author:Ice
     * @date:  2022/3/21 22:36
     * @param s1 原字符串
     * @param s2 子串
     */
    public static int kmpSearch(String s1, String s2){
//        生成next数组
        int[] next = kmpNext(s2);

        for (int i = 0, j = 0; i < s1.length(); i++){
            while (j > 0 && s1.charAt(i) != s2.charAt(j)){
                j = next[j - 1];
            }
            if (s1.charAt(i) == s2.charAt(j)){
                j++;
            }
            if (j == s2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    public static int[] kmpNext(String dest){
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++){
            while (j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}