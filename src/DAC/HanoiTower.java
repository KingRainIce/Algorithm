package DAC;

/**
 * @title:Hanoitower
 * @Author Ice
 * @Date: 2022/3/20 20:20
 * @Description 分治算法
 * @Version 1.0
 */

public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(5,'a', 'b', 'c');

    }

    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第一个盘从" + a + "->" + c);
        } else {
            //如果我们有 n >= 2 情况，总是可以看做是两个盘 1.最下边的一个盘 2. 上面的所有盘

            //1. 先把 最上面的所有盘 A->B， 移动过程会使用到 c
            hanoiTower(num - 1, a, c, b);
            //2. 把最下边的盘 A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3. 把B塔的所有盘 从 B->C , 移动过程使用到 a塔
            hanoiTower(num - 1, b, a, c);

        }

    }

}