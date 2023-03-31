package KMP;

/**
 * @title:ViolenceMatch
 * @Author Ice
 * @Date: 2022/3/21 12:19
 * @Version 1.0
 */

public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "hello,what is this";
        String str2 = "t is ";
        int index = violenceMatch(str2, str1);
        System.out.println("index=" + index);
    }

    public static int violenceMatch(String target, String str) {
        char[] a = target.toCharArray();
        char[] b = str.toCharArray();
        int aLen = a.length;
        int bLen = b.length;
        int i = 0;//target
        int j = 0;//str

        while (i < aLen && j < bLen) {
            if (a[i] == b[j]) {
                j++;
                i++;
            } else {
                j = j - (i - 1);
                i = 0;
            }

        }
        if (i == aLen) {
            return j - i;
        }else{
            return -1;
        }

    }
}