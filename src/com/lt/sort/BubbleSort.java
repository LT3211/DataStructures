package com.lt.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        //demo1();

        //测试冒泡排序
        int arr[] = {3, 9, -1, 10, -2};
        bubbleSort2(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 冒泡排序的演变过程
     */
    private static void demo1() {
        int arr[] = {3, 9, -1, 10, -2};

        //第一趟排序，就是将最大的数排在最后
        int temp = 0; //临时变量
        for (int j = 0; j < arr.length - 1; j++) {
            //如果前面的数比后面的数大，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }

        System.out.println("第一趟排序后的数组:");
        System.out.println(Arrays.toString(arr));


        //第二趟排序，就是将第二大的数排在倒数第二位
        for (int j = 0; j < arr.length - 1 - 1; j++) {
            //如果前面的数比后面的数大，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }

        System.out.println("第二趟排序后的数组:");
        System.out.println(Arrays.toString(arr));

        //第三趟排序，就是将第三大的数排在倒数第三位
        for (int j = 0; j < arr.length - 1 - 1 - 1; j++) {
            //如果前面的数比后面的数大，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }

        System.out.println("第三趟排序后的数组:");
        System.out.println(Arrays.toString(arr));


        //第四趟排序，就是将第四大的数排在倒数第四位
        for (int j = 0; j < arr.length - 1 - 1 - 1 - 1; j++) {
            //如果前面的数比后面的数大，则交换
            if (arr[j] > arr[j + 1]) {
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }

        System.out.println("第四趟排序后的数组:");
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 冒泡排序 的时间复杂度O(n^2)
     * 从小到大
     */
    public static void bubbleSort(int arr[]) {

        int temp = 0; //临时变量
        boolean flag = false; //标识变量，表示是否进行交换过

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (!flag) { //在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; //重置flag,进行下次判断
            }
        }
    }

    /**
     * 冒泡排序 的时间复杂度O(n^2)
     * 从大到小
     *
     * @param arr
     */
    public static void bubbleSort2(int arr[]) {

        int temp;
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    flag = false; //说明有元素交换过了
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (flag) { //说明没有交换过元素，已经排好序
                break;
            } else {
                flag = true;
            }
        }

    }
}
