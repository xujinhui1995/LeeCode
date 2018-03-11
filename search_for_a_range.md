## Search for a Range

Given an array integers sorted in ascending order, find the starting and ending position of a given target value.

Your agorithm's runtime complexity musy be in the order of O(log *n*).

If the target is not found in the array, return [-1, -1].

#### Java Solution (时间复杂度不是O(log *n*):

	class Solution {
	    public int[] searchRange(int[] nums, int target) {
	        int[] res = new int[2];
	        int i = 0;
			for(; i<nums.length; i++){
				if(target==nums[i]){
					res[0]=i;
					break;
				}
			}
			if(i==nums.length){
				res[0]=-1;
				res[1]=-1;
				return res;
			}
			for(i=nums.length-1; i>=res[0]; i--){
				if(target==nums[i]){
					res[1]=i;
	                break;
				}
			}
			return res;
	    }
	}

#### Java Discussion Solution:

	class Solution {
	    public int[] searchRange(int[] nums, int target) {
	        int start = firstGreaterEqual(nums, target);
			if(start==nums.length || nums[start] != target)
				return new int[]{-1, -1};
			return new int[] {start, firstGreaterEqual(nums, target+1)-1};
		}
		public int firstGreaterEqual(int[] nums, int target){
			int lo = 0;
			int hi = nums.length;
			while(lo<hi){
				int mid = lo + ((hi-lo)>>1);
				if(nums[mid]<target)
					lo = mid + 1;
				else
					hi = mid;
			}
			return lo;
	    }
	}

#### Python Solution:

	class Solution:
	    def searchRange(self, nums, target):
	        """
	        :type nums: List[int]
	        :type target: int
	        :rtype: List[int]
	        """
	        start = self.firstGreaterEqaul(nums, target)
	        if start==len(nums) or nums[start]!=target:
	            return [-1, -1]
	        return [start, self.firstGreaterEqaul(nums, target+1)-1]
	    def firstGreaterEqaul(self, nums, target):
	        lo, hi = 0, len(nums)
	        while lo<hi:
	            mid = (hi+lo)//2
	            if nums[mid]<target:
	                lo = mid + 1
	            else:
	                hi = mid
	        return lo