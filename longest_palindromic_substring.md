## Longest Palindromic Substring

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

有一个不认识的单词:palindromic:复发的,再发的

其实就是找出最长的回文子串.

其实应该先直接全部翻译一下那一个短语的,这样就不至于去看别人的代码了,本来自己想到的是,控制两个变量,分别从两侧向中间进行判断,只是确实得需要利用一个函数来实现,因为这两个值是要分开的,最后又发现,其实还是从中间向两侧进行判断比较合适,因为如果从两侧向中间,就相当于任意两个不同的字符都要做一次两侧,这样就比较CN2,就是对字符进行组合,而从中间,则只需要比较线性的次数,但是也要考虑到就是最后的最长回文长度可能是偶数可能是奇数.

	class Solution {
	    private int begin = 0;
	    private int end = 0;
	    private int length = 1;
	    public String longestPalindrome(String s) {
	        int size = s.length();
	        if(size < 2) return s;
	        for(int i=0; i<size-1; i++){
	            judge(s, i, i);//最长回文串长度为奇数
	            judge(s, i, i+1);
	        }
	        return s.substring(begin,end+1);
	    }
	    private void judge(String s, int i, int j){
	        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){
	            i--;
	            j++;
	        }
	        if(j-i-1>length){
	            begin = i+1;
	            end = j-1;
	            length = j-i-1;
	        }
		}
	}

python的一个优势就是可以直接使用数组表示字符串并且进行反向而不显得麻烦.

但是,看到了高赞的解答其实还是和java的实现不一样,这个更像是一种从两端向中间的方法,也不是向中间,就是仅仅确定两端就好了.

首先就是,我们一最右侧字符作为依据进行增长判断,然后右侧字符每增加一个,那最长的回文串增加至多两个,这个因为是对称的,右边加一个,左边加一个才两个,左边不加就只有一个,这种情况一般就是重复字符串和刚开始的时候.

因为重复字符串很少,所以这个就没有太高的利用,如果不是重复字符串,则一次增加一个的情况其实在一个查找中只用到一次,所以放在后面.

- 首先就是假设最长回文序列长度为maxLen,右侧的index已经循环到了i,maxLen的初始长度为1,除了第一次循环,i=0,小于maxLen意外,其他的情况下,i不会小于maxLen,因为,i是需要被测量的量,maxLen>i意味着,s[i]也在最长回文序列之中,这与我们没有测量i是矛盾的.
- 那maxLen=i呢,这就意味着,i之前的所有字符组成的串是一个回文序列.这种情况,要想继续增加,那么,只能增加一个最多,因为,左边已经到头了,没有办法增加左侧字符,这种情况一般出现在i=1和重复串的情况下,也就是我们先前讨论的增加一个的情况.
- 最后就是maxLen>i,这样的话其实我们就可以直接增加两个了,这时可能有疑问就是为什么不会只增加一个,如果只增加一个,那么说明,其实是可能的哎,那这样我们就右边加一.

逻辑:我们要增加两个,必须maxLen>i并且回文,如果不成立,就增加一个.

	class Solution:
		def longestPalindrome(self, s):
			"""
			:type s: str
			:rtype: str
			"""
			if len(s)<2:
				return s
			maxLen = 1
			start = 0
			for i in range(len(s)):
				if i-maxLen>=1 and s[i-maxLen-1:i+1]==s[i-maxLen-1:i+1][::-1]:
					start = i-maxLen-1
					maxLen = maxLen + 2
					continue
				if i-maxLen>=0 and s[i-maxLen:i+1]==s[i-maxLen:i+1][::-1]:
					start = i-maxLen
					maxLen += 1
			return s[start:start+maxLen]