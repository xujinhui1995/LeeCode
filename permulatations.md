## Permulatations

Given a collection of distinct numbers, return all possible permutations.

For example,

`[1,2,3]` have the following permutations:

	[
		[1,2,3],
		[1,3,2],
		[2,1,3],
		[2,3,1],
		[3,1,2],
		[3,2,1]
	]

#### Java Discussion Solution:

	class Solution {
	    public List<List<Integer>> permute(int[] nums) {
	        List<List<Integer>> res = new LinkedList<List<Integer>>();
	    	backTrack(res, new LinkedList<Integer>(), nums);
	    	return res;
	    }
	    public void backTrack(List<List<Integer>> res, List<Integer> tempList, int[] nums){
	    	if(tempList.size()==nums.length){
	    		res.add(new LinkedList<Integer>(tempList));
	    	}else{
	    		for(int i=0; i<nums.length; i++){
	    			if(tempList.contains(nums[i])) continue;
	    			tempList.add(nums[i]);
	    			backTrack(res, tempList, nums);
	    			tempList.remove(tempList.size()-1);
	    		}
	    	}
	    }
	}

#### Python Discussion Solution:

	class Solution:
	    def permute(self, nums):
	        """
	        :type nums: List[int]
	        :rtype: List[List[int]]
	        """
	        res = []
	        self.dfs(nums, [], res)
	        return res
	    def dfs(self, nums, path, res):
	        if not nums:
	            res.append(path)
	        for i in range(len(nums)):
	            self.dfs(nums[:i]+nums[i+1:], path+[nums[i]], res)