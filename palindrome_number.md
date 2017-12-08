## Palindrome Number

Determine whether an integer is a palindrome. Do this without extra space.

本来以为我的就够简单了，没想到还有更简单的。

	class Solution{
	    public boolean isPalindrome(int x){
	        if(x<0)
	            return false;
	        if(x==0)
	            return true;
	        int buf = x;
	        int ret = 0;
	        while(buf>0){
	            ret = ret*10 + buf%10;
	            buf /= 10;
	        }
	        if(ret==x)
	            return true;
	        return false;
	    }
	}

这个就是整个进行reverse，如果reverse之后的值相等，那么就为真。如果溢出，那就说明不是回文，因为reverse前后一样，那reverse未溢出，reverse后也不会。

	class Solution {
	    public boolean isPalindrome(int x) {
	        if(x<0 || (x>0 && x%10==0)) return false; //小于0或者是10的倍数
	        int rev = 0;
	        while(x>rev){
	            rev = rev*10 + x%10;
	            x /= 10;
	        }
	        return x==rev || x==rev/10;
	    }
	}

只需要比较一半。

已经不敢想象如果python更简单会是什么样子了。