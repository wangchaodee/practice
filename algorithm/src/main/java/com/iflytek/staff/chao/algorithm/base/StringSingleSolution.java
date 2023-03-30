package com.iflytek.staff.chao.algorithm.base;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 单字符串的算法
 */
public class StringSingleSolution {

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
    public int lengthOfLongestSubstring_2(String s) {
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

    /**
     * 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int[] chars= new int[128];
        int left = -1;
        int max =0 ;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            chars[c]++ ;
            while (chars[c]>1){
                chars[s.charAt(++left)]--;
            }
            max = Math.max(max, i - left);
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



    /**
     * 8. 字符串转换整数 (atoi) 字符转换数字
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

    /**
     * 面试题 05.02. 二进制数转字符串
     * @param num
     * @return
     */
    public String printBin(double num) {
        StringBuilder sb = new StringBuilder("0.") ;
        while (sb.length() <=32 && num !=0){
            num *=2 ;
            int digit = (int) num ;
            sb.append(digit);
            num -=digit;
        }
        return sb.length()>32 ? "ERROR" :sb.toString();
    }



}
