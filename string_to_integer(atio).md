## String to Integer (atio)

Implement atoi to convert a string to an integer.

其实这是一个非常平常的问题，平常到几乎不会有人关注，但是你又不得不承认这个简单的问题包含了很多很细小的基础知识。

一个字符串转换成一个整数，其实道理每个人都能说出来，就是循环，将前一次循环的结果*10,然后加上新的index的值。毕竟这就像小学生实数一样。

但是里面的整型的范围，溢出之后的处理，碰到空格的处理，碰到中间有字符之后的处理，正负号的处理都是有很多需要去注意的地方。

这个实现确实是耗费了很长的时间，主要问题就是在于没有去仔细看题目中有没有提到匹配过程中遇到非数字之后是直接抛异常还是中断，有空格是直接报错还是忽略，溢出之后是返回Integer.MAX_VALUE或者Integer.MIN_VALUE。

反正最后通过不断试验，发现就是匹配中遇到非数字字符后中断，返回非数字字符之前的整数，忽略空格，除了首位可以是标志位其余均为数字，溢出之后，正数取Integer.MAX_VALUE，负数取Integer.MIN_VALUE。

一个粗制滥造的实现：

	class Solution {
	    public int myAtoi(String str){
	        str = str.trim();
	        int size = str.length();
	        boolean flag = false;
	        int tail = 10;
	        long result = 0;
	        for(int i=0; i<size; i++){
	            if((result+"").length()>10 && flag)
		            return -2147483648;
		        if((result+"").length()>10 && !flag)
		            return 2147483647;
	            if(str.charAt(i)=='-' && i==0){
	                flag = true;
	                continue; 
	            }
	            if(str.charAt(i)=='+' && i==0){
	                continue; 
	            }
	            if(str.charAt(i)>='0' && str.charAt(i)<='9'){
	                result = result*tail + str.charAt(i)-'0';
	            }else{
	                break;
	            }
	        }
	        if((result+"").length()>10 && flag)
	            return -2147483648;
	        if((result+"").length()>10 && !flag)
	            return 2147483647;
	        if(flag)
	            result = -1*result; 
	         if(result>2147483647)
		        	result = 2147483647;
		        if(result<-2147483648)
		        	result = -2147483648;
	        return (int) result; 
	    }
	}

基本逻辑是比较清晰的，就是有过多的判断。