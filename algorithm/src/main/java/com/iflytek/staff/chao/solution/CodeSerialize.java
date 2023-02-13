package com.iflytek.staff.chao.solution;

import com.iflytek.staff.chao.structure.base.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

/**
 * @author : wangchaodee
 * @Description: 二叉树序列化与反序列化
 * @date Date : 2022年07月20日 11:20
 */
public class CodeSerialize {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder ans = new StringBuilder();
        serialize(root, ans);
        return ans.toString();
    }

    public void serialize(TreeNode root, StringBuilder str) {
        if (root == null) {
            str.append("#,");
        } else {
            str.append(root.val).append(",");
            serialize(root.left, str);
            serialize(root.right, str);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] datas = data.split(",");
        List<String> list = new ArrayList<>(Arrays.asList(datas));
        return deserialize(list);
    }

    private TreeNode deserialize(List<String> list) {
        if (list.get(0).equals("#")) {
            list.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0);
        root.left = deserialize(list);
        root.right = deserialize(list);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
