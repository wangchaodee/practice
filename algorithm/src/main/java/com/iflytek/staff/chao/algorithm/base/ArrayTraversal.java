package com.iflytek.staff.chao.algorithm.base;

import java.util.*;
import java.util.stream.Collectors;

import static com.iflytek.staff.chao.algorithm.base.SortUtil.less;

/**
 * @author : hamilton
 * @Description: 数组遍历
 * @date Date : 2022年08月14日 21:48
 */
public class ArrayTraversal {

    public void sortColors(int[] nums) {
        int N = nums.length;
        int iNum0 = 0, iNum1 = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] == 1) {
                SortUtil.exchange(nums, i, iNum1);
                iNum1++;
            } else if (nums[i] == 0) {
                SortUtil.exchange(nums, i, iNum0);
                if (iNum0 < iNum1) {
                    SortUtil.exchange(nums, i, iNum1);
                }
                iNum0++;
                iNum1++;
            }
        }

    }

    public void sortColors2(int[] nums) {
        int N = nums.length;
        int p = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] == 0) {
                SortUtil.exchange(nums, i, p);
                p++;
            }
        }

        for (int i = p; i < N; i++) {
            if (nums[i] == 1) {
                SortUtil.exchange(nums, i, p);
                p++;
            }
        }
    }

    /**
     * 判断是否存在重复数值
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;

        }
        return false;
    }

    public boolean containsDuplicate2(int[] nums) {
//        IntStream.of(nums).distinct().count()
        return Arrays.stream(nums).distinct().count() != nums.length;

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

    /**
     *  查找是否存在两数之和等于target
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) return new int[]{i, j};
            }
        }
        return new int[2];
    }

    public int[] twoSum2(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum < target) l++;
            else if (sum > target) r--;
            else return new int[]{l + 1, r + 1};
        }
        return null;
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


    /**
     * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
     *
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2) {
            return intersect(nums2, nums1);
        }
        Set<Integer> numCount = new HashSet<>();
        for (int i = 0; i < n1; i++) {
            numCount.add(nums1[i]);
        }

        int[] ans = new int[n1]; //Math.min(n1, n2)  第三行已经判断了
        int k = 0;
        for (int i = 0; i < n2; i++) {
            if (numCount.contains(nums2[i])) {
                numCount.remove(nums2[i]);
                ans[k++] = nums2[i];
            }
        }
        return Arrays.copyOfRange(ans, 0, k);
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
    public int[] intersect3(int[] nums1, int[] nums2) {
        Set<Integer> sames = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {

            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    sames.add(nums1[i]);
                }
            }
        }
        int[] ret = new int[sames.size()];
        Iterator<Integer> iterator = sames.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            ret[i++] = iterator.next();
        }

        return ret;
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

    /**
     * 查找数组中只存在一个的数字， 用的异或方式  ，其他成组存在的数会消除掉
     * @param nums
     * @return
     */
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

    public double average(int[] salary) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int n = salary.length;
        long total = 0;
        for (int i = 0; i < n; i++) {
            total += salary[i];
            min = Math.min(min, salary[i]);
            max = Math.max(max, salary[i]);
        }
        return (total - min - max) / (n - 2);
    }

    /**
     * 判断是否单调数列
     * @param nums
     * @return
     */
    public boolean isMonotonic(int[] nums) {
        boolean inc = true , decr = true ;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i-1]){
                inc = false;
            }
            if (nums[i] < nums[i-1]) {
                decr =false;
            }
        }

        return inc|| decr;
    }

    /**
     * 求每一处元素 排除自身 其他元素的乘积
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int N = nums.length ;
        int[] result = new int[N] ;
        int tmp =1 ;
        for (int i = 0; i < N; i++) {
            result[i] *=tmp;
            tmp *=nums[i];
        }
        tmp=1;
        for (int i = N-1; i >=0  ; i--) {
            result[i] *= tmp ;
            tmp *= nums[i];
        }
        return  result ;
    }

    /**
     * 和上面的方法相似  用的空间更多
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        int[] proleft = new int[N];
        int pro = 1;
        proleft[0] = pro;
        for (int i = 1; i < N; i++) {
            pro *= nums[i - 1];
            proleft[i] = pro;
        }

        int[] proRight = new int[N];
        pro = 1;
        proRight[N - 1] = pro;
        for (int i = N - 2; i >= 0; i--) {
            pro *= nums[i + 1];
            proRight[i] = pro;
        }

        for (int i = 0; i < N; i++) {
            ans[i] = proleft[i] * proRight[i];
        }
        return ans;
    }

    public int repeatedNTimes(int[] A) {
        Map<Integer, Integer> map = new HashMap();
        int n = A.length / 2 + 1;

        for (int i = 0; i < n; i++) {
            if (map.getOrDefault(A[i], 0) == 1) {
                return A[i];
            }
            map.put(A[i], 1);
        }
        return A[n];
    }

    public int longestSubarray(int[] nums, int limit) {
        int l = 0;
        int r = 1;
        int max = 1;
        TreeMap<Integer, Integer> map = new TreeMap();
        while (r < nums.length) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[l], map.get(nums[l]) - 1);
                if (map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }
                l++;
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
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




    /**
     * 统计连续1 的最大数量
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length ;
        int cnt = 0 ;
        int max = 0 ;
        for (int i = 0; i < n; i++) {
            if(nums[i]==1) {
                cnt++;
            }else {
                if(cnt>max){
                    max=cnt;
                }
                cnt=0 ;
            }
        }
        return max>cnt ? max :cnt;
    }

    /**
     * 提莫攻击  返回总的中毒秒数
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int count =0 ;
        int next = 0;
        for (int i = 0; i <timeSeries.length ; i++) {
            if(timeSeries[i] > next){
                count +=duration ;
            }else {
                count += (timeSeries[i] + duration -1 ) - next ;
            }
            next = timeSeries[i] + duration -1 ;
        }
        return count ;
    }

    /**
     * 返回第三大的数
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        int N = nums.length;

        Set<Integer> sets = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue(3);

        for (int i = 0; i < N; i++) {
            if(sets.contains(nums[i])){
                continue;
            }
            sets.add(nums[i]);
            if (pq.size() < 3) {
                pq.offer(nums[i]);
            } else if( pq.peek() < nums[i]) {
                pq.poll();
                pq.add(nums[i]);
            }
        }

        if (pq.size() == 3) {
            return pq.peek();
        } else {
            int max = 0;
            while (!pq.isEmpty()) {
                max = pq.poll();
            }
            return max;
        }
    }

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length ;
       return Math.max(nums[n-3]* nums[n-2]* nums[n-1], nums[0]* nums[1]* nums[n-1]) ;
    }



    public int[] sortedSquares(int[] nums) {

        int l = 0;
        int r = nums.length - 1;
        int[] result = new int[nums.length];
        for (int k = nums.length - 1; k >= 0; k--) {
            int ll = nums[l] * nums[l];
            int rr = nums[r] * nums[r];
            if (less(ll, rr)) {
                result[k] = rr;
                r--;
            } else {
                result[k] = ll;
                l--;
            }

        }
        return result;
    }

    public int[] sortedSquares2(int[] nums) {


        int N = nums.length;
        for (int i = 0; i < N; i++) {
            nums[i] = nums[i] * nums[i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(nums[j], nums[j - 1])) {
                    exchange(nums, j, j - 1);
                }
            }
        }

        return nums;
    }

    /**
     * 将数组中的零元素移动到尾端
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int N = nums.length;
        int l = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] != 0) {
                exchange(nums, l, i);
                ++l;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        int l = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[l++] = nums[i];
            }
        }
        while (l < nums.length) {
            nums[l++] = 0;
        }
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



    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int missingNumber(int[] nums) {
        int v = 0;
        for (int i = 0; i < nums.length; i++) {
            v += nums[i] - i;
        }
        return v;
    }



    public int[] getLeastNumbers2(int[] arr, int k) {

        int[] least = new int[k];
        if (k <= 0) return least;
        int N = arr.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }

        for (int i = k; i < N; i++) {
            if (less(arr[i], pq.peek())) {
                pq.poll();
                pq.offer(arr[i]);
            }
        }


        for (int i = 0; i < k; i++) {
            least[i] = pq.poll();
        }
        return least;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        int N = arr.length;
        int[] least = new int[k];
        for (int i = 0; i < k; i++) {
            int min = i;
            for (int j = i; j < N; j++) {
                if (less(arr[j], arr[min])) min = j;
            }
            exchange(arr, i, min);
            least[i] = arr[i];
        }
        return least;
    }


    public int[] twoSumNoOrder(int[] numbers, int target) {
        int N = numbers.length;
        Map<Integer, Integer> numOrder = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (numOrder.containsKey(numbers[i])) {
                int j = numOrder.get(numbers[i]);
                return new int[]{j, i};
            }
            numOrder.put(target - numbers[i], i);
        }
        return null;
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






    /**
     * int[] result =new ArraySolution().plusOne(new int[]{8, 9, 9, 9});
     * for (int e : result) {
     * System.out.print(e);
     * }
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {

        int sum = digits[digits.length - 1] + 1;
        digits[digits.length - 1] = sum % 10;
        int tmp = sum / 10;
        for (int i = digits.length - 2; i >= 0; i--) {
            int t = digits[i] + tmp;
            tmp = t / 10;
            digits[i] = t % 10;
        }
        if (tmp == 1) {
            int[] digits2 = new int[digits.length + 1];
            digits2[0] = 1;
            return digits2;
        } else {
            return digits;
        }

    }






    public int removeDuplicates(int[] nums) {
        int l = 1;
//        int r = 1;
        for (int r = 1; r < nums.length; r++) {
            if (nums[r] != nums[r - 1]) {
                nums[l++] = nums[r];
            }
        }
        return l;
    }

    public int removeElement(int[] nums, int val) {
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] != val) {
                nums[l++] = nums[r];
            }
        }
        return l;
    }


    /**
     * 获取杨辉三角 第rowIndex行的那列数组
     *
     * @param rowIndex
     * @return 1
     * 11
     * 121
     * 1331
     * dp[i][j]= dp[i-1][j-1] + dp[i-1][j]    //左右边界只取到一个上层值
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < rowIndex; i++) {
            ans.add(1);
            for (int j = i - 1; j > 0; j--) {
                ans.set(j, ans.get(j) + ans.get(j - 1));
            }
        }
        return ans;
    }

    /**
     * 组成的三角形 最大周长
     *
     * @param nums
     * @return
     */
    public int largestPerimeter(int[] nums) {
        int N = nums.length;
        if (N < 3) return 0;
        Arrays.sort(nums);
        for (int i = N - 1; i > 1; i--) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }
        return 0;
    }

    public int findDuplicate(int[] nums) {
        int N = nums.length;
        int l = 0, r = N - 1, mid = 0, ans = 0, count = 0;
        while (l <= r) {
            mid = (r + l) >> 1;
            for (int i = 0; i < N; i++) {
                if (nums[i] <= mid) count++;
            }
            if (count > mid) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
            count = 0;
        }
        return ans;
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int N = nums.length;
        int l = 1, r = 1000000, mid = 0, ans = 0, count = 0;
        while (l <= r) {
            mid = (r + l) >> 1;
            for (int i = 0; i < N; i++) {
                count += (nums[i] - 1 + mid) / mid;
            }

            if (count <= threshold) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
            count = 0;
        }
        return ans;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int N = temperatures.length;
        int[] ans = new int[N];
        Stack<Integer> stack = new Stack<>();

        for (int i = N-1; i >= 0; i--) {

            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()] ){
                stack.pop();
            }

            if(stack.isEmpty()){
                ans[i]=0;
                stack.push(i);
            }else {
                ans[i]= stack.peek()-i;
                stack.push(i);
            }

        }
        return ans;
    }

    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int k = 0;
        Stack<Integer> kstack = new Stack<>();
        Stack<StringBuilder> sbStack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '[') {
                kstack.push(k);
                sbStack.push(res);
                k = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder();
                int times = kstack.pop();
                for (int i = 0; i < times; i++) {
                    temp.append(res);
                }
                res = sbStack.pop().append(temp);

            } else if (Character.isDigit(c)) {
                k = 10 * k + c - '0';
            } else {
                res.append(c);
            }

        }
        return res.toString();

    }

    /**
     * 多少个连续字数组 和为k ,, 因为按题意数组不适合排序， 双指针方式不适用 ， 适用前缀后， 固定一端 ，然后遍历求和
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j >= 0; j--) {
                sum += nums[i];
                if (sum == k) {
                    count++;
                }

            }
        }
        return count;
    }

    /**
     * 返回所有数乘积的符号
     *
     * @param nums
     * @return
     */
    public int arraySign(int[] nums) {
        int count = 0; // 负数的数量
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) return 0;
            if (nums[i] < 0) count++;
        }
        return count % 2 == 1 ? -1 : 1;
    }

    /**
     * 判断是否能形成等差数列
     *
     * @param arr
     * @return
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int dengcha = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != dengcha) return false;
        }
        return true;
    }

    /**
     * 是否快乐数 ， 快乐数 最终会收敛到1 ， 否则 进入循环
     *
     * @param n 正数
     * @return
     */
    public boolean isHappy(int n) {
        Set<Integer> cycles = new HashSet<>();
        cycles.add(n);
        while (n != 1) {
            int next = 0;
            int t = n;
            while (t > 0) {
                int y = t % 10;
                next += y * y;
                t /= 10;
            }
            if (cycles.contains(next)) return false;
            cycles.add(next);
            n = next;
        }
        return true;
    }

    /**
     * 下一个更大的元素
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int j = 0;
            while (j < nums2.length && nums1[i] != nums2[j]) j++;

            while (j < nums2.length && nums1[i] >= nums2[j]) j++;

            ans[i] = (j == nums2.length ? -1 : nums2[j]);
        }
        return ans;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int n2 = nums2.length ;
        Map<Integer,Integer> numIndex = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i =n2-1; i >=0; i--) {
            // 如果 后续有值， 但小于等于当前值， 则废弃掉 当前值的左侧序列元素的下一个更大元素 不会超过当前值
            while (!stack.isEmpty() && nums2[i] >=stack.peek()){
                stack.pop();
            }
            if(stack.isEmpty()){
                // 右侧无更大元素
                numIndex.put(nums2[i],-1);
            }else {
                // 右侧更大元素是当前栈顶元素  ， 元素互补相同，不用考虑覆盖的情况
                numIndex.put(nums2[i],stack.peek());
            }
            // 当前元素放入栈中
            stack.push(nums2[i]);
        }
        int[] ans = new int[nums1.length];
        // 两个数组中 内部元素是不重复的，且nums1 的元素一定存在于nums2中  ，不用考虑空值
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = numIndex.get(nums1[i]);
        }
        return ans ;
    }

    /**
     * 循环数组  计算下一个更大元素
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length ;
        Map<Integer,Integer> numIndex = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        int[] ans = new int[n];
        for (int i =2*n-1; i >0; i--) {
            // 如果 后续有值， 但小于等于当前值， 则废弃掉 当前值的左侧序列元素的下一个更大元素 不会超过当前值
            while (!stack.isEmpty() && nums[i%n] >=stack.peek()){
                stack.pop();
            }
            if(stack.isEmpty()){
                // 右侧无更大元素
                ans[i%n]=-1;
            }else {
                // 右侧更大元素是当前栈顶元素  ， 元素互补相同，不用考虑覆盖的情况
                if(ans[i%n] ==-1 || ans[i%n] ==0 ){
                    ans[i%n]= stack.peek();
                }
            }
            // 当前元素放入栈中
            stack.push(nums[i%n]);
        }

        return ans ;
    }

    public boolean checkStraightLine(int[][] coordinates) {
        // y = ax + b ;   特殊情况 竖线  水平线
        int[] ratio = calculateRatio(coordinates[0][0], coordinates[0][1],
                coordinates[1][0], coordinates[1][1]);
        int N = coordinates.length;
        int i = 2;
        if (ratio[0] == 1) {
            while (i < N && coordinates[i][0] == coordinates[i - 1][0]) i++;
            return i == N;
        } else {
            while (i < N) {
                int expectY = (coordinates[i][0] * ratio[2] + ratio[1]) / ratio[3];
                if (expectY != coordinates[i][1]) return false;
                i++;
            }
        }
        return true;

    }

    private int[] calculateRatio(int x1, int y1, int x2, int y2) {
        int[] res = new int[4];  // 类型 ，  b 值 ，a值，
        // 不存重复的点 ，
        // 竖线
        if (x1 == x2 && y1 != y2) {
            res[0] = 1;
        }//水平线
        else if (x1 != x2 && y1 == y2) {

            res[1] = y1;
            res[3] = 1;
        } else {
            // 是否会不能整除
            res[1] = y2 * x1 - y1 * x2;

            res[2] = y1 - y2;
            res[3] = x1 - x2;
        }
        return res;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int N = nums.length;
        int pro = 1;
        int j = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {
            pro *= nums[i];
            while (j <= i && pro >= k) {
                pro /= nums[j++];
            }
            count += (i - j + 1);
        }
        return count;
    }

    public boolean increasingTriplet(int[] nums) {
        int N = nums.length;
        if (N < 3) return false;
        int first = nums[0], second = Integer.MAX_VALUE;

        for (int j = 1; j < N; j++) {
            if (nums[j] > second) return true;
            if (nums[j] > first) {
                second = nums[j];
            } else {
                first = nums[j];
            }
        }
        return false;
    }




    /**
     * 所有奇数位子数组的和
     *
     * @param arr
     * @return
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int N = arr.length;
        int all = 0;
        for (int i = 0; i < N; i++) {
            int total = 0;
            for (int j = i; j >= 0; j -= 2) {
                int sum = 0;
                for (int k = i; k >= j; k--) {
                    sum += arr[k];
                }
                total += sum;
            }
            all += total;
        }
        return all;
    }


    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int sum = 0;
        int M = 1000000007;
        int[][] chaArray = new int[N][3];

        // 计算绝对差值  形成数组
        for (int i = 0; i < N; i++) {
            int cha = nums1[i] - nums2[i];
            if (cha < 0) cha = -cha;
            chaArray[i][0] = cha;
            chaArray[i][1] = nums1[i];
            chaArray[i][2] = nums2[i];
        }

        //差值排序
        Arrays.sort(chaArray, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        Arrays.sort(nums1);
        //倒叙 从最大的差值数组中 找能减小的值
        int maxReplace = 0;
        for (int i = N - 1; i >= 0; i--) {
            int num2 = chaArray[i][2];
            int l = binarySearchLeft(nums1, num2);
            int replaceCha = 0;
            if (l == N) {
                //所有数小于num2
                replaceCha = num2 - nums1[N - 1];
            } else if (l == 0) {
                //所有数大于num2
                replaceCha = nums1[0] - num2;
            } else {
                replaceCha = Math.min(nums1[l] - num2, num2 - nums1[l - 1]);
            }

            maxReplace = Math.max(maxReplace, chaArray[i][0] - replaceCha);
            // 找到最优值
            if (replaceCha == 0) break;
        }
        for (int i = 0; i < N; i++) {
            sum = (sum + chaArray[i][0]) % M;
        }


        return (sum - maxReplace + M) % M;
    }

    private int binarySearchLeft(int arr[], int target) {
        int l = 0, m = 0, r = arr.length - 1;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (arr[m] < target) l = m + 1;
            else r = m - 1;
        }
        return l;
    }

    public int[] sortByBits(int[] arr) {
        int N = arr.length;
        int[][] arrBit = new int[N][2];
        for (int i = 0; i < N; i++) {
            arrBit[i][0] = arr[i];
//            arrBit[i][1] = hammingWeight(arr[i]);
        }
        Arrays.sort(arrBit, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = arrBit[i][0];
        }
        return ans;
    }

    public int[] sortByBits2(int[] arr) {
        int N = arr.length;

        int[] arrBit = new int[10001];
        for (int i = 0; i < N; i++) {
            arrBit[arr[i]] = hammingWeight(arr[i]);
        }
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return arrBit[o1] != arrBit[o2] ? arrBit[o1] - arrBit[o2] : o1 - o2;
            }
        });

        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }




    private int hammingWeight(int i) {

        //假方法 Number类中有
        return 0;
    }


    public int[] arrayRankTransform(int[] arr) {
        Map<Integer, Integer> arrIndex = new HashMap<>();
        int[] arrCopy = arr.clone();
        Arrays.sort(arrCopy);
        int index = 1;
        arrIndex.put(arrCopy[0], 1);
        for (int i = 1; i < arrCopy.length; i++) {
            if (arrCopy[i] == arrCopy[i - 1]) {
                continue;
            }
            arrIndex.put(arrCopy[i], ++index);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrIndex.get(arr[i]);
        }
        return arr;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        int i = 0;
        while (i < k) {
            queue.offer(nums[i]);
            i++;
        }

        for (int j = k; j < nums.length; j++) {
            if (nums[j] > queue.peek()) {
                queue.poll();
                queue.offer(nums[j]);
            }
        }
        return queue.peek();
    }

    public int[] topKFrequent(int[] nums, int k) {
        // 数字 频率
        Map<Integer, Integer> dataCount = new HashMap<>();
        for (int num : nums) {
            dataCount.put(num, dataCount.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        for (Map.Entry<Integer, Integer> data : dataCount.entrySet()) {
            if (queue.size() < k) {
                queue.offer(new int[]{data.getKey(), data.getValue()});
            } else {
                if (data.getValue() > queue.peek()[1]) {
                    queue.poll();
                    queue.offer(new int[]{data.getKey(), data.getValue()});
                }
            }
        }

        int[] ans = new int[k];
        int i = 0;
        while (!queue.isEmpty()) {
            ans[i++] = queue.poll()[0];
        }
        return ans;
    }

    public String frequencySort(String s) {
        int[] cf = new int[128];
        for (char c : s.toCharArray()) {
            cf[c]++;
        }

        // 字符 频率
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        for (int i = 0; i < 128; i++) {
            if (cf[i] > 0) {
                queue.offer(new int[]{i, cf[i]});
            }
        }

        StringBuilder ans = new StringBuilder();
        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            while (data[1] > 0) {
                ans.append((char) data[0]);
                data[1]--;
            }
        }

        return ans.toString();
    }


    /**
     * 最少操作次数， 使数组都变为 0
     *
     * @param nums
     * @return
     */
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums){
            if(num>0) set.add(num);
        }
        return  set.size();
    }

    public int maximumGroups(int[] grades) {
        int N = grades.length ;
        int l=1 ,r = N -1 ;
        while (l<r){
            int m = (r+l+1) /2 ;
            if(m <= 2*N /(m+1) ){
                l=m ;
            }else {
                r=m-1;
            }
        }
        return l <= 2*N /(l+1) ? l : l-1;
    }


    /**
     * 采购零件方案
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans(int[] nums, int target) {
        int mod = 1000000007;
        int count = 0 ;
        int N = nums.length;
        Arrays.sort(nums);
        int j= N-1 ;
        for (int i = 0; i <N &&  nums[i]*2 <= target ; i++) {
            int t = target -nums[i];
            while (j>i && nums[j] > t){
                j--;
            }
            if(j==i) break;

            count =( count + (j-i) ) % mod ;
        }
        return count;
    }

    /**
     * 适用于num值较小  ， 否则占用空间太大
     * @param num
     * @param xPos
     * @param yPos
     * @return
     */
    public int orchestraLayout2(int num, int xPos, int yPos) {

        int[][] grid = new int[num][num];
        int[][] direction = new int[][]{{0,1},{1,0},{0,-1},{-1,0}} ;

        int total = num*num ;
        int i = 0 ,  j =0 ;
        int di = 0 ;
        for (int k = 0; k < total; k++) {
            grid[i][j] =( k % 9) +1 ;

            int ti =i+ direction[di][0];
            int tj =j+ direction[di][1];
            if (ti<0 || ti>=num || tj<0 || tj>=num ||  grid[ti][tj] !=0) {
                di = (di+1) % 4 ;
            }
            i += direction[di][0];
            j += direction[di][1];
        }
        return  grid[xPos][yPos] ;
    }

    /**
     * 面积法
     * @param num
     * @param xPos
     * @param yPos
     * @return
     */
    public int orchestraLayout(int num, int xPos, int yPos) {
        int quan =( num+1)/2 ;//总圈数
        //坐标所在圈数
        int ceng =  Math.min( Math.min(xPos,yPos) ,Math.min(num-1 -xPos,num-1 -yPos))+1 ;
        // 所在圈的边长  , 总边长 减 2层 外层圈边长
        long innerBian = num - 2*(ceng-1) ;
        long area = 1L*num* num ; // 需要先转long类型
        long inArae = innerBian* innerBian ;
        long index =  ((area- inArae )%9) +1  ; // 前两个值 需要计算时就要用long类型， 否则会出错
        int left = ceng -1 ;
        int right = num -ceng ;
        // 判断坐标所在内圈的哪一边
        if(xPos==left){
            index += yPos-left;
        }else if(yPos==right){
            index += right-left;
            index += xPos-left;
        }else if(xPos==right){
            index += 2*(right-left);
            index += right-yPos;
        }else if(yPos==left){
            index += 3*(right-left);
            index += right-xPos;
        }

        return (int) (index%9==0? 9 :index%9 );
    }

    public int[] spiralOrder(int[][] matrix) {
        int N = matrix.length ;
        if(N==0){ return new int[0];}
        int M = matrix[0].length;
        int total = N*M ;
        int[] ans = new int[total];

        boolean[][] seen = new boolean[N][M];
        int[][] direction = new int[][]{{0,1},{1,0},{0,-1},{-1,0}} ;
        int di = 0 ;

        int i = 0 ,  j =0 ;

        for (int k = 0; k < total; k++) {
            ans[k]=matrix[i][j] ;
            seen[i][j]=true;
            int ti =i+ direction[di][0];
            int tj =j+ direction[di][1];
            if (ti<0 || ti>=N || tj<0 || tj>=M ||  seen[ti][tj]) {
                di = (di+1) % 4 ;
            }
            i += direction[di][0];
            j += direction[di][1];
        }

        return  ans;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        int N = matrix.length ;
        List<Integer> ans = new ArrayList<>();
        if(N==0){ return ans;}
        int M = matrix[0].length;
        int total = N*M ;

        boolean[][] seen = new boolean[N][M];
        int[][] direction = new int[][]{{0,1},{1,0},{0,-1},{-1,0}} ;
        int di = 0 ;

        int i = 0 ,  j =0 ;

        for (int k = 0; k < total; k++) {
            ans.add(matrix[i][j] );
            seen[i][j]=true;
            int ti =i+ direction[di][0];
            int tj =j+ direction[di][1];
            if (ti<0 || ti>=N || tj<0 || tj>=M ||  seen[ti][tj]) {
                di = (di+1) % 4 ;
            }
            i += direction[di][0];
            j += direction[di][1];
        }

        return  ans;
    }

    public int[] plusOne2(int[] digits) {
        int N = digits.length ;

        int i =N-1;
        int add =1 ;
        while (i>=0 ){
            int t = digits[i]+add;
            digits[i] = t%10 ;
            add = t/10 ;
            if(add==0){
                break;
            }
            i--;
        }
        //最高位 未进位
        if(add==0){
            return digits;
        }else {
            int[] ans = new int[N+1];
            ans[0]=add;
            for (int j = 0; j < N; j++) {
                ans[j+1] = digits[j];
            }
            return ans;
        }
    }


    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();
        int i = num.length -1 ;
        int add =0 ;
        while (i>=0 || k>0 || add>0){
            int number = i>=0 ? num[i] : 0 ;
            int k1 = k%10 ;
            int t = number+ k1 +add ;

            // 按位结果
            ans.add(t%10);

            // 迭代
            add =t/10;
            i--;
            k /=10 ;
        }
        List<Integer> reverse = new ArrayList<>();
        for (int j = ans.size()-1; j >=0; j--) {
            reverse.add(ans.get(j));
        }
        return reverse;
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = l.length ;
        List<Boolean> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.copyOfRange(nums,l[i] ,r[i]+1);
            Arrays.sort(arr);
            int d = arr[1] -arr[0];
            boolean flag = true;
            for (int j = 2; j < arr.length; j++) {
                if(arr[j] - arr[j-1] !=d) {
                    flag=false;
                    break;
                }
            }
            ans.add(flag);
        }
        return ans;
    }

    public String reformat(String s) {
        Queue<Character> abc = new LinkedList<>();
        Queue<Character> num = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c>='0' && c <='9'){
                num.offer(c);
            }
            if(c>='a' && c <= 'z'){
                abc.offer(c);
            }
        }
        int a = abc.size() , b = num.size() ;
        if(Math.abs(a - b) >1 ){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int n = Math.min(a,b);
        for (int i = 0; i <n ; i++) {
            sb.append(abc.poll());
            sb.append(num.poll());
        }
        if(a>b) {
            sb.append(abc.poll());
        }else if(a<b){
            sb.insert(0,num.poll());
        }

        return sb.toString();
    }


    /**
     * 数据分组  按照对应的groupSize 将元素分组
     * @param groupSizes
     * @return
     */
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> ans = new ArrayList<>();

        Map<Integer,List<Integer>> map = new HashMap<>();

        for (int i = 0; i < groupSizes.length ; i++) {
            List<Integer>  cur = map.getOrDefault(groupSizes[i] , new ArrayList<>());
            cur.add(i);
            if(cur.size() == groupSizes[i]){
                ans.add(cur);
                map.put(groupSizes[i],new ArrayList<>());
            }else {
                map.put(groupSizes[i],cur);
            }
        }
        return ans ;
    }


    /**
     * 给你三个整数数组 nums1、nums2 和 nums3 ，请你构造并返回一个 元素各不相同的 数组，且由 至少 在 两个 数组中出现的所有值组成。数组中的元素可以按 任意 顺序排列。
     * @param nums1
     * @param nums2
     * @param nums3
     * @return
     */
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        Set<Integer> dataExist = new HashSet<>();
        Set<Integer> dataExist2 = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (Integer num : nums1){
            dataExist.add(num);
        }
        for (Integer num : nums2){
            if(dataExist.contains(num)){
                result.add(num);
            }else {
                dataExist2.add(num);
            }
        }

        for (Integer num : nums3){
            if(dataExist.contains(num)){
                result.add(num);
            }
            if(dataExist2.contains(num)){
                result.add(num);
            }
        }

        return  result.stream().collect(Collectors.toList());
    }

    /**
     * 找出错误的数字
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int[] cnt = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
        }
        int repeat = 0 ;
        int miss =0 ;
        for (int i = 1; i <= nums.length ; i++) {
            if(cnt[i] == 2) repeat = i;
            if(cnt[i]==0) miss =i ;
        }
        return new int[]{repeat,miss};
    }

    /**
     * 找出所有未出现的数字
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] cnt = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= nums.length ; i++) {
            if(cnt[i]==0) ans.add(i) ;
        }
        return ans;
    }

    /**
     * 找出重复的数组
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        int[] cnt = new int[nums.length+1];
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= nums.length ; i++) {
            if(cnt[i]==2) ans.add(i) ;
        }
        return ans;
    }

    /**
     * 最小操作次数
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > max) max = nums[i];
        }
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt += (nums[i] - max) ;
        }
        return cnt ;
    }

    /**
     * 数组的改变、移动
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int  n = nums.length ;
        int cnt =1 ;
        for (int j = 1; j < n; j++) {
            if(nums[j-1] >nums[j]){
                cnt++;
                if(cnt>1) return false;
                if(j>1 && nums[j] < nums[j-2]){
                    nums[j] = nums[j-1];
                }
            }
        }

        return true ;
    }

    /**
     * 平滑处理  九个格的平均值
     * @param img
     * @return
     */
    public int[][] imageSmoother(int[][] img) {
        int m = img.length ;
        int n = img[0].length ;

        int[][] ans = new int[m][n] ;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int num=0 , sum =0 ;
                for (int k = i-1; k <=i+1 ; k++) {
                    for (int l = j-1; l <=j+1 ; l++) {
                        if(k>=0 && k<m  && l >=0 && l<n) {
                            num++ ;
                            sum += img[k][l];
                        }
                    }
                }
                ans[i][j] =  sum /num ;
            }

        }

        return  ans ;
    }

    /**
     * 范围求和
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public int maxCount(int m, int n, int[][] ops) {
        int a = m , b = n ;
        for (int i = 0; i < ops.length; i++) {
            if(ops[i][0] < a )  a = ops[i][0] ;
            if(ops[i][1] < b ) b = ops[i][1] ;
        }
        return  a * b ;
    }

    /**
     * 统计战舰数量
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        int m = board.length ;
        int n = board[0].length ;
        int ans =0 ;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] =='X'){
                    for (int k = j+1; k <n && board[i][k]=='X'  ; k++) {
                        board[i][k]='.';
                    }

                    for (int k = i+1; k <m && board[k][j]=='X'  ; k++) {
                        board[k][j]='.';
                    }
                    ans++ ;
                }
            }
        }

        return ans ;
    }

    /**
     * 旋转数组
     * @param nums
     * @return
     */
    public int maxRotateFunction(int[] nums) {
        int f =0  , n = nums.length ;
        int sum = Arrays.stream(nums).sum();
        for (int i = 0; i < n; i++) {
            f += i* nums[i] ;
        }
        int ans = f ;
        for (int i = n-1; i >0; i--) {
            f += sum - n*nums[i];
            ans = Math.max(f,ans);
        }
        return ans ;
    }

}