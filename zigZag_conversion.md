## ZigZag Conversion

有时候你就不得不佩服人类的智慧

the string `"PAYPALISHIRING"` is written in a zigzag pattern on a given number of rows like this:
	
	P   A   H   N
	A P L S I I G
	Y   I   R

And then read line by line: `"PANNAPLSIIGYIR"`

Write the code that will take a string and make this conversion given a number of rows:

	string convert(string text, int nRows);

`convert("PAYPLISHIRING", 3)` should return `"PAHNAPLSIIGYIR"`

其实说了这么多就是将字符串按照字符先直下,然后斜上,构成一个"之"字,然后再一行一行的读.

我的想法比较单纯,就是找一下规律,然后一行一行的读,可以说是很无聊了.

	class Solution {
	    public String convert(String s, int numRows) {
	        int len = s.length();
	        int n = numRows;
	        if(len<=1 || numRows==1 || len<=numRows){
	            return s;
	        }
	        int col = len/(2*n-2);
	        if(len%(2*n-2)!=0){
	            col++;
	        }
	        String result = "";
	        for(int i=0; i<col; i++){
	            int index = i*(2*n-2);
	            result += s.substring(index,index+1);
	        }
	        for(int i=1; i<numRows-1; i++){
	            for(int j=0; j<=col; j++){
	            	int index = j*(2*n-2)+i;
	            	if(index>=0 && index<len){
	            		result += s.substring(index,index+1);
	            	}
	            	index = j*(2*n-2)+2*n-i-2;
	            	if(index>=0 && index<len){
	            		result += s.substring(index,index+1);
	            	}
	            }
	        }
	        for(int i=0; i<=col; i++){
	            int index = i*(2*n-2)+n-1;
	            if(index<len){
	                result += s.substring(index,index+1);
	            }
	        }
	        return result;
	    }
	}

然后看了别人的,真的是让人感叹.

主要就是我们把每列的字符加入到一个字符串中,先直下,从一个StringBuffer数组中0->n-1,然后斜上,从n-2->1,不算最上和最下的两个.

	class Solution {
	    public String convert(String s, int numRows) {
	        int nRows = numRows;
	        int size = s.length();
	        if(size<=1) return s;
	        char[] c = s.toCharArray();
	        StringBuffer[] sb = new StringBuffer[size];
	        for(int i=0; i<sb.length; i++){
	            sb[i] = new StringBuffer();
	        }
	        int id = 0;
	        while(id<size){
	            for(int idx=0; idx<nRows && id<size; idx++){
	                sb[idx].append(c[id++]);
	            }
	            for(int idx=nRows-2; idx>=1 && id<size; idx--){
	                sb[idx].append(c[id++]);
	            }
	        }
	        for(int i=1; i<sb.length; i++){
	            sb[0].append(sb[i]);
	        }
	        return sb[0].toString();
	    }
	}	

本来是想着用python实现一边,但是python总是会产生更好的作法.

	if numRows<=1 or len(s)<=numRows:
            return s
        
    L = [''] * numRows
    index, step = 0, 1
    
    for x in s:
        L[index] += x
        if index == 0:
            step = 1
        elif index == numRows -1:
            step = -1
        index += step
    
    return ''.join(L)	