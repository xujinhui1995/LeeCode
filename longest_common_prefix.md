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
	                if(i<strs[j].length() && strs[j]!="" && strs[0].substring(0,i+1).equals(strs[j].substring(0,i+1))){
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

#### Problem solution 2

	class Solution {
	    public String longestCommonPrefix(String[] strs) {
	        if(strs.length<=0)
	            return "";
	        if(strs.length==1)
	            return strs[0];
	        Arrays.sort(strs);
	        int index = 0;
	        for(int i=0; i<strs[0].length(); i++){
	            if(strs[0].substring(0,i+1).equals(strs[strs.length-1].substring(0,i+1))){
	                index = i;
	            }else{
	                break;
	            }
	        }
	        if(index==0)
	            return "";
	        return strs[0].substring(0,index+1);
	    }
	}

#### Discussion Solution 1(useing array sort)

	class Solution {
	    public String longestCommonPrefix(String[] strs) {
	        if(strs.length<=0)
	            return "";
	        if(strs.length==1)
	            return strs[0];
	        Arrays.sort(strs);
	        char[] bgn = strs[0].toCharArray();
	        char[] end = strs[strs.length-1].toCharArray();
	        StringBuffer sb = new StringBuffer();
	        for(int i=0; i<bgn.length && i<end.length; i++){
	            if(bgn[i]==end[i]){
	                sb.append(bgn[i]);
	            }else{
	                break;
	            }
	        }
	        return sb.toString();
	    }
	}


#### Discussion Solution 2

	class Solution {
	    public String longestCommonPrefix(String[] strs) {
	        if(strs==null || strs.length==0){
	            return "";
	        }
	        int i = 1;
	        String pre = strs[0];
	        while(i<strs.length){
	            while(strs[i].indexOf(pre)!=0){
	                pre = pre.substring(0,pre.length()-1);
	            }
	            i++;
	        }
	        return pre;
	    }
	}

#### python Discussion Solution1:

	class Solution:
	    def longestCommonPrefix(self, strs):
	        """
	        :type strs: List[str]
	        :rtype: str
	        """
	        if not strs:
	            return ""
	        shortest = min(strs,key=len)
	        for i,ch in enumerate(shortest):
	            for others in strs:
	                if others[i] != ch:
	                    return shortest[:i]
	        return shortest

#### python Discussion Solution2:

	class Solution:
	    def longestCommonPrefix(self, strs):
	        """
	        :type strs: List[str]
	        :rtype: str
	        """
	        if not strs:
	            return ""
	        for i, letter_group in enumerate(zip(*strs)):
	            if len(set(letter_group)) > 1:
	                return strs[0][:i]
	        return min(strs)

question1: the difference between zip(strs) and zip(*strs)

question2:the problem solutions