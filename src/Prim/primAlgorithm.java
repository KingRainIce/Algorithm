package Prim;

import java.util.Arrays;
import java.util.Map;

/**
 * @title:primAlgorithm
 * @Author Ice
 * @Date: 2022/3/22 22:45
 * @Version 1.0
 */

public class primAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int [][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};

        MGraph mGraph = new MGraph(verxs, data, weight);

        System.out.println(Arrays.toString(mGraph.data));
        MinTree minTree = new MinTree();
        minTree.prim(mGraph, 1);

    }


}

class MinTree{

    public void showGraph(MGraph graph){
        for (int[] link : graph.weight)
            System.out.println(Arrays.toString(link));
    }

    /**
     * @author:Ice
     * @date:  2022/3/22 23:08
     * @description:
     * @param v 表示从图的第几个顶点开始生成  'A' ->0
     */
    public void prim(MGraph graph, int v){
        int visited[] = new int[graph.verxs];//表示顶点是否被访问过,默认初始化为 0
        visited[v] = 1;//标记当前结点已访问
        //记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//表示无穷，代表不连通
        // n 个顶点要得到 n - 1 条边
        for (int k = 1; k < graph.verxs; k++){
//            i 表示查找点的下标
            for (int i = 0; i < graph.verxs; i++){
//                 j 表示被查找的下标，比较出最小值
                for (int j = 0; j < graph.verxs; j++){
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }


    }

}



class MGraph{
    int verxs;
    char[] data;
    int[][] weight;

    public MGraph(int verxs, char[] data, int[][] weight){
        this.verxs = verxs;
        this.weight = weight;
        this.data = data;
        System.out.println(Arrays.toString(data));
    }
}