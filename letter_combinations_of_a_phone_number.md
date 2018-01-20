### Letter Combinations of a Phone Number

Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters(just like on the telephone buttons) is given below.

#### java:

	class Solution {
	    public List<String> letterCombinations(String digits) {
	        String[] chart = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
	        List<String> list = new LinkedList<String>();
	        if(digits.length()<=0)
	            return list;
	        for(int i=0; i<digits.length(); i++){
	            List<String> buf = new LinkedList<String>();
	            for(String s : list){
	                buf.add(s);
	            }
	            int singleNum = Integer.parseInt(digits.charAt(i)+"");
	            if(list.isEmpty()){
	                for(int j=0; j<chart[singleNum-2].length(); j++){
	                    list.add(chart[singleNum-2].charAt(j)+"");
	                }
	            }else{
	                list.clear();
	                for(int j=0; j<chart[singleNum-2].length(); j++){
	                    for(String s : buf){
	                        list.add(s+chart[singleNum-2].charAt(j));
	                    }
	                }
	            }
	        }
	        return list;
	    }
	}

#### Discussion Java Solution:
 
	class Solution {
	    public List<String> letterCombinations(String digits) {
	        String[] chart = {"0","1","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
	        LinkedList<String> list = new LinkedList<String>();
	        if(digits.length()<=0)
	            return list;
	        list.add("");
	        for(int i=0; i<digits.length(); i++){
	            int num = Character.getNumericValue(digits.charAt(i));
	            while(list.peek().length()==i){
	                String str = list.remove();
	                for(char s : chart[num].toCharArray())
	                    list.add(str+s);
	            }
	        }
	        return list;
	    }
	}

#### python1：

	class Solution:
	    def letterCombinations(self, digits):
	        """
	        :type digits: str
	        :rtype: List[str]
	        """
	        if '' == digits: return []
	        kvmaps = {
	            '2': 'abc',
	            '3': 'def',
	            '4': 'ghi',
	            '5': 'jkl',
	            '6': 'mno',
	            '7': 'pqrs',
	            '8': 'tuv',
	            '9': 'wxyz'
	        }
	        res = ['']
	        for c in digits:
	            tmp = []
	            for y in res:
	                for x in kvmaps[c]:
	                    tmp.append(y+x)
	            res = tmp
	        return res

#### python2：

	from functools import reduce
	class Solution:
	    def letterCombinations(self, digits):
	        """
	        :type digits: str
	        :rtype: List[str]
	        """
	        if '' == digits: return []
	        kvmaps = {
	            '2': 'abc',
	            '3': 'def',
	            '4': 'ghi',
	            '5': 'jkl',
	            '6': 'mno',
	            '7': 'pqrs',
	            '8': 'tuv',
	            '9': 'wxyz'
	        }
	        return reduce(lambda acc, digits: [x + y for x in acc for y in kvmaps[digits]], digits, [''])