package com.lt.recursion;

public class Queue8 {

    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果，比如arr={0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {

        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d解法", count);
        System.out.println();
        System.out.printf("一个判断冲突的次数%d", +judgeCount);
    }

    /**
     * 编写一个方法，放置n个皇后
     * 注意: check是每一次递归时，进入到check中都有for (int i = 0; i < max; i++) ，因此会有回溯
     *
     * @param n
     */
    public void check(int n) {
        if (n == max) {  //n=8,其实8个皇后就放好了
            print();
            return;
        }

        //依此放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n，放到该行的第一列
            array[n] = i;
            //判断当前放置第n个皇后到i列时，是否冲突
            if (judge(n)) {  //不冲突
                //接着放n+1个皇后，即开始递归
                check(n + 1);
            }
            //如果冲突，就继续执行array[n]=i;即将第n个皇后，放置在本行的后移一个位置
        }
    }


    /**
     * 查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            /**
             * 说明:
             * 1. array[i] == array[n] 表示判断第n个皇后是否和前面的n-1个皇后在同一列
             * 2.Math.abs(n - i) == Math.abs(array[n] - array[i])表示判断第n个皇后是否和第i个皇后是否在同一斜线
             *  n=1 放置第2个皇后在第2列 n=1 arr[1]=1
             *  当i=0时 Math.abs(1-0) ==1 Mat.abs(array[n]-array[i])=Math.abs(1-0)=1
             * 3.判断是否在同一行，没有必要，n每次都在递增
             */
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 写一个方法，可以将皇后摆放的位置输出
     */
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
