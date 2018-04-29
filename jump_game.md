## Jump Game

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maxmium jump length at that position.

Determine if you are able to reach the last index.

**Example 1:**

	Input: [2,3,1,1,4]
	Output: true
	Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

**Example 2:**

	Input: [3,2,1,0,4]
	Output: false
	Explanation: You will always arrive at index 3 no matter what. Its maximum
	             jump length is 0, which makes it impossible to reach the last index.


#### Java Solution :

	class Solution {
	    public boolean canJump(int[] nums) {
	        boolean[] flag = new boolean[nums.length];
			flag[0] = true;
			for(int i=0; i<nums.length; i++){
				if(flag[i]){
					for(int j=i+1; j<=i+nums[i]; j++){
						if(j<nums.length)
							flag[j] = true;
					}
				}
			}
			return flag[nums.length-1];
	    }
	}

#### Java Discussion Solution :

	public boolean canJump(int[] A) {
	    int max = 0;
	    for(int i=0;i<A.length;i++){
	        if(i>max) {return false;}
	        max = Math.max(A[i]+i,max);
	    }
	    return true;
	}

#### Python TimeOut Solution :

	class Solution:
	    def canJump(self, nums):
	        """
	        :type nums: List[int]
	        :rtype: bool
	        """
	        flag = [False]*len(nums)
	        flag[0] = True
	        for i in range(len(nums)):
	            if flag[i]:
	                for j in range(i+1, i+nums[i]+1):
	                    if j<len(nums):
	                        flag[j] = True
	        return flag[-1]

#### Python Solution :

	class Solution:
	    def canJump(self, nums):
	        """
	        :type nums: List[int]
	        :rtype: bool
	        """
	        m = 0
	        for i in range(len(nums)):
	            if i>m :
	                return False
	            m = max(nums[i]+i, m)
	        return True