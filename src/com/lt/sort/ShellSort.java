package com.lt.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0,-1,-2};
        shellSort3(arr);
    }

    /**
     * 使用逐步推导的方式来编写希尔排序
     * 对有序序列在插入时采用交换法
     *
     * @param arr
     */
    public static void shellSort(int[] arr) {

        int temp = 0;
        //希尔排序的第一轮排序
        //因为第一轮排序，是将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中的所有元素(共五组，每组有两个元素)，步长5
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序1轮后=" + Arrays.toString(arr));

        //---------------------------------------------------------------

        //希尔排序的第二轮排序
        //因为第二轮排序，是将10个数据分成了5/2组
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中的所有元素(共2组，每组有5个元素)，步长2
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序2轮后=" + Arrays.toString(arr));


        //---------------------------------------------------------------

        //希尔排序的第三轮排序
        //因为第三轮排序，是将10个数据分成了5/2/2组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中的所有元素(共1组，每组有10个元素)，步长1
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素大于加上步长后的那个元素，说明交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序3轮后=" + Arrays.toString(arr));

    }


    /**
     * 希尔排序
     * 对有序序列在插入时采用交换法
     *
     * @param arr
     */
    public static void shellSort2(int[] arr) {

        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中的所有元素(共gap组，每组有两n/gap个元素)，步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("希尔排序" + (++count) + "轮后=" + Arrays.toString(arr));
        }
    }

    /**
     * 对交换的希尔排序进行优化
     * 对有序序列在插入时采用移位法
     *
     * @param arr
     */
    public static void shellSort3(int[] arr) {

        //增量gap，并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接擦汗如排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while循环后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
