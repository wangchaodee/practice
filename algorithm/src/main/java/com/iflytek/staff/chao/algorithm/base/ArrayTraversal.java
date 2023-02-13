package com.iflytek.staff.chao.algorithm.base;

import java.util.*;
import java.util.stream.Collectors;

import static com.iflytek.staff.chao.algorithm.base.SortUtil.less;

/**
 * @author : wangchaodee
 * @Description: 数组遍历
 * @date Date : 2022年08月14日 21:48
 */
public class ArrayTraversal {




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



    /**
     * 查找数组中只存在一个的数字， 用的异或方式  ，其他成组存在的数会消除掉
     *
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
            //去除重复
            if (i > 0) {
                if (nums[i] == nums[i - 1]) {
                    continue;
                }
            }

            int ci = n - 1;
            int target = 0 - nums[i];
            for (int j = i + 1; j < n - 1; j++) {

                //去除重复
                if (j > i + 1) {
                    if (nums[j] == nums[j - 1]) {
                        continue;
                    }
                }

                // 双指针方式寻找
                while (j < ci && nums[j] + nums[ci] > target) {
                    ci--;
                }

                if (j == ci) {
                    break;
                }

                if (nums[j] + nums[ci] == target) {
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
     * 896 判断是否单调数列
     *
     * @param nums
     * @return
     */
    public boolean isMonotonic(int[] nums) {
        boolean inc = true, decr = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                inc = false;
            }
            if (nums[i] < nums[i - 1]) {
                decr = false;
            }
        }

        return inc || decr;
    }

    public boolean isMonotonic2(int[] nums) {
        boolean inc = false, decr = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                if(decr) return false;
                inc = true;
            }

            if (nums[i] < nums[i - 1]) {
                if(inc) return false;
                decr = true;
            }
        }

        return true;
    }

    /**
     * 求每一处元素 排除自身 其他元素的乘积
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int N = nums.length;
        int[] result = new int[N];
        int tmp = 1;
        for (int i = 0; i < N; i++) {
            result[i] *= tmp;
            tmp *= nums[i];
        }
        tmp = 1;
        for (int i = N - 1; i >= 0; i--) {
            result[i] *= tmp;
            tmp *= nums[i];
        }
        return result;
    }

    /**
     * 和上面的方法相似  用的空间更多
     *
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


    /**
     * 统计连续1 的最大数量
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            cnt = (nums[i] == 1 ? cnt + 1 : 0);

            if (cnt > max) {
                max = cnt;
            }

        }
        return max;
    }

    /**
     * 提莫攻击  返回总的中毒秒数
     *
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int count = 0;
        int next = 0;
        for (int i = 0; i < timeSeries.length; i++) {
            if (timeSeries[i] > next) {
                count += duration;
            } else {
                count += (timeSeries[i] + duration - 1) - next;
            }
            next = timeSeries[i] + duration - 1;
        }
        return count;
    }

    /**
     * 返回第三大的数
     *
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        int N = nums.length;

        Set<Integer> sets = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue(3);

        for (int i = 0; i < N; i++) {
            if (sets.contains(nums[i])) {
                continue;
            }
            sets.add(nums[i]);
            if (pq.size() < 3) {
                pq.offer(nums[i]);
            } else if (pq.peek() < nums[i]) {
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
        int n = nums.length;
        return Math.max(nums[n - 3] * nums[n - 2] * nums[n - 1], nums[0] * nums[1] * nums[n - 1]);
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
                    SortUtil.exchange(nums, j, j - 1);
                }
            }
        }

        return nums;
    }

    /**
     * 将数组中的零元素移动到尾端
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int N = nums.length;
        int l = 0;
        for (int i = 0; i < N; i++) {
            if (nums[i] != 0) {
                SortUtil.exchange(nums, l, i);
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
            if (SortUtil.less(arr[i], pq.peek().intValue())) {
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
            SortUtil.exchange(arr, i, min);
            least[i] = arr[i];
        }
        return least;
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


    /**
     * 66. 加一
     * int[] result =new ArraySolution().plusOne(new int[]{8, 9, 9, 9});
     * for (int e : result) {
     * System.out.print(e);
     * }
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {

        int tmp =1;
        for (int i = digits.length - 1; i >= 0; i--) {
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

    /**
     * 找出重复的数组
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        int[] cnt = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (cnt[i] == 2) ans.add(i);
        }
        return ans;
    }

    public int findDuplicate(int[] nums) {
        int N = nums.length;
        int l = 0, r = N - 1, mid = 0;
        while (l <= r) {
            mid = (r + l) >> 1;
            int count = 0;
            for (int i = 0; i < N; i++) {
                if (nums[i] <= mid) count++;
            }
            if (count > mid) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
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
        Number number = new Number();
        for (int i = 0; i < N; i++) {
            arrBit[arr[i]] = number.hammingWeight(arr[i]);
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


    /**
     * 最少操作次数， 使数组都变为 0
     *
     * @param nums
     * @return
     */
    public int minimumOperations(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0) set.add(num);
        }
        return set.size();
    }

    public int maximumGroups(int[] grades) {
        int N = grades.length;
        int l = 1, r = N - 1;
        while (l < r) {
            int m = (r + l + 1) / 2;
            if (m <= 2 * N / (m + 1)) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l <= 2 * N / (l + 1) ? l : l - 1;
    }


    /**
     * 采购零件方案
     *
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans(int[] nums, int target) {
        int mod = 1000000007;
        int count = 0;
        int N = nums.length;
        Arrays.sort(nums);
        int j = N - 1;
        for (int i = 0; i < N && nums[i] * 2 <= target; i++) {
            int t = target - nums[i];
            while (j > i && nums[j] > t) {
                j--;
            }
            if (j == i) break;

            count = (count + (j - i)) % mod;
        }
        return count;
    }


    /**
     * 989 数组形式的整数加个整数
     * @param num
     * @param k
     * @return
     */
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();
        int i = num.length - 1;
        int add = 0;
        while (i >= 0 || k > 0 || add > 0) {
            int number = i >= 0 ? num[i--] : 0;
            int k1 = k % 10;
            int t = number + k1 + add;

            // 按位结果
            ans.add(t % 10);

            // 迭代
            add = t / 10;
            k /= 10;
        }

        List<Integer> reverse = new ArrayList<>();
        for (int j = ans.size() - 1; j >= 0; j--) {
            reverse.add(ans.get(j));
        }
        return reverse;
    }

    public List<Integer> addToArrayForm2(int[] num, int k) {
        LinkedList<Integer> ans = new LinkedList<>();
        int i = num.length - 1;
        while (i >= 0 || k > 0 ) {
            int number = i >= 0 ? num[i--] : 0;

            k += number  ;

            // 按位结果
            ans.addFirst(k % 10);

            // 迭代
            k /= 10;
        }
        return ans;
    }


    /**
     * 找出错误的数字
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int[] cnt = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
        }
        int repeat = 0;
        int miss = 0;
        for (int i = 1; i <= nums.length; i++) {
            if (cnt[i] == 2) repeat = i;
            if (cnt[i] == 0) miss = i;
        }
        return new int[]{repeat, miss};
    }

    public int[] findErrorNums2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                SortUtil.exchange(nums, i, nums[i] - 1);
            }
        }
        int repeat = 0;
        int miss = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                repeat = nums[i];
                miss = i + 1;
                break;
            }
        }
        return new int[]{repeat, miss};
    }

    /**
     * 找出所有未出现的数字
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] cnt = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            cnt[nums[i]]++;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (cnt[i] == 0) ans.add(i);
        }
        return ans;
    }


    /**
     * 最小操作次数
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) max = nums[i];
        }
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            cnt += (nums[i] - max);
        }
        return cnt;
    }


    /**
     * 旋转数组
     *
     * @param nums
     * @return
     */
    public int maxRotateFunction(int[] nums) {
        int f = 0, n = nums.length;
        int sum = Arrays.stream(nums).sum();
        for (int i = 0; i < n; i++) {
            f += i * nums[i];
        }
        int ans = f;
        for (int i = n - 1; i > 0; i--) {
            f += sum - n * nums[i];
            ans = Math.max(f, ans);
        }
        return ans;
    }

    /**
     * 优美的排列 II
     * 给你两个整数 n 和 k ，请你构造一个答案列表 answer ，该列表应当包含从 1 到 n 的 n 个不同正整数，并同时满足下述条件：
     * 假设该列表是 answer = [a1, a2, a3, ... , an] ，那么列表 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数。
     *
     * @param n
     * @param k
     * @return
     */
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        ans[0] = 1;
        for (int i = 1, interval = k; i < k + 1; i++, interval--) {
            ans[i] = (i % 2 == 1) ? ans[i - 1] + interval : ans[i - 1] - interval;
        }
        for (int i = k + 1; i < n; i++) {
            ans[i] = i + 1;
        }
        return ans;
    }

    /**
     * 565. 数组嵌套
     * 可不用额外空间  ， 循环时 将 nums[i] = -1 ，  可以原地判断
     *
     * @param nums
     * @return
     */
    public int arrayNesting(int[] nums) {
        int max = 0;
        boolean[] seen = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int cnt = 0;
            while (!seen[i]) {
                cnt++;
                seen[i] = true;
                i = nums[i];
            }

            max = Math.max(max, cnt);
        }
        return max;
    }

    /**
     * 769. 最多能完成排序的块  arr  的值 是 0 到n-1 之间
     *
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {

        int right = 0;
        int cnt = 1;
        for (int i = 0; i < arr.length; i++) {
            right = Math.max(right, arr[i]);
            if (right == i) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * 1480. 一维数组的动态和
     * @param nums
     * @return
     */
    public int[] runningSum(int[] nums) {
        int n = nums.length ;
        int[] ans = new int[n];
        ans[0] = nums[0];
        for (int i = 1; i <n ; i++) {
            ans[i] = ans[i-1] + nums[i];
        }
        return ans ;
    }

    /**
     * 724. 寻找数组的中心下标
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int n = nums.length ;
        int[] ans = new int[n];
        ans[0] = nums[0];
        for (int i = 1; i <n ; i++) {
            ans[i] = ans[i-1] + nums[i];
        }
        if(ans[n-1] - ans[0] == 0) return 0;
        for (int i = 1; i <n ; i++) {
            if(ans[n-1] - ans[i] == ans[i-1] ) return i;
        }
        return -1 ;
    }

    /**
     * 18 四数之和
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int N = nums.length ;
        if(N<4) return ans ;
        Arrays.sort(nums);

        for (int i = 0; i < N-3; i++) {
            if(i>0 && nums[i]==nums[i-1]) continue; // 重复值
            if((long)nums[i] + nums[i+1]+nums[i+2] + nums[i+3] > target) continue; // 超出范围
            if((long)nums[i] + nums[N-1]+nums[N-2] + nums[N-3] < target) continue; // 范围值过小
            int a = nums[i];
            for (int j = i+1; j < N-2  ; j++) {
                if(j>i+1 && nums[j]==nums[j-1]) continue; // 重复值
                if((long)nums[i] + nums[j]+nums[j+1] + nums[j+2] > target) continue; // 超出范围
                if((long)nums[i] + nums[j]+nums[N-2] + nums[N-1] < target) continue; // 范围值过小

                int b = nums[j];
                int l = j+1 ,h = N-1 ;
                while (l<h ){
                    long sum =(long) a +b+ nums[l] + nums[h] ;
                    if(sum==target) {
                        ans.add( Arrays.asList(a,b, nums[l] , nums[h]));
                        while (l<h&& nums[l] == nums[l+1]) l++;
                        l++;
                        while (l<h&& nums[h] == nums[h-1]) h--;
                        h--;
                    }else if(sum<target){
                        l++;
                    }else {
                        h--;
                    }
                }
            }

        }
        return ans;
    }

}
