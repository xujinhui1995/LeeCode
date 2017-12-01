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

	class Solution {
	    public int reverse(int x) {
	        int result = 0;
	        while(x!=0){
	            int tail = x % 10;
	            int newResult = result * 10 + tail;
	            if((newResult-tail)/10!=result)
	                return 0;
	            result = newResult;
	            x  = x / 10;
	        }
	        return result;
	    }
	}

python3.0还是和2.0有很大区别的.

	class Solution:
	    def reverse(self, x):
	        """
	        :type x: int
	        :rtype: int
	        """
	        s = (x > 0) - (x < 0)
	        r = int(repr(s*x)[::-1])
	        return s*r*(r<2**31)