## Multiply Strings

Given two non-negative integers `num1` and `num2` represented as strings, return the product of `num1` and `num2`.

**Note:**
1. The length of both `num1` and `num2` is < 110.
2. Both `num1` and `num2` contains only digits `0-9`.
3. Both `num1` and `num2` does not contain any leading zero.
4. You **must not use any built-in BigInteger library** or **convert the inputs to integer** directly.


#### Java Solution:

	class Solution {
	    public String multiply(String num1, String num2) {
	        if(num1.equals("0")||num2.equals("0"))
	            return "0";
	        int addPlus = 0;
	    	String res = "";
	        if(num1.length()>num2.length()){
	            res = num1;
	            num1 = num2;
	            num2 = res;
	            res = "";
	        }
	    	for(int i=num1.length()-1; i>=0; i--){
	    		String temp = "";
	    		for(int j=num2.length()-1; j>=0; j--){
	    			int singleRes = Integer.parseInt(num1.charAt(i)+"")*Integer.parseInt(num2.charAt(j)+"");
	    			int singleNum = (singleRes + addPlus)%10;
	    			temp = singleNum + temp;
	    			addPlus = (singleRes + addPlus)/10;
	    		}
	            if(addPlus>0){
	    		    temp = addPlus + temp;
	                addPlus = 0;
	            }
	    		for(int j=0; j<num1.length()-1-i; j++){
	    			temp = temp + "0";
	    		}
	    		String ttemp = "";
	            if(res.length()==0){
	                ttemp = temp;
	            }else{
	            	ttemp = add(res, temp);
	            }
	    		res = ttemp;
	    	}
	    	return res;
	    }
		private static String add(String res, String temp) {
			// TODO Auto-generated method stub'
			String ttemp = "";
			int sub = Math.abs(res.length()-temp.length());
			for(int i=0; i<sub; i++) {
				if(temp.length()>res.length()) {
					res = "0" + res;
				}
				if(res.length()>temp.length()) {
					temp = "0" + temp;
				}
			}
			int addPlus = 0;
			for(int i=temp.length()-1; i>=0; i--) {
				int singleNum = Integer.parseInt(""+temp.charAt(i))+Integer.parseInt(""+res.charAt(i));
				int single = (singleNum + addPlus)%10;
				addPlus = (singleNum + addPlus)/10;
				ttemp = single + ttemp;
			}
			if(addPlus>0)
				ttemp = addPlus + ttemp;
			return ttemp;
	    }
	}

世上最悲惨的事莫过于终于理清了逻辑，却提示你超时。

#### Java Discussion Solution:

	class Solution {
	    public String multiply(String num1, String num2) {
	        int m = num1.length();
	    	 int n = num2.length();
	    	 int[] pos = new int[m+n];
	    	 for(int i=m-1; i>=0; i--){
	    	 	for(int j=n-1; j>=0; j--){
	    	 		int mul = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
	    	 		int p1 = i+j;
	    	 		int p2 = i+j+1;
	    	 		int sum = mul + pos[p2];
	    	 		pos[p1] += sum/10;
	    	 		pos[p2] = sum%10;
	    	 	}
	    	 }
	    	 StringBuilder sb = new StringBuilder();
	    	 for(int p : pos) if(!(sb.length()==0&&p==0)) sb.append(p);
	    	 return sb.length()==0?"0":sb.toString();
	    }
	}

#### Python Solution:

	class Solution:
	    def multiply(self, num1, num2):
	        """
	        :type num1: str
	        :type num2: str
	        :rtype: str
	        """
	        if int(num1[0])==0 or int(num2[0])==0:
	            return "0"
	        m, n = len(num1), len(num2)
	        pos = [0]*(m+n)
	        for i in range(m-1,  -1, -1):
	            for j in range(n-1, -1, -1):
	                mul = int(num1[i])*int(num2[j])
	                p1, p2 = i+j, i+j+1
	                sum = mul + pos[p2]
	                pos[p1] += sum//10
	                pos[p2] = sum%10
	        res = ""
	        for i in range(len(pos)):
	            if not (len(res)==0 and pos[i]==0):
	                res += str(pos[i])
	        if len(res)==0:
	            return "0"
	        return res

#### Python Discussion Solution：

	class Solution:
	    def multiply(self, num1, num2):
	        """
	        :type num1: str
	        :type num2: str
	        :rtype: str
	        """
	        product = [0]*(len(num1)+len(num2))
	        pos = len(product)-1
	        for n1 in reversed(num1):
	            tempPos = pos
	            for n2 in reversed(num2):
	                product[tempPos] += int(n1) * int(n2)
	                product[tempPos-1] += product[tempPos]//10
	                product[tempPos] %= 10
	                tempPos -= 1
	            pos -= 1
	        pt = 0
	        while pt < len(product)-1 and product[pt] == 0:
	            pt += 1
	        return ''.join(map(str, product[pt:]))