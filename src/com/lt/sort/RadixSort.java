package com.lt.sort;

import java.util.Arrays;

/**
 * 基数排序(桶排序)
 */
public class RadixSort {
    public static void main(String[] args) {
        int arr[] = {53, 3, 542, 748, 14, 214};
        radixSort2(arr);
    }

    /**
     * 基数排序方法推导过程
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {

        //第一轮(针对每个元素的各位进行排序处理)

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定位arr.length
        //3.基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际放入了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        //第一轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] / 1 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //按照这个桶的顺序(一维数组的下标依此取出数据，放入原来数组)
        int index = 0;
        //遍历每一个桶，并将桶中的数据放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，我们才放入原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶即第k个桶(即第k个一维数组),放入原数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //第一轮处理后，需要将每个bucketElementCounts[k]=0!!!
            bucketElementCounts[k] = 0;
        }

        System.out.println("第一轮，对个位的排序处理arr=" + Arrays.toString(arr));

        //第二轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的十位的值
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //按照这个桶的顺序(一维数组的下标依此取出数据，放入原来数组)
        index = 0;
        //遍历每一个桶，并将桶中的数据放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，我们才放入原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶即第k个桶(即第k个一维数组),放入原数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCounts[k] = 0;
        }

        System.out.println("第二轮，对十位的排序处理arr=" + Arrays.toString(arr));

        //第三轮(针对每个元素的个位进行排序处理)
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的百位的值
            int digitOfElement = arr[j] / 100 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }

        //按照这个桶的顺序(一维数组的下标依此取出数据，放入原来数组)
        index = 0;
        //遍历每一个桶，并将桶中的数据放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据，我们才放入原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶即第k个桶(即第k个一维数组),放入原数组
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            bucketElementCounts[k] = 0;
        }

        System.out.println("第三轮，对百位的排序处理arr=" + Arrays.toString(arr));
    }

    /**
     * 基数排序方法
     *
     * @param arr
     */
    public static void radixSort2(int[] arr) {

        //得到数组中最大的数的位数
        int max = arr[0]; //假定第一个数就是最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        //得到最大数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //说明
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定位arr.length
        //3.基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际放入了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素对应的位进行排序处理，第一次是个位，第二次是十位...
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            //按照这个桶的顺序(一维数组的下标依此取出数据，放入原来数组)
            int index = 0;
            //遍历每一个桶，并将桶中的数据放入原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才放入原数组中
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶(即第k个一维数组),放入原数组
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个bucketElementCounts[k]=0!!!
                bucketElementCounts[k] = 0;
            }
            System.out.println("第" + (i + 1) + "轮，对个位的排序处理arr=" + Arrays.toString(arr));
        }
    }
}
