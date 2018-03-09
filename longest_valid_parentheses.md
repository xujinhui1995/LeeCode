## Longest Valid Parentheses

Given a string containing just the characters `'('` and `')'`. find the length of the longest valid (well-fromed) parentheses substring.

For `"(()"`, the logest valid parentheses substring is `"()"`, which the length = 2.

Another example is `")()())"`, where the longest valid parentheses substring is `"()()"`, which has length = 4.

我的烂的一批的代码，并不能通过。

	class Solution {
	    public int longestValidParentheses(String s) {
	        //首先将字符串转化数组。
			char[] chars = s.toCharArray();
			int length = s.length();
			int start = 0;
			int intrupt = 0;
			int max = 0;
	        if(length<2)
	            return 0;
			//从开始开始进行循环
	        Stack<Character> stack = new Stack<Character>();
			while(start<length && intrupt<length){
				if(chars[intrupt]=='('){
					if(intrupt+1<length){
						if(stack.isEmpty()&&chars[intrupt+1]=='('){
							max = max>(intrupt-start)?max:(intrupt-start);
							start = intrupt+1;
							intrupt = start;
						}else{
							stack.push('(');
							intrupt++;
						}
					}else{
	                    intrupt++;
	                    start++;
	                }
				}else{
					if(stack.isEmpty()){
						max = max>(intrupt-start)?max:(intrupt-start);
						start = intrupt+1;
						intrupt = start;
					}else{
						stack.pop();
						intrupt++;
					}
				}
			}
	        max = max>(intrupt-start)?max:(intrupt-start);
			int temp = 0;
			while(!stack.isEmpty()){
				temp++;
	            stack.pop();
			}
			max = (temp==0||max==0)?max:(max-temp);
			return max;
	    }
	}


#### Java Discussion Solution 1

	class Solution {
	    public int longestValidParentheses(String s) {
	        int length = s.length();
			Stack<Integer> stack = new Stack<Integer>();
			char[] arr = s.toCharArray();
			int max = 0;
			for(int i=0; i<length; i++){
				if(arr[i]=='('){
					stack.push(i);
				}else{
					if(!stack.isEmpty()){
						if(arr[stack.peek()]=='('){
							stack.pop();
						}else{
							stack.push(i);
						}
					}else{
						stack.push(i);
					}
				}
			}
			if(stack.isEmpty()) return length;
			int a = length;
			while(!stack.isEmpty()){
				int b = stack.pop();
				max = max>(a-1-b)?max:(a-1-b);
				a = b;
			}
			max = max>a?max:a;
			return max;
	    }
	}

#### Java Discussion Solution 2:

	class Solution {
	    public int longestValidParentheses(String s) {
	        Stack<Integer> stack = new Stack<Integer>();
			int left = -1;
			int max = 0;
			for(int i=0; i<s.length(); i++){
				if(s.charAt(i)=='(') stack.push(i);
				else{
					if(stack.isEmpty()) left = i;
					else{
						stack.pop();
						if(stack.isEmpty()) max = Math.max(max, i-left);
						else max = Math.max(max, i-stack.peek());
					}
				}
			}
			return max;
	    }
	}

#### Python Solution:

	class Solution:
	    def longestValidParentheses(self, s):
	        """
	        :type s: str
	        :rtype: int
	        """
	        res, left = 0, -1
	        stack = []
	        for i in range(len(s)):
	            if s[i]=='(':
	                stack.append(i)
	            else:
	                if not stack:
	                    left = i
	                else:
	                    stack.pop()
	                    if not stack:
	                        res = max(res, (i-left))
	                    else:
	                        res = max(res, (i-stack[-1]))
	        return res