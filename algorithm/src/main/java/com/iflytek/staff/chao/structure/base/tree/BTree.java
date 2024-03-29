package com.iflytek.staff.chao.structure.base.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : wangchaodee
 * @Description: 二叉树 节点   模拟B+ 树
 * @date Date : 2022年06月24日 下午3:24
 */


public class BTree {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }


    Node last = null;
    Node nextStart = null;

    public Node connect2(Node root) {
        if (root == null) {
            return root;
        }
        Node start = root;
        while (start != null) {
            last = null;
            nextStart = null;
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) {
                    handNode(p.left);
                }
                if (p.right != null) {
                    handNode(p.right);
                }
            }
            start = nextStart;
        }
        return root;
    }

    private void handNode(Node p) {
        if (last != null) {
            last.next = p;
        }
        if (nextStart == null) {
            nextStart = p;
        }
        last = p;
    }

    public Node connect3(Node root) {
        if (root == null) {
            return root;
        }
        Node start = root;
        Node nextStart = null;
        while (start != null) {

            Node last = null;
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) {
                    handle(p.left, last, nextStart);
                }

                if (p.right != null) {
                    handle(p.right, last, nextStart);
                }
            }
            start = nextStart;
            nextStart = null;
        }

        return root;
    }

    private void handle(Node p, Node last, Node nextStart) {
        if (nextStart == null) {
            nextStart = p;
        }
        if (last != null) {
            last.next = p;
        }
        last = p;
    }

    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表
     * @param root
     * @return
     */
    Node pre , head ;
    public Node treeToDoublyList(Node root) {
        if(root==null) return root ;
        dfs(root) ;
        head.left = pre ;
        pre.right= head ;
        return head ;
    }

    private void dfs(Node cur){
        if(cur==null) return;
        dfs(cur.left);
        if(pre!=null){
            pre.right= cur;
        }else {
            head=cur;
        }
        cur.left= pre ;
        pre= cur ;
        dfs(cur.right);
    }
}

