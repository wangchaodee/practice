package com.iflytek.staff.chao.algorithm.base;

/**
 * @author : wangchaodee
 * @Description: 状态压缩
 */
public class DynamicPlanStateCompression {

    /**
     * 1255. 得分最高的单词集合
     */
    public int maxScoreWords(String[] words, char[] letters, int[] score) {

        int[] count = new int[26];
        for (char c : letters) {
            count[c - 'a']++;
        }

        int n = words.length;
        int compaction = 1 << n;
        int res = 0;
        for (int i = 1; i < compaction; i++) {

            int[] wordCount = new int[26];

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 0) {
                    // words[j] 不在集合中
                    continue;
                }

                for (char c : words[j].toCharArray()) {
                    wordCount[c - 'a']++;
                }
            }

            boolean ok = true;

            int sum = 0;
            for (int j = 0; j < 26; j++) {
                if (count[j] < wordCount[j]) {
                    ok = false;
                    break;
                }
                sum += wordCount[j] * score[j];
            }
            if (ok) {
                res = Math.max(res, sum);
            }
        }

        return res;
    }


}
