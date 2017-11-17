##Add Two Numbers
	You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

	You may assume the two numbers do not contain any leading zero, except the number 0 itself.

刚开始的思路是将两条链表相加,然后算出来结果再放回新链表,结果发现因为链表太长越界.舍弃:

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	class Solution {
	    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        long number1 = getNumber(l1);
			long number2 = getNumber(l2);
			long temp = number1 + number2;
			return getListNode(temp);
	    }
	    public long getNumber(ListNode node) {
			long i = 1;
			long result = node.val;
			while (node.next != null) {
				node = node.next;
				i *= 10;
				result = result + node.val * i;
			}
			return result;
		}
	
		public ListNode getListNode(long number) {
			String buf = number + "";
			char[] buffer = buf.toCharArray();
			ListNode result = new ListNode((int)(number % 10));
			ListNode node = result;
			int length = buffer.length;
			for (int i = length - 2; i >= 0; i--) {
				String str = buffer[i] + "";
				int num = Integer.parseInt(str);
				ListNode ln = new ListNode(num);
				node.next = ln;
				node = node.next;
			}
			return result;
		}
	}

于是看起来只能使用一个标志位,然后将数据挨个计算,并设置一个标记为来表示进位.好在不需要反向链表.

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	class Solution {
	    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        boolean flag = false;//进位标志,值为true时表示需要进位.
	        ListNode result = new ListNode(l1.val+l2.val);
	        if(l1.val+l2.val>=10){
	        	flag = true;
	        	result.val = l1.val+l2.val-10;
	        }
	        result = getResult(flag,result,l1,l2);
	        return result;
	    }
	    public ListNode getResult(boolean flag, ListNode res, ListNode l1, ListNode l2){
	    	ListNode result = res;
	    	while(l1.next != null || l2.next != null){
	        	if(l1.next!=null && l2.next!=null){
	        		int buf = l1.next.val + l2.next.val + (flag?1:0);
	        		flag = false;
	        		if(buf>=10){
	        			buf = buf - 10;
	        			flag = true;
	        		}
	        		result.next = new ListNode(buf);
	        		result = result.next;
	                l1 = l1.next;
	                l2 = l2.next;
	        	}else if (l1.next!=null) {
	        		int buf = l1.next.val + (flag?1:0)*1;
	        		flag = false;
	        		if(buf>=10){
	        			buf = buf -10;
	        			flag = true;
	        		}
	        		result.next = new ListNode(buf);
	        		result = result.next;
	                l1 = l1.next;
	        	}else if(l2.next!=null){
	        		int buf = l2.next.val + (flag?1:0)*1;
	        		flag = false;
	        		if(buf>=10){
	        			buf = buf -10;
	        			flag = true;
	        		}
	        		result.next = new ListNode(buf);
	        		result = result.next;
	                l2 = l2.next;
	        	}
	        }
	        if(flag){
	        	result.next = new ListNode(1);
	        }
	        return res;
	    }
	}

写Python写的有点迷糊,总是忘记分号;下面用Python实现一下.

	# Definition for singly-linked list.
	# class ListNode:
	#     def __init__(self, x):
	#         self.val = x
	#         self.next = None
	
	class Solution:
	    def addTwoNumbers(self, l1, l2):
	        """
	        :type l1: ListNode
	        :type l2: ListNode
	        :rtype: ListNode
	        """
	        flag = False
	        buf = l1.val + l2.val
	        if buf >= 10:
	            buf = buf - 10
	            flag = True
	        result = ListNode(buf)
	        return self.getResult(flag, result, l1, l2)
	
	    def getResult(self, flag, result, l1, l2):
	        res = result
	        while l1.next or l2.next:
	            if l1.next and l2.next:
	                number = l1.next.val + l2.next.val + (1 if flag else 0)
	                flag = False
	                if number >= 10:
	                    number = number - 10
	                    flag = True
	                result.next = ListNode(number)
	                l1 = l1.next
	                l2 = l2.next
	                result = result.next
	            elif l1.next:
	                number = l1.next.val + (1 if flag else 0)
	                flag = False
	                if number >= 10:
	                    number = number - 10
	                    flag = True
	                result.next = ListNode(number)
	                l1 = l1.next
	                result = result.next
	            elif l2.next:
	                number = l2.next.val + (1 if flag else 0)
	                flag = False
	                if number >= 10:
	                    number = number - 10
	                    flag = True
	                result.next = ListNode(number)
	                l2 = l2.next
	                result = result.next
	        if flag:
	            result.next = ListNode(1)
	        return res

暂时是可以了.当然,这可以说是纯粹复制一下逻辑.

		# Definition for singly-linked list.
		# class ListNode:
		#     def __init__(self, x):
		#         self.val = x
		#         self.next = None
		
		class Solution:
		    def addTwoNumbers(self, l1, l2):
		        """
		        :type l1: ListNode
		        :type l2: ListNode
		        :rtype: ListNode
		        """
		        flag = 0
		        root = node = ListNode(0)
		        while l1 or l2 or flag:
		        	v1 = v2 = 0
		        	if l1:
		        		v1 = l1.val
		        		l1 = l1.next
		        	if l2:
		        		v2 = l2.val
		        		l2 = l2.next
		        	flag,number = divmod(v1+v2+flag,10)
		        	node.next = ListNode(number)
		        	node = node.next
		        return root.next

不过还是这个实现的更优雅啊.