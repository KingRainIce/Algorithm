package dynamic;

/**
 * @title:KnapsackProblem
 * @Author Ice
 * @Date: 2022/3/20 22:35
 * @Version 1.0
 * @discription 动态规划算法
 */

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weight = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值
        int capacity = 4; //背包的容量
        int n = val.length; //物品的个数

        //v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][capacity + 1];
        //是否放入新物品
        int[][] isPut = new int[n + 1][capacity + 1];

        //初始化第一行和第一列,在本程序中可以不去处理,因为默认就是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (weight[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    if (v[i - 1][j] < val[i - 1] + val[j - weight[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - weight[i - 1]];
                        isPut[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
//        倒序查找
        int i = isPut.length - 1;
        int j = isPut[0].length - 1;
        while (i * j > 0) {
            if (isPut[i][j] == 1) {
                System.out.printf("第%d个商品放入背包\n", i);
                j -= weight[i - 1];
            }
            i--;
        }
    }
}