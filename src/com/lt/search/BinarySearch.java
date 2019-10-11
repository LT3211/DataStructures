package com.lt.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 注意:二分查找的前提是该数组是有序的
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1234};

        /*int resIndex = binarySearch(arr, 0, arr.length - 1, 89);
        System.out.println("resIndex=" + resIndex);*/

        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("list=" + list);
    }

    /**
     * 二分查找算法
     *
     * @param arr     数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }
    }


    /**
     * 思考题{1，8，10，89，1000，1000，1234}当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如1000
     * <p>
     * 思路分析：
     * 1. 在找到mid索引值，不要马上返回
     * 2.向mid索引的左边扫描，将所有满足1000，的元素的下标，加入到集合List中
     * 3.向mid索引的右边扫描，将所有满足1000，的元素的下标，加入到集合List中
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else {
            List<Integer> list = new ArrayList<>();
            //向mid索引的左边扫描，将所有满足1000，的元素的下标，加入到集合List中
            int temp = mid - 1;
            while (true) {
                //判断是否已经不等于目标值或下标超出
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                //将下标存入List
                //这样写右一个弊端，当相同的数过多，左边的下标值就不是顺序的了
                list.add(temp--);
            }

            list.add(mid);

            //向mid索引的右边扫描，将所有满足1000，的元素的下标，加入到集合List中
            temp = mid + 1;
            while (true) {
                if (temp > right || arr[temp] != findVal) {
                    break;
                }
                list.add(temp++);
            }
            return list;
        }
    }
}
