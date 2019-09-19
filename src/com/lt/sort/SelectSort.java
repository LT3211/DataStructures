package com.lt.sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        int arr[] = {101, 34, 119, 1};
        selectSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序推导过程
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        //第一轮
        int minIndex = 0;
        int min = arr[0];
        for (int j = 0 + 1; j < arr.length; j++) {
            if (min > arr[j]) {    //说明假定的最小值，并不是最小
                min = arr[j];     //重置min
                minIndex = j;    //重置minIndex
            }
        }

        //将最小值，放在arr[0],即交换
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }

        System.out.println("第一轮后:");
        System.out.println(Arrays.toString(arr));

        //第二轮
        minIndex = 1;
        min = arr[1];
        for (int j = 1 + 1; j < arr.length; j++) {
            if (min > arr[j]) {    //说明假定的最小值，并不是最小
                min = arr[j];     //重置min
                minIndex = j;    //重置minIndex
            }
        }

        //将最小值，放在arr[0],即交换
        if (minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }

        System.out.println("第二轮后:");
        System.out.println(Arrays.toString(arr));


        //第三轮
        minIndex = 2;
        min = arr[2];
        for (int j = 2 + 1; j < arr.length; j++) {
            if (min > arr[j]) {    //说明假定的最小值，并不是最小
                min = arr[j];     //重置min
                minIndex = j;    //重置minIndex
            }
        }

        //将最小值，放在arr[0],即交换
        if (minIndex != 2) {
            arr[minIndex] = arr[2];
            arr[2] = min;
        }

        System.out.println("第三轮后:");
        System.out.println(Arrays.toString(arr));

    }


    /**
     * 选择排序
     * 从小到大
     *
     * @param arr
     */
    public static void selectSort2(int[] arr) {

        for (int i = 0; i < arr.length-1; i++) {
            int min = arr[i];
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            if (minIndex != i) {   //如果最小值索引不等于i，则交换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
