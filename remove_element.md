### Remove Element

Given an array and a value, remove all instances of that value **<font color='blue'>in-place</font>** and return the new length.

Do not allocate extra space for another array, you must do this by **modifying the input array <font color='blue'>in-place</font>** with O(1) extra momery.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

#### Java Solution :

	class Solution {
	    public int removeElement(int[] nums, int val) {
	        int i=0;
	        int newLength = 0;
	        int pre = 0;
	        for(;i<nums.length;i++){
	            if(nums[i]!=val){
	                nums[pre++]=nums[i];
	                newLength++;
	            }
	        }
	        return newLength;
	    }
	}


#### Python Solution :

	class Solution:
	    def removeElement(self, nums, val):
	        """
	        :type nums: List[int]
	        :type val: int
	        :rtype: int
	        """
	        if len(nums)<1:
		        return len(nums)
	        pre = i = 0
	        while i<len(nums):
	            if val!=nums[i]:
	                nums[pre]=nums[i]
	                pre += 1
	            i += 1
	        return pre