## Unique Paths II

A robot is located at the top-left corner of a *m x n* grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot  is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

![](https://i.imgur.com/spvFkSf.png)

**Note:** *m* and *n* will be at most 100.

**Example 1:**

	Input:
	[
	  [0,0,0],
	  [0,1,0],
	  [0,0,0]
	]
	Output: 2
	Explanation:
	There is one obstacle in the middle of the 3x3 grid above.
	There are two ways to reach the bottom-right corner:
	1. Right -> Right -> Down -> Down
	2. Down -> Down -> Right -> Right


#### Java Solution :

	class Solution {
	    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
	        int m = obstacleGrid.length;
	    	int n = obstacleGrid[0].length;
	        if(obstacleGrid[m-1][n-1]==1 || obstacleGrid[0][0]==1) return 0;
	    	int[][] res = new int[m][n];
	        res[0][0]=(obstacleGrid[0][0]==1?0:1);
	        for(int i=1; i<n; i++){
	            if(obstacleGrid[0][i]==1){
	                res[0][i] = 0;
	            }else{
	                res[0][i] = res[0][i-1];
	            }
	        }
	        for(int i=1; i<m; i++){
	            if(obstacleGrid[i][0]==1){
	                res[i][0] = 0;
	                //System.out.println(obstacleGrid[i][0] + ":" + res[i][0]);
	            }else{
	                res[i][0] = res[i-1][0];
	            }
	        }
	    	for(int i=1; i<m; i++){
	    		for(int j=1; j<n; j++){
	                if(obstacleGrid[i][j]!=1){
	                    if(obstacleGrid[i-1][j]==0&&obstacleGrid[i][j-1]==0) res[i][j]=res[i-1][j]+res[i][j-1];
	                    else if(obstacleGrid[i-1][j]==0) res[i][j]=res[i-1][j];
	                    else if(obstacleGrid[i][j-1]==0) res[i][j]=res[i][j-1];
	                    else res[i][j]=0;
	                }else{
	                    res[i][j]=0;
	                }
	                //System.out.println(res[1][0]);
	    		}
	    	}
	    	return res[m-1][n-1];
	    }
	}


#### public Discussion Solution :

	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
	    int width = obstacleGrid[0].length;
	    int[] dp = new int[width];
	    dp[0] = 1;
	    for (int[] row : obstacleGrid) {
	        for (int j = 0; j < width; j++) {
	            if (row[j] == 1)
	                dp[j] = 0;
	            else if (j > 0)
	                dp[j] += dp[j - 1];
	        }
	    }
	    return dp[width - 1];
	}


#### Python Discussion Solution :

	class Solution:
	    def uniquePathsWithObstacles(self, obstacleGrid):
	        """
	        :type obstacleGrid: List[List[int]]
	        :rtype: int
	        """
	        if obstacleGrid[0][0] == 1: return 0
	        for i in range(len(obstacleGrid)):
	            for j in range(len(obstacleGrid[0])): 
	                if obstacleGrid[i][j] == 1 or i == j == 0:
	                    obstacleGrid[i][j] -= 1
	                else:
	                    add1 = obstacleGrid[i - 1][j] if i > 0 else 0
	                    add2 = obstacleGrid[i][j - 1] if j > 0 else 0
	                    obstacleGrid[i][j] += add1 + add2
	        return abs(obstacleGrid[-1][-1])