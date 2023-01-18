package com.iflytek.staff.chao.structure.base.list;

import com.iflytek.staff.chao.structure.base.tree.TreeNode;

/**
 * @author : hamilton
 * @Description: 链表和二叉树的交叉算法问题
 * @date Date : 2023年01月09日 14:01
 */
public class ListAndTreeSolution {

    /**
     * 判断链表是否与二叉树的某一条路径相同  ，运用深度优先算法和递归算法实现判断
     *
     * @param head
     * @param root
     * @return
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        return dfsSubPath(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfsSubPath(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        if (head.val != root.val) return false;
        return dfsSubPath(head.next, root.left) || dfsSubPath(head.next, root.right);
    }
}
