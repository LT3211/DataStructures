package com.lt.sparsearray;

import java.io.*;

public class IOUtil {
    //将稀疏数组存入文件
    public static File writeArray(int[][] array) {
        //将稀疏数组写入文件
        File file = new File("data.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            for (int arr[] : array) {
                for (int j = 0; j < arr.length; j++) {
                    fileWriter.write(arr[j] + "\t"); //以字符串的形式写入文件
                }
                fileWriter.write("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    //从文件读取到稀疏数组
    public static int[][] readArray(File file) {
        LineNumberReader lnr = null;
        BufferedReader in = null;
        int array[][] = null;
        try {
            lnr = new LineNumberReader(new FileReader(file));
            //开始一个字符一个字符的跳过 一直到最后一个字符。读取完成
            lnr.skip(Long.MAX_VALUE);
            //lnr.getLineNumber()+1 获得数组有几行
            array = new int[lnr.getLineNumber() + 1][3];
            in = new BufferedReader(new FileReader(file));
            String line;  //一行数据
            int row = 0;
            //逐行读取，并将每个数据放入到数组中
            while ((line = in.readLine()) != null) {
                String[] temp = line.split("\t");
                for (int j = 0; j < temp.length; j++) {
                    array[row][j] = Integer.parseInt(temp[j]);
                }
                row++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                lnr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return array;
    }
}
