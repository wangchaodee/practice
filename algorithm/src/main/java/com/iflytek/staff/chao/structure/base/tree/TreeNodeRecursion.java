package com.iflytek.staff.chao.structure.base.tree;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 二叉树相关问题 递归类型  层次遍历  迭代方式遍历
 * @date Date : 2023年01月09日 14:06
 */
public class TreeNodeRecursion {

    /**
     * 111 计算tree 最小 深度  ，递归方式  深度遍历
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = minDepth(root.left);
        int right = minDepth(root.right);
        // 子树存在为空时 需要取不为空的子树深度 再加根节点
        if (left == 0 || right == 0) return left + right + 1;

        return Math.min(left, right) + 1;
    }

    /**
     * 104. Maximum Depth of Binary Tree (Easy)
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
     * 110 判断是否平衡树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    boolean result = true;

    public boolean isBalanced2(TreeNode root) {
        maxDepth(root);
        return result;
    }

    public int maxDepth_3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth_3(root.left);
        int right = maxDepth_3(root.right);
        if (Math.abs(left - right) > 1) result = false;
        return Math.max(left, right) + 1;
    }

    /**
     * 判断是否平衡树
     *
     * @param root
     * @return
     */
    public boolean isBalanced3(TreeNode root) {
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
     * 112 判断路径和的值为targetSum  ， 深度优先
     *
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
     * 113  113. 路径总和 II
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        dfsPathSum(root, targetSum, new ArrayDeque<>(), ans);
        return ans;
    }

    private void dfsPathSum(TreeNode node, int target, Deque<Integer> path, List<List<Integer>> ans) {
        path.addLast(node.val);
        if (node.val == target && node.right == null && node.left == null) {
            ans.add(new ArrayList<>(path));
            //return;
        }

        int rest = target - node.val;
        if (node.left != null) {
            dfsPathSum(node.left, rest, path, ans);
        }
        if (node.right != null) {
            dfsPathSum(node.right, rest, path, ans);
        }

        path.removeLast();
    }

    /**
     * 437
     * 路径III 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum3(TreeNode root, int targetSum) {
        if (root == null) return 0;
        return dfsPathSumWithRoot(root, targetSum) + pathSum3(root.left, targetSum) + pathSum3(root.right, targetSum);
    }

    // 减法时 int target  会导致溢出 ，改为long , 适配节点值为大值的情况
    // [1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000]   target = 0 ;
    private int dfsPathSumWithRoot(TreeNode root, long target) {
        if (root == null) return 0;
        int ret = 0;
        if (root.val == target) {
            ret++;
        }

        ret += dfsPathSumWithRoot(root.left, target - root.val) + dfsPathSumWithRoot(root.right, target - root.val);

        return ret;
    }

    /**
     * 653. 两数之和 IV - 输入二叉搜索树
     *
     * @param root
     * @param k
     * @return
     */
    Set<Integer> sets = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        if (sets.contains(k - root.val)) return true;
        sets.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    /**
     * 235 235. 二叉搜索树的最近公共祖先
     * 剑指 Offer 68 - II. 二叉树的最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) return lowestCommonAncestor(root.right, p, q);
        if (root.val > p.val && root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        return root;
    }

    /**
     * 236. 二叉树的最近公共祖先
     * 找最近的共同父节点   树中节点值 互不相同
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_236(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return (left == null) ? right : (right == null) ? left : root;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Map<Integer, TreeNode> nodeParent = new HashMap<>();
        Set<Integer> pPath = new HashSet<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        nodeParent.put(root.val, null);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                nodeParent.put(node.left.val, node);
                queue.offer(node.left);
            }

            if (node.right != null) {
                nodeParent.put(node.right.val, node);
                queue.offer(node.right);
            }
        }

        while (p != null) {
            pPath.add(p.val);
            p = nodeParent.get(p.val);
        }

        while (q != null) {
            if (pPath.contains(q.val)) {
                return q;
            }
            q = nodeParent.get(q.val);
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

    /**
     * 98. 验证二叉搜索树
     *
     * @param root
     * @return
     */
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

    private boolean isValidBST(TreeNode root, long low, long high) {
        if (root == null) return true;
        if (root.val <= low || root.val >= high) {
            return false;
        }
        return isValidBST(root.left, low, root.val) && isValidBST(root.right, root.val, high);
    }

    private TreeNode prev;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        if (!isValidBST(root.left)) {
            return false;
        }
        if (prev != null && prev.val > root.val) {
            return false;
        }
        prev = root;

        if (!isValidBST(root.right)) {
            return false;
        }

        return true;
    }

    /**
     * 617 合并两个二叉树
     *
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

    /***
     *  572 是否为子树
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return isEqual(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isEqual(TreeNode node1, TreeNode node2) {
        if (node1 == null) return node2 == null;
        if (node2 == null) return false;
        return node1.val == node2.val && isEqual(node1.left, node2.left) && isEqual(node1.right, node2.right);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (isLeafNode(root.left)) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    private boolean isLeafNode(TreeNode node) {
        if (node == null) return false;
        return node.left == null && node.right == null;
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
        if (root == null) return 0;
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
    }

    public int countNodes2(TreeNode root) {
        if (root == null) return 0;

        TreeNode left = root;
        int h = 0;
        while (left.left != null) {
            left = left.left;
            h++;
        }

        int l = 1 << h, r = (1 << (h + 1)) - 1, mid = 0;
        while (l < r) {
            // 取整时会向下， +1 保证可以向上
            mid = (r + l + 1) >> 1;
            if (nodeExist(root, h, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean nodeExist(TreeNode root, int h, int idx) {
        int bits = 1 << (h - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & idx) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

    /**
     * 剑指 Offer 32 - III. 从上到下打印二叉树 III
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean toRight = true;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            List<Integer> line = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();
                if (toRight) {
                    line.add(node.val);
                } else {
                    line.add(0, node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

            }
            ans.add(line);
            toRight = !toRight;
        }
        return ans;
    }

    /**
     * 199. 二叉树的右视图
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int sz = queue.size();

            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();
                if (i == sz - 1) {
                    ans.add(node.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }

        }
        return ans;
    }


    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }

        if (key == root.val) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            TreeNode node = root.right;
            while (node.left != null) {
                node = node.left;
            }

            root.right = deleteNode(root.right, node.val);
            node.left = root.left;
            node.right = root.right;
            return node;
        }

        return root;
    }


    /**
     * 找最大层 的最小值
     *
     * @param root
     * @return
     */
    public int maxLevelSum(TreeNode root) {
        int ans = 1, max = root.val;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {

            level++;
            int sz = queue.size();
            int cur = 0;

            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();
                cur += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (cur > max) {
                ans = level;
                max = cur;
            }
        }
        return ans;
    }

    /**
     * 543. 二叉树的直径  两个节点间的最大路径值
     *
     * @param root
     * @return
     */
    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }

    /**
     * 12. 相同节点值的最大路径长度
     *
     * @param root
     * @return
     */
    int path = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfsPath(root);
        return path;
    }

    private int dfsPath(TreeNode root) {
        if (root == null) return 0;
        int left = dfsPath(root.left);
        int right = dfsPath(root.right);

        int leftPath = root.left != null && root.val == root.left.val ? left + 1 : 0;
        int rightPath = root.right != null && root.val == root.right.val ? right + 1 : 0;
        // 以root 为桥接的路径 是root 下的最大路径
        path = Math.max(path, leftPath + rightPath);
        // 返回上层 是给出单侧最长链  ，然后可以与外侧的单侧链组合
        return Math.max(leftPath, rightPath);
    }

    /**
     * 671. 找出二叉树中第二小的节点
     *
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        if (root.left == null && root.right == null) return -1;
        int leftVal = root.left.val;
        int rightVal = root.right.val;
        if (leftVal == root.val) leftVal = findSecondMinimumValue(root.left);
        if (rightVal == root.val) rightVal = findSecondMinimumValue(root.right);
        if (leftVal != -1 && rightVal != -1) return Math.min(leftVal, rightVal);
        if (leftVal != -1) return leftVal;
        return rightVal;
    }

    /**
     * 513. 二叉树 找左下角的值
     *
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) queue.add(root.right);
            if (root.left != null) queue.add(root.left);
        }
        return root.val;
    }

    /**
     * 剑指 Offer 26. 树的子结构
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) return false;
        if (isEqual(A, B)) return true;

        if (A.left != null && isSubStructure(A.left, B)) return true;

        if (A.right != null && isSubStructure(A.right, B)) return true;

        return false;
    }

    /**
     * 剑指 Offer 27. 二叉树的镜像
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if(root==null) return root;
        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);
        root.left = right;
        root.right = left ;
        return  root;
    }

    /**
     * 968. 监控二叉树
     * @return
     */
    public int minCameraCover(TreeNode root) {
        int[] array = dfs(root);
        return array[1] ;
    }

    // 0 根节点放摄像机  1 不确定是否放根  2 根节点不放
    private int[] dfs(TreeNode root) {
        if(root==null) return new int[]{Integer.MAX_VALUE/2,0,0};
        int[] left = dfs(root.left);
        int[] right= dfs(root.right);
        int[] array = new int[3];
        array[0] = left[2] + right[2] +1 ;
        array[1] =Math.min(array[0] , Math.min( left[0] + right[1] , left[1] + right[0]));
        array[2] = Math.min(array[0] , left[1] + right[1]);
        return array;
    }

    /**
     * 100. 相同的树
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null) return q==null;
        if(q==null) return false;

        if(p.val !=q.val) return false;

        return isSameTree(p.left ,q.left) && isSameTree(p.right,q.right);
    }

    /**
     * 剑指 Offer 54. 二叉搜索树的第k大节点
     * @param root
     * @param k
     * @return
     */
    int val , k ;
    public int kthLargest(TreeNode root, int k) {
      this.k = k ;
      dfsKth(root);
      return val;
    }

    private void dfsKth(TreeNode cur){
        if(cur==null) return;
        dfsKth(cur.right);
        if(k==0) return;
        k--;
        val= cur.val ;
        dfsKth(cur.left);
    }


    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int parent =Integer.MAX_VALUE ;
        for (int i = postorder.length-1 ;i>=0 ;i--){
            int v = postorder[i];

            while (!stack.isEmpty() && stack.peek() >v){
                parent = stack.pop();
            }

            if(v > parent) return false ;

            stack.add(v);
        }
        return true ;
    }

    /**
     *  剑指 Offer II 044. 二叉树每层的最大值
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>() ;
        if(root == null) return ans ;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root) ;
        while (!queue.isEmpty()){
            int size = queue.size() ;
            int max = Integer.MIN_VALUE ;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll() ;
                if(max< node.val){
                    max = node.val;
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            ans.add(max);
        }
        return ans ;
    }

    /**
     * 剑指 Offer II 045. 二叉树最底层最左边的值
     * @param root
     * @return
     */
    public int findBottomLeftValue_2(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root) ;
        int left = Integer.MIN_VALUE ;
        while (!queue.isEmpty()){
            int size = queue.size() ;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll() ;
                if(i==0){
                    left = node.val;
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
        }
        return left;
    }

    /**
     * 剑指 Offer II 047. 二叉树剪枝
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        if(root == null) return null ;
        root.left = pruneTree(root.left) ;
        root.right = pruneTree(root.right) ;
        if(root.left == null && root.right == null && root.val ==0) {
            return null ;
        }
        return root ;
    }

    /**
     * 剑指 Offer II 050. 向下的路径节点之和
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return  dfsSum(root, 0);
    }

    private int dfsSum(TreeNode root, int preSum) {
        if( root == null ) return  0 ;
        int sum = preSum *10 + root.val ;
        if(root.left == null && root.right == null){
            return sum ;
        }else {
            return dfsSum(root.left , sum) + dfsSum(root.right , sum) ;
        }
    }

    /**
     * 剑指 Offer II 051. 节点之和最大的路径
     * @param root
     * @return
     */
    int maxSum = Integer.MIN_VALUE ;
    public int maxPathSum(TreeNode root) {
        maxGain(root) ;
        return maxSum ;
    }

    private int maxGain(TreeNode node){
        if(node == null) return  0 ;

        int leftGain = Math.max(maxGain(node.left ),0) ;
        int rightGain = Math.max(maxGain(node.right),0);
        int pathNum = leftGain + node.val + rightGain ;
        maxSum = Math.max(pathNum,maxSum);
        return node.val + Math.max(leftGain,rightGain);
    }





}
