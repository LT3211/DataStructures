package com.lt.sparsearray;

import java.io.*;

/**
 * 优化后代码，有文件的操作
 */
public class SparseArray2 {
    public static void main(String[] args) {
        //创建原始数组
        //0表示没有棋子，1表示黑子，2表示蓝子
        int chessArray[][] = new int[11][11];
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[2][4] = 2;
        chessArray[1][4] = 1;

        System.out.println("打印原始数组:");
        for (int arr[] : chessArray) {
            for (int j = 0; j < arr.length; j++) {
                System.out.printf("%d\t", arr[j]);
            }
            System.out.println();
        }

        //获得原数组不为0的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    sum++;
                }
            }
        }

        //创建稀疏数组
        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        int count = 0; //计数
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArray[i][j];
                }
            }
        }

        System.out.println("稀疏数组为:");
        for (int arr[] : sparseArray) {
            for (int j = 0; j < arr.length; j++) {
                System.out.printf("%d\t", arr[j]);
            }
            System.out.println();
        }

        File file=IOUtil.writeArray(sparseArray);

        int sparseArray2[][]=IOUtil.readArray(file);




        //根据稀疏数组还原原队列
        int chessArray2[][] = new int[sparseArray2[0][0]][sparseArray2[0][1]];
        for (int i = 1; i < sparseArray2.length; i++) {
            for (int j = 0; j < 3; j++) {
                chessArray2[sparseArray2[i][0]][sparseArray2[i][1]] = sparseArray2[i][2];
            }
        }

        System.out.println("还原数组");
        for (int arr[] : chessArray2) {
            for (int j = 0; j < arr.length; j++) {
                System.out.printf("%d\t", arr[j]);
            }
            System.out.println();
        }

    }
}
