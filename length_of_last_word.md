## Length of Last Word

Given a string *s* consists of upper/lower-case alphabets and empty space characters `' '`, return the length of last word in the string.

If the last word does not exist, return 0.

**Note:** A word is defined as a character sequence consists of non-apace characters only.

**Example:**

	Input: "Hello World"
	Output: 5

#### Java Solution:

	class Solution {
	    public int lengthOfLastWord(String s) {
	        String[] trmp = s.split(" ");
	        if(trmp==null || trmp.length==0)
	            return 0;
	        return trmp[trmp.length-1].length();
	    }
	}

#### Java Solution:

	class Solution {
	    public int lengthOfLastWord(String s) {
	        int n = s.length()-1;
	        int end = -1;
	        for(int i=n; i>=0; i--){
	        	if(s.charAt(i)!=' '){
	        		end = i;
	        		break;
	        	}
	        }
	        if(n<0 || end<0)
	        	return 0;
	        int start = -1;
	        for(int i=end; i>=0; i--){
	        	if(s.charAt(i)==' '){
	        		start = i;
	        		break;
	        	}
	        }
	        return end-start;
	    }
	}

#### Java Discussion Solution:

	class Solution {
	    public int lengthOfLastWord(String s) {
	        if(s==null || s.length()==0) return 0;
	        int i = s.length()-1;
	        int count = 0;
	        while(i>=0 && s.charAt(i--)==' ');
	        i++;
	        while(i>=0 && s.charAt(i--)!=' ') count++;
	        return count;
	    }
	}

#### Python Solution:

	class Solution:
	    def lengthOfLastWord(self, s):
	        """
	        :type s: str
	        :rtype: int
	        """
	        if not s or len(s)==0:
	            return 0
	        i, count = len(s)-1, 0
	        while i>=0 and s[i]==' ':
	            i -= 1
	        while i>=0 and s[i]!=' ':
	            i -= 1
	            count += 1
	        return count

#### Python Disucssion Solution:

	class Solution:
	    def lengthOfLastWord(self, s):
	        """
	        :type s: str
	        :rtype: int
	        """
	        s = s.strip(' ')
	        return len(s) - 1 - s.rfind(' ')