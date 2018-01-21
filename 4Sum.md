### 4Sum

Given an array S of n integers, are there elements a, b, c and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

**Note**: The solution set must not contain duplicate quedruplets.

#### Java Solution with 3Sum:

	class Solution {
	    public List<List<Integer>> fourSum(int[] nums, int target) {
	        Arrays.sort(nums);
	        List<List<Integer>> list = new LinkedList<List<Integer>>();
	        for(int i=0; i<nums.length-3; i++){
	            for(int j=i+1; j<nums.length-2; j++){
	                if((i>0&&nums[i]==nums[i-1]) || (j>i+1&&nums[j]==nums[j-1]))
	                    continue;
	                int lo=j+1, hi=nums.length-1;
	                while(lo<hi){
	                    int sum = nums[i]+nums[j]+nums[lo]+nums[hi];
	                    if(sum==target){
	                        list.add(Arrays.asList(nums[i],nums[j],nums[lo],nums[hi]));
	                        while(lo<hi && nums[lo]==nums[lo+1]) lo++;
	                        while(lo<hi && nums[hi]==nums[hi-1]) hi--;
	                        lo++;
	                        hi--;
	                    }else if(sum<target){
	                        lo++;
	                    }else{
	                        hi--;
	                    }
	                }
	            }
	        }
	        return list;
	    }
	}

#### Java Discussion Solution:

