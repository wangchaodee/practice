package com.iflytek.staff.chao.list;

import jdk.internal.org.objectweb.asm.tree.InnerClassNode;

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

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();


        return list;
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

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        if (root.val > high) return rangeSumBST(root.left, low, high);
        if (root.val < low) return rangeSumBST(root.right, low, high);
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
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

    public boolean isSubPath(ListNode head, TreeNode root) {
        return dfsSubPath(head,root) || isSubPath(head,root.left) || isSubPath(head,root.right);
    }

    private boolean dfsSubPath(ListNode head, TreeNode root){
        if(head==null) return true;
        if(root==null) return false ;
        if(head.val != root.val) return false;
        return dfsSubPath(head.next, root.left) || dfsSubPath(head.next,root.right);
    }

}
