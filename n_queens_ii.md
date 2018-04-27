## N Queens II

The *n*-queens puzzle is the problem of placing *n* queens on an *nxn* chessboard such that no two queens attack each other.

![](https://i.imgur.com/RS85EQd.png)

Given an integer n, return the number of distinct solutions to the *n*-queens puzzle.

**Example:**

	Input: 4
	Output: 2
	Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
	[
	 [".Q..",  // Solution 1
	  "...Q",
	  "Q...",
	  "..Q."],
	
	 ["..Q.",  // Solution 2
	  "Q...",
	  "...Q",
	  ".Q.."]
	]

#### Java Solution:

	class Solution {
	    private int count = 0;
		private boolean[] cols;
		private boolean[] diag1;
		private boolean[] diag2;
		private char[][] board;
	    public void nQueens(int n, int row){
			if(n<=row){
				count++;
				return;
			}
			for(int i=0; i<n; i++){
				if(!cols[i] && !diag1[i+row] && !diag2[row-i+n]){
					cols[i] = true;
					diag1[i+row] = true;
					diag2[row-i+n] = true;
					board[row][i] = 'Q';
	
					nQueens(n, row+1);
	
					cols[i] = false;
					diag1[i+row] = false;
					diag2[row-i+n] = false;
					board[row][i] = '.';
				}
			}
		}
	    public int totalNQueens(int n) {
	        if(n<=0) return count;
			cols = new boolean[n];
			diag1 = new boolean[n<<1];
			diag2 = new boolean[n<<1];
			board = new char[n][n];
			for(int i=0; i<n; i++){
				for(int j=0; j<n; j++){
					board[i][j] = '.';
				}
			}
			nQueens(n, 0);
			return count;
	    }
	}