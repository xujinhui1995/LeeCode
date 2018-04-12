## Rotate Image

You are given an *n x n* 2D matrix representing an image.

Rotate the image by 90 degrees(clockwise).

**Note:**

You have to rotate the image **in-place**, which means you have to modify the input 2D matrix directly. **DO NOT** allocate another 2D matrix and do the rotation.

**Example 1:**

	Given input matrix = 
	[
	  [1,2,3],
	  [4,5,6],
	  [7,8,9]
	],
	
	rotate the input matrix in-place such that it becomes:
	[
	  [7,4,1],
	  [8,5,2],
	  [9,6,3]
	]

**Example 2:**

	Given input matrix =
	[
	  [ 5, 1, 9,11],
	  [ 2, 4, 8,10],
	  [13, 3, 6, 7],
	  [15,14,12,16]
	], 
	
	rotate the input matrix in-place such that it becomes:
	[
	  [15,13, 2, 5],
	  [14, 3, 4, 1],
	  [12, 6, 8, 9],
	  [16, 7,10,11]
	]

#### Java Solution:

	class Solution {
	    public void rotate(int[][] matrix) {
	        int temp = 0;
	    	//分层来进行旋转，总共需要旋转n/2次
	    	int n = matrix.length;
	    	for(int i=0; i<n/2; i++){
	    		for(int j=i; j<n-1-i; j++){
	    			temp=matrix[i][j];
	    			matrix[i][j]=matrix[n-1-j][i];
	    			matrix[n-1-j][i]=matrix[n-1-i][n-1-j];
	    			matrix[n-1-i][n-1-j]=matrix[j][n-1-i];
	    			matrix[j][n-1-i]=temp;
	    		}
	    	}
	    }
	}

#### Python Solution:

	class Solution:
	    def rotate(self, matrix):
	        """
	        :type matrix: List[List[int]]
	        :rtype: void Do not return anything, modify matrix in-place instead.
	        """
	        n = len(matrix)
	        for i in range(n//2):
	            for j in range(i,n-1-i):
	                temp=matrix[i][j]
	                matrix[i][j]=matrix[n-1-j][i]
	                matrix[n-1-j][i]=matrix[n-1-i][n-1-j]
	                matrix[n-1-i][n-1-j]=matrix[j][n-1-i]
	                matrix[j][n-1-i]=temp