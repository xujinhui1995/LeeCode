### Longest Common Prefix

Write a function to find the longest common prefix string amongst an array of strings.

很奇怪,这一段在本地没有任何问题,但是Leecode上就是不对.
	
	class Solution {
	    public String longestCommonPrefix(String[] strs) {
	        if(strs.length==0)
	            return "";
	        if(strs.length==1)
	            return strs[0];
	        int index = 0;
	        boolean flag = true;
	        for(int i=0; i<strs[0].length(); i++){
	            for(int j=1; j<strs.length; j++){
	                if(i<strs[j].length() && strs[j]!="" && strs[0].substring(0,i+1)==strs[j].substring(0,i+1)){
	                    index = i+1;
	                }else{
	                    flag = false;
	                    break;
	                }
	            }
	            if(!flag)
	                break;
	        }
	        if(index==0)
	            return "";
	        return strs[0].substring(0,index);
	    }
	}