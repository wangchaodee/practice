package com.iflytek.staff.chao.list;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

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
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next= node.next.next;
    }

    /**
     * 判断是否为回文形式的链表
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if(head==null || head.next==null) return true;
        Stack<ListNode> stack = new Stack<>();

        ListNode right = head ;
        while (right!=null){
            stack.push(right);
            right = right.next ;
        }

        right= stack.pop();
        while (head !=right){
            if(head.val != right.val){
                return false ;
            }
            head= head.next;
            right= stack.pop();
        }
        return true;
    }

}
