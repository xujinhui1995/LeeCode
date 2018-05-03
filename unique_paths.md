## Unique Paths

A robot is located at the top-left corner of a *m x n* grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right conrner of the grid (marked 'Finish' in the diagram below).

How many possible unique payhs are there?

![](https://i.imgur.com/r9VtjYT.png)

**Note:** *m* and *n* will be at most 100.

**Example 1:**

	Input: m = 3, n = 2
	Output: 3
	Explanation:
	From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
	1. Right -> Right -> Down
	2. Right -> Down -> Right
	3. Down -> Right -> Right

**Example 2:**

	Input: m = 7, n = 3
	Output: 28

#### Java Solution :

	class Solution {
	    public int uniquePaths(int m, int n) {
	        if(m<=1) return 1;
	    	if(n<=1) return 1;
	        int[][] temp = new int[m+1][n+1];
	    	for(int i=1; i<=m; i++){
	            for(int j=1; j<=n; j++){
	                if(i==1 || j==1) temp[i][j]=1;
	                else temp[i][j] = temp[i-1][j]+temp[i][j-1];
	            }
	        }
	        return temp[m][n];
	    }
	}

#### Python Solution :

	class Solution:
	    def uniquePaths(self, m, n):
	        """
	        :type m: int
	        :type n: int
	        :rtype: int
	        """
	        if m<=1:
	            return 1
	        if n<=1:
	            return 1;
	        temp = [[0 for i in range(n+1)] for i in range(m+1)]
	        for i in range(1, m+1):
	            for j in range(1, n+1):
	                if i==1 or j==1:
	                    temp[i][j]=1
	                else:
	                    temp[i][j] = temp[i-1][j]+temp[i][j-1]
	        return temp[m][n]

#### Python Discussion Solution :

	import math
	    def uniquePaths(self, m, n):
	        return int(math.factorial(m+n-2)/(math.factorial(m-1)*math.factorial(n-1)))