package com.iflytek.staff.chao.list;

import java.util.*;

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


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


    /**
     * 是否对称 轴对称  二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;

        if (left != null || right != null) return false;

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

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);

        root.left = left;
        root.right = right;
        return root;
    }

//    private TreeNode invertTree(TreeNode root ,TreeNode left ,TreeNode right){
//        if(right!=null){
//            right=invertTree(right,right.left,right.right);
//        }
//        root.left= right ;
//        if(left != null) {
//            left = invertTree(left,left.left,left.right );
//        }
//        root.right= left ;
//
//        return  root;
//    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.val == targetSum && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }


    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode p = root;
        while (p != null) {
            if (p.val < val) {
                if (p.right == null) {
                    p.right = new TreeNode(val);
                    break;
                } else {
                    p = p.right;
                }
            } else {
                if (p.left == null) {
                    p.left = new TreeNode(val);
                    break;
                } else {
                    p = p.left;
                }
            }
        }
        return root;
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        long pre = Long.MIN_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= pre) {
                return false;
            }
            pre = root.val;
            root = root.right;
        }
        return true;

    }

    private boolean isValidBST(TreeNode root, int low, int high) {
        if (root == null) return true;
        if (root.val < low || root.val > high) {
            return false;
        }
        return isValidBST(root.left, low, root.val) && isValidBST(root.right, root.val, high);
    }


    public boolean findTarget(TreeNode root, int k) {
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        TreeNode left = root;
        TreeNode right = root;
        while (left != null) {
            leftStack.push(left);
            left = left.left;
        }

        while (right != null) {
            rightStack.push(right);
            right = right.right;
        }

        left = getLeft(leftStack);
        right = getRight(rightStack);

        while (left != right) {
            if (left.val + right.val == k) {
                return true;
            }

            if (left.val + right.val < k) {
                left = getLeft(leftStack);
            } else {
                right = getRight(rightStack);
            }
        }
        return false;
    }

    private TreeNode getRight(Stack<TreeNode> rightStack) {
        TreeNode root = rightStack.pop();
        TreeNode node = root.left;
        while (node != null) {
            rightStack.push(node);
            node = node.right;
        }
        return root;
    }

    private TreeNode getLeft(Stack<TreeNode> leftStack) {
        TreeNode root = leftStack.pop();
        TreeNode node = root.right;
        while (node != null) {
            leftStack.push(node);
            node = node.left;
        }
        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pStack = getPath(root, p);
        List<TreeNode> qStack = getPath(root, q);

        TreeNode parent = root;
        for (TreeNode node : pStack) {
            if (!qStack.contains(node)) {
                break;
            }
            parent = node;
        }

        return parent;

    }

    private List<TreeNode> getPath(TreeNode root, TreeNode p) {
        List<TreeNode> stack = new ArrayList<>();
        TreeNode node = root;
        while (node != p && node != null) {
            stack.add(node);
            if (node.val < p.val) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        stack.add(p);
        return stack;
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        if (root.val > high) return rangeSumBST(root.left, low, high);
        if (root.val < low) return rangeSumBST(root.right, low, high);
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }


    /**
     * 错误写法
     *
     * @param root
     * @return
     */
//    public static boolean isValidBST(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        //左节点小于父节点,右节点大于父节点
//        if (root.left != null && root.left.val >= root.val || root.right != null && root.right.val <= root.val ) {
//                return false;
//        }
//
//        return isValidBST(root.left) && isValidBST(root.right);
//
//    }


    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }

        if (min >= root.val || max <= root.val) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }


    /**
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
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

//    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
//        if (root1 != null && root2 != null) {
//            return new TreeNode(
//                    root1.val + root2.val, mergeTrees(root1.left, root2.left), mergeTrees(root1.right, root2.right)
//            );
//        } else if (root1 != null && root2 == null) {
//            return root1;
//        } else if (root1 == null && root2 != null) {
//            return root2;
//        } else {
//            return null;
//        }
//
//    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        if (root1 != null) {
            return root2;
        }
        if (root2 != null) {
            return root1;
        }

        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = mergeTrees(root1.left, root2.left);
        merged.right = mergeTrees(root1.right, root2.right);

        return merged;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int n = nums.length ;
        if(n==0) return null;
        return  sortedArrayToBST(nums , 0 ,n-1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int l, int r) {
        if(l>r) return null;
        int mid = (r-l)/2 +l ;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums,l,mid-1);
        root.right= sortedArrayToBST(nums,mid+1,r);
        return root;
    }

}
