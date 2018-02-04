### Implement strStr()

Implement <font color='blue'>strStr()</font>

Return the index of the first occurrence of needle in haystack，or **-1** if needle is not part of haystack.

	class Solution {
	    public int strStr(String haystack, String needle) {
	        if((haystack.length()<=0&&needle.length()<=0))
	            return 0;
	        if(needle.length()<=0)
	            return 0;
	        int sLength = haystack.length();
	        int pLength = needle.length();
	        int[] next = new int[pLength];
	        getNext(next, needle);
	        int i = 0;
	        int j = 0;
	        while(i<sLength && j<pLength){
	            if(j==-1 || haystack.charAt(i)==needle.charAt(j)){
	                i++;
	                j++;
	            }else{
	                j=next[j];
	            }
	        }
	        if(j==pLength)
	            return i-j;
	        else
	            return -1;
	    }
	    public void getNext(int[] next, String needle){
	        int j = 0;
	        int k = -1;
	        int length = needle.length();
	        next[0] = -1;
	        while(j<length-1){
	            if(k==-1 || needle.charAt(j)==needle.charAt(k)){
	                ++j;
	                ++k;
	                if(needle.charAt(j)!=needle.charAt(k))
	                    next[j] = k;
	                else
	                    next[j] = next[k];
	            }else{
	                k = next[k];
	            }
	        }
	    }
	}

----

	class Solution:
	    def strStr(self, haystack, needle):
	        """
	        :type haystack: str
	        :type needle: str
	        :rtype: int
	        """
	        def getNext(self,p,next):
	            leng = len(p)
	            j = 0
	            next.append(-1)
	            k = -1
	            while j < leng-1:
	                if k==-1 or p[j]==p[k]:
	                    k += 1
	                    j += 1
	                    if p[j] != p[k]:
	                        next.append(k)
	                    else:
	                        next.append(next[k])
	                else:
	                    k = next[k]
	        s, p = haystack, needle
	        sLen = len(s)
	        pLen = len(p)
	        i = j = 0
	        next = []
	        getNext(self,p,next)
	        while(i<sLen and j<pLen):
	            if(j==-1 or s[i]==p[j]):
	                i += 1
	                j += 1
	            else:
	                j = next[j]
	        if j==pLen:
	            return i - j
	        else:
	            return -1