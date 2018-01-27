### Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

#Java Soluton :

	class Solution {
	    public List<String> generateParenthesis(int n) {
	        List<String> list = new LinkedList<String>();
	        if(n<=0)
	            return list;
	        if(n==1){
	            list.add("()");
	            return list;
	        }
	        if(n==2)
	            return Arrays.asList("(())", "()()");
	        List<String> buf = generateParenthesis(n-1);
	        for(String s: buf){
	            list.add("("+s+")");
	            if(!list.contains("()"+s))
	                list.add("()"+s);
	            if(!list.contains(s+"()"))
	                list.add(s+"()");
	        }
	        for(int i=2; i<=n/2; i++){
	            buf = generateParenthesis(i);
	            List<String> buf2 = generateParenthesis(n-i);
	            for(String s1: buf){
	                for(String s2: buf2){
	                    if(!list.contains(s1+s2))
	                        list.add(s1+s2);
	                    if(!list.contains(s2+s1))
	                        list.add(s2+s1);
	                }
	            }
	        }
	        return list;
	    }
	}

#### Java Discussion Solution :

	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *    int val;
	 *    ListNode next;
	 *    ListNode(int x) { val = x; }
	 * }
	 */
	import java.util.*;
	class Solution{
	    public List<String> generateParenthesis(int n){
	        List<String> list = new LinkedList<String>();
	        /**
	         * The idea here is to only add '(' and ')' that we know will guarantee
	         * us a solution (instead of adding 1 too many close).
	         * Once we add a '(' we will then discard it and try a ')' which can only 
	         * close a valid '('. Each of these steps are recursively called.
	         */
	        backtarck(list, "", 0, 0, n);
	        return list;
	    }
	    public void backtarck(List<String>, String str, int open, int close, int max){
	        if(str.length() == 2*max){
	            list.add(str);
	            return;
	        }
	        if(open<max){
	            backtarck(list, str+"(", open+1, close, max);
	        }
	        if(close<open){
	            backtarck(list, str+")", open, close+1, max);   
	        }
	    }
	}

#### Python Solution :

	class Solution:
	    def generateParenthesis(self, n):
	        """
	        :type n: int
	        :rtype: List[str]
	        """
	        def backTrack(list, s, open, close, max):
	            if len(s) == max*2:
	                list.append(s)
	                return;
	            if open<max:
	                backTrack(list, s+'(', open+1, close, max)
	            if close<open:
	                backTrack(list, s+')', open, close+1, max)
	        list = []
	        backTrack(list, '', 0, 0, n)
	        return list