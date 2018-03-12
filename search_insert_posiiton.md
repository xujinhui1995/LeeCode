## Search Insert Position

Given a sorted array and a target value, return the index if the target is not found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

#### Java Solution:

	class Solution {
	    public int searchInsert(int[] nums, int target) {
	        int i = 0;
			for(; i<nums.length; i++){
				if(nums[i]>=target)
					break;
			}
			return i;
	    }
	}

#### Java Discussion Solution:

	class Solution {
	    public int searchInsert(int[] nums, int target) {
	        int lo = 0;
			int hi = nums.length-1;
			while(lo<=hi){
				int mid = (lo+hi)/2;
				if(nums[mid]==target) return mid;
				else if(nums[mid]>target) hi = mid - 1;
				else lo = mid + 1;
			}
			return lo;
	    }
	}


#### Python Solution:

	class Solution:
	    def searchInsert(self, nums, target):
	        """
	        :type nums: List[int]
	        :type target: int
	        :rtype: int
	        """
	        lo, hi = 0, len(nums)-1
	        while lo<=hi:
	            mid = (lo+hi)//2
	            if nums[mid]==target:
	                return mid
	            elif nums[mid]<target:
	                lo = mid + 1
	            else:
	                hi = mid - 1
	        return lo