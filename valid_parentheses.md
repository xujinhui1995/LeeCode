### Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
the brackets must close in the correct order, "()" and "()[]{}" sre all valid but "(]" and "([)]" are not.

#### Java Discussion Solution:
	class Solution {
	    public boolean isValid(String s) {
	        Stack<Character> stack = new Stack<Character>();
	        for(char c: s.toCharArray()){
	            if(c=='('){
	                stack.push(')');
	            }else if(c=='{'){
	                stack.push('}');
	            }else if(c=='['){
	                stack.push(']');
	            }else if(stack.isEmpty() || stack.pop()!=c){
	                return false;
	            }
	        }
	        return stack.isEmpty();
	    }
	}

#### Python Discussion Solution:

	class Solution:
	    def isValid(self, s):
	        """
	        :type s: str
	        :rtype: bool
	        """
	        stack = []
	        dict = {"]":"[", "}":"{", ")":"("}
	        for char in s:
	            if char in dict.values():
	                stack.append(char)
	            elif char in dict.keys():
	                if stack == [] or dict[char] !=stack.pop():
	                    return False
	        return stack == []