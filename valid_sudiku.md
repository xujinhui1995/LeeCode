## Valid Sudoku

Detemine if a Sudoku is valid, according to:[Sudoku Puzzles - The Rules.](http://sudoku.com.au/TheRules.aspx).

The Sudoku board counld be partially filled, where empty cells are filled with the character `'.'`.

![](https://i.imgur.com/vY0lJF3.png)

A partially filled sudoku which is valid.

**Note:**

A valid Sudoku board(partially filled) id not neccessarilly solvable. Only the filled cells need to be validated.

#### Java Solution：

	class Solution {
	    public boolean isValidSudoku(char[][] board) {
	        boolean flag = true;
			for(int i=0; i<9; i++){
				List<Character> list = new LinkedList<Character>();
				for(int j=0; j<9; j++){
	                if(board[i][j]=='.')
	                    continue;
					if(!list.contains(board[i][j])){
						list.add(board[i][j]);
					}else{
						flag = false;
						return flag;
					}	
				}
			}
			for(int i=0; i<9; i++){
				List<Character> list = new LinkedList<Character>();
				for(int j=0; j<9; j++){
	                if(board[j][i]=='.')
	                    continue;
					if(!list.contains(board[j][i])){
						list.add(board[j][i]);
					}else{
						flag = false;
						return flag;
					}	
				}
			}
			for(int n=0; n<3; n++){
				for(int i=0; i<3; i++){
					List<Character> list = new LinkedList<Character>();
					for(int j=0; j<3; j++){
						for(int m=0; m<3; m++){
	                        if(board[i*3+j][m+n*3]=='.')
	                            continue;
							if(!list.contains(board[i*3+j][m+n*3])){
								list.add(board[i*3+j][m+n*3]);
							}else{
								flag = false;
								return flag;
							}
						}
					}
				}
			}
			return flag;
	    }
	}


#### Java Discussion Solution:

	class Solution {
	    public boolean isValidSudoku(char[][] board) {
	        for(int i=0; i<9; i++){
				HashSet<Character> col = new HashSet<Character>();
				HashSet<Character> row = new HashSet<Character>();
				HashSet<Character> cube = new HashSet<Character>();
				for(int j=0; j<9; j++){
					if(board[i][j]!='.' && !row.add(board[i][j])){
						return false;
					}
					if(board[j][i]!='.' && !col.add(board[j][i])){
						return false;
					}
					int RowIndex = 3*(i/3);
					int ColIndex = 3*(i%3);
					if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3])){
						return false;
					}
				}
			}
			return true;
	    }
	}


#### Python Discussion Solution 1:

	class Solution:
	    def isValidSudoku(self, board):
	        """
	        :type board: List[List[str]]
	        :rtype: bool
	        """
	        return 1 == max(collections.Counter(
		        x
		        for i, row in enumerate(board)
		        for j, c in enumerate(row)
		        if c != '.'
		        for x in ((c, i), (j, c), (i/3, j/3, c))
		    ).values() + [1])

### Python Discussion Solution 2:

	def isValidSudoku(self, board):
	    seen = sum(([(c, i), (j, c), (i/3, j/3, c)]
	                for i, row in enumerate(board)
	                for j, c in enumerate(row)
	                if c != '.'), [])
	    return len(seen) == len(set(seen))

### Python Discussion Solution 3:

	def isValidSudoku(self, board):
	    seen = set()
	    return not any(x in seen or seen.add(x)
	                   for i, row in enumerate(board)
	                   for j, c in enumerate(row)
	                   if c != '.'
	                   for x in ((c, i), (j, c), (i/3, j/3, c)))

### Python Discussion Solution 4:

	def isValidSudoku(self, board):
	    seen = set()
	    return not any(x in seen or seen.add(x)
	                   for i, row in enumerate(board)
	                   for j, c in enumerate(row)
	                   if c != '.'
	                   for x in ((c, i), (j, c), (i/3, j/3, c)))