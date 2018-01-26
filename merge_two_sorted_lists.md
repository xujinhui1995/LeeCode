### Merge Two Sorted Lists

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

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
	    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	        if(l1==null)
	            return l2;
	        if(l2==null)
	            return l1;
	        ListNode head = l1.val>l2.val?l2:l1;
	        ListNode buf = new ListNode(0);
	        if(l1.val>l2.val){
	            buf = l1;
	            l1 = l2;
	            l2 = buf;
	        }
	        while(l1!=null){
	            while(l2!=null){
	                if(l1.val<=l2.val){
	                    buf = l1;
	                    l1 = l1.next;
	                    break;
	                }else{
	                    buf.next = l2;
	                    buf = l2;
	                    l2 = l2.next;
	                    buf.next = l1;
	                }
	            }
	            if(l2==null)
	            	break;
	        }
	        if(l1==null){
	        	buf.next = l2;
	        }
	        return head;
	    }
	}

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
	    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	        if(l1==null)
	            return l2;
	        if(l2==null)
	            return l1;
	        int i = 1;
	        ListNode ll1 = l1;
	        ListNode ll2 = l2;
	        while(l1!=null){
	            i++;
	            l1 = l1.next;
	        }
	        while(l2!=null){
	            i++;
	            l2 = l2.next;
	        }
	        int[] arr = new int[i-1];
	        i = 0;
	        l1 = ll1;
	        l2 = ll2;
	        while(l1!=null){
	            arr[i] = l1.val;
	            l1 = l1.next;
	            i++;
	        }
	        while(l2!=null){
	            arr[i] = l2.val;
	            l2 = l2.next;
	            i++;
	        }
	        Arrays.sort(arr);
	        for(Integer intege: arr){
	        	System.out.println(intege);
	        }
	        ListNode head = new ListNode(arr[0]);
	        ListNode buf1 = head;
	        ListNode buf2 = null;
	        for(int m=1; m<arr.length; m++){
	            buf2 = new ListNode(arr[m]);
	            buf1.next = buf2;
	            buf1 = buf2;
	        }
	        return head;
	    }
	}

#### Java Discussion Solution :

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	/***
	 *  递归
	 */
	class Solution {
	    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	        if(l1==null)
	            return l2;
	        if(l2==null)
	            return l1;
	        ListNode head = null;
	        if(l1.val<l2.val){
	            head = l1;
	            head.next = mergeTwoLists(l1.next, l2);
	        }else{
	            head = l2;
	            head.next = mergeTwoLists(l1, l2.next);
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
	    def mergeTwoLists(self, l1, l2):
	        """
	        :type l1: ListNode
	        :type l2: ListNode
	        :rtype: ListNode
	        """
	        def merge(l1, l2):
	            if not l1:
	                return l2
	            if not l2:
	                return l1
	            head = None
	            if l1.val<l2.val:
	                head = l1
	                head.next = merge(l1.next, l2)
	            else:
	                head = l2
	                head.next = merge(l1, l2.next)
	            return head
	        return merge(l1,l2)

#### Python Discussion Solution1 :

	# Definition for singly-linked list.
	# class ListNode:
	#     def __init__(self, x):
	#         self.val = x
	#         self.next = None
	
	class Solution:
		# iteratively
	    def mergeTwoLists(self, l1, l2):
	        """
	        :type l1: ListNode
	        :type l2: ListNode
	        :rtype: ListNode
	        """
	        dummy = cur = ListNode(0)
	        while l1 and l2:
	            if l1.val < l2.val:
	                cur.next = l1
	                l1 = l1.next
	            else:
	                cur.next = l2
	                l2 = l2.next
	            cur = cur.next
	        cur.next = l1 or l2
	        return dummy.next

#### Python Discussion Solution2 :

	# Definition for singly-linked list.
	# class ListNode:
	#     def __init__(self, x):
	#         self.val = x
	#         self.next = None
	
	class Solution(object):
		#recursively
	    def mergeTwoLists(self, l1, l2):
	    '''
	    type l1: ListNode
	    type l2: ListNode
	    rType: ListNode
	    '''
	    if not l1 or not l2:
	        return l1 or l2
	    if l1.val < l2.val:
	        l1.next = self.mergeTwoLists(l1.next, l2)
	        return l1
	    else:
	        l2.next = self.mergeTwoLists(l1, l2.next)
	        return l2

	#### Python Discussion Solution3 :
	
	# Definition for singly-linked list.
	# class ListNode:
	#     def __init__(self, x):
	#         self.val = x
	#         self.next = None
	
	class Solution:
		# in-place, iteratively
	    def mergeTwoLists(self, l1, l2):
	        """
	        :type l1: ListNode
	        :type l2: ListNode
	        :rtype: ListNode
	        """
	        if None in (l1, l2):
	            return l1 or l2
	        dummy = cur = ListNode(0)
	        dummy.next = l1
	        while l1 and l2:
	            if l1.val < l2.val:
	                l1 = l1.next
	            else:
	                nxt = cur.next
	                cur.next = l2
	                tmp = l2.next
	                l2.next = nxt
	                l2 = tmp
	            cur = cur.next
	        cur.next = l1 or l2
	        return dummy.next