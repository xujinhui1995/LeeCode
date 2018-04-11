## Permtations II

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,

`[1,1,2]` have the following unique permutations:

	[
		[1,1,2],
		[1,2,1],
		[2,1,1]
	]

#### Java Discussion Solution:

	class Solution {
	    public List<List<Integer>> permuteUnique(int[] nums) {
	        List<List<Integer>> res = new LinkedList<List<Integer>>();
	    	Arrays.sort(nums);
	    	if(nums.length<=0)
	    		return res;
	    	//先前也考虑到这个问题的节点就在于如何识别已经用过的数字，可能有点困。
	    	//看到了解答，恍然大悟
	    	boolean[] used = new boolean[nums.length];
	    	subPermuteUnique(nums, res, new LinkedList<Integer>(), used);
	    	return res;
	    }
	    public void subPermuteUnique(int[] nums, List<List<Integer>> res, List<Integer> tempList, boolean[] used){
	    	if(tempList.size()==nums.length){
	    		res.add(new LinkedList<Integer>(tempList));
	    		return;
	        }
	    	for(int i=0; i<nums.length; i++){
	    		//如果存在重复的，取第二个
	    		if(used[i]) continue;
	    		if(i>0 && nums[i-1]==nums[i]&&!used[i-1]) continue;
	    		tempList.add(nums[i]);
	    		used[i]=true;
	    		subPermuteUnique(nums, res, tempList, used);
	    		used[i]=false;
	    		tempList.remove(tempList.size()-1);
	    	}
	    }
	}


#### Python Solution:

	class Solution:
	    def permuteUnique(self, nums):
	        """
	        :type nums: List[int]
	        :rtype: List[List[int]]
	        """
	        nums.sort()
	        res = []
	        self.dfs(nums, [], res)
	        return res
	    def dfs(self, nums, path, res):
	        if not nums:
	            res.append(path)
	        for i in range(len(nums)):
	            if i==0 or nums[i-1]!=nums[i]:
	                self.dfs(nums[:i]+nums[i+1:], path+[nums[i]], res)

