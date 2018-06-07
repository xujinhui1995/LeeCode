## Add Binary

Given two binary string, return their sum(also a binary string).

The input strings are both **non-empty** and contains only characters `1` or `0`.

**Example 1:**
	
	Input: a = "11", b = "1"
	Output: "100"

**Example 2:**

	Input: a = "1010", b = "1011"
	Output: "10101"

#### Java Solution :

	class Solution {
	    public String addBinary(String a, String b) {
	        int sub = Math.max(a.length(),b.length())-Math.min(a.length(),b.length());
	        for(int i=0; i<sub; i++){
	            if(a.length()>b.length()){
	                b = "0" + b;
	            }else{
	                a = "0" + a;
	            }
	        }
	        int len = a.length();
	        boolean flag = false;
	        String res = "";
	        for(int j=a.length()-1; j>=0; j--){
	            if(a.charAt(j)=='0' && b.charAt(j)=='0'){
	                if(flag){
	                    res = "1" + res;
	                }else{
	                    res = "0" + res;
	                }
	                flag = false;
	            }else if(a.charAt(j)=='1' && b.charAt(j)=='1'){
	                if(flag){
	                    res = "1" + res;
	                }else{
	                    res = "0" + res;
	                }
	                flag = true;
	            }else{
	                if(flag){
	                    res = "0" + res;
	                    flag = true;
	                }else{
	                    res = "1" + res;
	                    flag = false;
	                }
	            }
	        }
	        if(flag) return "1"+res;
	        else return res;
	    }
	}

#### Java Disucussion Solution :

	class Solution {
	    public String addBinary(String a, String b) {
	        int al = a.length();
	        int bl = b.length();
	        int mer = 0;
	        int sub = Math.max(al, bl);
	        char[] resCh = new char[sub+1];
	        for(int i=0; i<sub+1; i++){
	            int ai = (al>i)?(a.charAt(al-1-i)-'0'):0;
	            int bi = bl>i?(b.charAt(bl-1-i)-'0'):0;
	            mer = ai + bi + mer;
	            resCh[sub-i] = (char)(mer%2+'0');
	            mer = mer/2;
	        }
	        int bal = '1' - resCh[0];
	        return new String(resCh, bal, resCh.length-bal);
	    }
	}

#### Python Discussion Solution :

	class Solution:
	    def addBinary(self, a, b):
	        """
	        :type a: str
	        :type b: str
	        :rtype: str
	        """
	        return bin(eval('0b' + a) + eval('0b' + b))[2:]