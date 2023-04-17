package com.iflytek.staff.chao.algorithm.base;

import com.iflytek.staff.chao.util.NumberUtil;

import java.util.List;

/**
 * @author : wangchaodee
 * @Description: 字符串类的动态规划算法
 * @date Date : 2023年02月16日 14:23
 */
public class DynamicPlanString {

    /**
     * 1143 最长的公共子序列
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        if (m > n) return longestCommonSubsequence(text2, text1);

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = dp[i - 1][n]; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }


    /**
     * 139 单词拆分
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int N = s.length();
        boolean[] dp = new boolean[N + 1];
        dp[0] = true;
        for (int i = 1; i <= N; i++) {
            for (String w : wordDict) {
                int len = w.length();
                if (len <= i && w.equals(s.substring(i - len, i)) && !dp[i]) {
                    dp[i] = dp[i] || dp[i - len];
                }
            }
        }
        return dp[N];
    }


    /**
     * 583 只允许删除操作  ，求最小删除次数
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m - dp[m][n] + n - dp[m][n];
    }

    /**
     * 72. 编辑距离
     * 可以删除  、修改  、插入 字符， 求最少操作次数
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (m * n == 0) {
            return m + n;
        }

        int[][] dp = new int[m + 1][n + 1];
        // 首列差异
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        // 首行差异
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {
            // 转换charArray 再查找 更快
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //  dp[i - 1][j], dp[i][j - 1]  增加一个字符 ， dp[i - 1][j - 1]) 修改一个字符   即可 达到i 和 j 指向的字符相等
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        return dp[m][n];
    }

    /**
     * 5. 最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s;
        boolean[][] dp = new boolean[len][len];
        // 单个字符默认是回文
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int maxLen = 1;
        int begin = 0;
        // 遍历不同长度的字符串
        for (int l = 2; l <= len; l++) {
            // 遍历左侧位置
            for (int i = 0; i < len; i++) {
                int j = i + l - 1;
                // 越界
                if (j >= len) break;

                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    // 三个字符  或两个字符的
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 516. 最长回文子序列
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        if (len < 2) return len;
        int[][] dp = new int[len][len];
        // 单个字符默认是回文
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            char c = s.charAt(i);
            // 遍历左侧位置
            for (int j = i + 1; j < len; j++) {
                if (c != s.charAt(j)) {
                    // 字符不相等时  取缩小一个字符的字串的回文数量
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                } else {
                    // 2 个字符 0，1  变为 1，0  对应值 默认为0   ，所以不用判断 i j  的长度
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
            }
        }

        return dp[0][len - 1];
    }

    /**
     * 91 分割整数构成字母字符串
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int N = s.length();
        int[] dp = new int[N + 1];
        dp[0] = 1;
        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            if (c != '0') {
                dp[i + 1] += dp[i];
            }
            if (i >= 1 && s.charAt(i - 1) != '0' && ((s.charAt(i - 1) - '0') * 10 + (c - '0') <= 26)) {
                dp[i + 1] += dp[i - 1];
            }

        }
        return dp[N];
    }

    /**
     * 97. 交错字符串
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t) return false;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return dp[n][m];
    }


    /**
     * 1392. 最长快乐前缀
     *
     * @param s
     * @return
     */
    public String longestPrefix(String s) {
        int n = s.length();
        int mod = NumberUtil.MOD;
        long mul = 1, base = 31;
        long prefix = 0, suffix = 0;
        int happy = 0;
        for (int i = 1; i < n; i++) {
            // 必须用long 否则溢出 会导致问题
            prefix = (prefix * base + (s.charAt(i - 1) - 'a')) % mod;
            suffix = (suffix + (s.charAt(n - i) - 'a') * mul) % mod;
            if (prefix == suffix) {
                happy = i;
            }
            mul = mul * base % mod;
        }
        return s.substring(0, happy);
    }

    /**
     * 1653. 使字符串平衡的最少删除次数
     *
     * @param s
     * @return
     */
    public int minimumDeletions(String s) {
        int leftb = 0, righta = 0;
        char[] array = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (array[i] == 'a') righta++;
        }
        int res = righta;
        for (int i = 0; i < s.length(); i++) {
            if (array[i] == 'a') {
                righta--;
            } else {
                leftb++;
            }
            res = Math.min(res, leftb + righta);
        }
        return res;
    }

    /**
     * LCP 69. Hello LeetCode!
     * @param words
     * @return
     */
//    public int Leetcode(String[] words) {
//
//    }
}
