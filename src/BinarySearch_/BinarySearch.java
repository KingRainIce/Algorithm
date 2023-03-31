package BinarySearch_;

/**
 * @title:BinarySearch
 * @Author Ice
 * @Date: 2022/3/20 20:01
 * @Version 1.0
 */

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 10);
        System.out.println("index=" + index);
    }

    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = (right + left) / 2;
            if (target < arr[mid]){
                right = mid - 1;
            }else if (target == arr[mid]){
                return mid;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }

}