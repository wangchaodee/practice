package com.iflytek.staff.chao.structure.base.list;

import java.util.*;

/**
 * @author : wangchaodee
 * @Description: 单链表涉及题解
 * @date Date : 2023年01月09日 13:39
 */
public class ListNodeSolution {

    /**
     * 找出两个链表的交点
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> setA = new HashSet<>();
        while (headA != null) {
            setA.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (setA.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;
        while (l1 != l2) {
            l1 = (l1 == null) ? headB : l1.next;
            l2 = (l2 == null) ? headA : l2.next;
        }
        return l1;
    }

    /**
     * 剑指 Offer 24. 反转链表
     * 翻转链表   递归   或者采用头插法
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = new ListNode();
        while (head != null) {
            ListNode temp = head.next;
            //头插法
            head.next = prev.next;
            prev.next = head;
            head = temp;
        }
        return prev.next;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = reverseList2(head.next);
        head.next = null;
        next.next = head;
        return newHead;
    }

    /**
     * 归并两个有序的链表
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode prev = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }
            prev = prev.next;
        }
        prev.next = (list1 == null ? list2 : list1);
        return head.next;
    }

    /**
     * 从有序链表中删除重复节点
     * 删除重复的 ， 重复的节点保留一个
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = head;
        while (head != null && head.next != null) {
            ListNode tmp = head.next.next;
            if (head.val == head.next.val) {
                head.next = tmp;
            } else {
                head = head.next;
            }
        }
        return prev;
    }

    /**
     * 删除重复的  不保留
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {

        if (head == null) return head;
        ListNode res = new ListNode(-1, head);
        int deleteVal;
        ListNode pre = res;

        while (pre.next != null && pre.next.next != null) {
            if (pre.next.val == pre.next.next.val) {
                deleteVal = pre.next.val;
                while (pre.next != null && pre.next.val == deleteVal) {
                    pre.next = pre.next.next;
                }
            } else {
                pre = pre.next;
            }
        }

        return res.next;
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * 删除链表的倒数第 n 个节点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        int s = 0;
        while (s < n) {
            first = first.next;
            s++;
        }

        ListNode ret = new ListNode(0, head);
        ListNode second = ret;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // 间隔连接  断链
        ListNode temp = second.next;
        second.next = temp.next;
        temp.next=null;

        return ret.next;
    }

    /**
     * 查找链表的中间节点  双指针同时迭代，双步指针指向尾节点时 返回单步节点即可
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode oneStep = head;
        ListNode twoStep = head;

        while (twoStep != null) {
            twoStep = twoStep.next;
            if (twoStep != null) {
                oneStep = oneStep.next;
                twoStep = twoStep.next;
            }
        }
        return oneStep;
    }

    public ListNode reverseKGroup(ListNode head, int k) {


        ListNode dummy = new ListNode(-1, head);
        ListNode connect = dummy;

        ListNode pre = dummy.next;
        ListNode revse = pre;

        while (revse != null) {

            int count = 1;
            while (revse != null && count < k) {
                count++;
                revse = revse.next;
            }

            if (revse != null) {
                ListNode next = revse.next;
                revse.next = null;
                connect.next = reverseList(pre);
                connect = pre;
                connect.next = next;
                pre = next;
                revse = pre;
            }
        }
        return dummy.next;
    }

    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     * @param head
     * @param k  本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        int count = 0; // 为0  而不是1
        ListNode revse = head;
        while (revse != null && count < k) {
            count++;
            revse = revse.next;
        }

        while (revse != null ) {
            head = head.next;
            revse = revse.next;
        }
        return head;
    }

    /**
     * 141 判断链表是否存在环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        while (head != null) {
            if (seen.contains(head)) {
                return true;
            }
            seen.add(head);
            head = head.next;
        }
        return false;
    }

    //142   a+(n+1)b+nc=2(a+b)⟹a=c+(n−1)(b+c)
    public ListNode detectCycle(ListNode head) {

        ListNode oneStep = head;
        ListNode twoStep = head;

        while (twoStep != null && twoStep.next != null ) {
            twoStep = twoStep.next.next;
            oneStep = oneStep.next;
            if(twoStep == oneStep) break;
        }
        if(twoStep == null || twoStep.next ==null) return null ;
        twoStep =head ;
        while (twoStep !=oneStep){
            twoStep = twoStep.next;
            oneStep = oneStep.next;
        }
        return oneStep;
    }


    public ListNode removeElements(ListNode head, int val) {
        ListNode ans = new ListNode(-1, head);
        ListNode prev = ans;
        while (head != null) {
            if (head.val == val) {
                ListNode next = head.next;
                prev.next = next;
                head = head.next;
                continue;
            }
            head = head.next;
            prev = prev.next;
        }
        return ans.next;
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        int cnt = 0;
        while (head != null) {
            cnt++;
            stack.add(head.val);
            head = head.next;
        }
        int[] ret = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            ret[i] = stack.pop();
        }
        return ret;
    }

    /**
     * 删除链表中指定的节点， 由于不知前置节点， 用替代法完成删除
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /**
     * 剑指 Offer 18. 删除链表的节点
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(0,head);
        ListNode cur = dummy;
        while(cur.next !=null){
            if(cur.next.val==val){
                cur.next = cur.next.next;
                break;
            }
            cur=cur.next;
        }
        return dummy.next;
    }

    /**
     * 234 判断是否为回文形式的链表
     * 另一种用快慢双指针 切分两个半组链表  翻转后半链表 进行比较是否相等
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        Stack<ListNode> stack = new Stack<>();

        ListNode right = head;
        while (right != null) {
            stack.push(right);
            right = right.next;
        }

        right = stack.pop();
        while (head != right) {
            if (head.val != right.val) {
                return false;
            }
            head = head.next;
            if(head==right) break;
            right = stack.pop();
        }
        return true;
    }


    public ListNode[] splitListToParts(ListNode head, int k) {
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            ++count;
            cur = cur.next;
        }

        int avg = count / k;
        int add1 = count % k;

        ListNode[] ret = new ListNode[k];
        cur = head;
        for (int i = 0; i < k && cur != null; i++) {
            ret[i] = cur;

            int size = avg + ((i < add1) ? 1 : 0);

            for (int j = 1; j < size; j++) {
                cur = cur.next;
            }

            ListNode temp = cur.next;
            cur.next = null;
            cur = temp;
        }

        return ret;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);

        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode lNode = pre.next;

        ListNode rNode = pre;
        for (int i = 0; i < right - left + 1; i++) {
            rNode = rNode.next;
        }

        ListNode succ = rNode.next;

        pre.next = null;
        rNode.next = null;

        ListNode reverse = reverseList(lNode);
        lNode.next = succ;
        pre.next = rNode;

        return dummy.next;

    }


    public int getDecimalValue(ListNode head) {
        int sum = 0;
        while (head != null) {
            sum = sum * 2 + head.val;
            head = head.next;
        }
        return sum;
    }

    /**
     * 2. 两数相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode  pre =new ListNode();
        ListNode  ans =pre ;
        int add =0 ;
        while (l1 != null || l2 != null || add != 0) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;

            int v = v1 + v2 + add;
            add = v / 10;
            pre.next = new ListNode(v % 10);
            pre = pre.next;
            l1 = l1 != null ? l1.next :null;
            l2 = l2 != null ? l2.next :null;
        }

        return  ans.next;
    }

    /**
     * 445. 两数相加 II
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode ans = addTwoNumbers(l1,l2);
        return reverseList(ans);
    }


    /**
     * 交换链表中的相邻结点
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode ans = new ListNode(-1, head);
        ListNode pre = ans;
        while (pre.next != null && pre.next.next != null) {

            ListNode next = pre.next;
            ListNode nextNext = pre.next.next;

            next.next = nextNext.next;
            nextNext.next = next;
            pre.next = nextNext;
            pre = next;

        }
        return ans.next;
    }

    /**
     * 143. 重排链表
     * @param head
     */
    public void reorderList(ListNode head) {

        List<ListNode> listNodes = new ArrayList<>();
        while (head != null) {
            listNodes.add(head);
            head = head.next;
        }

        int l = 0, r = listNodes.size() - 1;
        while (l < r) {
            listNodes.get(l).next = listNodes.get(r);
            l++;
            if (l == r) break;
            listNodes.get(r).next = listNodes.get(l);
            r--;
        }
        listNodes.get(l).next = null;
    }

    public void reorderList2(ListNode head) {
         ListNode firstPart = head ;
         ListNode middle = middleNode(firstPart);
         ListNode reverse = reverseList(middle.next);
         middle.next = null ;
         while (reverse !=null ){
             ListNode temp = reverse.next ;
             reverse.next = head.next ;
             head.next = reverse ;
             head = reverse.next ;
             reverse = temp ;
         }
    }

    public int[][] spiralMatrix(int m, int n, ListNode head) {

        int[][] grid = new int[m][n];
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        matrixFill(grid, -1);

        int total = m * n;
        int i = 0, j = 0;
        int di = 0;
        while (head != null) {
            grid[i][j] = head.val;
            head = head.next;

            int ti = i + direction[di][0];
            int tj = j + direction[di][1];
            if (ti < 0 || ti >= m || tj < 0 || tj >= n || grid[ti][tj] != -1) {
                di = (di + 1) % 4;
            }
            i += direction[di][0];
            j += direction[di][1];
        }
        return grid;
    }

    private void matrixFill(int[][] grid, int val) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = val;
            }
        }
    }

    /**
     * 奇偶链表
     * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return head;

        ListNode odd = head;
        ListNode evenHead = head.next;
        ListNode even = evenHead;

        while (even != null && even.next != null) {
            // 后一个奇数节点  ，可能为null
            ListNode tmp = even.next;
            // 修改next指针 为间隔的节点
            even.next = tmp.next;
            odd.next = tmp;
            //后移指针
            odd = tmp;
            even = tmp.next;
        }
        // 链接两个链表
        odd.next = evenHead;

        return head;
    }

    /**
     * 61. 旋转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(k==0 || head == null || head.next == null){
            return head ;
        }
        int n =1;
        ListNode cur= head;

        // 计算个数
        while(cur.next !=null){
            cur = cur.next;
            n++ ;
        }
        // 连接成环
        cur.next = head ;

        int step = n- k%n ;
        while(step-- >0){
            cur =cur.next;
        }
        // 断链
        ListNode ret = cur.next;
        cur.next=null;
        return ret;
    }

    /**
     * 147. 对链表进行插入排序
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if(head==null || head.next==null) return head ;
        ListNode ret = new ListNode(-1,head);
        ListNode last = ret.next;
        ListNode cur = head.next;
        while (cur!=null){
            if(last.val <= cur.val){
                last = cur;
            }else {

                //比较
                ListNode pre = ret;
                while (pre.next.val <= cur.val) {
                    pre = pre.next;
                }
                // 插入
                last.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            // 更改指针
            cur = last.next;
        }
        return ret.next;
    }

    /**
     * 148. 排序链表
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return sortList(head,null);
    }

    private ListNode sortList(ListNode head ,ListNode tail ){
        if(head==null) return head ;
        if(head.next == tail){
            head.next = null;
            return head ;
        }
        ListNode mid = middleNode(head,tail);
        ListNode first = sortList(head,mid);
        ListNode second = sortList(mid,tail);
        ListNode list = mergeTwoLists2(first,second);
        return list ;
    }

    private ListNode middleNode(ListNode head,ListNode tail) {
        ListNode oneStep = head;
        ListNode twoStep = head;

        while (twoStep != tail) {
            twoStep = twoStep.next;
            if (twoStep != tail) {
                oneStep = oneStep.next;
                twoStep = twoStep.next;
            }
        }
        return oneStep;
    }


}
