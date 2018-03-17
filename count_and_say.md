## Count and Say

The count-and-say sequence is the sequence of integers with the first five terms as following:

	1.  	1
	2.  	11
	3.  	1211
	4.  	111221

`1` is read off as `"one 1"` or `11`.
`11` is read off as `"two 1s"` or `21`.
`21` is read off as `"one 2`, then `one 1"` or `1211`.

Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each them of the sequence of integers will be represented as a string.

#### Java Discussion Solution:

	class Solution {
	    public String countAndSay(int n) {
	        StringBuilder curr = new StringBuilder();
			StringBuilder prev = curr;
			curr.append(1);
			for(int i=1; i<n; i++){
				int count = 1;
				prev = curr;
				curr = new StringBuilder();
				char say = prev.charAt(0);
				for(int j=1; j<prev.length(); j++){
					if(prev.charAt(j)!=say){
						curr.append(count).append(say);
						count = 1;
						say = prev.charAt(j);
					}else{
						count++;
					}
				}
				curr.append(count).append(say);
			}
			return curr.toString();
	    }
	}


#### Python Solution:

	class Solution:
	    def countAndSay(self, n):
	        """
	        :type n: int
	        :rtype: str
	        """
	        curr = "1"
	        prev = curr
	        
	        for i in range(1,n):
	            count = 1
	            prev = curr
	            say = prev[0]
	            curr = ""
	            for j in range(1,len(prev)):
	                if prev[j]!=say:
	                    curr+=str(count)
	                    curr+=str(say)
	                    say = prev[j]
	                    count = 1
	                else:
	                    count+=1
	            curr+=str(count)
	            curr+=str(say)
	        return curr

#### Python Discussion Solution 1:

	def countAndSay(self, n):
	    s = '1'
	    for _ in range(n - 1):
	        s = re.sub(r'(.)\1*', lambda m: str(len(m.group(0))) + m.group(1), s)
	    return s

#### Python Discussion Solution 2:

	def countAndSay(self, n):
	    s = '1'
	    for _ in range(n - 1):
	        s = ''.join(str(len(group)) + digit
	                    for group, digit in re.findall(r'((.)\2*)', s))
	    return s

#### Python Discussion Solution 2:

	def countAndSay(self, n):
	    s = '1'
	    for _ in range(n - 1):
	        s = ''.join(str(len(list(group))) + digit
	                    for digit, group in itertools.groupby(s))
	    return s