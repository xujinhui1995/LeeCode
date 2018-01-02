## Integer to Roman

Given an integer, convert it to a roman numeral.
Input is guaranteed to be within the range from 1 to 3999.

	class Solution {
	    public String intToRoman(int num) {
	        String[] bas = {"","I","II","III","IV","V","VI","VII","VIII","IX","X"};
	        String result = "";
	        if(num<=10)
	            return bas[num];
	        result += bas[num%10];
	        num /= 10;
	        int m = 1;
	        while(num>0){
	            int temp = num%10;
	            if(m==1){
	                if(temp<5)
	                    if(temp==4)
	                        result = "XL" + result;
	                    else
	                        result = "XXX".substring(0,temp) + result;
	                else
	                    if(temp==9)
	                        result = "XC" + result;
	                    else
	                        result = "LXXX".substring(0,temp-4) + result;
	            }else if(m==2){
	                if(temp<5)
	                    if(temp==4)
	                        result = "CD" + result;
	                    else
	                        result = "CCC".substring(0,temp) + result;
	                else
	                    if(temp==9)
	                        result = "CM" + result;
	                    else
	                        result = "DCCC".substring(0,temp-4) + result;
	            }else{
	                    result = "MMM".substring(0,temp) + result;
	            }  
	            m++;
	            num = num / 10;
	         }
	         return result;
	    }
	}

复用

	class Solution {
	    public String intToRoman(int num) {
	        String[] bas = {"","I","II","III","IV","V","VI","VII","VIII","IX","X"};
	        String[] pattern = {"","XL","CD","","XXX","CCC","MMM","XC","CM","","LXXX","DCCC",""};
	        String result = "";
	        if(num<=10)
	            return bas[num];
	        result += bas[num%10];
	        num /= 10;
	        int m = 1;
	        while(num>0){
	            int temp = num%10;
	            if(temp<5)
	                if(temp==4)
	                    result = pattern[m] + result;
	                else
	                    result = pattern[m+3].substring(0,temp) + result;
	            else
	                if(temp==9)
	                    result = pattern[m+6] + result;
	                else
	                    result = pattern[m+9].substring(0,temp-4) + result;
	            m++;
	            num = num / 10;
	         }
	         return result;
	    }
	}

确实，这个代码简洁明了，逻辑清晰，符合好代码的特征。或许，应该试着明白，什么是好代码，以及优雅的。

	class Solution {
	    public String intToRoman(int num) {
	        String[] I = {"","I","II","III","IV","V","VI","VII","VIII","IX"};
	        String[] X = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
	        String[] C = {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
	        String[] M = {"","M","MM","MMM"};
	        return M[num/1000] + C[(num/100)%10] + X[(num/10)%10] + I[num%10];
	    }
	}

python

	class Solution:
	    def intToRoman(self, num):
	        """
	        :type num: int
	        :rtype: str
	        """
	        I = ["","I","II","III","IV","V","VI","VII","VIII","IX"]
	        X = ["","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"]
	        C = ["","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"]
	        M = ["","M","MM","MMM"]
	        return M[num//1000] + C[(num//100)%10] + X[(num//10)%10] + I[num%10];