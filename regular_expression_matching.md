## Regular Expression Matching

Implement regular expression matching with support for '.' and '*'.

绝望，深深的绝望

怎么都不对

动态规划

	class Solution {
	    public boolean isMatch(String s, String p) {
		/**
        *this solution is assuming s has no regular expressions.
        *
        *dp:res[i][j]=is s[0,...,i-1] matched with p[0,...,j-1];
        *
        *If p[j-1]!='*',res[i][j] = res[i-1][j-1] && (s[i-1]==p[j-1] || p[j-1]=='.').
        *Otherwise, res[i][j] is true if
        *res[i][j-1] or res[i][j-2] or res[i-1][j] && (s[i-1]==p[j-2] || p[j-2]=='.').
        *int the three cases, the first case is included of the third case.
        *
        *Boundaries: res[0][0]=true;//s=p="".res[i][0]=false,i>0.
        *res[0][j]=is p[0,...,j-1] empty, j>0, and so res[0][1] is false.
        *res[0][j]=p[j-1]=='*'&&res[0][j-2].
        *
        *O(n) space is enough to store a row of res.
        */
	        int m = s.length();
	        int n = p.length();
	        boolean[] res = new boolean[n+1];
	        res[0] = true;
	        for(int i=2; i<=n; i++){
	            res[i] = res[i-2] && p.charAt(i-1)=='*';
	        }
	        boolean pre, cur;
	        char pc, sc, tc;
	        for(int i=1; i<=m; i++){
	            pre = res[0];
	            res[0] = false;
	            sc = s.charAt(i-1);
	            for(int j=1; j<=n; j++){
	                cur = res[j];
	                pc = p.charAt(j-1);
	                if(pc!='*'){
	                    res[j] = pre && (sc==pc || pc=='.');
	                }else{
	                    tc = p.charAt(j-2);
	                    res[j] = res[j-2] || (res[j] && (sc==tc || tc=='.'));
	                }
	                pre = cur;
	            }
	        }
	        return res[n];
	    }
	}

所谓奇淫技巧，这里体现的就是边界的处理。非常干净利落。

下面来分析一下这个算法的具体流程。

1.既然是一个动态规划问题，那就有两个方面主要：

- 首先，这里是求res[i][j]时，根据p[j-1]的值来分两种情况进行求解。
	
- 第二个就是边界问题，也就是初值的求取。

- 还是要先说一下，为了节约空间，将二维数组简化成一个一维数组，根据s字符串的前段子串长度来对数组进行循环利用，并且与此同时，通过调用本身来达到求i-1的目的。

- 我们用res[i][j]来表示前i个s字符串中的子串和前j个p字符串中的子串的匹配情况。

2.求res[i][j]

- 虽然说是在问题中用到了一维数组，但是在理论论证过程中，还是先用二维数组来解释。

- 通配符匹配之所以比较麻烦，主要的问题就是在于regex中含有`*`，这最终导致了regex所能表示的长度和所匹配位置的不确定性。如果不存在`*`,那么`res[i][j]=res[i-1][j-1] && (s[i-1]==p[j-1] || p[j-1]=='.'`。但是如果含有`*`，事情就变得麻烦了一点，但是还是可以看出，导致这种区别的原因就在于p[j-1]是否是`*`。 

- 如果`p[j-1]！='*'`，则`res[i][j]=res[i-1][j-1] && (s[i-1]==p[j-1] || p[j-1]=='.'`。

- 否则，也即`p[j-1]=='*'`，`res[i][j]`的值就具体取决于三种情况，这三种情况其中之一为真，则`res[i][j]`为真。
![](https://i.imgur.com/5aSkprA.png)
这三种情况都是有可能使`res[i][j]`为`true`。

    **first case**：这种情况下就是已经知道res[i][j-1]为true，因为p[j-1]的值为`*`，所以res[i][j]也同样为true，这种情况只不过就是`*`没有代表任何数值，`字符+*`表示的是字符本身而已。
	
	**second case**：这种情况下就是已经知道res[i][j-2]位true，因为p[j-1]的值为`*`，所以res[i][j]也同样为true，这种情况就是说明`字符+*`表示字符出现了0次。

	**third case**：这种情况就是说s中有重复的串，所以`字符+*`就代表了多个字符。所以要加一个`s[i-1]==p[j-2] || p[j-2]=='.'`的判断。
	
	**explanation**：之所以p串会向前推两个，而s串只推一个，是因为s串没有特殊字符，所以一个字符代表一个整体，p串含有特殊字符，可能存在两个字符代表一个整体。所以我们一次前推一个整体就可以了，至于整体前一个，完全可以依靠更前面的部分来实现。