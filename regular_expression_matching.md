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

3.代码逻辑分析。

- **减小空间复杂度**：为了减小空间复杂度，将二维数组使用一维数组代替

- 首先呢，我们需要考虑三种情况，分别是p为空s不为空，s为空p不为空，和s，p均为空。
	1. **p为空s不为空**。这种情况必然为false。此时res数组的大小为1，不经过三个循环中的任意一个直接返回res[1]=false;
	2. **s为空p不为空**。这种情况取决于`*`的位置，如果恰好在偶数位，且总长度为偶数，则为true，否则为false。因为在数组中，boolean默认的初始化值为false，且res[0][1]必然为false(第一个字符不能为`*`)，所以直接使用初始化值。至于后面的就用`res[j] = res[j-2] && res[j-1]=='*';`这个就是如果j前两个字符外的前j-3个字符为true，且j前两个字符(j-1和j-2位)分别是字符和`*`，则res[j]为true，否则为false。经过一次j=2开始的循环，得到第一组数据，也就是当s串为空的数据。这时只经历第一个循环，然后返回res[n]。
	3. **s,p均为空**。此时，会经历第二个循环的外循环，将res[1]的值置为true，然后返回res[1]。

- 这三种较为特殊的情况得到了处理之后，就是利用动态规划来递归求值了。回顾一下这一段代码。

		char pc,sc,tc;
		boolean pre,cur;
		for(int i=1; i<=m; i++){
		    pre = res[0];
		    res[0] = false;
		    sc = s.charAt(i-1);
		    for(int j=1; j<=n; j++){
		        cur = res[j];
		        pc = p.charAt(j-1);
		        if(pc!='*'){
		            res[j] = pre && (pc==sc || pc=='.');
		        }else{//pc=='*',then it has a preceding char, i.e. j>1
		            tc = p.charAt(j-2);
		            res[j] = res[j-2] || (res[j] && (sc==tc || tc=='.'));
		        }
		        pre = cur;
		    }
		}
	1. 首先说明的就是第一眼看到的问题，j是从1开始的，那再`res[j] = res[j-2] || (res[j] && (sc==tc || tc=='.'));`，`1-2=-1`不会产生越界的情况吗？这里也做了说明，就是因为，pc已经是`*`了，那么pc必然有一个前缀，所以第一次循环肯定不会走到这里来。
	2. 首先分析一下内部逻辑，cur其实就是作为了一个中间变量，真正起作用的是cur。
		- 在if中，pre表示的是res[i-1][j-1]，因为在上一次内循环中，记录了cur = res[i-1][j-1]，然后res[i-1][j-1]更新，然后pre = cur = res[i-1][j-1]。在当前循环中，pre表示的就是res[i-1][j-1]与我们的理论相符合。
		- 在else语句中，本来是应该有三个或条件，现在只剩下两个，是因为第一个被包含在了其中。也即，case2,3位false，case1也为false，case2,3有为true的，那就不用考虑case1了。
		![](https://i.imgur.com/sgrHWKI.png)
			1. 首先是第1,2,3中情况，case2,3中已经有一个为true，那么，显然，结果为true，那也就与case1无关。
			2. 我们重点关注4,5。虽然两个都是均为false，但是内涵是不一样的。
				![](https://i.imgur.com/IBJzXke.png)
				- 4指的是，res[j-2]为false，那&&后面的条件我们也就没有判断的必要了，这种情况我们可以看一下上图。是什么样子。我们用逆反定理，我们假设case1位true，因为j-1位`*`，所以`p[j-2]==s[i-1] || p[j-2]=='.';`又因为`p[j-2]!='*';`，所以，`res[i][j-1] = res[i-1][j-2] && (s[i-1]==p[j-2] || p[j-2]=='.');`，一直后半部分为true，结果也为true，所以`res[i-1][j-2]`为true。这样，我们在加上p[j-2]和p[j-1]，字符加`*`表示出现0次同样成立，也即`res[i-1][j]=true;`。也就是说，case3为true。也就是`case1(true) -> case3(true)`，等同于`case3(false) -> case1(false)`。result也为false。
				- 下面是第五种情况，也即`res[i-1][j-2]`为true，但是`(s[i-1]==p[j-2] || p[j-2]=='.')`为false。因为`p[j-2]！'*'`，所以`res[i][j-1] = res[i-1][j-2] && (s[i-1]==p[j-2] || p[j-2]=='.');`为false。所以result也为false。
		- 最后就是pre=cur的逻辑了。这个主要是通过中间量来取得res[i-1][j-1]。这个前面已经说过了。
		- 内部循环的逻辑已经明了了。
	3. 现在要看的就是代码的外循环了。
		
			for(int i=1; i<=m; i++){
			    pre = res[0];
			    res[0] = false;
			    sc = s.charAt(i-1);
			    for(int j=1; j<=n; j++);
			}
		
		- 第一行主要体现的是首次内部循环，pre也即`res[i-1][j-1]==res[0][0];`也就是`res[0]`。
		- 第二行则主要是`n>1`后，`res[0][j-1]=false`。接下来就是一次次循环，一次次覆盖，最终得到res[n]，也就是结果。