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

python的一个优势就是可以直接