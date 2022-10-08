package com.iflytek.staff.chao.array;

import java.util.*;

/**
 * @author : hamilton
 * @Description: 字符相关算法
 * @date Date : 2022年06月23日 下午4:53
 */
public class CharSolution {

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

    public boolean isPalindrome(String s) {
        int n = s.length();
        int l = 0, r = n - 1;
        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;

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


    public boolean checkInclusion(String s1, String s2) {

        int N1 = s1.length();
        int N2 = s2.length();
        if (N2 < N1) {
            return false;
        }
        int[] dict = new int[26];
        for (int i = 0; i < N1; i++) {
            dict[s1.charAt(i) - 'a']++;
            dict[s2.charAt(i) - 'a']--;
        }

        int diff = 0;
        for (int i = 0; i < 26; i++) {
            if (dict[i] != 0) diff++;
        }

        if (diff == 0) {
            return true;
        }

        for (int i = N1; i < N2; i++) {
            int x = s2.charAt(i) - 'a';
            int y = s2.charAt(i - N1) - 'a';
            if (x == y) {
                continue;
            }
            if (dict[x] == 0) {
                diff++;
            }
            dict[x]++;
            if (dict[x] == 0) {
                diff--;
            }
            if (dict[y] == 0) {
                diff++;
            }
            dict[y]--;
            if (dict[y] == 0) {
                diff--;
            }
            if (diff == 0) {
                return true;
            }
        }

        return false;

    }

    public String reverseWords(String s) {
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


    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();
        List<String> fuhao = Arrays.asList("+", "-", "*", "/");
        for (int i = 0; i < tokens.length; i++) {
            if (fuhao.contains(tokens[i])) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                if ("+".equals(tokens[i])) {
                    stack.push(num1 + num2);
                } else if ("-".equals(tokens[i])) {
                    stack.push(num1 - num2);
                } else if ("*".equals(tokens[i])) {
                    stack.push(num1 * num2);
                } else if ("/".equals(tokens[i])) {
                    stack.push(num1 / num2);
                }
            } else {
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }

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

    public List<Integer> findAnagrams(String s, String p) {
        int PL = p.length();
        int SL = s.length();
        List<Integer> ans = new ArrayList<>();
        int[] pchars = new int[26];
        for (char pchar : p.toCharArray()) {
            ++pchars[pchar - 'a'];
        }
        int prev = -1;
        for (int i = 0; i < SL; i++) {
            int c = s.charAt(i) - 'a';
            // 不符合， 左指正需要移动
            while (pchars[c] == 0) {
                int pr = s.charAt(++prev) - 'a';
                // 将💺指针的对应的字符 频率补回去
                ++pchars[pr];
            }
            //扣掉当前字符
            --pchars[c];
            if (i - prev == PL) {
                ans.add(prev + 1);
            }

        }
        return ans;
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


    /**
     * 判断s 是否是 t 的子序列
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {

        int i = 0;
        int tl = t.length();
        for (char c : s.toCharArray()) {
            while (i < tl && t.charAt(i) != c) {
                i++;
            }
            if (i == tl) return false;
            i++;
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

    public List<Integer> partitionLabels(String s) {
        int N = s.length();
        Map<Character, Integer> charRightIndexMap = new HashMap<>();
        int[] dp = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            char c = s.charAt(i);
            Integer idx = charRightIndexMap.putIfAbsent(c, i);
            if (idx == null) {
                dp[i] = i;
            } else {
                dp[i] = idx.intValue();
            }
        }
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while (i < N) {
            int split = dp[i];
            int j = i;
            while (j <= split) {
                if (dp[j] > split) {
                    split = dp[j];
                }
                j++;
            }
            ans.add(split - i + 1);
            i = split + 1;
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

    public List<List<String>> groupAnagrams(String[] strs) {

        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        List<List<String>> ansList = new ArrayList<>();

        boolean[] seen = new boolean[strs.length];

        for (int i = 0; i < strs.length; i++) {
            if (!seen[i]) {
                List<String> ans = new ArrayList<>();
                ans.add(strs[i]);
                int[] count = new int[127];
                int j = 0;
                while (j < strs[i].length()) {
                    char c = strs[i].charAt(j++);
                    count[c]++;
                }

                for (int k = i + 1; k < strs.length; k++) {
                    if (seen[k]) continue; //已标记的跳过
                    // 验证字母频率是否一致
                    int[] countCopy = count.clone();
                    if (strs[i].length() < strs[k].length()) break;
                    if (isYiWeiCi(countCopy, strs[i].length(), strs[k])) {
                        ans.add(strs[k]);
                        seen[k] = true;
                    }
                }

                seen[i] = true;
                ansList.add(ans);
            }
        }
        return ansList;
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

    public String minRemoveToMakeValid(String s) {
        int N = s.length();
        Set<Integer> indexRemove = new HashSet<>();
        Stack<Integer> openFlag = new Stack<>();
        // 添加无效有括号
        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            if(c=='('){
                openFlag.push(i);
            }
            if(c==')') {
                if(!openFlag.isEmpty()){
                    openFlag.pop();
                }else {
                    indexRemove.add(i);
                }
            }
        }
        //添加无效左括号
        while (!openFlag.isEmpty()) {
            indexRemove.add(openFlag.pop());
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if(indexRemove.contains(i)){
                continue;
            }
            ans.append(s.charAt(i));
        }
        return ans.toString();

    }

    /**
     * 解密字符
     * @param key
     * @param message
     * @return
     */
    public String decodeMessage(String key, String message) {

        Map<Character,Character> map = new HashMap<>();
        map.put(' ',' ');
        char a = 'a';
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if(!map.containsKey(c)){
                map.put(c,a++ );
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < message.length() ; i++) {
            ans.append(map.get(message.charAt(i)));
        }
        return ans.toString();
    }



}
