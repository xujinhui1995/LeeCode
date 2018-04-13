## Group Anagrams

Given an array of strings, group anagrams together.

For example, given:`["tea", "tae", "tan", "ate", "nat", "bat"]`,

Return:

	[
	  ["ate", "eat","tea"],
	  ["nat","tan"],
	  ["bat"]
	]

**Note:** All inputs will be in lower-case.

#### Java Wrong Solution:
	
	//本来是想着按照每个字符串所包含的字符来进行计算，类似于MD5加密
	//只不过好像总是有重复。	
	class Solution {
	    public List<List<String>> groupAnagrams(String[] strs) {
	        List<List<String>> res = new LinkedList<List<String>>();
	    	int[] count = new int[strs.length];
	    	int[] bount = new int[strs.length];
	    	for(int i=0; i<strs.length; i++){	
	    		int temp = 1;
	    		for(int j=0; j<strs[i].length(); j++){
	    			temp *= (strs[i].charAt(j)-'a'+1);
	    			count[i] += (strs[i].charAt(j)-'a');
	    		}
	    		count[i] = temp*count[i] - count[i];
	    		bount[i] = count[i];
	    	}
	    	Arrays.sort(count);
	    	for(int i=0; i<strs.length; i++){
	    		if(i==0||count[i-1]!=count[i]){
	    			List<String> list = new LinkedList<String>();
	    			for(int j=0; j<strs.length; j++){
	    				if(bount[j]==count[i]){
	    					list.add(strs[j]);
	    				}
	    			}
	    			res.add(list);
	    		}
	    	}
	    	return res;
	    }
	}

#### Java Discussion Solution:

	class Solution {
	    public List<List<String>> groupAnagrams(String[] strs) {
			//方案跟简单，写的很好
	        if(strs==null || strs.length==0)
	    		return new LinkedList<List<String>>();
	    	Map<String, List<String>> map = new HashMap<String, List<String>>();
	    	for(String s : strs){
	    		char[] sc = s.toCharArray();
	    		Arrays.sort(sc);
	    		String keyStr = String.valueOf(sc);
	    		if(!map.containsKey(keyStr))
	    			map.put(keyStr, new LinkedList<String>());
	    		map.get(keyStr).add(s);
	    	}
	    	return new LinkedList<List<String>>(map.values());
	    }
	}

#### Java Discussion Solution:

	class Solution:
	    def groupAnagrams(self, strs):
	        """
	        :type strs: List[str]
	        :rtype: List[List[str]]
	        """
	        //python 丰富的函数
			dic = {}
	        for item in sorted(strs):
	            sortedItem = ''.join(sorted(item))
	            dic[sortedItem] = dic.get(sortedItem, []) + [item]
	        res = []
	        for i in dic.values():
	            res.append(i)
	        return res