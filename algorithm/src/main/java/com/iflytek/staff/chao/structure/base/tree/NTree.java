package com.iflytek.staff.chao.structure.base.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : hamilton
 * @Description: N个节点的树
 * @date Date : 2023年02月05日 14:39
 */
public class NTree {
    public int val;
    public List<NTree> children;

    public NTree() {}

    public NTree(int _val) {
        val = _val;
    }

    public NTree(int _val, List<NTree> _children) {
        val = _val;
        children = _children;
    }

 /*************************************************************************/


 class Node extends NTree{
     public List<Node> children;

     /**
      * 102. 二叉树的层序遍历
      * @param root
      * @return
      */
     public List<Integer> preorder(Node root) {
         List<Integer> ans = new ArrayList<>() ;
         if(root == null) return ans ;
         ans.add(root.val);
         for (Node child : root.children) {
             ans.addAll(preorder(child));
         }
         return ans ;
     }

     /**
      * 590. N 叉树的后序遍历
      * @param root
      * @return
      */
     public List<Integer> postorder(Node root) {
         List<Integer> ans = new ArrayList<>() ;
         if(root == null) return ans ;

         for (Node child : root.children) {
             ans.addAll(preorder(child));
         }
         ans.add(root.val);
         return ans ;
     }

     /**
      * 429. N 叉树的层序遍历
      * @param root
      * @return
      */
     public List<List<Integer>> levelOrder(Node root) {
         List<List<Integer>> ans = new ArrayList<>();
         if (root == null) return ans;
         Queue<Node> queue = new LinkedList<>();
         queue.offer(root);
         while (!queue.isEmpty()) {
             int sz = queue.size();
             List<Integer> line = new ArrayList<>();
             for (int i = 0; i < sz; i++) {
                 Node node = queue.poll();

                 line.add(node.val);

                 for (Node child : node.children) {
                     queue.offer(child);
                 }
             }
             ans.add(line);
         }
         return ans;
     }
 }

}

