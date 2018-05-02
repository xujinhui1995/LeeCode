## Rotate List

Given a linked list, rotate the list to the right by *k* places, where *k* is non-negative.

**Example 1:**

	Input: 1->2->3->4->5->NULL, k = 2
	Output: 4->5->1->2->3->NULL
	Explanation:
	rotate 1 steps to the right: 5->1->2->3->4->NULL
	rotate 2 steps to the right: 4->5->1->2->3->NULL

**Example 2:**

	Input: 0->1->2->NULL, k = 4
	Output: 2->0->1->NULL
	Explanation:
	rotate 1 steps to the right: 2->0->1->NULL
	rotate 2 steps to the right: 1->2->0->NULL
	rotate 3 steps to the right: 0->1->2->NULL
	rotate 4 steps to the right: 2->0->1->NULL

#### Java Solution :

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	class Solution {
	    public ListNode rotateRight(ListNode head, int k) {
	        if(head==null) return head;
	        int n = 0;
	        ListNode l3 = head;
	        ListNode temp = head;
	        while(temp!=null){
	        	temp = temp.next;
	        	n++;
	        }
	        k = k % n;
	        if(k==0) return head;
	        for(n=1; n<=k; n++){
	        	l3 = l3.next;
	        }
	        ListNode l1 = head;
	        ListNode l2 = head.next;
	        while(l3.next!=null){
	        	l1 = l1.next;
	        	l2 = l2.next;
	        	l3 = l3.next;
	        }
	        l1.next = null;
	        l3.next = head;
	        return l2;
	    }
	}

#### Python Disucussion :

	# Definition for singly-linked list.
	# class ListNode:
	#     def __init__(self, x):
	#         self.val = x
	#         self.next = None
	
	class Solution:
	    def rotateRight(self, head, k):
	        """
	        :type head: ListNode
	        :type k: int
	        :rtype: ListNode
	        """
	        if not head:
	            return head
	        n, l3, temp = 0, head, head
	        while temp:
	            temp = temp.next
	            n += 1
	        k = k%n
	        if k==0:
	            return head
	        for i in range(1, k+1):
	            l3 = l3.next
	        l1, l2 = head, head.next
	        while l3.next:
	            l1, l2, l3 = l1.next, l2.next, l3.next
	        l1.next = None
	        l3.next = head
	        return l2