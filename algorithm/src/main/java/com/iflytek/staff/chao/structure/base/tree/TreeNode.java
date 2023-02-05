package com.iflytek.staff.chao.structure.base.tree;


import java.util.*;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /****************************************************************/

    /**
     * 前序层遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;

    }

    private void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);

            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }
        return res;
    }

    /**
     * 中序层遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null && !stack.isEmpty()) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }

        return list;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.addAll(inorderTraversal2(root.left));
        res.add(root.val);
        res.addAll(inorderTraversal2(root.right));
        return res;
    }

    /**
     * 后序层遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.addAll(postorderTraversal(root.left));
        res.addAll(postorderTraversal(root.right));
        res.add(root.val);
        return res;
    }

    /**
     *102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int sz = queue.size();
            List<Integer> line = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();

                line.add(node.val);

                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            ans.add(line);
        }
        return ans;
    }

    /**
     * 107. 二叉树的层序遍历 II   , 自底层到上层
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int sz = queue.size();
            List<Integer> line = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();

                line.add(node.val);

                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            ans.add(0,line);
        }
        return ans;
    }

    /**
     * 637. 二叉树的层平均值
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {

        List<Double> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int sz = queue.size();
            double avg = 0 ;
            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();

                avg +=node.val;

                if(node.left != null){
                    queue.offer(node.left);
                }

                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            ans.add(avg/sz);
        }
        return ans;
    }

    /**
     * 9 树的对称 是否对称 轴对称  二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;

        if (left == null || right == null) return false;

        if( left.val != right.val ) return false ;
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    private boolean isMirror2(TreeNode left1, TreeNode right1) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(left1);
        queue.offer(right1);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) continue;

            if (left == null || right == null || left.val != right.val) return false;

            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }

    /**
     * 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);

        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * 搜索特定值
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode search(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            return search(root.left, val);
        } else {
            return search(root.right, val);
        }
    }

    /**
     * 938. 二叉搜索树的范围和
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        if (root.val > high) return rangeSumBST(root.left, low, high);
        if (root.val < low) return rangeSumBST(root.right, low, high);
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }






    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int N = postorder.length;
        if (N == 0) return null;
        int rootVal = postorder[N - 1];
        TreeNode root = new TreeNode(rootVal);
        int i = 0;
        while (i < N && inorder[i] != rootVal) {
            i++;
        }
        // 0 到 i-1 是左节点的 ,
        if (i > 0) {
            root.left = buildTree(Arrays.copyOfRange(inorder, 0, i), Arrays.copyOfRange(postorder, 0, i));
        }
        if (i < N - 1) {
            root.right = buildTree(Arrays.copyOfRange(inorder, i + 1, N), Arrays.copyOfRange(postorder, i, N - 1));
        }
        return root;
    }

    public TreeNode buildTree(int[] inorder, int inl, int inr, int[] postorder, int pl, int pr) {
        if (pr < pl) return null;

        int rootVal = postorder[pr];
        TreeNode root = new TreeNode(rootVal);
        int i = 0;
        while (inorder[inl + i] != rootVal) {
            i++;
        }
        // 0 到 i-1 是左节点的 ,
        root.left = buildTree(inorder, inl, inl + i - 1, postorder, pl, pl + i - 1);
        root.right = buildTree(inorder, inl + i + 1, inr, postorder, pl + i, pr - 1);
        return root;
    }


    public TreeNode buildTreePre(int[] preorder, int[] inorder) {
        int N = preorder.length;
        return buildTreePre(inorder, 0, N - 1, preorder, 0, N - 1);
    }

    public TreeNode buildTreePre(int[] inorder, int inl, int inr, int[] preOrder, int pl, int pr) {
        if (pl > pr) return null;

        int rootVal = preOrder[pl];
        TreeNode root = new TreeNode(rootVal);
        int i = 0;
        while (inorder[inl + i] != rootVal) {
            i++;
        }
        // 0 到 i-1 是左节点的 ,
        root.left = buildTreePre(inorder, inl, inl + i - 1, preOrder, pl + 1, pl + i);
        root.right = buildTreePre(inorder, inl + i + 1, inr, preOrder, pl + i + 1, pr);
        return root;
    }







}
