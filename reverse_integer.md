## Reverse Integer

Given a 32-bit signed integer, reverse digits of an integer.

Assume we are dealing with an environment which could only hold integer within the 32-bit signed integer range. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

	class Solution {
	    public int reverse(int x) {
	        Integer num = x;
	        if(x<0)
	            num = - x;
	        String s = num+"";
	        StringBuffer sb = new StringBuffer(s);
	        s = sb.reverse().toString();
	        int result = 0;
	        try{
	            result = Integer.parseInt(s);
	        }catch(Exception e){
	            result = 0;    
	        }
	        if(x<0)
	            return -result;
	        return result;
	    }
	}

很无聊,看看别人的.