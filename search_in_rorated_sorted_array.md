## Search in Rotated Sorted Array

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforedhand.

(i.e., `0 1 2 3 4 5 6 7` might become `4 5 6 7 0 1 2`).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

#### Java Solution:

	class Solution {
	    public int search(int[] nums, int target) {
	        if(nums.length<=0)
				return -1;
	        if(nums.length==1){
	            if(nums[0]==target)
	                return 0;
	            else
	                return -1;
	        }
			int k = 0;
			for(int i=1; i<nums.length; i++){
				if(nums[i]<nums[i-1])
					k=i;
			}
			Arrays.sort(nums);
			int temp = Arrays.binarySearch(nums, target);
			if(temp<0)
	            return -1;
			return (temp+k)%(nums.length);
	    }
	}

#### Python Solution:

	class Solution:
	    def search(self, nums, target):
	        """
	        :type nums: List[int]
	        :type target: int
	        :rtype: int
	        """
	        if len(nums)<=0:
	            return -1
	        if len(nums)==1:
	            if nums[0]==target:
	                return 0
	            else:
	                return -1
	        k = 0
	        for i in range(len(nums)):
	            if nums[i]<nums[i-1]:
	                k = i
	        nums.sort();
	        temp = self.binary_search(nums,target)
	        if temp<0:
	            return -1
	        return (temp+k)%(len(nums))
	    def binary_search(self,lst,value):  
	        low, high = 0, len(lst)-1  
	        while low <= high:  
	            mid = (low + high) // 2  
	            if lst[mid] < value:  
	                low = mid + 1  
	            elif lst[mid] > value:  
	                high = mid - 1
	            else:
	                return mid  
	        return -1

#### Java Discussion Solution:

	class Solution{
		public int search(int[] nums, int target){
			int lo = 0;
			int hi = nums.length-1;
			//find the index of the smallest value using binary search.
			//Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
			//Proof by contradiction that mid < hi: if mid ==hi, then lo==hi and loop would have been terminated.
			while(lo<hi){
				int mid = (hi+lo)/2;
				if(nums[mid]>nums[hi])
					lo = mid+1;
				else
					hi = mid;
			}
			//lo==hi is the index of the smallest value and also the number of places rotated.
			int rot = lo;
			lo = 0;
			hi = nums.length-1;
			//The usual binary search and accounting for rotation.
			while(lo<=hi){
				int mid = (lo+hi)/2;
				int realmid = (mid+rot)%nums.length;
				if(nums[realmid]==target) return realmid;
				if(nums[realmid]<target) lo=mid+1;
				else hi = mid-1;
			}
			return -1;
		}
	}


#### Python Discussion Solution:

	class Solution:
	    def search(self, nums, target):
	        """
	        :type nums: List[int]
	        :type target: int
	        :rtype: int
	        """
	        if not nums:
	            return -1
	        lo, hi = 0, len(nums)-1
	        while lo<=hi:
	            mid = (lo+hi)//2
	            if target==nums[mid]:
	                return mid
	            if nums[lo]<=nums[mid]:
	                if nums[lo]<=target<=nums[mid]:
	                    hi = mid - 1
	                else:
	                    lo = mid + 1
	            else:
	                if nums[mid]<=target<=nums[hi]:
	                    lo = mid + 1
	                else:
	                    hi = mid - 1
	        return -1