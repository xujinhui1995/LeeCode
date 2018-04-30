## Spiral Matrix II

Given a positive integer n, generate a squence matrix filled with elements from 1 to *n*2 in spiral order.

**Example:**

	Input: 3
	Output:
	[
	 [ 1, 2, 3 ],
	 [ 8, 9, 4 ],
	 [ 7, 6, 5 ]
	]

#### Java Solution :

	class Solution {
	    public int[][] generateMatrix(int n) {       
	        int[][] res = new int[n][n];
	    	int deep = 0;
	    	int i = 1;
	    	while(deep<n/2){
	    		for(int j=deep; j<=n-1-deep; j++){
	    			res[deep][j] = i;
	    			i++;
	    		}
	    		for(int j=deep+1; j<=n-2-deep; j++){
	    			res[j][n-1-deep] = i;
	    			i++;
	    		}
	    		for(int j=n-1-deep; j>=deep; j--){
	    			res[n-1-deep][j] = i;
	    			i++;
	    		}
	    		for(int j=n-2-deep; j>=deep+1; j--){
	    			res[j][deep] = i;
	    			i++;
	    		}
	            deep++;
	    	}
	    	if(n%2==0){
	    		return res;
	    	}else{
	    		res[n/2][n/2]=i;
	    		return res;
	    	}
	    }
	}

#### Python Solution:

	class Solution:
	    def generateMatrix(self, n):
	        """
	        :type n: int
	        :rtype: List[List[int]]
	        """
	        res = [[0 for i in range(n)] for i in range(n)]
	        deep, i = 0, 1
	        while deep<n//2:
	            for j in range(deep, n-deep):
	                res[deep][j] = i
	                i += 1
	            for j in range(deep+1, n-1-deep):
	                res[j][n-1-deep] = i
	                i += 1
	            for j in range(n-1-deep, deep-1, -1):
	                res[n-1-deep][j] = i
	                i += 1
	            for j in range(n-2-deep, deep, -1):
	                res[j][deep] = i
	                i += 1
	            deep += 1
	        if n%2==0:
	            return res
	        else:
	            res[n//2][n//2]=i
	            return res