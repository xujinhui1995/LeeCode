## Combination Sum

Given a **set** of candidate numbers(**C**)(**without duplicates**) and a target number (***T***), find all unique combinations in **C** where the candidate numbers sums to ***T***.

The **same** repeated number may be chosen from **C** unlimited number of times.

**Note:**

- All numbers(including target) will be positive integers.
- The solution set must not contain duplicate combinations.

For example, given candidate set `[2, 3, 6, 7]` and target `7`,

A solution set is:

	[
		[7],
		[2, 2, 3]
	]

#### Java Discussion Solution:

	class Solution {
	    public List<List<Integer>> combinationSum(int[] candidates, int target) {
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	    	if(candidates==null || candidates.length==0)
	    		return res;
	    	Arrays.sort(candidates);
	    	if(candidates[0]>target)
	    		return res;
	    	combination(candidates, target, res, new ArrayList<Integer>(), 0);
	        return res;
	    }
	    public void combination(int[] candidates, int target, List<List<Integer>> list, List<Integer> curr, int start){
	    	if(target>0){
	    		for(int i=start; i<candidates.length && target>=candidates[i]; i++){
	    			curr.add(candidates[i]);
	    			combination(candidates, target-candidates[i], list, curr, i);
	    			curr.remove(curr.size()-1);
	    		}
	    	}else if(target==0){
	    		list.add(new ArrayList<Integer>(curr));
	    	}
	    }
	}


#### Python Discussion Solution:

	class Solution:
	    def combinationSum(self, candidates, target):
	        """
	        :type candidates: List[int]
	        :type target: int
	        :rtype: List[List[int]]
	        """
	        res = []
	        candidates.sort()
	        self.dfs(candidates, target, 0, [], res)
	        return res
	    def dfs(self, nums, target, index, path, res):
	        if target<0:
	            return
	        if target==0:
	            res.append(path)
	            return
	        for i in range(index, len(nums)):
	            self.dfs(nums, target-nums[i], i, path+[nums[i]], res)