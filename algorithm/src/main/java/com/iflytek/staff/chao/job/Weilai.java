package com.iflytek.staff.chao.job;

public class Weilai {
    //
    //  1 ,1,2 ,3,

    // TreeNode


//    private class TreeNode {
//        Integer value ;
//        TreeNode left ;
//        TreeNode right ;
//
//    }
//
//    public TreeNode findParent(TreeNode root ,TreeNode A , TreeNode B){
//
//        while(root!=null) {
//            TreeNode node =root;
//            if (findMinParent(node, A, B) && !findMinParent(node.left, A, B) && !findMinParent(node.right, A, B)) {
//                return node;
//            }
//            findParent()
//        }
//    }
//
//
//    private boolean findMinParent(TreeNode node ,TreeNode A , TreeNode B){
//        if(node==null){
//
//        }
//    }


    private Integer fiber(int n) {
        if (n <= 2) {
            return 1;
        }
        return fiber(n - 1) + fiber(n - 2);
    }

    private Integer n_1 = 1;
    private Integer n_2 = 1;

    private Integer fiber2(int n) {
        if (n <= 2) {
            return 1;
        }
        Integer ret = 0;
        for (int i = 3; i <= n; i++) {
            ret = n_1 + n_2;
            n_1 = n_2;
            n_2 = ret;

        }
        return ret;
    }

}
