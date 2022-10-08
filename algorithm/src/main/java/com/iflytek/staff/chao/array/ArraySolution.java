package com.iflytek.staff.chao.array;

import java.util.*;
import java.util.stream.Collectors;

import static com.iflytek.staff.chao.array.SortUtil.exchange;

public class ArraySolution {



    public void moveZeroes(int[] nums) {
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

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int l = 0, r = 0;

        List<Integer> list = new ArrayList<>();
        while (l < nums1.length && r < nums2.length) {
            if (nums1[l] < nums2[r]) {
                l++;
            } else if (nums1[l] > nums2[r]) {
                r++;
            } else {
                list.add(nums1[l]);
                l++;
                r++;
            }
        }
        int[] ret = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i] = list.get(i);
        }
        return ret;
    }


    public int singleNumber(int[] nums) {
        int a = nums[0];
        for (int i = 1; i < nums.length; i++) {
            a ^= nums[i];
        }
        return a;
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


    public int[] productExceptSelf(int[] nums) {
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

    public boolean containsDuplicate(int[] nums) {
//        IntStream.of(nums).distinct().count()
        return Arrays.stream(nums).distinct().count() != nums.length;

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



}
