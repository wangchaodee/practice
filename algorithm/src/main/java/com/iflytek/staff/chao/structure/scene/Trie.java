package com.iflytek.staff.chao.structure.scene;

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
}
