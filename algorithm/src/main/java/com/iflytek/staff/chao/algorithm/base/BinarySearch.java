package com.iflytek.staff.chao.algorithm.base;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 二分法的一些应用
 * @date Date : 2022年07月12日 21:39
 */
public class BinarySearch {


    int MOD = 1000000007;
    int[] pos = new int[2];

    /**
     * 二分查找   查左侧起始位置 ，需要在调用端判断一下返回位置上的值和 target 是否相等。
     *
     * @param arr
     * @param target
     * @return
     */
    public static int binarySearchLeft(int arr[], int target) {
        int l = 0, m = 0, r = arr.length; // 起始值
        while (l < r) {
            m = l + (r - l) / 2;
            if (arr[m] < target) l = m + 1;
            else r = m;
        }
        return l;
    }

    /**
     * 二分查找
     *
     * @param arr
     * @param target
     * @return
     */
    private int binarySearch(int arr[], int target) {
        int l = 0, m = 0, r = arr.length - 1;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] < target) l = m + 1;
            else r = m;
        }
        return l;
    }

    /**
     * 二分查找  基本模式
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch_1(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /******************************************************************************/

    /**
     * @param arr
     * @param target
     * @param l
     * @param r
     * @return
     */
    private int binarySearchLeft(int arr[], int target, int l, int r) {
        int m = 0;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] < target) l = m + 1;
            else r = m - 1;
        }
        return l;
    }

    private int binarySearchRight(int arr[], int target, int l, int r) {
        int m = 0;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] <= target) l = m + 1;
            else r = m - 1;
        }
        return r;
    }

    /**
     * 69. x 的平方根
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x <= 1) return x;
        int l = 0, m = 0, r = x;
        while (l <= r) {
            m = l + (r - l) / 2;
            int sqrt = x / m;
            if (sqrt == m) {
                return m;
            } else if (sqrt < m) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return r;
    }

    /**
     * 744. Find the Smallest Letter Greater Than Target (Easy)
     *
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, r = letters.length - 1;
        int m = 0;
        while (l < r) {
            m = (r - l) / 2 + l;
            if (letters[m] <= target) {
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

    /**
     * 在最多操作maxOperations次数内， 可以将数组 切割后的最小值
     *
     * @param nums
     * @param maxOperations
     * @return
     */
    public int minimumSize(int[] nums, int maxOperations) {
        Arrays.sort(nums);
        int ans = 0, l = 1, r = nums[nums.length - 1], m = 0;

        while (l <= r) {
            m = (r + l) >> 1;
            int cut = 0;
            for (int num : nums) {
                if (num > m) {
                    // 超过的 需要切割，  余数为零的 ，切个 次数少一个，  可以 将num -1 然后进行切分，效果一样
                    cut += num / m;
                    if (num % m == 0) {
                        cut -= 1;
                    }
                    if (cut > maxOperations) {
                        break;
                    }
                }
            }

            if (cut > maxOperations) {
                l = m + 1;
            } else {
                ans = m;
                r = m - 1;
            }
        }

        return ans;
    }

    /**
     * 最小吃的速度 能在h小时内，吃完piles
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int ans = 0, l = 1, r = 1000000000, m = 0;

        while (l <= r) {
            m = (r + l) >> 1;
            int cut = 0;
            for (int num : piles) {
                // 切分的次数要加上余数的次数 才是总耗费次数
                cut += num / m;
                if (num % m != 0) {
                    cut += 1;
                }
                if (cut > h) {
                    break;
                }
            }

            if (cut > h) {
                l = m + 1;
            } else {
                ans = m;
                r = m - 1;
            }
        }

        return ans;
    }

    /**
     * m 个小球  放在n 数组中 ，数组的下表位置 为上升序列，  求最大的间隔
     *
     * @param position
     * @param m
     * @return
     */
    public int maxDistance(int[] position, int m) {
        int N = position.length;
        if (N < m) return -1;
        Arrays.sort(position);
        int l = 1, r = position[N - 1] - position[0], ans = -1, mid = 0;
        while (l <= r) {
            mid = (r + l) >> 1;
            if (checkDis(mid, position, m)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return mid;

    }

    private boolean checkDis(int dis, int[] position, int m) {
        int count = 1;
        int pre = position[0];
        for (int i = 1; i < position.length; i++) {
            if (position[i] - pre >= dis) {
                count++;
                pre = position[i];
            }
        }
        return count >= m;
    }

    /**
     * 寻找峰值  同时大于左右值为峰值，相邻元素不相等  找到一个即可
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int N = nums.length;
        if (N == 1) return 0;
        if (N == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        }

        int ans = -1;
        int l = 0, m = 0, r = N - 1;
        while (l < r) {
            m = (r - l) / 2 + l;
            if (nums[m] > nums[m + 1]) {
                if (m > 0 && nums[m] > nums[m - 1]) {
                    ans = m;
                    break;
                } else {
                    r = m - 1;
                }
            } else {
                //nums[m] < nums[m+1]
                l = m + 1;
            }
        }

        return ans == -1 ? l : ans;
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

    public int specialArray(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);
        int l = 0, r = N - 1;
        int x = 0;
        int ans = -1;
        while (l <= r) {
            x = (r - l) / 2 + l;
            int idx = binarySearch(nums, x);
            if (x == N - idx) {
                ans = x;
                break;
            } else if (x > N - idx) {
                r = x - 1;
            } else {
                l = x + 1;
            }
        }
        return ans;
    }

    public int minSpeedOnTime(int[] dist, double hour) {
        int N = dist.length;
        if (N >= hour + 1) return -1;
        int ans = 0, l = 1, r = 10000000, m = 0;

        while (l <= r) {
            m = (r + l) >> 1;
            double times = 0;
            for (int i = 0; i < N - 1; i++) {
                times += ((dist[i] - 1) / m) + 1;
            }

            times = times + 1.0 * dist[N - 1] / m;

            if (times > hour) {
                l = m + 1;
            } else {
                ans = m;
                r = m - 1;
            }
        }

        return ans;
    }

    /**
     * 制作m束k朵花 ， 最少需要等待多少天，  不可行时返回-1
     *
     * @param bloomDay
     * @param m
     * @param k
     * @return
     */
    public int minDays(int[] bloomDay, int m, int k) {
        int L = bloomDay.length;
        int t = m * k;
        if (t > L) return -1;
        int low = bloomDay[0];
        int high = bloomDay[0];
        for (int i = 0; i < L; i++) {
            low = Math.min(low, bloomDay[i]);
            high = Math.max(high, bloomDay[i]);
        }
        int days = 0;
        while (low <= high) {
            days = high + low >> 1;
            if (checkFlowers(bloomDay, days, m, k)) {
                high = days - 1;
            } else {
                low = days + 1;
            }
        }
        return low;

    }

    private boolean checkFlowers(int[] bloomDay, int days, int m, int k) {
        int flowers = 0;
        int conbines = 0;
        for (int i = 0; i < bloomDay.length; i++) {
            if (bloomDay[i] <= days) {
                flowers++;
                if (flowers == k) {
                    conbines++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }
        return conbines >= m;
    }

    /**
     * k次操作后， 数组中 最大频率元素的最大可能频数  就是最多几个
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, ans = 1;
        long total = 0;
        for (int r = 1; r < nums.length; r++) {
            // 右移一位增加的差值
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            while (total > k) {
                //左移一位  去掉l 对应的差值
                total -= nums[r] - nums[l];
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;

    }

    /**
     * 540 有序数组 在都重复两次的数据中找只出现一次的数据
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int N = nums.length;
        int l = 0, r = N - 1, mid = 0;
        while (l < r) {
            mid = l + r >> 1;
            if (mid % 2 == 1) {
                mid--;  // 保证 l m h , 在偶数区间
            }
            if (nums[mid] == nums[mid + 1]) {
                l = mid + 2;
            } else {
                r = mid;
            }
        }
        return nums[l];
    }

    /**
     * 分割数组成三份 ， 右边的元素和不能左边小
     *
     * @param nums
     * @return
     */
    public int waysToSplit(int[] nums) {
        int N = nums.length;
        if (N < 3) return 0;
        //前n项数的和
        int[] total = new int[N + 1];
        for (int i = 0; i < N; i++) {
            total[i + 1] = total[i] + nums[i];
        }
        int leftMax = total[N] / 3;
        long totalCount = 0;
        for (int i = 1; i <= N && total[i] <= leftMax; i++) {
            int midleft = binarySearchLeft(total, 2 * total[i], i + 1, N - 1);
            int midRight = binarySearchRight(total, total[i] + (total[N] - total[i]) / 2, i + 1, N - 1);

            if (midRight >= midleft) {
                totalCount += midRight - midleft + 1;
            }
        }
        return (int) (totalCount % 1000000007);
    }

    public int[] searchRange(int[] nums, int target) {
        int l = binarySearchLeft(nums, target);
        int r = binarySearchLeft(nums, target + 1) - 1;
        if (l == nums.length || nums[l] != target) return new int[]{-1, -1};
        return new int[]{l, Math.max(l, r)};
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int N = worker.length;
        int[] arrange = new int[N];
        int[][] diffProfit = new int[difficulty.length][2];
        for (int i = 0; i < difficulty.length; i++) {
            diffProfit[i][0] = difficulty[i];
            diffProfit[i][1] = profit[i];
        }
        Arrays.sort(diffProfit, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        Arrays.sort(difficulty);
        //难度高的 利润并不一定最大， 迭代更新当前难度下的最大利润 , 同一难度的也与利润不一样的，
        int curProfitMax = 0;
        for (int i = 0; i < difficulty.length; i++) {
            curProfitMax = Math.max(curProfitMax, diffProfit[i][1]);
            diffProfit[i][1] = curProfitMax;
        }

        for (int i = 0; i < N; i++) {
//            int j = binarySearchRight(difficulty, worker[i]);  原先写法 ， 待验证left方式
            int j = binarySearchLeft(difficulty, worker[i] + 1);
            if (j < 0) continue;
            arrange[i] = diffProfit[j][1];
        }
        int total = 0;
        for (int i = 0; i < N; i++) {
            total += arrange[i];
        }
        return total;
    }

    /**
     * 153. 寻找旋转排序数组中的最小值  ， 旋转后 至多产生两个递增的分段
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int N = nums.length;
        int l = 0, m = 0, r = N - 1;
        while (l < r) {
            m = (r - l) / 2 + l;
            //只有和右侧值比较 才能确认m 所在分段
            if (nums[m] < nums[r]) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return nums[l];
    }

    /**
     * 154. 寻找旋转排序数组中的最小值 II
     *
     * @param nums
     * @return
     */
    public int findMinII(int[] nums) {
        int N = nums.length;
        int l = 0, r = N - 1, mid = 0, ans = -1;
        while (l < r) {
            mid = (r - l) / 2 + l;

            if (nums[mid] < nums[r]) {
                r = mid;
            } else if (nums[mid] == nums[r]) {
                r--;
            } else {
                l = mid + 1;
            }
        }
        return nums[l];
    }

    public int[] findRightInterval(int[][] intervals) {
        int N = intervals.length;
        int[][] startIndex = new int[N][2];
        for (int i = 0; i < N; i++) {
            startIndex[i][0] = intervals[i][0];
            startIndex[i][1] = i;
        }
        Arrays.sort(startIndex, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            int l = 0, r = N - 1, mid = 0, target = -1;
            while (l <= r) {
                mid = (r - l) / 2 + l;
                if (startIndex[mid][0] >= intervals[i][1]) {
                    target = startIndex[mid][1];
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            ans[i] = target;
        }
        return ans;
    }

    public int findLengthOfShortestSubarray(int[] arr) {
        int N = arr.length;
        int l = 0, r = N - 1;
        while (l < N - 1 && arr[l] <= arr[l + 1]) {
            l++;
        }

        if (l == N - 1) return 0;

        while (r > 0 && arr[r - 1] <= arr[r]) {
            r--;
        }
        // 搜寻 r 到 n-1 范围的值
        int ans = r;
        for (int i = 0; i < l; i++) {
            int t = arr[i];
            int k = r, j = N - 1, mid = k;
            while (k < j) {
                mid = (j - k) / 2 + k;
                if (arr[mid] < t) {
                    k = mid + 1;
                } else {
                    j = mid;
                }
            }
            ans = Math.min(ans, j - i - 1);
        }

        return ans;
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] preSum = new int[m + 1][n + 1];

        //构造前缀
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }


        // 遍历正方形 ，
        int ans = 0;
        int max = Math.min(m, n);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = ans + 1; k <= max; k++) {
                    if (i + k - 1 <= m && j + k - 1 <= n
                            && getRect(preSum, i, j, i + k - 1, j + k - 1) <= threshold) {
                        ans = k;
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }

    private int getRect(int[][] preSum, int i, int j, int x, int y) {
        return preSum[x][y] - preSum[i - 1][y] - preSum[x][j - 1] + preSum[i - 1][j - 1];
    }

    public int numSubseq(int[] nums, int target) {
        int N = nums.length;

        Arrays.sort(nums);
        long ans = 0;
        for (int i = 0; i < N; i++) {
            int f = target - nums[i];
            if (f < nums[i]) break;
            int l = i, r = N - 1, mid = 0;
            while (l < r) {
                mid = (r - l + 1) / 2 + l;

                if (nums[mid] <= f) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            // if(nums[r]>f || r<i) continue;
            ans += fastPow(2, r - i);
            ans %= MOD;
        }
        return (int) ans;
    }

    long fastPow(int _x, int n) {
        long res = 1, x = _x;
        while (n > 0) {
            if ((n & 1) == 1) res = (res * x) % MOD;
            x = (x * x) % MOD;
            n >>= 1;
        }
        return res % MOD;
    }

    public int findBestValue(int[] arr, int target) {
        int N = arr.length;
        Arrays.sort(arr);
        int l = target / N, r = arr[N - 1], mid = 0, ans = 0, min = target;
        while (l <= r) {
            mid = (r - l) / 2 + l;
            int total = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i] >= mid) {
                    total += mid * (N - i);
                    break;
                }
                total += arr[i];
            }

            if (total == target) {
                return mid;
            }

            if (total > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }

            if (Math.abs(total - target) < min) {
                ans = mid;
            }
        }
        return ans;
    }

    public int maxValue(int n, int index, int maxSum) {
        int l = index, r = index;
        int rest = maxSum - n;
        int ans = 1;
        while (l > 0 || r < n - 1) {
            int len = r - l + 1;
            if (rest >= len) {
                rest -= len;
                ans++;
                l = Math.max(0, l - 1);
                r = Math.min(n - 1, r + 1);
            } else {
                break;
            }
        }
        ans += rest / n;
        return ans;
    }

    public int[] findPeakGrid(int[][] mat) {
        dfs(mat, 0, 0);
        return pos;
    }

    private boolean dfs(int[][] mat, int i, int j) {
        if (i < 0 || i > mat.length - 1 || j < 0 || j > mat[0].length - 1) {
            return false;
        }

        return checkPeek(mat, i, j) || dfs(mat, i, j + 1) || dfs(mat, i + 1, j);
    }

    private boolean checkPeek(int[][] mat, int i, int j) {
        if (i - 1 >= 0 && mat[i][j] < mat[i - 1][j]) return false;
        if (j - 1 >= 0 && mat[i][j] < mat[i][j - 1]) return false;
        if (i + 1 < mat[0].length && mat[i][j] < mat[i + 1][j]) return false;
        if (j + 1 < mat.length && mat[i][j] < mat[i][j + 1]) return false;
        pos[0] = i;
        pos[1] = j;
        return true;
    }


    public int[] avoidFlood(int[] rains) {
        int N = rains.length;

        int[] res = new int[N];
        Arrays.fill(res, -1);
        //湖泊满水日期
        Map<Integer, Integer> map = new HashMap<>();
        //存不下雨日期
        TreeSet<Integer> treeSet = new TreeSet();

        for (int i = 0; i < N; i++) {
            if (rains[i] == 0) {
                treeSet.add(i);
                res[i] = 1;
                continue;
            }
            if (!map.containsKey(rains[i])) {
                map.put(rains[i], i);
                continue;
            }
            //上次水满的日期  查之后的空日期
            Integer t = treeSet.higher(map.get(rains[i]));
            if (t == null) return new int[0];
            treeSet.remove(t);
            res[t] = rains[i];
            map.put(rains[i], i);
        }
        return res;
    }

    public boolean isUgly(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        while (n % 5 == 0) n /= 5;
        return n == 1;
    }

    public int findLatestStep(int[] arr, int m) {
        int N = arr.length;
        if (N == m) return m;

        TreeSet<Integer> treeSet = new TreeSet();
        treeSet.add(0);
        treeSet.add(N - 1);

        for (int i = N - 1; i >= 0; i--) {
            int div = arr[i];
            int higher = treeSet.higher(div);
            int lower = treeSet.lower(div);
            if (div - lower - 1 == m || higher - div - 1 == m) {
                return i;
            } else {
                treeSet.add(i);
            }
        }
        return -1;
    }


    //    private int MOD = 1e9+7;
    public int maxProfit(int[] inventory, int orders) {
        int N = inventory.length;
//        Arrays.sort(inventory);
        int l = 0, r = (int) (1e9);
        long ans = 0;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (check(inventory, mid, orders)) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        for (int i = 0; i < N; i++) {
            if (inventory[i] > l) {
                ans += (long) (inventory[i] + l + 1) * (inventory[i] - l) / 2;
                orders -= (inventory[i] - l);
            }
        }
        ans += (long) l * orders;
        return (int) (ans % MOD);
    }

    private boolean check(int[] inventory, int up, int orders) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] > up) {
                orders -= (inventory[i] - up);
                if (orders <= 0) return true;
            }
        }
        return false;
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
        int m = nums1.length;
        int n = nums2.length;
        // 建立分割线   左边的值 要小于右边的值   左边元素 ( m+n+1 ) /2
        int partCount = (m + n + 1) / 2;
        int l = 0, r = m, i = 0, j = 0;
        // nums1[i-1] < nums2[j]    , nums2[j-1] < nums[i]
        while (l < r) {
            i = (l + r) / 2;
            j = partCount - i;
            if (nums1[i - 1] > nums2[j]) {
                r = i - 1;
            } else {
                l = i;
            }
        }
        i = l;
        int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
        int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];

        int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
        int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

        if ((m + n) % 2 == 1) {
            return Math.max(nums1LeftMax, nums2LeftMax);
        } else {
            return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2;
        }

    }


    /**
     * 计算x的n次方 指数值
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        double ans = 1.0;
        int i = n;
        while (i != 0) {
            if (i % 2 != 0) {
                ans *= x;
            }
            x *= x;
            i /= 2;
        }
        return n > 0 ? ans : 1 / ans;
    }

    /**
     * 模拟 错误版本  ， n > 10
     *
     * @param version
     * @return
     */
    public int firstBadVersion(int n) {
        int l = 1;
        int h = n;
        while (l < h) {
            int mid = l + (h - l) / 2;
            if (isBadVersion(mid)) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
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


    private int binarySearchGrid(int arr[], int target) {
        int l = 0, m = 0, r = arr.length - 1;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] < target) l = m + 1;
            else r = m - 1;
        }
        return l;
    }

    /**
     * 374. 猜数字大小
     *
     * @param n
     * @return
     */
    public int guessNumber(int n) {
        int l = 1, h = n;
        while (l < h) {
            int m = (h - l) / 2 + l;
            if (guess(m) <= 0) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private int guess(int m) {
        return 0; //0 ,-1 ,1
    }

    /**
     * 33. 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int N = nums.length;
        int l = 0, m = 0, r = N - 1;
        while (l <= r) {
            m = (r - l) / 2 + l;
            if (nums[m] == target) return m;
            //  顺序区间还是乱序区间
            if (nums[l] <= nums[m]) {
                if (nums[l] <= target && target < nums[m]) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            } else {
                if (nums[m] < target && target <= nums[r]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }

        return -1;
    }

    /**
     * 剑指 Offer II 068. 查找插入位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = (r - l) / 2 + l;
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    /**
     * 剑指 Offer II 069. 山峰数组的顶部
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int l = 1, r = arr.length - 2, ans = 0;
        int m = 0;
        while (l <= r) {
            m = l + (r - l) / 2;

            if (arr[m] < arr[m + 1]) {
                l = m + 1;
            } else {
                ans = m;
                r = m - 1;
            }
        }
        return ans;
    }

    public int peakIndexInMountainArray2(int[] arr) {
        int n = arr.length, ans = -1;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                ans = i;
                break;
            }
        }
        return ans;
    }


}
