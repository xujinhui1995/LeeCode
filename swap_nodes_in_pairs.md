### Swap Nodes in Pairs

Given a linked list, swap every two adjacent nodes and return its head.

For example,

Given `1->2->3->4`, you should return the list as `2->1->4->3`.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

#### Java Solution1 :

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	class Solution {
	    public ListNode swapPairs(ListNode head) {
	        if(head==null || head.next==null)
	            return head;
	        ListNode bef = new ListNode(0);
	        ListNode l0 = head;
	        ListNode l1 = head.next;
	        ListNode nxt = l1.next;
	        bef.next = l0;
	        swap(bef,l0,l1,nxt);
	        return bef.next;
	    }
	    public void swap(ListNode bef, ListNode l0, ListNode l1, ListNode nxt){
	        if(l1==null)
	            return;
	        if(nxt==null){
	            ListNode buf = l0;
	            bef.next = l1;
	            l1.next = buf;
	            buf.next = nxt;
	            return;
	        }
	        ListNode buf = l0;
	        bef.next = l1;
	        l1.next = buf;
	        buf.next = nxt;
	        bef = l0;
	        l0 = nxt;
	        l1 = nxt.next;
	        nxt = nxt.next==null?null:nxt.next.next;
	        swap(bef,l0,l1,nxt);
	    }
	}

借助中间变量，递归，处理好边界。不过似乎有点违背常数空间复杂度的要求。思路，利用循环，借助几个辅助变量，这样空间复杂度为常数。

#### Java Solution2 :

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	class Solution {
	    public ListNode swapPairs(ListNode head) {
	        if(head==null || head.next==null)
	            return head;
	        ListNode tail = new ListNode(0);
	        ListNode buf = tail;
	        tail.next = head;
	        ListNode dummy = head.next;
	        while(head!=null && dummy!=null){
	            tail.next = dummy;
	            head.next = dummy.next;
	            dummy.next = head;
	            tail = head;
	            head = head.next;
	            dummy = head==null?null:head.next;
	        }
	        return buf.next;
	    }
	}


#### Java Discussion Solution :

虽然不是常数空间，但还是很优雅的。

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	class Solution {
	    public ListNode swapPairs(ListNode head) {
	        if(head==null || head.next==null){
	            return head;
	        }
	        ListNode tail = head.next;
	        head.next = swapPairs(head.next.next);
	        tail.next = head;
	        return tail;    
	    }
	}