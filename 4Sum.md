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

	class Solution {
	    public List<List<Integer>> fourSum(int[] nums, int target) {
	        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
	        int len = nums.length;
	        if(len<4){
	            return res;
	        }
	
	        Arrays.sort(nums);
	
	        int max = nums[len-1];
	        if(4*nums[0]>target || 4*max<target){
	            return res;
	        }
	
	        int i, z;
	        for(i=0; i<len; i++){
	            z = nums[i];
	            if(i>0 && z==nums[i-1])//avoid duplicate
	                continue;
	            if(z+3*max<target)//z is too small
	                continue;
	            if(4*z>target)//z is too large
	                break;
	            if(4*z==target){//z is the boundary
	                if(i+3<len && nums[i+3]==z){
	                    res.add(Arrays.asList(z, z, z, z));
	                }
	                break;
	            }
	
	            threeSumForFourSum(nums, target-z, i+1, len-1, res, z);
	        }
	
	        return res;
	    }
	
	    /*
	    * Find all possible distinguished three numbers adding up to the target
	    * in sorted array nums[] between indices low and high. If there are
	    * add all of them into the ArrayList fourSumList, using
	    * fourSumList.add(Arrays.asList(z1, the three numbers)).
	    */
	    public void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList, int z1){
	        if(low+1>=high)
	            return;
	        int max = nums[high];
	        if(3*nums[low]>target || 3*max<target)
	            return;
	        int i, z;
	        for(i=low; i<high-1; i++){
	            z = nums[i];
	            if(i>low && z==nums[i-1])//avois duplicate
	                continue;
	            if(z+2*max<target)//z is too small
	                continue;
	            if(3*z>target)//z is too large
	                break;
	            if(3*z==target){
	                if(i+1<high && nums[i+2]==z){
	                    fourSumList.add(Arrays.asList(z1, z, z, z));
	                }
	                break;
	            }
	            twoSumForFourSum(nums, target-z, i+1, high, fourSumList, z1, z);
	        }
	    }
	
	    /*
	    * Find all possible distinguished two numbers adding to the target
	    * in sorted array nums[] between indicas low and high. If there are,
	    * add all of them into the ArrayList fourSumList, using
	    * fourSumList.add(Arrays.asList(z1, z2, the two numbers)).
	    */
	    public void twoSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList, int z1, int z2){
	        if(low>high)
	            return;
	        if(2*nums[low]>target || 2*nums[high]<target)
	            return;
	        int i = low, j=high, sum, x;
	        while(i<j){
	            sum = nums[i] + nums[j];
	
	            if(sum==target){
	                fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));
	
	                x = nums[i];
	                while(++i<j && x==nums[i])
	                    ;
	                x = nums[j];
	                while(i<--j && x==nums[j])
	                    ;
	            }
	            if(sum<target)
	                i++;
	            if(sum>target)
	                j--;
	        }
	        return;
	    }
	}

#### Python Discussion Solution:

	class Solution:
	    def fourSum(self, nums, target):
	        """
	        :type nums: List[int]
	        :type target: int
	        :rtype: List[List[int]]
	        """
	        def findNSum(nums, target, N, result, results):
	            if len(nums)<N or N<2 or target<nums[0]*N or target>nums[-1]*N:
	                return
	            if N==2: # two pointers solve sorted 2-sum problem. 
	                l, r = 0, len(nums)-1
	                while l<r:
	                    s = nums[r] + nums[l]
	                    if s==target:
	                        results.append(result+[nums[r], nums[l]])
	                        l += 1
	                        r -= 1
	                        while l<r and nums[l]==nums[l-1]:
	                            l += 1
	                        while l<r and nums[r]==nums[r+1]:
	                            r -= 1
	                    elif s<target:
	                        l += 1
	                    else:
	                        r -= 1
	            else: # recursively reduce N
	                for i in range(len(nums)-N+1):
	                    if i==0 or (i>0 and nums[i]!=nums[i-1]):
	                        findNSum(nums[i+1:],target-nums[i],N-1,result+[nums[i]],results)
	        results = []
	        findNSum(sorted(nums), target, 4, [], results)
	        return results


#### Rewrite Java From Python