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

#### Python Discussion Solution:

