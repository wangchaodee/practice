package com.iflytek.staff.chao.structure.scene;

import com.iflytek.staff.chao.structure.base.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author : wangchaodee
 * @Description: xxx
 */
public class CBTInserter {

    Queue<TreeNode> bottom ;
    TreeNode root ;

    public CBTInserter(TreeNode root) {
        bottom = new ArrayDeque<>();
        this.root = root ;

        Queue<TreeNode> queue = new ArrayDeque<>() ;
        queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right !=null){
                queue.offer(node.right);
            }

            if(!(node.left !=null && node.right !=null)){
                bottom.offer(node);
            }
        }
    }

    public int insert(int v) {
        TreeNode child = new TreeNode(v);
        TreeNode node = bottom.peek() ;
        int ret = node.val;
        if(node.left ==null){
            node.left =child ;
        }else {
            node.right = child ;
            bottom.poll();
        }
        bottom.offer(child);
        return ret;
    }

    public TreeNode get_root() {
        return root ;
    }

}
