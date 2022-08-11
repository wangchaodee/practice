package com.iflytek.staff.chao.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Search {


    /**
     * 模拟 错误版本  ， n > 10
     *
     * @param version
     * @return
     */
    public int firstBadVersion(int n) {
        int l = 1;
        int h = n;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (isBadVersion(mid)) {
                if (!isBadVersion(mid - 1)) {
                    return mid;
                } else {
                    h = mid - 1;
                }
            } else {
                l = mid + 1;
            }
        }
        return -1;

    }

    /**
     * 模拟 错误版本  字方法
     *
     * @param version
     * @return
     */
    boolean isBadVersion(int version) {
        if (version >= 10) {
            return true;
        }
        return false;
    }


    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;
        int m = 0;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] < arr[m - 1]) r = m - 1;

            if (arr[m] < arr[m + 1]) l = m + 1;

            if (arr[m] > arr[m - 1] && arr[m] > arr[m + 1]) break;
        }
        return m;
    }

    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {


        int cnt = 0;

        for (int i = 0; i < arr1.length; i++) {
            boolean ok = true;
            for (int j = 0; j < arr2.length; j++) {
                if (Math.abs(arr1[i] - arr2[j]) - d <= 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                cnt++;
            }
        }
        return cnt;

    }


    public int findTheDistanceValue2(int[] arr1, int[] arr2, int d) {

        Arrays.sort(arr2);
        int cnt = 0;

        for (int i = 0; i < arr1.length; i++) {
            boolean ok = true;
            int p = binarySearch(arr2, arr1[i]);
            if (p < arr2.length) {
                if (Math.abs(arr1[i] - arr2[p]) - d <= 0) {
                    ok = false;
                }
            }


            if (p - 1 >= 0) {
                if (Math.abs(arr1[i] - arr2[p - 1]) - d <= 0) {
                    ok = false;
                }
            }

            if (ok) {
                cnt++;
            }
        }
        return cnt;

    }




    private int binarySearch(int arr[], int target) {
        int l = 0, m = 0, r = arr.length - 1;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] < target) l = m + 1;
            else r = m;
        }
        return l;
    }

    public int mySqrt(int x) {
        int l = 0, m = 0, r = x;
        while (l < r) {
            m = l + (r - l) / 2;
            long sqrt = (long) m * m;
            if (sqrt > x) r = m - 1;
            else l = m;
        }
        return l;
    }


    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, r = letters.length - 1;
        int m = 0;
        while (l < r) {

            m = (r - l) / 2 + l;
            if (letters[m] - target <= 0) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        if (l < letters.length) {
            return letters[l];
        } else {
            return letters[0];
        }
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int l = 0;
        int r = n * m - 1;
        int mi = 0;
        int ri = 0;
        int li = 0;
        while (l <= r) {
            mi = (r - l) / 2 - l;
            ri = mi / m;
            li = mi % m;
            if (matrix[ri][li] == target) {
                return true;
            } else if (matrix[ri][li] > target) {
                r = mi - 1;
            } else {
                l = mi + 1;
            }
        }
        return false;
    }

    private int binarySearchGrid(int arr[], int target) {
        int l = 0, m = 0, r = arr.length - 1;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] < target) l = m + 1;
            else r = m - 1;
        }
        return l;
    }

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] idx = queue.poll();
            int r = idx[0];
            int l = idx[1];
            for (int i = 0; i < n; i++) {
                matrix[r][i] = 0;
            }
            for (int i = 0; i < m; i++) {
                matrix[i][l] = 0;
            }
        }
    }
}
