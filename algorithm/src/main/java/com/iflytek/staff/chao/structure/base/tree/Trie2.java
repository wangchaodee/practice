package com.iflytek.staff.chao.structure.base.tree;

/**
 * @author : wangchaodee
 * @Description: 前缀树  208. 实现 Trie (前缀树)
 * @date Date : 2023年02月21日 11:04
 */
public class Trie2 {


    boolean word;
    Trie2[]  children;

    public Trie2() {
        word =false;
        children= new Trie2[26];
    }

    public void insert(String word) {
        Trie2 node = this ;
        for (char c : word.toCharArray()) {
            if(node.children[c-'a'] ==null){
                node.children[c-'a'] = new Trie2();;
            }
            node = node.children[c-'a'];
        }
        node.word =true;
    }

    public boolean search(String word) {
        Trie2 node = startsPrefix(word);
        return node != null && node.word;
    }

    public boolean startsWith(String prefix) {
       return startsPrefix(prefix) !=null;
    }

    public Trie2 startsPrefix(String prefix) {
        Trie2 node = this ;
        for (char c : prefix.toCharArray()) {
            if(node.children[c-'a']==null){
               return null;
            }
            node = node.children[c-'a'];
        }
        return  node ;
    }


}
