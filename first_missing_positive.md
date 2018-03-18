## First Missing Positive

Given an unsorted integer array, find the first missing positive integer.

For example,

Given `[1,2,0]` return `3`,

and `[3,4,-1,1]` return `2`.

Your algorithm should run in *O*(*n*) time and users constant space.

#### Java Discussion Solution:

	class Solution {
	    public int firstMissingPositive(int[] nums) {
	        int length = nums.length;
	    	int k = partation(nums)+1;
	    	int res = k;
	    	for(int i=0; i<k; i++){
	    		int temp = Math.abs(nums[i]);
	    		if(temp<=k){
	    			nums[temp-1] = (nums[temp-1]<0?nums[temp-1]:(-nums[temp-1]));
	    		}
	    	}
	    	for(int i=0; i<length; i++){
	    		if(nums[i]>0){
	    			res = i;
	                break;
	            }
	    	}
	    	return res+1;
	    }
	    public int partation(int[] nums){
	    	int length = nums.length;
	    	int q = -1;
	    	for(int i=0; i<length; i++){
	    		if(nums[i]>0){
	    			q++;
	    			swap(nums,q,i);
	    		}
	    	}
	    	return q;
	    }
	    public void swap(int[] nums, int i, int j){
	    	int temp = nums[i];
	    	nums[i] = nums[j];
	    	nums[j] = temp;
	    }
	}

**主体思想**：

- 将原数组的正数和负数部分分离，并找出第一个负数的位置。
- 在负数之前的数，以其值的绝对值（防止后面赋为负值后越界）-1为角标，将值赋为负数。
- 找到第一个为正数的值，其值+1即为结果。

#### Python Discussion Solution:

	class Solution:
	    def firstMissingPositive(self, nums):
	        """
	        :type nums: List[int]
	        :rtype: int
	        """
	        nums.append(0)
	        n = len(nums)
	        for i in range(len(nums)): #delete those useless elements
	            if nums[i]<0 or nums[i]>=n:
	                nums[i]=0
	        for i in range(len(nums)): #use the index as the hash to record the frequency of each number
	            nums[nums[i]%n]+=n
	        for i in range(1,len(nums)):
	            if nums[i]//n==0:
	                return i
	        return n

1. for any array whose length is l, the first missing positive must be in range [1,...,l+1], 
so we only have to care about those elements in this range and remove the rest.
2. we can use the array index as the hash to restore the frequency of each number within 
 the range [1,...,l+1] 