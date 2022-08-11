package com.iflytek.staff.chao.list;

import java.util.*;

/**
 * @author : hamilton
 * @Description: TODO
 * @date Date : 2022年06月23日 下午3:49
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }


    public ListNode middleNode(ListNode head) {
        ListNode oneStep = head;
        ListNode twoStep = head;

        while (twoStep != null) {
            twoStep = twoStep.next;
            oneStep = oneStep.next;
            if (twoStep != null) {
                twoStep = twoStep.next;
            }
        }
        return oneStep;
    }

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
        second.next = second.next.next;
        return ret.next;
    }

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

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }


    public ListNode reverseKGroup(ListNode head, int k) {


        ListNode dummy = new ListNode(-1 ,head);
        ListNode connect = dummy;

        ListNode pre = dummy.next ;
        ListNode revse = pre;

        while (revse!=null){

            int count =1 ;
            while (revse!=null && count<k){
                count++;
                revse= revse.next;
            }

            if(revse!=null){
                ListNode next = revse.next ;
                revse.next=null ;
                connect.next = reverseList(pre);
                connect=pre;
                connect.next=next;
                pre = next;
                revse= pre;
            }
        }
        return dummy.next;
    }


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

    public ListNode mergeTwoLists3(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists3(list1.next, list2);
            return list1;

        } else {
            list2.next = mergeTwoLists3(list1, list2.next);

            return list2;
        }
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


    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
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

    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();

        int index = 0;
        while (head != null) {
            index++;
            stack.add(head.val);
            head = head.next;
        }
        int[] ret = new int[index];
        index = 0;
        while (!stack.empty()) {
            ret[index++] = stack.peek();
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
     * 判断是否为回文形式的链表
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
            right = stack.pop();
        }
        return true;
    }

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


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        ListNode ans = pre;
        int add = 0;
        while (l1 != null || l2 != null || add != 0) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;

            int v = v1 + v2 + add;
            add = v / 10;
            pre.next = new ListNode(v % 10);
            ;
            pre = pre.next;
            l1 = l1 != null ? l1.next : l1;
            l2 = l2 != null ? l2.next : l2;
        }

        return ans.next;
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode>  setA = new HashSet<>();
        while (headA!=null){
            setA.add(headA);
            headA=headA.next;
        }

        while (headB!=null){
            if(setA.contains(headB)){
                return headB;
            }
            headB=headB.next;
        }
        return null;
    }

    public ListNode deleteDuplicates3(ListNode head) {
        if(head==null || head.next ==null) return head;
        ListNode ans = new ListNode(-1, head);
        ListNode pre =ans ;
        while (pre.next !=null && pre.next.next!=null){
            if(pre.next.val == pre.next.next.val){
                int deVal = pre.next.val;
                while (pre.next!=null && pre.next.val==deVal){
                    pre.next = pre.next.next;
                }
            }else {
                pre =pre.next ;
            }
        }
        return ans.next;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode ans = new ListNode(-1, head);
        ListNode pre =ans ;
        while (pre.next !=null && pre.next.next!=null){

            ListNode next = pre.next ;
            ListNode nextNext = pre.next.next ;

            next.next= nextNext.next;
            nextNext.next= next;
            pre.next = nextNext;
            pre = next;

        }
        return ans.next;
    }


    public void reorderList(ListNode head) {

        List<ListNode> listNodes = new ArrayList<>();
        while (head !=null){
            listNodes.add(head);
            head=head.next;
        }

        int l=0, r= listNodes.size()-1;
        while (l < r) {
            listNodes.get(l).next= listNodes.get(r);
            l++;
            if(l==r) break;
            listNodes.get(r).next=listNodes.get(l);
            r--;
        }
        listNodes.get(l).next=null;

    }
}
