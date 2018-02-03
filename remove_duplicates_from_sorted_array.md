### Remove Duplicates from Sorted Array

Given a sorted array, remove the duplicates **<font color='blue'>in-place</font>** such that each element appear only once and return the new length.

Do not allocate extra space for anthor array, you must do this by modifying the input array **<font colot='blue'>in-place</font>** with O(1) extra momery.

#### Java Solution :

	class Solution {
	    public int removeDuplicates(int[] nums) {
	        int i = 1;
	        int pre = 1;
	        int newLength = 1;
	        int length = nums.length;
	        if(length<=1)
	            return length;
	        for(;i<length;i++){
	            if(nums[i]==nums[i-1]){    
	                continue;
	            }
	            nums[pre]=nums[i];
	            pre++;
	            newLength++;
	        }
	        return newLength;
	    }
	}

#### 优化

	class Solution {
	    public int removeDuplicates(int[] nums) {
	        int i = 1;
	        int pre = 1;
	        int length = nums.length;
	        for(;i<length;i++){
	            if(nums[i]!=nums[i-1]){    
	                nums[pre++]=nums[i];
	            }
	        }
	        return pre;
	    }
	}

#### Python Solution :

	class Solution:
	    def removeDuplicates(self, nums):
	        """
	        :type nums: List[int]
	        :rtype: int
	        """
	        if len(nums)<2:
	            return len(nums)
	        pre = i = 1
	        while i<len(nums):
	            if nums[i-1]!=nums[i]:
	                nums[pre]=nums[i]
	                pre += 1
	            i += 1
	        return pre