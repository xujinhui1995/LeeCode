## Maximum Subarray

Given an integer array `nums`, find the contigous subarray(containing at least one number)which has the largest sum and return its sum.

**Example:**

	Input: [-2,1,-3,4,-1,2,1,-5,4],
	Output: 6
	Explanation: [4,-1,2,1] has the largest sum = 6.

**Follow up:**

If you have figured out the O(*n*) solution, try coding another solution using the divide and conquer approach, which is more subtle.

#### Java Solution :

	class Solution {
	    public int maxSubArray(int[] nums) {
	        int left = 0;
			int right = 0;
			int i = 0;
			int j = nums.length-1;
			int max = 0;
			while(i<j){
				left += nums[i];
				right += nums[j];
				if(left>max){
					max = left;
				}
				if(right>max){
					max = right;
				}
				if(left<0) left=0;
				if(right<0) right=0;
				i++;
				j--;
			}
			if(max==0){
				Arrays.sort(nums);
				return nums[nums.length-1];
			}
			if(i==j) max=Math.max(left+right+nums[i],max);
	        if(i>j) max=Math.max(left+right,max);
			return max;
	    }
	}

#### Java Discussion Solution:

	class Solution {
	    public int maxSubArray(int[] nums) {
	        int[] dp = new int[nums.length];
			int max = nums[0];
			dp[0] = nums[0];
			for(int i=1; i<nums.length; i++){
				dp[i] = nums[i] + (dp[i-1]>0?dp[i-1]:0);
				max = Math.max(dp[i],max);
			}
			return max;
	    }
	}

#### Python Solution:

	class Solution:
	    def maxSubArray(self, nums):
	        """
	        :type nums: List[int]
	        :rtype: int
	        """
	        left,right,i,j,m = 0,0,0,len(nums)-1,0
	        while i<j:
	            left += nums[i]
	            right += nums[j]
	            if left>m:
	                m = left
	            if right>m:
	                m = right
	            if left<0:
	                left = 0
	            if right<0:
	                right = 0
	            i += 1
	            j -= 1
	        if m==0:
	            nums.sort()
	            return nums[-1]
	        if i==j:
	            m = max(left+right+nums[i],m)
	        if i>j:
	            m = max(left+right,m)
	        return m

#### Python Discussion Solution:

	class Solution:
	    def maxSubArray(self, nums):
	        """
	        :type nums: List[int]
	        :rtype: int
	        """
	        m = nums[0]
	        dp = [0]*len(nums)
	        dp[0] = nums[0]
	        for i in range(1, len(nums)):
	            dp[i] = nums[i] + (dp[i-1] if dp[i-1]>0 else 0)
	            m = max(dp[i],m)
	        return m