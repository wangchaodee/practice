package com.iflytek.staff.chao.array;

import java.util.*;

public class Number {


    /**
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        if (n > 1) {
            while (n % 3 == 0) n /= 3;
        }
        return n == 1;
    }


    /**
     * 计算小于n内 非负 素数
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        int[] primes = new int[n + 1];
        int count = 0;

        for (int i = 2; i < n; i++) {
            if (primes[i] == 1) continue;
            count++;
            for (int j = 2 * i; j < n; j += i) {
                primes[j] = 1;
            }
        }
        return count;
    }


    public int rob(int[] nums) {
        int max1 = 0; //没
        int max2 = 0;//偷了

        for (int i = 0; i < nums.length; i++) {
            int curr = Math.max(max2, max1 + nums[i]);
            max1 = max2;
            max2 = curr;
        }
        return max1 > max2 ? max1 : max2;

    }

    /**
     * 多次卖卖股票
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int max = 0;
        int m = prices.length;
        int buy = prices[0];
        for (int r = 0; r <m ; r++) {
            if (prices[r] > buy) {
                max += prices[r ] - buy;
            }
               buy= prices[r];

        }
        return max;
    }


    /**
     * 爬楼梯， 每次只可以走一步，或两步，   n步楼梯，有多少中走法，
     * 变化的斐波拉切数列 问题
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        return fiber2(n, 1, 1);
    }


    private Integer fiber2(int n, int a, int b) {
        if (n <= 1) {
            return 1;
        }
        Integer ret = 0;
        for (int i = 2; i <= n; i++) {
            ret = a + b;
            a = b;
            b = ret;

        }
        return ret;
    }


    public static int sqrt(int x) {
        if (x < 0) {
            return -1;
        }
        if (x == 0) {
            return 0;
        }

        return (int) sqrt(2, x);

    }

    public static double sqrt(double i, int x) {

        double res = (i + x / i) / 2;
        if (Math.abs(res - i) < 1) {
            return res;
        } else {
            return sqrt(res, x);
        }
    }

    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;

        }
        return false;
    }

    public int maxSubArray(int[] nums) {
        int pre = nums[0];
        int max = pre;
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(max, pre);
        }
        return max;
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) return new int[]{i, j};
            }
        }
        return new int[2];
    }

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[len];

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (len == depth) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, path, used, res);
            path.removeLast();
            used[i] = false;
        }
        return;
    }

    public int climbStairs2(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int n1 = 1;
        int n2 = 2;
        int t = 0;
        for (int i = 3; i <= n; i++) {
            t = n2;
            n2 = n1 + n2;
            n1 = t;
        }
        return n2;

    }

    public int rob2(int[] nums) {
        int N = nums.length;
        if (N == 1) return nums[0];

        int n1 = nums[0];
        int n2 = Math.max(n1, nums[1]);
        int t = 0;

        for (int i = 2; i < N; i++) {
            t = n2;
            n2 = Math.max(n2, n1 + nums[i]);
            ;
            n1 = t;
        }
        return n2;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int N = m + n - 1;
        int n1 = m - 1, n2 = n - 1;
        while (n1 >= 0 && n2 >= 0) {
            if (less(nums1[n1], nums2[n2])) {
                nums1[N--] = nums2[n2--];
            } else {
                nums1[N--] = nums1[n1--];
            }
        }
        while (n2 >= 0) {
            nums1[N--] = nums2[n2--];
        }

        return;

    }

    protected boolean less(int l, int r) {
        return l < r;
    }

    protected void exchange(int[] array, int i, int j) {
        if (i == j) return;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n1 = nums1.length;
        int n2 = nums2.length;
        int i = 0, j = 0;
        int[] ans = new int[Math.min(n1, n2)];
        int k = 0;
        while (i < n1 && j < n2) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                ans[k++] = nums1[i];
                i++;
                j++;
            }
        }

        return Arrays.copyOfRange(ans, 0, k);
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int i = 0; i < n1; i++) {
            int count = numCount.getOrDefault(nums1[i], 0);
            numCount.put(nums1[i], ++count);
        }
        int[] ans = new int[Math.min(n1, n2)];
        int k = 0;
        for (int i = 0; i < n2; i++) {
            int count = numCount.getOrDefault(nums2[i], 0);
            if (count > 0) {
                numCount.put(nums2[i], --count);
                ans[k++] = nums2[i];
            }
        }
        return Arrays.copyOfRange(ans, 0, k);
    }

    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    public int arrangeCoins(int n) {
        int k = 1;
        while (n >= 0) {
            n = n - k;
            k++;
        }
        return k - 1;

    }

    public int findKthPositive(int[] arr, int k) {
        int l = 0, r = arr.length - 1;
        int m = 0;
        while (l <= r) {
            m = (r - l) / 2 + l;
            if (arr[m] < m + k + 1) {
                l = m + 1;
            } else if (arr[m] >= m + k + 1) {
                r = m - 1;
            }
        }
        return l + k;
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

    private int binarySearch(int arr[], int target) {
        int l = 0, m = 0, r = arr.length - 1;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] < target) l = m + 1;
            else r = m - 1;
        }
        return l;
    }


    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] lines = new int[9][9];
        int[][][] box = new int[3][3][9];

        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != '.') {

                    int num = board[i][j] - '0' - 1;
                    rows[i][num]++;
                    lines[j][num]++;
                    box[i / 3][j / 3][num]++;
                    if (rows[i][num] > 1 || lines[j][num] > 1 || box[i / 3][j / 3][num] > 1) {
                        return false;
                    }

                }
            }
        }
        return true;

    }


    public int minimumTotal(List<List<Integer>> triangle) {
        int N = triangle.size();
        int[][] f = new int[N][N];

        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < N; i++) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int min = 0;
        for (int i = 0; i < N; i++) {
            min = Math.min(min, f[N - 1][i]);
        }
        return min;

    }


    public boolean judgeSquareSum(int c) {
        int a = 0;
        int b = (int) Math.sqrt(c);
        while (a < b) {
            if (a * a + b * b == c) {
                return true;
            } else if (a * a + b * b > c) {
                b--;
            } else {
                a++;
            }
        }
        return false;
    }


    public int maxDistance(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int i = 0, j = 0;
        int distance = 0;
        while (i < n1 && j < n2) {
            if (nums1[i] <= nums2[j]) {
                distance = Math.max(distance, j - i);
                j++;
            } else {
                i++;
                while (i > j) {
                    j++;
                }
            }
        }

        return distance;

    }

    public int search(int[] nums, int target) {
        int N = nums.length;
        int ans = -1;
        int l = 0, m = 0, r = N - 1;
        while (l <= r) {
            m = (r - l) + l;
            if (nums[m] == target) {
                ans = m;
            }
            if (nums[0] <= nums[m]) {
                if (nums[0] <= target && target < nums[m]) {
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

        return ans;
    }

    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }


    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32 && n != 0; i++) {
            res |= (n & 1) << (31 - i);
            n = n >>> 1;
        }
        return res;
    }

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    public int findMin(int[] nums) {

        int N = nums.length;
        int l = 0, m = 0, r = N - 1;
        while (l < r) {
            m = (r - l) + l;
            if (nums[m] < nums[r]) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return nums[l];
    }


    public int minSubArrayLen(int target, int[] nums) {
        int N = nums.length, l = -1;
        int sum = 0;
        int min = N + 1;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
            if (sum >= target) {
                min = Math.min(min, i - l + 1);

                while (sum > target) {
                    sum -= nums[++l];
                }
            }
        }
        if (min == N + 1) {
            return 0;
        }
        return min;
    }

    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int left = j + 1, right = n - 1, mid = 0, k = 0;
                while (left <= right) {
                    mid = (right - left) / 2 + left;
                    if (nums[mid] < nums[i] + nums[j]) {
                        k = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                ans += k - j;
            }
        }
        return ans;


    }

    private void triangleNum(int[] nums, int len, Deque<Integer> deque, boolean[] used, List<List<Integer>> ans) {
        if (deque.size() == len) {
            //判断 a + b < c

            ans.add(new ArrayList<>(deque));

        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            deque.add(nums[i]);
            used[i] = true;
            triangleNum(nums, len, deque, used, ans);
            deque.removeLast();
            used[i] = false;
        }
    }


    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
//        if(x >= arr[n-1]) {
//            return Arrays.stream(Arrays.copyOfRange(arr ,n-k ,n)).boxed().collect(Collectors.toList()) ;
//        }
//
//        if(x <= arr[0]){
//            return Arrays.stream(Arrays.copyOfRange(arr ,0 ,k)).boxed().collect(Collectors.toList()) ;
//        }

        int left = 0, right = n - 1, mid = 0, index = 0;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (arr[mid] >= x) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }


        int low = Math.max(0, left - k - 1), high = Math.min(n - 1, left + k - 1);

        while (high - low >= k) {
            if ((x - arr[low]) <= (arr[high] - x)) {
                high--;
            } else if ((x - arr[low]) > (arr[high] - x)) {
                low++;
            } else {
                continue;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = low; i <= high; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        int i = 0;
        long total = 0;
        while (k >= 0) {
            k = k - chalk[i];
            total += chalk[i];
            i++;
            if (i == n) {
                i = 0;
                k = (int) (k % total);
            }
        }
        return i - 1 < 0 ? n - 1 : i - 1;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            if (i > 0) {
                if (nums[i] == nums[i - 1]) {
                    continue;
                }
            }

            int ci = n - 1;
            int target = 0 - nums[i];
            for (int j = i + 1; j < n - 1; j++) {
                if (j > i + 1) {
                    if (nums[j] == nums[j - 1]) {
                        continue;
                    }
                }

                while (j < ci && nums[j] + nums[ci] > target) {
                    ci--;
                }

                if (j == ci) {
                    break;
                }

                if (j < ci) {
                    ans.add(Arrays.asList(nums[i], nums[j], nums[ci]));
                }
            }
        }

        return ans;
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        int len = 1;
        dp[len] = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] > dp[len]) {
                dp[++len] = nums[i];
            } else {
                int l = 1, r = len, m = 0, p = 0;
                while (l <= r) {
                    m = (r - l) / 2 + l;
                    if (dp[m] >= nums[i]) {
                        r = m - 1;
                    } else {
                        p = m;
                        l = m + 1;
                    }
                }
                dp[p + 1] = nums[i];
            }
        }
        return len;
    }

    public int minimumSize(int[] nums, int maxOperations) {
        Arrays.sort(nums);
        int ans = 0, l = 1, r = nums[nums.length - 1], m = 0;

        while (l <= r) {
            m = (r + l) >> 1;
            int cut = 0;
            for (int num : nums) {
                if (num > m) {
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


    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int l = 0, r = n - 1, m = 0, line = 0;
        while (l <= r) {
            m = (r + l) >> 1;
            if (matrix[m][0] <= target) {
                line = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        int k = matrix[0].length;
        l = 0;
        r = k - 1;
        m = 0;
        int lie = 0;
        while (l <= r) {
            m = (r + l) >> 1;
            if (matrix[line][m] == target) {
                return true;
            } else if (matrix[line][m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }



    public int longestSubarray(int[] nums, int limit) {
        int l = 0 ;
        int r = 1 ;
        int max= 1 ;
        TreeMap<Integer,Integer> map = new TreeMap();
        while(r<nums.length){
            map.put(nums[r],map.getOrDefault(nums[r],0)+1);
            while( map.lastKey()-map.firstKey()>limit  ){
                map.put(nums[l],map.get(nums[l])-1);
                if(map.get(nums[l])==0){
                    map.remove(nums[l]);
                }
                l++;
            }
            max = Math.max(max,r-l+1);
            r++ ;
        }
        return max ;
    }



    public double averageWaitingTime(int[][] customers) {
        int n = customers.length;
        if(n<1){
            return 0;
        }
        int time =0 ;
        int wait = 0;
        for(int i=0 ;i<n; i++){
            if(time < customers[i][0]){
                time = customers[i][0];
            }
            time += customers[i][1];
            wait += time - customers[i][0] ;
        }
        return  wait/n ;
    }


    public int repeatedNTimes(int[] A) {
        Map<Integer,Integer> map = new HashMap();
        int n = A.length /2 +1 ;

        for(int i = 0 ;i< n ;i++){
            if(map.getOrDefault(A[i],0)==1){
                return A[i] ;
            }
            map.put(A[i],1);
        }
        return A[n];
    }


    public int countOdds(int low, int high) {
        int interval = high - low  +1 ;
         if( interval%2==0) {
             return interval/2 ;
         }else {
             return interval/2 +low%2 ;
         }
    }

    public double average(int[] salary) {
        int min =Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int n = salary.length ;
        long total = 0 ;
        for (int i = 0; i < n; i++) {
            total += salary[i];
            min = Math.min(min,salary[i]);
            max= Math.max(max,salary[i]);
        }
        return ( total - min -max )/(n-2) ;
    }

    public int reverse(int x) {
        long rev=0 ;
        int t  ;
        int i=0;
        while (x!=0){
            t = x % 10 ;
            rev = 10*rev + t ;
            x = x /10 ;
        }
        if(rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE){
            return 0;
        }
        return  (int)  rev ;
    }



}
