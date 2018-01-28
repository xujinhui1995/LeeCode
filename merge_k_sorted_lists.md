### Merge K Sorted Lists

merge k sorted linked lists and return it as one sorted list.Analyze and describe its complexity.

	public ListNode mergeKLists(ListNode[] lists) {
	        if(lists.length<=0)
	            return null;
	        int i = 1;
	        for(ListNode list: lists){
	            ListNode buf = list;
	            while(buf!=null){
	                i++;
	                buf = buf.next;
	            }
	        }
	        if(i<=1)
	            return null;
	        int[] arr = new int[i-1];
	        i = 0;
	        for(ListNode list: lists){
	            ListNode buf = list;
	            while(buf!=null){
	                arr[i]=buf.val;
	                i++;
	                buf = buf.next;
	            }
	        }
	        Arrays.sort(arr);
	        ListNode head = new ListNode(arr[0]);
	        ListNode buf1 = head;
	        ListNode buf2 = null;
	        for(int j=1; j<arr.length; j++){
	            buf2 = new ListNode(j);
	            buf1.next = buf2;
	            buf2 = buf1;
	        }
	        return head;
	    }
	}

#### Java Discussion Solution :

跟上面的方法的主题思想是差不多的，但是利用了优先队列，提高了效率，也简化了代码。

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	import java.util.*;
	class Solution {
	    public ListNode mergeKLists(ListNode[] lists) {
	        if(lists==null || lists.length<=0)
	            return null;
	        PriorityQueue<ListNode> quene = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){
	            @Override
	            public int compare(ListNode l1, ListNode l2){
	                if(l1.val<l2.val)
	                    return -1;
	                else if(l1.val==l2.val)
	                    return 0;
	                else
	                    return 1;
	            }
	        });
	        ListNode dummy = new ListNode(0);
	        ListNode tail = dummy;
	        for(ListNode node: lists){
	            if(node!=null)
	                quene.add(node);
	        }
	        while(!quene.isEmpty()){
	            tail.next = quene.poll();
	            tail = tail.next;
	            if(tail.next!=null)
	                quene.add(tail.next);
	        }
	        return dummy.next;
	    }
	}


#### Python Problem Solution :

	# Definition for singly-linked list.
	# class ListNode:
	#     def __init__(self, x):
	#         self.val = x
	#         self.next = None
	
	from queue import PriorityQueue
	class Solution:
	    def mergeKLists(self, lists):
	        """
	        :type lists: List[ListNode]
	        :rtype: ListNode
	        """
	        def cmp(self, l):
	            if self.val<l.val:
	                return -1
	            elif self.val==l.val:
	                return 0
	            else:
	                return 1
	        dummy = tail = ListNode(0)
	        if not lists or len(lists)==0:
	            return []
	        q = PriorityQueue()
	        for node in lists:
	            if node: q.put(node)
	        while q.qsize()>0:
	            tail.next = q.get()
	            tail = tail.next;
	            if tail.next:
	                q.put(tail.next)
	        return dummy.next;