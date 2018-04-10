## Jump Game II

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:

Given array A = `[2,3,1,1,4]`

The minimum number of jumps to reach the last index is `2`. (Jump `1` step from index 0 to 1, then `3` steps to the last index.)

**Note：**

You can assume that you can always reach the last index.

#### Java Wrong Solution:

	class Solution {
		//动态规划DP
		//大概是因为前一个题目用的是动态规划的原因，所以这个问题也自然而然的想用动态规划
		//另外一个原因是因为，以前有一个跳跃台阶的问题，一次可以跳小于n的阶数，求的是有多少种情况
		//感觉还是和那个问题很像的，只不过这样的话复杂度可能会很大。
	    public int jump(int[] nums) {
	        if(nums.length==0)
	    		return 0;
	    	if(nums.length<=2){
	    		return nums.length-1;
	    	}
	    	int res = nums.length-1;
	    	for (int i=nums.length-2; i>=0; i--){
	    		if(nums[i]>=nums.length-1-i){
	    			int temp = subJump(nums, i)+1;
	    			if(temp<res)
	    				res = temp;
	    		}
	    	}
	    	return res;
	    }
	    public int subJump(int[] nums, int end){
	        int res = end;
	    	if(end<=1)
	    		return end;
	    	for(int i=end-1; i>=0; i--){
	    		if(nums[i]>=end-i){
	    			int temp = subJump(nums, i)+1;
	    			if(temp<res)
	    				res = temp;
	    		}
	    	}
	    	return res;
	    }
	}

果然超时挂掉了，不过我在测验是不是有死循环的过程中，发现了人工查找时的方法。

#### Java Solution:

	class Solution {
	    public int jump(int[] nums) {
	        int res = subJump(nums, nums.length-2, nums.length-1);
	        return res;
	    }
	    public int subJump(int[] nums, int start, int end){
	    	if(end<=1)
	    		return end;
	    	int temp = end;
	    	for(int i=start; i>=0; i--){
	    		if(nums[i]>=end-i){
	    			temp = i;
	    		}
	    	}
	    	end = temp;
	    	start = temp-1;
	    	return subJump(nums, start, end)+1;
	    }
	}


#### Java Discussion Solution:

	class Solution {
		//惊为天人
	    public int jump(int[] nums) {
	        int step_count = 0;
	    	int last_jump_max = 0;
	    	int curr_jump_max = 0;
	    	for(int i=0; i<nums.length-1; i++){
	    		curr_jump_max = Math.max(curr_jump_max, i+nums[i]);
	    		if(i == last_jump_max){
	    			step_count++;
	    			last_jump_max = curr_jump_max;
	    		}
	    	}
	    	return step_count;
	    }
	}

#### Python Wrong Solution:

	class Solution:
	    def jump(self, nums):
	        """
	        :type nums: List[int]
	        :rtype: int
	        """
	        return self.subJump(nums, len(nums)-2, len(nums)-1)
	    def subJump(self, nums, start, end):
	        if end<=1:
	            return end
	        temp = end
	        for i in range(start, -1, -1):
	            if nums[i]>=end-i:
	                temp = i
	        start = temp - 1
	        end = temp
	        return self.subJump(nums, start, end)+1

解释型语言的劣势导致超时了。

#### Python Solution:

	class Solution:
	    def jump(self, nums):
	        """
	        :type nums: List[int]
	        :rtype: int
	        """
	        step_count, last_jump_max, current_jump_max = 0,0,0
	        for i in range(len(nums)-1):
	            current_jump_max = max(current_jump_max, i+nums[i])
	            if i == last_jump_max:
	                step_count += 1
	                last_jump_max = current_jump_max
	        return step_count