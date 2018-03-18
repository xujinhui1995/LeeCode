## Combination Sum II

Given a collection of candidate numbers (**C**) and a target number (***T***), find all unique combinations in **C** where the candidate numbers sums to ***T***.

Each number in **C** may only be used **once** in the combination.

**Note:**

- All numbers (including target) will be positive integers.
- The solution set must not contain duplicate combinations.

For example, given candidate set `[10, 1, 2, 7, 6, 1, 5]` and target `8`.

A solution set is:

	[
		[1, 7],
		[1, 2, 5],
		[2, 6],
		[1, 1, 6]
	]

#### Java Discussion Soluiton:

	class Solution {
	    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	    	Arrays.sort(candidates);
	    	if(target<candidates[0])
	    		return res;
	    	combination(candidates, res, new ArrayList<Integer>(), target, 0);
	    	return res;
	    }
	    public void combination(int[] candidates, List<List<Integer>> list, List<Integer> curr, int target, int start){
	    	if(target>0){
	    		for(int i=start; i<candidates.length && target>=candidates[i]; i++){
	                if(i>0&&candidates[i]==candidates[i-1]&&curr.size()==0)
	                    continue;
	    			curr.add(candidates[i]);
	    			combination(candidates, list, curr, target-candidates[i], i+1);
	    			curr.remove(curr.size()-1);
	    		}
	    	}else if(target==0){
	            if(!list.contains(curr))
	    		    list.add(new ArrayList<Integer>(curr));
	    	}
	    }
	}

#### Java Discussion Solution:

	class Solution {
	    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	        Arrays.sort(candidates);
	    	List<List<Integer>> res = new ArrayList<List<Integer>>();
	    	List<Integer> path = new ArrayList<Integer>();
	    	dfs_com(candidates, 0, target, path, res);
	   		return res;
	    }
	    public void dfs_com(int[] candidates, int start, int target, List<Integer> curr, List<List<Integer>> res){
	    	if(target==0){
	    		res.add(new ArrayList(curr));
	    		return;
	    	}
	    	if(target<0)
	    		return;
	    	for(int i=start; i<candidates.length; i++){
	    		if(i>start && candidates[i]==candidates[i-1])
	    			continue;
	    		curr.add(candidates[i]);
	    		dfs_com(candidates, i+1, target-candidates[i], curr, res);
	    		curr.remove(curr.size()-1);
	    	}
	    }
	}

#### Python2 Discussion Solution:

	class Solution(object):
	    def combinationSum2(self, candidates, target):
	        """
	        :type candidates: List[int]
	        :type target: int
	        :rtype: List[List[int]]
	        """
	        candidates.sort()
	        table = [None] + [set() for i in range(target)]
	        for i in candidates:
	        	if i>target:
	        		break
	        	for j in range(target-i, 0, -1):
	        		table[i+j] |= {elt + (i,) for elt in table[j]}
	        	table[i].add((i,))
	        return map(list, table[target])

#### Python3 Discussion Solution:

	class Solution:
	    def combinationSum2(self, candidates, target):
	        """
	        :type candidates: List[int]
	        :type target: int
	        :rtype: List[List[int]]
	        """
	        candidates.sort()
	        dp = [set() for i in range(target+1)]
	        dp[0].add(())
	        for num in candidates:
	            for t in range(target, num-1, -1):
	                for prev in dp[t-num]:
	                    dp[t].add(prev + (num,))
	        return list(dp[-1])