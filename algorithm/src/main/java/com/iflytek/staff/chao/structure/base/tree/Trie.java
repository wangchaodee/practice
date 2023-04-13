package com.iflytek.staff.chao.structure.base.tree;

import java.util.List;

/**
 * @author : wangchaodee
 * @Description: 前缀树  208. 实现 Trie (前缀树)
 * @date Date : 2023年02月21日 11:04
 */
public class Trie {

    private Node root ;

    public Trie() {
        root= new Node();
    }

    public void insert(String word) {
        Node node = root ;
        for (char c : word.toCharArray()) {
            if(node.children[c-'a'] ==null){
                node.children[c-'a'] = new Node();;
            }
            node = node.children[c-'a'];
        }
        node.word =true;
    }

    public boolean search(String word) {
        Node node = startsPrefix(word);
        return node != null && node.word;
    }

    public boolean startsWith(String prefix) {
       return startsPrefix(prefix) !=null;
    }

    public Node startsPrefix(String prefix) {
        Node node = root ;
        for (char c : prefix.toCharArray()) {
            if(node.children[c-'a']==null){
               return null;
            }
            node = node.children[c-'a'];
        }
        return  node ;
    }

    class Node{
        boolean word;
        Node[]  children;

        public Node() {
            word =false;
            children= new Node[26];
        }
    }


    /**
     * 剑指 Offer II 063. 替换单词
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        Node trie = new Node() ;
        for(String dict : dictionary){
            Node cur = trie ;
            for (int i = 0; i < dict.length(); i++) {
                char c = dict.charAt(i);
                if(cur.children[c-'a'] ==null){
                    cur.children[c-'a'] = new Node();;
                }
                cur = cur.children[c-'a'];
            }
            cur.word = true ;
        }

        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = findPrefix(words[i] , trie);
        }
        return String.join(" " , words);
    }

    private String findPrefix(String word , Node trie){
        StringBuffer str = new StringBuffer() ;
        Node cur = trie ;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.word){
                return str.toString() ;
            }
            if(cur.children[c-'a']==null){
                return word;
            }
            str.append(c);
            cur = cur.children[c-'a'];
        }
        return str.toString();
    }
}
