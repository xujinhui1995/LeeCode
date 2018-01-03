### Roman to Integer

Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.

比较某一个字符和其后面第一个字符所代表数值的大小,大则加此字符,否则则减去.

	import java.util.*;
	class Solution {
	    public int romanToInt(String s) {
	        Map<String, Integer> map = new HashMap<String, Integer>();
	        map.put("I", 1);
	        map.put("X", 10);
	        map.put("C", 100);
	        map.put("M", 1000);
	        map.put("V", 5);
	        map.put("L", 50);
	        map.put("D", 500);
	        int num = map.get(s.substring(s.length()-1, s.length()));
	        int index = s.length()-2;
	        while(index>=0){
	            int m = map.get(s.substring(index, index+1));
	            int n = map.get(s.substring(index+1, index+2));
	            if(m<n){
	                num -= m;
	            }else{
	                num += m;
	            }
	            index--;
	        }
	        return num;
	    }
	}

思想都是一样的:


	class Solution(object):
	    def maxArea(self, s):
	    '''
	    trpe s: string
	    rType: int
	    '''
	    map = {'I':1, 'X':10, 'C':100, 'M':1000, 'V':5, 'L':50, 'D':500}
	    num, pre = 0, 1000
	    for i in [map[j] for j in s]:
	        num, pre =  num + i - 2*pre if i > pre else num + i, i
	    return num