##Longest Substring Without Repeating Characters##
###Given a string, find the length of the longest substring without repeating characters.###

这个问题首先想到的就是暴力穷举了,从长到短,但是必然因为复杂度太高而放弃.

然后就是昨天上课时老师说到的一个问题,对于这个问题的话就是如果有一段字符串时有重复的,那么所有包含这个字符串的字符串都被否定.但是这个实现起来好像是很麻烦的样子.

最后就是,既然字符串都是字母,那字符串的最长的长度只有24,那我们只需要分析24*n次了.可以试一下.