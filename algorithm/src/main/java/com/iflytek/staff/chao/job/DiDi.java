package com.iflytek.staff.chao.job;

public class DiDi {

    public void print(int[][] matrix) {

        if (matrix == null) {
            return;
            //
        }

        int M = matrix.length;
        int N = matrix[0].length;
        int t = 0, d = M - 1; //行
        int l = 0, r = N - 1; //列
        while (t <= d && l <= r) {

            //行
            for (int i = l; i <= r; i++) {
                printNum(matrix[t][i]);
            }
            t++;
            if (t > d) {
                break;
            }
            //右列
            for (int j = t; j <= d; j++) {
                printNum(matrix[j][r]);
            }

            r--;
            if (l > r) {
                break;
            }
            //底行
            for (int i = r; i >= l; i--) {
                printNum(matrix[d][i]);
            }
            d--;

            if (t > d) {
                break;
            }
            //左列
            for (int j = d; j >= t; j--) {
                printNum(matrix[j][l]);
            }
            l++;
            if (l > r) {
                break;
            }
        }


    }

    private void printNum(int num) {
        //打印
        System.out.println(num);
    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        DiDi diDi = new DiDi();
        diDi.print(matrix);

        System.out.println("num");

    }

    public int[] spiralOrder(int[][] matrix) {

        int[] ret;
        if (matrix == null) {
            return null;
            //
        }

        int M = matrix.length;
        int N = matrix[0].length;

        ret = new int[M * N];
        int t = 0, d = M - 1; //行
        int l = 0, r = N - 1; //列
        int index = 0;
        while (t <= d && l <= r) {

            //行
            for (int i = l; i <= r; i++) {
                ret[index++] = matrix[t][i];
            }
            t++;
            if (t > d) {
                break;
            }
            //右列
            for (int j = t; j <= d; j++) {
                ret[index++] = matrix[j][r];
            }
            r--;
            if (l > r) {
                break;
            }
            //底行
            for (int i = r; i >= l; i--) {
                ret[index++] = matrix[d][i];
            }
            d--;
            if (t >= d) {
                break;
            }
            //左列
            for (int j = d; j >= t; j--) {
                ret[index++] = matrix[j][l];
            }
            l++;
        }
        return ret;
    }

}
