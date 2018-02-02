### Reverse Nodes in K-Group

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

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
	    public ListNode reverseKGroup(ListNode head, int k) {
	        if(head==null || k<=1)
	            return head;
	        Stack<ListNode> stack = new Stack<ListNode>();
	        ListNode tail = new ListNode(0);
	        ListNode dummy = tail;
	        dummy.next = head;
	        int i = 0;
	        while(head!=null){
	            if(i<k){
	                stack.push(head);
	                head = head.next;
	                i++;
	            } 
	            if(i==k){
	                while(i>0){
	                    dummy.next = stack.pop();
	                    dummy = dummy.next;
	                    i--;
	                }
	                dummy.next = head;
	            }
	        }
	        return tail.next;
	    }
	}


#### Java Discussion Solution :
	
	目前一个高票回答就是利用递归，但是给我的感觉并不是常数空间复杂度，因为对于这个问题来说，首先不是常数循环次数，其次也不是尾递归，所以不会产生优化。我的想法是，先对前k个Node进行reverse，然后得到reverse后的第k个Node，此时返回kNode.next = reverse(kAnd1Node,k);并且记录下第一次的开始Node，当最后一次递归时，将其返回。当然，下面这个还是高赞中的我认为的高空间复杂度的方案。


	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	class Solution {
	    public ListNode reverseKGroup(ListNode head, int k) {
	        ListNode curr = head;
	        int count = 0;
	        while (curr != null && count != k) { // find the k+1 node
	            curr = curr.next;
	            count++;
	        }
	        if (count == k) { // if k+1 node is found
	            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
	            // head - head-pointer to direct part, 
	            // curr - head-pointer to reversed part;
	            while (count-- > 0) { // reverse current k-group: 
	                ListNode tmp = head.next; // tmp - next head in direct part
	                head.next = curr; // preappending "direct" head to the reversed list 
	                curr = head; // move head of reversed part to a new node
	                head = tmp; // move "direct" head to the next node in direct part
	            }
	            head = curr;
	        }
	        return head;
	    }
	}

#### Python Solution :

	# Definition for singly-linked list.
	# class ListNode:
	#     def __init__(self, x):
	#         self.val = x
	#         self.next = None
	
	class Solution:
	    def reverseKGroup(self, head, k):
	        """
	        :type head: ListNode
	        :type k: int
	        :rtype: ListNode
	        """
	        if not head or k<=1:
	            return head
	        stack = []
	        tail = ListNode(None)
	        dummy = tail
	        dummy.next = head
	        i = 0
	        while head:
	            if i<k:
	                stack.append(head)
	                head = head.next
	                i += 1
	            if i==k:
	                while i>0:
	                    dummy.next = stack.pop()
	                    dummy = dummy.next
	                    i -= 1
	                dummy.next = head
	        return tail.next

#### Python Discussion Solution :

	# Definition for singly-linked list.
	# class ListNode:
	#     def __init__(self, x):
	#         self.val = x
	#         self.next = None
	
	class Solution:
	    def reverseKGroup(self, head, k):
	        """
	        :type head: ListNode
	        :type k: int
	        :rtype: ListNode
	        """
	        dummy = jump = ListNode(None)
	        dummy.next = l = r = head
	
	        while True:
	            count = 0
	            while r and count<k: # use r to locate the range
	                r = r.next
	                count += 1
	            if count == k: # if size k satisfied, reverse the inner linked list
	                pre, cur = r, l
	                for _ in range(k):
	                    cur.next, cur, pre = pre, cur.next, cur # standard reversing
	                jump.next, jump, l = pre, l, r # connect two k-groups
	            else:
	                return dummy.next