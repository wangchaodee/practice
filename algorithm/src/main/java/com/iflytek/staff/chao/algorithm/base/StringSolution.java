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
     * 67 二进制求和
     * 剑指 Offer II 002. 二进制加法
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


    public char findTheDifference(String s, String t) {
        int N = s.length();
        int code = t.charAt(0);
        for (int i = 0; i < N; i++) {
            code += t.charAt(i + 1) - s.charAt(i);
        }
        return (char) code;
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


}
