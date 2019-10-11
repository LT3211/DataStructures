package com.lt.search;

import java.util.Arrays;

/**
 * 插值查找算法
 * 与二分类似，只是mid的取值更接近目标数据
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int arr[] = new int[100];

        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        int index = insertValueSearch(arr, 0, arr.length - 1, 0);

        System.out.println("index=" + index);
    }


    /**
     * 编写插值查找算法
     * 说明:插值查找算法，也要求数组是有序的
     *
     * @param arr     待查找数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 目标值
     * @return 如果找到就返回对应的下标，如果没有找到，返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        if (left > right || findVal < arr[0] || findVal > arr[right]) {
            return -1;
        }

        //求出mid，自适应写法
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {    //说明向右查找
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
