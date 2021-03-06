##Longest Substring Without Repeating Characters##
###Given a string, find the length of the longest substring without repeating characters.###

这个问题首先想到的就是暴力穷举了,从长到短,但是必然因为复杂度太高而放弃.

然后就是昨天上课时老师说到的一个问题,对于这个问题的话就是如果有一段字符串时有重复的,那么所有包含这个字符串的字符串都被否定.但是这个实现起来好像是很麻烦的样子.

最后就是,既然字符串都是字母,那字符串的最长的长度只有24,那我们只需要分析24*n次了.可以试一下.

结果就是出现的字符不只是字母而已,于是结果自然的复杂度过高.

自己确实是难以想出来,于是就借鉴了他人的想法,但也还是想了很久才搞明白.

这个问题的解决方案其实类似于KMP算法,找出来匹配的字符串,只不过那个是解决的左边标志位的问题,而这个解决的是右边变量的问题.

我们先设立一个左边的标志位left,然后一个临时位index,再加上一个map,存储的是每一个字符c的上一次出现的位置加一.于是,如果说我们的在index位的字符已经在map中存在了,那就将左边的标志位置为map中字符c的value值.因为不能重复,并且字符c左边的字符串已经经过计算,所以字符c左边的值包括字符c都可以被忽略.

![](https://i.imgur.com/oqfyOvU.png)

还有一个问题,如果字符c的位加一小于左边标志位,那么,也应该加上.

接下来就是实现了.但是我觉得现在java的劣势又开始体现了.

	import java.util.*;
	class Solution {
	    public int lengthOfLongestSubstring(String s) {
	        int leftIndex = 0;
	        int longest = 0;
	        int length = s.length();
	        Map<Character,Integer> m = new HashMap<Character,Integer>();
	        for(int i=0; i<length; i++){
	            char c = s.charAt(i);
	            if(!m.containsKey(c) || m.get(c)<leftIndex){
	                longest = ((i-leftIndex+1)>longest?(i-leftIndex+1):longest);
	            }else{
	                leftIndex = m.get(c);
	            }
	            m.put(c,i+1);
	        }
	        return longest;
	    }
	}

也是非常的坎坷了,但是Python的实现应该就会简单很多.

	class Solution:
	    def lengthOfLongestSubstring(self, s):
	        """
	        :type s: str
	        :rtype: int
	        """
	        longest = 0
	        leftIndex = 0
	        term = {}
	        for index, each in enumerate(s):
	            if each not in term or term[each]<leftIndex:
	                longest = max(index-leftIndex+1,longest)
	            else:
	                leftIndex = term[each]
	            term[each] = index+1
	        return longest