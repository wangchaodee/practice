package com.iflytek.staff.chao.structure.base.list;

import java.util.*;

/**
 * @author : hamilton
 * @Description: 二叉树相关问题
 * @date Date : 2023年01月09日 14:06
 */
public class TreeNodeSolution {



    /**
     * 判断路径和的值为targetSum  ， 深度优先
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.val == targetSum && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 653. 两数之和 IV - 输入二叉搜索树
     * @param root
     * @param k
     * @return
     */
    Set<Integer> sets = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if(root==null) return false;
        if(sets.contains(k-root.val)) return true ;
        sets.add(root.val);
        return findTarget(root.left,k) || findTarget(root.right,k);
    }

    /**
     * 236. 二叉树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);

        return ans;
    }

    private TreeNode ans;

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;

        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);

        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }

        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    /**
     * 找最近的共同父节点   树中节点值 互不相同
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Map<Integer,TreeNode>  nodeParent = new HashMap<>();
        Set<Integer> pPath = new HashSet<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        nodeParent.put(root.val,null);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();

            if(node.left!=null){
                nodeParent.put(node.left.val ,node);
                queue.offer(node.left);
            }

            if(node.right !=null){
                nodeParent.put(node.right.val,node);
                queue.offer(node.right);
            }
        }

        while (p !=null){
            pPath.add(p.val);
            p= nodeParent.get(p.val);
        }

        while (q!=null){
            if(pPath.contains(q.val)){
                return q ;
            }
            q= nodeParent.get(q.val);
        }
        return root;
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

    public boolean isValidBST2(TreeNode root) {
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

    private TreeNode prev ;
    public boolean isValidBST(TreeNode root) {
        if(root==null) return  true ;

        if(!isValidBST(root.left)){
            return false ;
        }
        if(prev!=null && prev.val >root.val){
            return false;
        }
        prev = root ;

        if(!isValidBST(root.right)){
            return false ;
        }

        return true ;
    }

    /**
     * 合并两个二叉树
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        root1.val = root1.val + root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;

        if (root == null) return false;

        if (root.val != subRoot.val) {
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        } else {
            return isEqual(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
    }


    private boolean isEqual(TreeNode node1, TreeNode node2) {
        if (node1 == null) return node2 == null;
        if (node2 == null) return false;
        return node1.val == node2.val && isEqual(node1.left, node2.left) && isEqual(node1.right, node2.right);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        return root == null ? 0 : dfs(root);
    }

    private int dfs(TreeNode node) {
        int ans = 0;
        if (node.left != null) {
            ans += isLeafNode(node.left) ? node.left.val : dfs(node.left);
        }
        if (node.right != null) {
            ans += isLeafNode(node.right) ? 0 : dfs(node.right);
        }
        return ans;
    }

    private boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root==null) return ans;
        dfsPathSum(root,targetSum,new ArrayDeque<>(), ans);
        return ans;
    }

    private void  dfsPathSum(TreeNode node , int target, Deque<Integer> path , List<List<Integer>> ans){

        if(node.val==target && node.right==null && node.left==null){
            ans.add(new ArrayList<>(path));
            return;
        }

        int rest = target - node.val ;
        path.addLast(node.val);
        if(node.left!=null){
            dfsPathSum(node.left,rest,path,ans);
        }
        if(node.right!=null){
            dfsPathSum(node.right,rest,path,ans);
        }

        path.removeLast();
    }


}
