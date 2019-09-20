package com.lt.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int arr[] = {101, 34, 119, 1};
        insertSort2(arr);
    }

    /**
     * 逐步推导插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {

        //第一轮{101, 34, 119, 1}=>{34, 101, 119, 1}

        int insertVal = arr[1];   //定义待插入的数
        int insertIndex = 1 - 1;  //即待插入的数的前面这个数的下标

        //给insertVal找到插入的位置
        //insertIndex >= 0 保证在给insertVal找插入位置，不越界
        //insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];   //将啊a[insertIndex]后移
            insertIndex--;
        }

        //当退出循环时，说明插入的位置找到，insertIndex+1
        //比如当比下标为0的元素还小时，此时退出while1循环，insertIndex的值为-1，而插入的下标为0
        arr[insertIndex + 1] = insertVal;

        System.out.println("第一轮插入:");
        System.out.println(Arrays.toString(arr));

        //------------------------------------------------------------------------------

        //第二轮{34, 101, 119, 1}==>{34, 101, 119, 1}

        insertVal = arr[2];   //定义待插入的数
        insertIndex = 2 - 1;  //即待插入的数的前面这个数的下标

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];   //将啊a[insertIndex]后移
            insertIndex--;
        }

        arr[insertIndex + 1] = insertVal;

        System.out.println("第二轮插入:");
        System.out.println(Arrays.toString(arr));

        //------------------------------------------------------------------------------

        //第三轮{34, 101, 119, 1}==>{1, 34, 101, 119}

        insertVal = arr[3];   //定义待插入的数
        insertIndex = 3 - 1;  //即待插入的数的前面这个数的下标

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];   //将啊a[insertIndex]后移
            insertIndex--;
        }
        arr[insertIndex + 1] = insertVal;

        System.out.println("第三轮插入:");
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];   //定义待插入的数
            int insertIndex = i - 1;  //即待插入的数的前面这个数的下标

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];   //将啊a[insertIndex]后移
                insertIndex--;
            }

            //这里我们判断是否需要赋值,如果待插入的位置就是当前位置则不需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
