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