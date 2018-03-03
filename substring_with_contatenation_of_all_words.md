## Substring with Concatenation of All Words

You are given a string, **b**, and a list of words, **words**, that are all of the same length. Find all starting indeces of substring(s) in **s** that is a concatenation of each word in **words** exactly once and without any intervening characters.

#### Java Discussion Solution

	class Solution {
	    public List<Integer> findSubstring(String s, String[] words) {
			//统计words中的字符串出现的顺序
	        //s从头开始判断，截取len长度的字符串与words中的字符串进行比对，如果存在
	        //就将seen中的计数加一，如果seen中某字符串中出现的次数多于counts，表明有重复
	        //这时如果前面的j和num相等，则表明i到i+j*len这一段字符串由words中的字符串唯一组成。
	        //不多也不少。
	        Map<String,Integer> counts = new HashMap<String,Integer>();
	        for (String word : words){
	            counts.put(word, counts.getOrDefault(word, 0)+1);
	        }
	        int length = s.length();
	        int num = words.length;
	        int len = words[0].length();
	        List<Integer> indexes = new LinkedList<Integer>();
	        for (int i=0; i<length-num*len+1; i++){
	            int j=0;
	            Map<String,Integer> seen = new HashMap<String,Integer>();
	            while(j<num){
	                String word = s.substring(i+j*len, i+(j+1)*len);
	                if(counts.containsKey(word)){
	                    seen.put(word, seen.getOrDefault(word, 0)+1);
	                    if(seen.get(word)>counts.getOrDefault(word, 0)){
	                        break;
	                    }
	                }else{
	                    break;
	                }
	                j++;
	            }
	            if(j==num)
	                indexes.add(i);
	        }
	        return indexes;
	    }
	}

#### Python Discussion Solution

class Solution:
    
    def findSubstring(self, s, words):
        """
        :type s: str
        :type words: List[str]
        :rtype: List[int]
        """
        if not s or not words or not words[0]:
            return []
        s_len = len(s)
        word_len= len(words[0])
        words_length = len(words)*word_len
        counts = {}
        for word in words:
            counts[word] = counts[word] + 1 if word in counts else 1
        ans = []
		//在循环内已经将基本的可能性遍历，之所以循环是为了防止弹簧型words。
        for i in range(min(word_len, s_len - words_length + 1)):
            self._findSubstring(i, s_len, word_len, words_length, s, counts, ans)
        return ans
    def _findSubstring(self, i, s_len, word_len, words_length, s, counts, ans):
            seen = {}
            l = i;
            while i + word_len <= s_len:
                word = s[i:i + word_len]
                i += word_len
                if word not in counts:
                    l = i
                    seen.clear()
                else:
                    seen[word] = seen[word] + 1 if word in seen else 1
                    while seen[word] > counts[word]:
                        seen[s[l:l + word_len]] -= 1
                        l += word_len
                    if i - l == words_length:
                        ans.append(l)