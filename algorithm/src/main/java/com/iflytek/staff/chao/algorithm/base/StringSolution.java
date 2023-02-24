package com.iflytek.staff.chao.algorithm.base;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 字符串类的 算法判断
 * @date Date : 2022年07月31日 18:01
 */
public class StringSolution {

    /**
     * 49. 字母异位词分组
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> strMap = new HashMap<>();

        for (String str : strs) {
            String key = getKey(str);
            List<String> list = strMap.getOrDefault(key, new ArrayList<>());
            list.add(str);
            strMap.put(key, list);
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> value : strMap.values()) {
            ans.add(value);
        }
        return ans;
    }

    private String getKey(String str) {
        if (str.length() == 0) return str;
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }



    /**
     * KMP 算法  获取k的值
     *
     * @param ps
     * @return
     */
    public int[] getNext(String ps) {
        int N = ps.length();
        char[] arr = ps.toCharArray();
        int[] next = new int[N];
        int j = 0;
        int k = -1;
        next[j] = k;
        while (j < N - 1) {
            if (k == -1 || arr[j] == arr[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * 28. 找出字符串中第一个匹配项的下标
     * "mississippi"  "issip"
     * "mississippi" "pi"
     * "mississippi" "issipi"
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;

        int nl = needle.length();
        int n = 0;

        int hl = haystack.length();
        int i = 0;

        int[] next = getNext(needle);

        while (i < hl && n < nl) {
            if (n == -1 || haystack.charAt(i) == needle.charAt(n)) {
                n++;
                i++;
            } else {
                n = next[n];
            }
        }
        if (n == nl) {
            return i - n;
        }
        return -1;
    }


    public int strStr2(String haystack, String needle) {
        if (needle.length() == 0) return 0;

        int nl = needle.length();
        int n = 0; // n = nl 时 搜索到目标

        int hl = haystack.length();
        int i = 0;
        while (i < hl && n < nl) {
            if (haystack.charAt(i) == needle.charAt(n)) {
                n++;
                i++;
            } else {
                i = i - n + 1;
                n = 0;
            }
        }
        if (n == nl) {
            return i - n + 1;
        }
        return -1;
    }

    public int strStr3(String haystack, String needle) {
        int nl = needle.length();
        int hl = haystack.length();
        if(nl >hl) return -1 ;
        int end = hl-nl ;
        for (int i = 0; i <= end ; i++) {
            int n = 0 ;
            while (n<nl && haystack.charAt(i+n) == needle.charAt(n)) {
                n++;
            }

            if (n == nl) {
                return i ;
            }
        }
        return -1;
    }

    /**
     *459. 重复的子字符串
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        int N = s.length();
        if (N <= 1) return false;
        //  i 代表子串的长度 ，子串长度的上限为原始串的一半 超过的不用遍历筛查
        for (int i = 1; i * 2 <= N; i++) {
            //是否为整数倍数  不为整数倍的代表不是这个i长度的字串重复多遍生成的，可以跳过
            if (N % i == 0) {
                boolean match = true;
                // 前面的i长度代表重复的子串
                for (int j = i; j < N; j++) {
                    // 依次判断间隔长度 i 的首位两端 字符是否相等即可 ，j-i 指向首字符 ，j 指向尾子符
                    if (s.charAt(j) != s.charAt(j - i)) {
                        // 代表 字段长度i 的不是正确解
                        match = false;
                        break;
                    }
                }
                // 代表 长度i的字串 是一种重复的字串  ，代表找到了个最小程度 直接结束筛查即可
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern2(String s) {
        return (s+s).indexOf(s,1) != s.length() ;
    }


    /**
     * 67 二进制求和
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {

        int add = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;

        // 以逆序的方式 存入结果  结果需要翻转
        StringBuilder ans = new StringBuilder();
        while (i >= 0 || j >= 0 || add > 0) {
            int a1 = i >= 0 ? a.charAt(i--) - '0' : 0;
            int b1 = j >= 0 ? b.charAt(j--) - '0' : 0;

            int t = a1 + b1 + add;
            //位计算结果
            ans.append(t % 2);
            //进位值
            add = t / 2;
        }
        return ans.reverse().toString();
    }

    /**
     * 反转字符串中的单词
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] strlist = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strlist.length - 1; i >= 0; i--) {
            if (strlist[i].length() == 0) continue;
            sb.append(strlist[i]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String reverseWords2(String s) {
        char[] chars = s.toCharArray();
        int N = chars.length;
        int l = -1, r = -1;
        char t = 0;
        for (int i = 0; i < N; i++) {
            if (chars[i] == ' ') {
                l = r;
                r = i;
                reverseChars(chars, t, l + 1, r - 1);
            }

            if (i == N - 1) {
                l = r;
                r = i;
                reverseChars(chars, t, l + 1, r);
            }

        }

        return new String(chars);
    }

    private void reverseChars(char[] s, char t, int l, int r) {

        while (l < r) {
            t = s[r];
            s[r] = s[l];
            s[l] = t;
            l++;
            r--;
        }
    }

    public void reverseString(char[] s) {
        int N = s.length;
        int l = 0, r = N - 1;
        char t;
        while (l < r) {
            t = s[r];
            s[r] = s[l];
            s[l] = t;
            l++;
            r--;
        }

    }

    /**
     * 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> indexMap = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (indexMap.containsKey(s.charAt(i))) {
                left = indexMap.get(s.charAt(i)) + 1;
            } else {
                max = Math.max(max, i - left);
            }
            indexMap.put(s.charAt(i), i);
        }

        return max;
    }


    public int countSegments(String s) {

        String[] strs = s.split(" ");
        int n = strs.length;
        for (String str : strs) {
            if (str.length() == 0) {
                n--;
            }
        }
        return n;
    }



    public char findTheDifference(String s, String t) {
        int N = s.length();
        int code = t.charAt(0);
        for (int i = 0; i < N; i++) {
            code += t.charAt(i + 1) - s.charAt(i);
        }
        return (char) code;
    }


    /**
     * 字符转换数字
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int N = s.length();
        //if(N==0) return 0 ;
        int ans = 0;
        char c;
        boolean negetive = false;// 正负符号
        int num = 0;
        int i = 0;
        while (i < N && s.charAt(i) == ' ') {
            i++;
        }
        if (i >= N) return 0;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            if (s.charAt(i) == '-') negetive = true;
            i++;
        }

        while (i < N) {
            c = s.charAt(i);
            i++;
            if (Character.isDigit(c)) {
                num = c - '0';
                if (ans < (Integer.MIN_VALUE + num) / 10) {
                    return Integer.MIN_VALUE;
                }

                if (ans > (Integer.MAX_VALUE - num) / 10) {
                    return Integer.MAX_VALUE;
                }

                if (negetive) {
                    ans = 10 * ans - num;
                } else {
                    ans = 10 * ans + num;
                }
            } else {
                break;
            }
        }

        return ans;
    }

    /**
     * 转换罗马数字
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int N = s.length();
        int ans = 0;
        int pre = 0;
        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            int cn = getOne(c);
            if (cn > pre) {
                ans += cn - 2 * pre;
            } else {
                ans += cn;
            }
            pre = cn;
        }
        return ans;
    }

    private int getOne(char one) {
        switch (one) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    private int getTwo(String two) {
        switch (two) {
            case "IV":
                return 4;
            case "IX":
                return 9;
            case "XL":
                return 40;
            case "XC":
                return 90;
            case "CD":
                return 400;
            case "CM":
                return 900;
            default:
                return 0;
        }
    }


    public String countAndSay(int n) {
        if (n == 1) return "1";
        String pre = countAndSay(n - 1);
        int N = pre.length();
        int count = 1;
        int pre_num = pre.charAt(0) - '0';
        int cur_num = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N; i++) {
            cur_num = pre.charAt(i) - '0';
            if (cur_num != pre_num) {
                sb.append(count).append(pre_num);
                pre_num = cur_num;
                count = 1;
            } else {
                count++;
            }
        }
        return sb.toString();
    }

    /**
     * 14. 最长公共前缀
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int N = strs.length;
        String prefix = strs[0];
        for (int i = 1; i < N; i++) {
            prefix = commonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) break;
        }

        return prefix;
    }

    private String commonPrefix(String prefix, String str) {
        int len = Math.min(prefix.length(), str.length());
        int i = 0;
        while (i < len) {
            if (prefix.charAt(i) == str.charAt(i)) {
                i++;
            } else {
                break;
            }
        }
        return prefix.substring(0, i);
    }

    /**
     * 剑指 Offer 05. 替换空格
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                ans.append("%20");
            } else {
                ans.append(c);
            }
        }
        return ans.toString();
    }

    /**
     * 844. 比较含退格的字符串
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        int sidx = s.length() - 1;
        int tidx = t.length() - 1;
        int ds = 0, dt = 0; // 连续的退格字符数量

        while (sidx >= 0 && tidx >= 0) {

            while (sidx >= 0) {
                if (s.charAt(sidx) == '#') {
                    ds++;
                    sidx--;
                } else if (ds > 0) {
                    ds--;
                    sidx--;
                } else {
                    break;
                }
            }

            while (tidx >= 0) {
                if (t.charAt(tidx) == '#') {
                    dt++;
                    tidx--;
                } else if (dt > 0) {
                    dt--;
                    tidx--;
                } else {
                    break;
                }
            }

            if (sidx < 0 || tidx < 0) {
                // 只有一个为空时， 是不相等的
                return sidx < 0 && tidx < 0;
            }

            char sh = s.charAt(sidx);
            char th = t.charAt(tidx);
            if (th == sh) {
                sidx--;
                tidx--;
            } else {
                return false;
            }
        }
        return sidx == tidx;
    }



    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int diff = 0; // 超过2 时 异常
        char s1a = ' ', s2a = ' ';
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff == 2) {
                    return false;
                } else if (diff == 1) {
                    if (s1a != s2.charAt(i) || s2a != s1.charAt(i)) return false;
                    diff++;
                } else {
                    s1a = s1.charAt(i);
                    s2a = s2.charAt(i);
                    diff++;
                }
            }
        }
        return true;
    }

    public boolean isSubsequence(String s, String t, Set<Integer> removeIndex) {

        int i = 0;
        int tl = t.length();
        for (char c : s.toCharArray()) {
            while (i < tl && (removeIndex.contains(i) || t.charAt(i) != c)) {
                i++;
            }
            if (i == tl) return false;
            i++;
        }
        return true;
    }

    /**
     * 判断s  可移除字符的最大数目
     *
     * @param s         长序列
     * @param p         子序列
     * @param removable
     * @return
     */
    public int maximumRemovals(String s, String p, int[] removable) {
        int N = removable.length;
        int l = 0, r = N - 1, mid = 0, ans = 0;
        while (l <= r) {
            mid = l + r >> 1;
            Set<Integer> remove = new HashSet<>();
            for (int i = 0; i <= mid; i++) {
                remove.add(removable[i]);
            }
            if (isSubsequence(p, s, remove)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }


    public String mergeAlternately(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < l1 && i < l2) {
            char a = word1.charAt(i);
            char b = word2.charAt(i);
            sb.append(a).append(b);
            i++;
        }

        if (i < l1) {
            sb.append(word1.substring(i));
        } else {
            sb.append(word2.substring(i));
        }

        return sb.toString();
    }

    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        StringBuilder pre = new StringBuilder();
        int i = 0;
        while (i < command.length()) {
            char c = command.charAt(i);
            if ('G' == c) {
                sb.append(c);
            } else {
                pre.append(c);
                if ("()".equals(pre.toString())) {
                    sb.append('o');
                    pre.delete(0, pre.length());
                } else if ("(al)".equals(pre.toString())) {
                    sb.append("al");
                    pre.delete(0, pre.length());
                }
            }
            i++;
        }
        return sb.toString();
    }


    /**
     * 找出t中比s多出的一个不同字母  只多一个不一样的
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference2(String s, String t) {
        int N = s.length();
        int[] code = new int[128];
        for (char c : s.toCharArray()) {
            code[c]++;
        }
        for (char c : t.toCharArray()) {
            code[c]--;
            if (code[c] < 0) return c;
        }
        //多余的
        return t.charAt(N);
    }


    public boolean wordPattern(String pattern, String s) {
        String[] sArray = s.split(" ");
        if (pattern.length() != sArray.length) return false;

        Map<Character, String> relations = new HashMap<>();

        Map<String, Character> relations2 = new HashMap<>();
        int i = 0;
        while (i < pattern.length()) {
            char a = pattern.charAt(i);
            String sa = sArray[i];
            if (relations.containsKey(a)) {
                if (!sa.equals(relations.get(a))) return false;
            } else if (relations2.containsKey(sa)) {
                if (a != relations2.get(sa)) return false;
            } else {
                relations.put(a, sa);
                relations2.put(sa, a);
            }
            i++;
        }
        return true;
    }


    /**
     * 用源字串的频率 判断 str是否异位词
     *
     * @param count
     * @param n
     * @param str
     * @return
     */
    private boolean isYiWeiCi(int[] count, int n, String str) {
        if (n != str.length()) return false;
        // 验证字母频率是否一致

        int l = 0;
        while (l < str.length()) {
            char c = str.charAt(l++);
            count[c]--;
            if (count[c] < 0) return false;
        }
        return true;
    }


    /**
     * 将字符串中的大写字母转为小写   a =97  ,A = 65 (+25 到Z),  字符串中由可打印的ascii码组成
     *
     * @param s
     * @return
     */
    public String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();

        for (char i : s.toCharArray()) {
            if (65 <= i && i <= 90) {
                i = (char) (i + 32);
            }
            sb.append(i);
        }
        return sb.toString();
    }

    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        int N = s.length();
        for (int i = 0; i < N; i++) {
            int c = 0;
            if (i + 2 < N && s.charAt(i + 2) == '#') {
                c = 96 + (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
                i = i + 2;
            } else {
                c = 96 + (s.charAt(i) - '0');
            }
            sb.append((char) c);
        }
        return sb.toString();
    }

    public boolean isAlienSorted(String[] words, String order) {

        for (int i = 0; i < words.length - 1; i++) {
            if (!isAlienSorted(words[i], words[i + 1], order)) return false;
        }
        return true;
    }

    private boolean isAlienSorted(String left, String right, String order) {
        int i = 0;
        while (i < left.length() && i < right.length() && left.charAt(i) == right.charAt(i)) i++;

        if (i < left.length()) {
            if (i == right.length()) {
                return false;
            } else {
                return order.indexOf(left.charAt(i)) < order.indexOf(right.charAt(i));
            }
        }
        return true;
    }


    public String addStrings(String num1, String num2) {

        int N1 = num1.length();
        int N2 = num2.length();
//        if(N1 > N2) return addStrings(num2,num1);

        int i = 0;
        int add = 0;

        StringBuilder sb = new StringBuilder();
        while (i < N1 || i < N2) {
            int a = i < N1 ? num1.charAt(N1 - 1 - i) - '0' : 0;
            int b = i < N2 ? num2.charAt(N2 - 1 - i) - '0' : 0;
            int c = a + b + add;
            if (c > 9) {
                add = 1;
            } else {
                add = 0;
            }
            c = c % 10;
            sb.append(c);
            i++;
        }

        if (add != 0) sb.append(add);

        return sb.reverse().toString();
    }

    /**
     * 43. 字符串相乘
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) return "0";

        int N1 = num1.length();
        int N2 = num2.length();
        String ans = "0";
        //设定num2的下标 从后往前
        for (int i = N2 - 1; i >= 0; i--) {

            StringBuilder curr = new StringBuilder();

            //补充移位产生的10倍个数
            for (int j = N2 - 1; j > i; j--) {
                curr.append("0");
            }

            int y = num2.charAt(i) - '0';
            int add = 0;

            //循环num1 从尾部
            for (int j = N1 - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int positive = x * y + add;
                curr.append(positive % 10);
                add = positive / 10;
            }
            if (add != 0) curr.append(add);

            //将此次乘积 加到总乘积中  需要翻转
            ans = addStrings(ans, curr.reverse().toString());
        }

        return ans;
    }

    public String multiply2(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) return "0";

        int N1 = num1.length();
        int N2 = num2.length();

        int[] ans = new int[N1+N2] ;
        //设定num2的下标 从后往前
        for (int i = N2 - 1; i >= 0; i--) {
            int y = num2.charAt(i) - '0';
            //循环num1 从尾部
            for (int j = N1 - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                ans[i+j+1] += y*x;
            }
        }

        for (int i = N1+N2 -1; i >0 ; i--) {
            ans[i-1] += ans[i] /10 ;
            ans[i] %=10 ;
        }

        int idx =0 ;
        if(ans[idx] == 0 ) idx++ ;
        StringBuffer sb = new StringBuffer();
       while (idx < N1 + N2){
           sb.append(ans[idx++]);
       }

        return sb.toString();
    }


    public List<String> findRepeatedDnaSequences(String s) {
        int N = s.length();
        Map<String, Integer> strCount = new HashMap<>();

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < N - 10; i++) {
            String temp = s.substring(i, i + 10);
            strCount.put(temp, strCount.getOrDefault(temp, 0) + 1);
            if (strCount.get(temp) == 2) {
                ans.add(temp);
            }
        }
        return ans;
    }


    /**
     * 解密字符
     *
     * @param key
     * @param message
     * @return
     */
    public String decodeMessage(String key, String message) {

        Map<Character, Character> map = new HashMap<>();
        map.put(' ', ' ');
        char a = 'a';
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, a++);
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            ans.append(map.get(message.charAt(i)));
        }
        return ans.toString();
    }


    public String reformat(String s) {
        Queue<Character> abc = new LinkedList<>();
        Queue<Character> num = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num.offer(c);
            }
            if (c >= 'a' && c <= 'z') {
                abc.offer(c);
            }
        }
        int a = abc.size(), b = num.size();
        if (Math.abs(a - b) > 1) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int n = Math.min(a, b);
        for (int i = 0; i < n; i++) {
            sb.append(abc.poll());
            sb.append(num.poll());
        }
        if (a > b) {
            sb.append(abc.poll());
        } else if (a < b) {
            sb.insert(0, num.poll());
        }

        return sb.toString();
    }

    /**
     * 205 字符串同构
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        // 记录一个字符上次出现的位置，如果两个字符串中的字符上次出现的位置一样，那么就属于同构。
        int[] preIndexS = new int[256];
        int[] preIndexT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char ss = s.charAt(i), tt = t.charAt(i);
            if (preIndexS[ss] != preIndexT[tt]) {
                return false;
            }
            // 默认值为0 ， 初始i=0 , 所以加1 以区别
            preIndexT[tt] = i + 1;
            preIndexS[ss] = i + 1;
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        int L = s.length();
        char[] cArr = new char[128];
        for (int i = 0; i <L ; i++) {
            int j = s.charAt(i);
            char m = t.charAt(i);
            if(cArr[j]==0){
                for (int k = 0; k <128 ; k++) {
                    if(k!=j && cArr[k]==m) return false;
                }
                cArr[j]=m;
            }else {
                if(cArr[j] !=m) return false;
            }
        }

        return true;
    }

    /**
     * 647 回文子串
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int total = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            total += countSubstrings(s, i, i, n);
            total += countSubstrings(s, i, i + 1, n);
        }
        return total;
    }

    private int countSubstrings(String s, int left, int right, int n) {
        int cnt = 0;
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            cnt++;
        }
        return cnt;
    }

    /**
     * 409. 计算一组字符集合可以组成的回文字符串的最大长度
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {

        int[] cnts = new int[128];
        for (char c : s.toCharArray()) {
            cnts[c]++;
        }
        int palindrome = 0;
        for (int cnt : cnts) {
            palindrome += (cnt / 2) * 2;
        }
        if (palindrome < s.length()) palindrome += 1;
        return palindrome;
    }

    /**
     * 找s中最长的回环字串 5
     *
     * @param s
     * @return
     */
    int max = 0;
    String subString;

    public String longestPalindrome2(String s) {
        int n = s.length();
        if (n <= 1) return s;
        for (int i = 0; i < n; i++) {
            findSubstrings(s, i, i, n);
            findSubstrings(s, i, i + 1, n);
        }
        return subString;
    }

    private void findSubstrings(String s, int left, int right, int n) {

        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (right - left - 2 > max) {
            max = right - left - 2;
            subString = s.substring(left + 1, right);
        }
    }

    /**
     * 696. 计数二进制子串
     *
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        int preLen = 0, curLen = 1, count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                curLen++;
            } else {
                preLen = curLen;
                curLen = 1;
            }

            if (preLen >= curLen) {
                count++;
            }
        }
        return count;
    }

    /**
     * 58. 最后一个单词的长度
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int cnt = 0 ;
        boolean empty = false ;
        for (char c : s.toCharArray()) {
            if(c == ' ') {
                empty = true ;
            }else {
                if(empty){
                    empty = false ;
                    cnt =0 ;
                }
                cnt++;
            }
        }
        return cnt ;
    }

    /**
     *剑指 Offer 58 - II. 左旋转字符串
     * @param s
     * @param n
     * @return
     */
    public String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        char[]  arr = new char[chars.length];
        int j=0;
        for (int i = n; i <chars.length ; i++) {
            arr[j++] = chars[i];
        }
        for (int i = 0; i < n; i++) {
            arr[j++] = chars[i];
        }
        return new String(arr);
    }

    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        int l = s.length() ;
        Map<Character , Integer> letterCount = new HashMap<>() ;
        for (int i = 0; i <l ; i++) {
            char c = s.charAt(i);
            letterCount.put(c, letterCount.getOrDefault(c,0)+1);
        }

        for (int i = 0; i <l ; i++) {
            char c = s.charAt(i);
            if(letterCount.get(c)==1) return c;
        }

        return ' ';
    }

}
