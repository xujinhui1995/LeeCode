### 3Sum Closest

Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

#### java:

	class Solution {
	    public int threeSumClosest(int[] nums, int target) {
	        //排序
	        Arrays.sort(nums);
	        int res = nums[0] + nums[1] + nums[nums.length-1];
	        for(int i=0; i<nums.length-2; i++){
	            if(i==0 || (i>0 && nums[i]!=nums[i-1])){
	                int lo=i+1, hi=nums.length-1;
	                while(lo<hi){
	                    int num = nums[i] + nums[lo] + nums[hi];
	                    if(num>target){
	                        hi--;
	                    }else{
	                        lo++;
	                    }
	                    if(Math.abs(num-target)<Math.abs(res-target))
	                        res = num;
	                }
	            }
	        }
	        return res;
	    }
	}

#### python:

	class Solution:
	    def threeSumClosest(self, nums, target):
	        """
	        :type nums: List[int]
	        :type target: int
	        :rtype: int
	        """
	        nums.sort()
	        res = nums[0] + nums[1] + nums[len(nums)-1]
	        for i in range(len(nums)-2):
	            lo, hi = i+1, len(nums)-1
	            if i>0 and nums[i]==nums[i-1]:
	                continue
	            while lo<hi:
	                num = nums[i] + nums[lo] + nums[hi]
	                if num>target:
	                    hi -= 1
	                else:
	                    lo += 1
	                if abs(num-target) < abs(res-target):
	                    res = num
	        return res