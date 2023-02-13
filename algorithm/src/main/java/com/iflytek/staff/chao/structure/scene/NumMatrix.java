package com.iflytek.staff.chao.structure.scene;

/**
 * @author : wangchaodee
 * @Description: 304. 二维区域和检索 - 矩阵不可变
 * @date Date : 2023年02月10日 11:00
 */
public class NumMatrix {
    int[][] matrixSum;
    public NumMatrix(int[][] matrix) {

        int M =  matrix.length ;
        int N= matrix[0].length ;
        matrixSum = new int[M+1][N+1] ;
        for(int i=0 ; i< M ; i++){
            for(int j=0;j< N ; j++){
                matrixSum[i+1][j+1]=matrixSum[i][j+1] +matrixSum[i+1][j] -matrixSum[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return matrixSum[row2+1][col2+1] - matrixSum[row2+1][col1] - matrixSum[row1][col2+1] +matrixSum[row1][col1];
    }
/***********************************************************************/
        // 平均运行时间更少
    public NumMatrix(int[][] matrix, boolean row) {

        int M =  matrix.length ;
        int N= matrix[0].length ;
        matrixSum = new int[M][N+1] ;
        for(int i=0 ; i< M ; i++){
            for(int j=0;j< N ; j++){
                matrixSum[i][j+1]=matrixSum[i][j]+ matrix[i][j];
            }
        }
    }

    public int sumRegion2(int row1, int col1, int row2, int col2) {
        int sum = 0 ;
        for(int i= row1; i<row2+1 ;i++){
            sum += matrixSum[i][col2+1] - matrixSum[i][col1] ;
        }
        return sum ;
    }
}
