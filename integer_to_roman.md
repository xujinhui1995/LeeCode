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