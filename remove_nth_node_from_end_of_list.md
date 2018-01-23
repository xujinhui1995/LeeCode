### Remove Nth Node From End of List

Given a linked list, remove the nth noce from the end of list and return its head.


#### Java Solution:

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	class Solution {
	    public ListNode removeNthFromEnd(ListNode head, int n) {
	        ListNode list = head;
	        int i = 1;
	        while(list.next!=null){
	            i++;
	            list = list.next;
	        }
	        if(i==n)
	            return head.next;
	        if(n>i || n<=0)
	            return head;
	        list = head;
	        for(int m=1; m<i; m++){
	            if(m==(i-n)){
	                list.next = list.next.next;
	                break;
	            }
	            list = list.next;
	        }
	        return head;
	    }
	}

#### Java Discussion One Pass Solution:

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	class Solution {
	    public ListNode removeNthFromEnd(ListNode head, int n) {
	        ListNode start = new ListNode(0);
	        start.next = head;
	        ListNode fast = start, slow = start;
	        for(int i=1; i<n+1; i++){
	            fast = fast.next;
	        }
	        while(fast.next!=null){
	            fast = fast.next;
	            slow = slow.next;
	        }
	        slow.next = slow.next.next;
	        return start.next;
	    }
	}