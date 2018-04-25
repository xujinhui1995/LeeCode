## Pow(x, n)

Implement pow(*x*, n)

#### Java Discussion Solution：

	class Solution {
		//忽略了可以用递归的方法
	    public double myPow(double x, int n) {
	        if(n==0) return 1;
	    	double res = myPow(x, n/2);
	    	return (n%2==0)?res*res:n<0?1/x*res*res:res*res*x;
	    }
	}


#### Python Solution:

	class Solution:
	    def myPow(self, x, n):
	        """
	        :type x: float
	        :type n: int
	        :rtype: float
	        """
	        if not n:
	            return 1
	        res = self.myPow(x, n//2 if n>0 or n%2==0 else n//2+1)
	        return res*res if n%2==0 else ((1/x*res*res) if n<0 else res*res*x)

#### Python Discussion Solution:

	class Solution:
	    def myPow(self, x, n):
	        """
	        :type x: float
	        :type n: int
	        :rtype: float
	        """
	        if not n:
	            return 1
	        if n<0:
	            return 1 / self.myPow(x, -n)
	        if n%2:
	            return x * self.myPow(x, n-1)
	        return self.myPow(x * x, n/2)