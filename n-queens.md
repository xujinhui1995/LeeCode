## N Queens

The *n*-queens puzzle is the problem of placing *n* queens chessboard such that no two queens attack each other.

![](https://i.imgur.com/RS85EQd.png)

Given an integer *n*, return all distinct aolutions to the *n*-queens puzzle.

Each solution contains a disctinct configuration of the *n*-queens' placement, where empty space respectively.

**Example:**

	Input: 4
	Output: [
	 [".Q..",  // Solution 1
	  "...Q",
	  "Q...",
	  "..Q."],
	
	 ["..Q.",  // Solution 2
	  "Q...",
	  "...Q",
	  ".Q.."]
	]
	Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.


#### Java Discussion Solution :

	class Solution {
	    private List<List<String>> res = new ArrayList<List<String>>();
		private boolean[] cols;
		private boolean[] diag1;
		private boolean[] diag2;
		private char[][] board;
		public void nQueens(int n, int row){
			if(row >= n){
				List<String> sol = new ArrayList<String>();
				for(int i=0; i<n; i++){
					sol.add(new String(board[i]));
				}
				res.add(sol);
				return ;
			}
			for(int i=0; i<n; i++){
				if(!cols[i] && !diag1[i+row] && !diag2[row-i+n]){
					cols[i] = true;
					diag1[i+row] = true;
					diag2[row-i+n] = true;
					board[row][i] = 'Q';
	
					nQueens(n, row+1);
	
					//换一列
					cols[i] = false;
					diag1[i+row] = false;
					diag2[row-i+n] = false;
					board[row][i] = '.';
				}
			}
		}
	    public List<List<String>> solveNQueens(int n) {
	        if(n<=0) return res;
	    	cols = new boolean[n];
	    	diag1 = new boolean[2*n];
	    	diag2 = new boolean[2*n];
	    	board = new char[n][n];
	    	for(int i=0; i<n; i++){
	    		for(int j=0; j<n; j++){
	    			board[i][j] = '.';
	    		}
	    	}
	    	nQueens(n, 0);
	    	return res;
	    }
	}


#### Python Discussion Solution:

	class Solution:
	    def solveNQueens(self, n):
	        """
	        :type n: int
	        :rtype: List[List[str]]
	        """
	        def DFS(queens, xy_dif, xy_sum):
	            p = len(queens)
	            if p==n:
	                result.append(queens)
	                return None
	            for q in range(n):
	                if q not in queens and p-q not in xy_dif and p+q not in xy_sum: 
	                    DFS(queens+[q], xy_dif+[p-q], xy_sum+[p+q])  
	        result = []
	        DFS([],[],[])
	        return [ ["."*i + "Q" + "."*(n-i-1) for i in sol] for sol in result]