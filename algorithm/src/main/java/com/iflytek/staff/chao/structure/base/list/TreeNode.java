package com.iflytek.staff.chao.structure.base.list;

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

    /**
     * 前序层遍历
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
     * 是否对称 轴对称  二叉树
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

        return left.val == right.val && isMirror(left.left, right.right) && isMirror(left.right, right.left);
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


    /**
     * 判断是否平衡树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 计算tree 最小 深度  ，递归方式  深度遍历
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(minDepth(root.left), min);
        }
        if (root.right != null) {
            min = Math.min(minDepth(root.right), min);
        }
        return min + 1;
    }

    /**
     * 计算tree深度  ，递归方式  深度遍历
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 计算tree深度  ，层级遍历
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }

        return res;
    }

    /**
     * 判断是否平衡树
     *
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root) {
        return recur(root) != -1;
    }

    /**
     * 后序遍历  剪枝
     *
     * @param node
     * @return
     */
    private int recur(TreeNode node) {
        if (node == null) return 0;
        int left = recur(node.left);
        if (left == -1) return -1;
        int right = recur(node.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
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


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "#";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    sb.append("#,");
                    continue;
                }
                sb.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("#".equals(data)) return null;
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < values.length; i++) {
            TreeNode parent = queue.poll();
            if (!"#".equals(values[i])) {
                TreeNode left = new TreeNode(Integer.valueOf(values[i]));
                parent.left = left;
                queue.add(left);
            }
            if (!"#".equals(values[++i])) {
                TreeNode right = new TreeNode(Integer.valueOf(values[i]));
                parent.right = right;
                queue.add(right);
            }
        }
        return root;
    }

    public int countNodes(TreeNode root) {
        if(root==null) return 0 ;
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left+right +1 ;
    }

    public int countNodes2(TreeNode root) {
        if(root==null) return 0 ;

        TreeNode left = root ;
        int h=0 ;
        while (left.left!=null){
            left=left.left;
            h++;
        }

        int l = 1<<h , r= (1<<(h+1) )-1  ,mid =0;
        while (l<r){
            // 取整时会向下， +1 保证可以向上
            mid = (r+l+1) >> 1 ;
            if(nodeExist(root ,h, mid)){
                l=mid;
            }else {
                r= mid-1 ;
            }
        }
        return  l ;
    }

    private  boolean nodeExist(TreeNode root ,int h, int idx ){
        int bits = 1 << (h-1) ;
        TreeNode node =root;
        while (node!=null && bits>0){
            if((bits&idx)==0){
                node= node.left;
            }else {
                node=node.right;
            }
            bits >>=1 ;
        }
        return node !=null;
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        int l = 0 , r = nums.length-1 ;
        return buildTreeNode(nums,0,r);
    }

    private TreeNode buildTreeNode(int[] nums , int l , int r){
         if(l>r) return null;
         int mid = ( r+l ) /2 ;
         TreeNode node = new TreeNode(nums[mid]);
         node.left = buildTreeNode(nums, l, mid - 1);
         node.right= buildTreeNode(nums,mid+1,r);
         return  node ;
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>>  ans = new ArrayList<>();
        if(root==null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean toRight =true;
        while (!queue.isEmpty()){
            int sz = queue.size();
            List<Integer> line = new ArrayList<>();
            for (int i = 0; i <sz ; i++) {
                TreeNode node = queue.poll();
                if(toRight){
                    line.add(node.val);
                }else {
                    line.add(0, node.val);
                }
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }

            }
            ans.add(line);
            toRight = !toRight;
        }
        return ans ;
    }


    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> ans = new ArrayList<>();
        if(root==null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int sz = queue.size();

            for (int i = 0; i <sz ; i++) {
                TreeNode node = queue.poll();
                if(i==sz-1){
                    ans.add(node.val);
                }

                if(node.left!=null){
                    queue.add(node.left);
                }

                if(node.right!=null){
                    queue.add(node.right);
                }
            }

        }
        return ans ;
    }



    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return  null ;

        if(key > root.val) {
            root.right = deleteNode(root.right, key) ;
        }

        if(key< root.val){
            root.left = deleteNode(root.left ,key);
        }

        if(key == root.val){
            if(root.left ==null){
                return root.right;
            }
            if(root.right ==null){
                return root.left;
            }

            TreeNode node = root.right;
            while (node.left!=null){
                node = node.left;
            }

            root.right= deleteNode(root.right, node.val);
            node.left = root.left;
            node.right = root.right;
            return node;
        }

        return  root;
    }


    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();

        while (root!=null || !stack.isEmpty()){
            while (root !=null){
                stack.push(root);
                root =root.left;
            }
            root = stack.pop();
            k--;
            if(k==0){
                return root.val;
            }
            root= root.right;
        }
        return root.val;
    }

    /**
     * 找最大层 的最小值
     * @param root
     * @return
     */
    public int maxLevelSum(TreeNode root) {
        int ans =1 , max =root.val;

        Queue<TreeNode> queue = new LinkedList<>() ;
        queue.offer(root);
        int level=0;
        while (!queue.isEmpty()){

            level++;
            int sz = queue.size();
            int cur = 0 ;

            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();
                cur +=node.val ;
                if(node.left !=null){
                    queue.offer(node.left);
                }
                if(node.right !=null){
                    queue.offer(node.right);
                }
            }

            if(cur>max){
                ans = level;
                max =cur;
            }
        }
        return ans;
    }



}
