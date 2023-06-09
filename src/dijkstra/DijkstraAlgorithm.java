package dijkstra;

import java.rmi.MarshalException;
import java.util.Arrays;

/**
 * @title:DijkstrAlgorithm
 * @Author Ice
 * @Date: 2022/3/25 11:04
 * @Version 1.0
 */

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //创建 Graph对象
        Graph graph = new Graph(vertex, matrix);
        //测试, 看看图的邻接矩阵是否ok
        graph.showGraph();
        //测试迪杰斯特拉算法
        graph.dsj(6);//C
        graph.showDijkstra();
    }
}

class Graph{
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph(){
        for (int[] link : matrix){
            System.out.println(Arrays.toString(link));
        }
    }

    public void showDijkstra(){
        vv.show();
    }

    public void dsj(int index){
        vv = new VisitedVertex(vertex.length, index);
        update(index);//更新index顶点到周围顶点的距离和前驱顶点
        for (int j = 1; j < vertex.length; j++){
            index = vv.updateArr();// 选择并返回新的访问顶点
            update(index);// 更新index顶点到周围顶点的距离和前驱顶点
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index) {
        int len = 0;
        //根据遍历我们的邻接矩阵的  matrix[index]行
        for(int j = 0; j < matrix[index].length; j++) {
            // len 含义是 : 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离的和
            //出发点到 index，index 到 j 的距离
            len = vv.getDis(index) + matrix[index][j];
            // 如果j顶点没有被访问过，并且 len 小于出发顶点到j顶点的距离，就需要更新
            if(!vv.isVisited(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index); //更新j顶点的前驱为index顶点
                vv.updateDis(j, len); //更新出发顶点到j顶点的距离
            }
        }
    }

}

class VisitedVertex{
    // 记录各个顶点是否访问过 1表示访问过,0未访问,会动态更新
    public int[] alreadyArr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] preVisited;
    // 记录出发顶点到其他所有顶点的距离,比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;

    /**
     * @author:Ice
     * @date:  2022/3/25 11:13
     * @description:构造器
     * @param index 出发点对应的下标，G -> 6
     * @param length 顶点的个数
     */
    public VisitedVertex(int length, int index){
        this.alreadyArr = new int[length];
        this.preVisited = new int[length];
        this.dis = new int[length];
        //初始化 dis数组
        Arrays.fill(dis, 65535);
        this.alreadyArr[index] = 1; //设置出发顶点被访问过
        this.dis[index] = 0;//设置出发顶点的访问距离为0
    }


//一些小工具

    /**
     * @author:Ice
     * @date:  2022/3/25 11:16
     * @description:判断顶点是否被访问过
     * @param index 判断的下标
     *
     */
    public boolean isVisited(int index){
        return alreadyArr[index] == 1;
    }

    /**
     * @author:Ice
     * @date:  2022/3/25 11:18
     * @description:更新出发点到顶点的距离
     */
    public void updateDis(int index, int len){
        dis[index] = len;
    }

    /**
     * @author:Ice
     * @date:  2022/3/25 11:19
     * @description:更新 pre 这个顶点的前驱节点为 index 顶点
     */
    public void updatePre(int pre, int index){
        preVisited[pre] = index;
    }

    public int getDis(int index){
        return dis[index];
    }

    /**
     * @author:Ice
     * @date:  2022/3/25 11:27
     * @description:继续选择并返回新的访问顶点， 比如这里的G 完后，就是 A点作为新的访问顶点(注意不是出发顶点)
     */
    public int updateArr(){
        int min = 65535;
        int index = 0;
        for (int i = 0; i < alreadyArr.length; i++){
            if (alreadyArr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }

    public void show() {

        System.out.println("==========================");
        //输出already_arr
        for(int i : alreadyArr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出pre_visited
        for(int i : preVisited) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出dis
        for(int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        //为了好看最后的最短距离，我们处理
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "("+i+") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();

    }


}