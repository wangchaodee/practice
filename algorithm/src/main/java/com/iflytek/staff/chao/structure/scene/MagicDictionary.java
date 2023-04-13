package com.iflytek.staff.chao.structure.scene;

import com.iflytek.staff.chao.structure.base.tree.Trie;

/**
 * @author : wangchaodee
 * @Description: 剑指 Offer II 064. 神奇的字典
 */
public class MagicDictionary {

    private Node root ;

    public MagicDictionary() {
        root= new Node();
    }

    public void buildDict(String[] dictionary) {
        for(String dict: dictionary){
            insert(dict);
        }
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

    public boolean search(String searchWord) {
        return dfs(searchWord,root,0,false);
    }

    private boolean dfs(String searchWord , Node node , int pos , boolean modified){
        if(pos == searchWord.length()){
            return modified&& node.word;
        }
        int idx = searchWord.charAt(pos) - 'a' ;
        if(node.children[idx] !=null){
            if(dfs(searchWord,node.children[idx] ,pos+1 , modified)){
                return true ;
            }
        }
        if(!modified){
            for (int i = 0; i < 26; i++) {
                if(i!=idx && node.children[i] !=null){
                    if( dfs(searchWord,node.children[i] ,pos+1 , true)){
                        return true;
                    }
                }
            }
        }
        return false;
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
