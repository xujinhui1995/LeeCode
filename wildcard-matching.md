## Wildcard Matching

Implement wildcard pattern matching with support for `'?'` and `'*'`.

	'?' Matches any single character.
	'*' Matches any sequence of characters (including the empty sequence).
	
	The matching should cover the entire input string (not psrtial).
	
	The function prototype should be:
	bool isMatch(const char *s, const char *p)
	
	Some examples:
	isMatch("aa", "a") → false
	isMatch("aa", "aa") → true
	isMatch("aaa", "aa") → false
	isMatch("aa", "*") → true
	isMatch("aa", "a*") → true
	isMatch("ab", "?*") → true
	isMatch("aab", "c*a*b*") → false

这个问题的重点我觉得是初始值和上下的衔接。

#### Java Solution:

	class Solution {
	    public boolean isMatch(String s, String p) {
	        int sLength = s.length();
	    	int pLength = p.length();
	        if(sLength==0&&pLength==0)
	            return true;
	        if(sLength!=0&&pLength==0)
	            return false;
	    	boolean[][] res = new boolean[s.length()+1][p.length()+1];
	    	for(int i=0; i<=sLength; i++){
	    		res[i][0] = false;
	    	}
	        for(int i=0; i<pLength; i++){
	            if(p.charAt(i)=='*'){
	                if(i==0)
	                    res[0][i+1]=true;
	                else
	                    res[0][i+1]=res[0][i];
	            }else{
	                res[0][i+1]=false;
	            }
	        }
	        
	    	res[0][0] = true;
	    	//数组res表示对字符串s和p分别截取i和j长度的substring后的匹配结果。
	    	//其中，如果模式串长度为0而匹配串长度不为0，则必然为false。同为0则为true。
	    	//嵌套两层，外层循环匹配串，内层循环模式串。
	    	//如果p[j]=='*'，如果j==1，则res[i][j]为true，否则res[i][j]=res[i][j-1]||res[i-1][j-1]。
	    	//如果p[j]=='?'，则res[i][j]=res[i-1][j-1];
	    	//否则，res[i][j]=res[i-1][j-1]&&s[i]==p[j];
	    	for(int i=1; i<=s.length(); i++){
	    		for(int j=1; j<=p.length(); j++){
	    			if(p.charAt(j-1)=='*'){
	    				if(j==1)
	    					res[i][j]=true;
	    				else
	    					res[i][j]=res[i][j-1]||res[i-1][j-1]||res[i-1][j];
	    			}else if(p.charAt(j-1)=='?'){
	    				res[i][j]=res[i-1][j-1];
	    			}else{
	    				res[i][j]=res[i-1][j-1]&&(s.charAt(i-1)==p.charAt(j-1));
	    			}
	    		}
	    	}
	        //System.out.println(s.length()+":"+p.length());
	    	return res[s.length()][p.length()];
	        //return ((p.length()==1)&&(p.charAt(0)=='*'));
	    }
	}

缝缝补补终于通过。

#### Python Solution:

	class Solution:
	    def isMatch(self, s, p):
	        """
	        :type s: str
	        :type p: str
	        :rtype: bool
	        """
	        res = [[0 for i in range(len(p)+1)] for i in range(len(s)+1)]
	        for i in range(len(s)+1):
	            res[i][0]=False
	        for i in range(len(p)):
	            if p[i]=='*':
	                if i==0:
	                    res[0][i+1]=True
	                else:
	                    res[0][i+1]=res[0][i]
	            else:
	                res[0][i+1]=False
	        res[0][0]=True
	        for i in range(1,len(s)+1):
	            for j in range(1,len(p)+1):
	                if p[j-1]=='*':
	                    if j==1:
	                        res[i][j]=True
	                    else:
	                        res[i][j]=res[i-1][j] or res[i-1][j-1] or res[i][j-1]
	                elif p[j-1]=="?":
	                    res[i][j] = res[i-1][j-1]
	                else:
	                    res[i][j] = res[i-1][j-1] and s[i-1]==p[j-1]
	        return res[-1][-1]

后记：
	
	二维数组的创建
	一个i*j的数组
	matrix = [[0 for i in range(j)] for i in range(i)]

