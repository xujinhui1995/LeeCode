## Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the character `'.'`.

You may assue that there will be only one unique solution.

![](https://i.imgur.com/O2GGUCG.png)

#### Java Discussion Solution:

	class Solution {
	    public void solveSudoku(char[][] board) {
			if(board==null || board.length==0)
				return;
			solve(board);
		}
		public boolean solve(char[][] board){
			for(int i=0; i<board.length; i++){
				for(int j=0; j<board[0].length; j++){
					if(board[i][j]=='.'){
						for(char c='1'; c<='9'; c++){
							if(isValid(board, i, j, c)){
	                            board[i][j]=c;
								if(solve(board)){
									return true;
								}else{
									board[i][j]='.';
								}
							}
						}
						return false;
					}
				}
			}
			return true;
		}
		public boolean isValid(char[][] board, int row, int col, char c){
			for(int i=0; i<9; i++){
				if(board[i][col]!='.' && board[i][col]==c){
					return false;
				}
				if(board[row][i]!='.' && board[row][i]==c){
					return false;
				}
				if(board[(row/3)*3+i/3][(col/3)*3+i%3]!='.' && board[(row/3)*3+i/3][(col/3)*3+i%3]==c){
					return false;
				}
			}
			return true;
	    }
	}

#### Python2 Discussion Solution:

It's similar t ohow human solve Sudoku.

1. create a hash table (dictionary) `val` to store poasible valus in every location.
2. Each time, start from the location with fewest possible valus, choose one value from them update the board and possible values at other locations. If this updates is valid, keep solving (DFS). If this update is invalid (leaving zero possible values at some locations) or this value doesn't lead to the solution, undo the updates and then choose the next value.

--------------------------------------------------------------------------------
	
	class Solution(object):
	    def solveSudoku(self, board):
	        """
	        :type board: List[List[str]]
	        :rtype: void Do not return anything, modify board in-place instead.
	        """
	        self.board = board
	        self.val = self.PossibleVals()
	        self.Solver()
	
	    def PossibleVals(self):
	        a = "123456789"
	        d, val = {}, {}
	        for i in xrange(9):
	            for j in xrange(9):
	                ele = self.board[i][j]
	                if ele != ".":
	                    d[("r", i)] = d.get(("r", i), []) + [ele]
	                    d[("c", j)] = d.get(("c", j), []) + [ele]
	                    d[(i//3, j//3)] = d.get((i//3, j//3), []) + [ele]
	                else:
	                    val[(i,j)] = []
	        for (i,j) in val.keys():
	            inval = d.get(("r",i),[])+d.get(("c",j),[])+d.get((i/3,j/3),[])
	            val[(i,j)] = [n for n in a if n not in inval ]
	        return val
	
	    def Solver(self):
	        if len(self.val)==0:
	            return True
	        kee = min(self.val.keys(), key=lambda x: len(self.val[x]))
	        nums = self.val[kee]
	        for n in nums:
	            update = {kee:self.val[kee]}
	            if self.ValidOne(n, kee, update): # valid choice
	                if self.Solver(): # keep solving
	                    return True
	            self.undo(kee, update) # invalid choice or didn't solve it => undo
	        return False
	
	    def ValidOne(self, n, kee, update):
	        self.board[kee[0]][kee[1]] = n
	        del self.val[kee]
	        i, j = kee
	        for ind in self.val.keys():
	            if n in self.val[ind]:
	                if ind[0]==i or ind[1]==j or (ind[0]/3,ind[1]/3)==(i/3,j/3):
	                    update[ind] = n
	                    self.val[ind].remove(n)
	                    if len(self.val[ind])==0:
	                        return False
	        return True
	
	    def undo(self, kee, update):
	        self.board[kee[0]][kee[1]]="."
	        for k in update:            
	            if k not in self.val:
	                self.val[k]= update[k]
	            else:
	                self.val[k].append(update[k])
	        return None