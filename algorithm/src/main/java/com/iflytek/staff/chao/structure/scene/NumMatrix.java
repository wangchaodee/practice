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



    /**
     * 1314. 矩阵区域和
     * @param mat
     * @param k
     * @return
     */
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int[][] matrixSum = getSumMatrix(mat);

        int[][] ans =getMatrixBlockSum(matrixSum,k);
        return ans ;
    }

    private int[][]  getMatrixBlockSum(int[][] matrix ,int k) {

        int M =  matrix.length ;
        int N= matrix[0].length ;
        int[][] matrixSum = new int[M-1][N-1] ;
        for(int i=0 ; i< M-1 ; i++){
            for(int j=0;j< N-1 ; j++){
                int row1 = i-k <0 ? 0 :i-k;
                int cow1 = j-k< 0? 0 :j-k ;
                int row2 = i+k >M-2 ? M-2 :i+k;
                int cow2 = j+k> N-2? N-2 :j+k ;
                matrixSum[i][j]=sumRegion(matrix,row1,cow1,row2,cow2) ;
            }
        }
        return matrixSum ;
    }

    private int[][]  getSumMatrix(int[][] matrix) {

        int M =  matrix.length ;
        int N= matrix[0].length ;
        int[][] matrixSum = new int[M+1][N+1] ;
        for(int i=0 ; i< M ; i++){
            for(int j=0;j< N ; j++){
                matrixSum[i+1][j+1]=matrixSum[i][j+1] +matrixSum[i+1][j] -matrixSum[i][j] + matrix[i][j];
            }
        }
        return matrixSum ;
    }

    private int sumRegion(int[][] matrixSum,int row1, int col1, int row2, int col2) {
        return matrixSum[row2+1][col2+1] - matrixSum[row2+1][col1] - matrixSum[row1][col2+1] +matrixSum[row1][col1];
    }

}
