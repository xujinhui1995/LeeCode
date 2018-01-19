### 3Sum

Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

还是把我超时的代码加上来吧，思路错了没法办，主要还是太懒，不愿意去往深里想，就像这个题目，知道要排序，但是不去想怎么利用排序。最后就是LinkedList在增删上比ArrayList更有优势。

	import java.util.*;
	class Solution {
	    public List<List<Integer>> threeSum(int[] nums) {
	        List<List<Integer>> list = new ArrayList<List<Integer>>();
			// 排序
			Arrays.sort(nums);
			// 字符串中是否含有0
			boolean flag = false;
			// 三个不同的数字加起来等于0，则两负一正或者两正一负
			int indexOfZero = Arrays.binarySearch(nums, 0);
			if (indexOfZero<0) {
				for (int i = 0; i < nums.length; i++) {
					if (nums[i] > 0) {
						indexOfZero = i;
						break;
					}
				}
			} else {
				flag = true;
			}
			if (indexOfZero <= 0)// 没有正数或者负数
				return list;
			// 在有0的情况下，在其左右各挑选一个字母
			if (flag) {
	            int count = 0;
	            for(int i=0; i<nums.length; i++){
	                if(nums[i]==0){
	                    count++;
	                }
	            }
	            if(count>=3){
	                List<Integer> l = new ArrayList<Integer>();
	                l.add(0);
	                l.add(0);
	                l.add(0);
	                list.add(l);
	            }
				for (int i = 0; i < indexOfZero; i++) {
					int index = Arrays.binarySearch(nums, Math.abs(nums[i]));
					if (index > 0) {
						List<Integer> l = new ArrayList<Integer>();
						l.add(nums[i]);
						l.add(0);
						l.add(Math.abs(nums[i]));
						if(!list.contains(l) && nums[i]!=0)
							list.add(l);
					}
				}
	
			}
			// 选取两个负数并将其和的绝对值与正数进行比较
			for (int i = 0; i < indexOfZero - 1; i++) {
				for (int j = i + 1; j < indexOfZero; j++) {
					int adds = nums[i] + nums[j];
					int index = Arrays.binarySearch(nums, Math.abs(adds));
					if (index > 0) {
						List<Integer> l = new ArrayList<Integer>();
						l.add(nums[i]);
						l.add(nums[j]);
						l.add(nums[index]);
						if(!list.contains(l))
							list.add(l);
					}
				}
			}
			// 选取两个正数并将其和的相反数与负数进行比较
			for (int i = nums.length - 1; nums[i] > 0; i--) {
				for (int j = i - 1; nums[j] > 0; j--) {
					int adds = nums[i] + nums[j];
					int index = Arrays.binarySearch(nums, (-1 * adds));
					if (index >= 0) {
						List<Integer> l = new ArrayList<Integer>();
						l.add(nums[i]);
						l.add(nums[j]);
						l.add(nums[index]);
						if(!list.contains(l))
							list.add(l);
					}
				}
			}
			return list;
	    }
	}

#### Java Discussion Solution 1

	import java.util.*;
	class Solution {
	    public List<List<Integer>> threeSum(int[] nums) {
	        List<List<Integer>> list = new LinkedList<List<Integer>>();
	        Arrays.sort(nums);
	        for(int i=0; i<nums.length-2; i++){
	            if(i==0 || (i>0 && nums[i]!=nums[i-1])){
	                int lo=i+1, hi=nums.length-1, sum=0-nums[i];
	                while(lo<hi){
	                    if(nums[lo]+nums[hi]==sum){
	                        list.add(Arrays.asList(nums[i],nums[lo],nums[hi]));
	                        while(lo<hi && nums[lo]==nums[lo+1]) lo++;
	                        while(lo<hi && nums[hi]==nums[hi-1]) hi--;
	                        lo++; hi--;
	                    }else if(nums[lo]+nums[hi]<sum){
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

#### python Solution：

	class Solution:
	    def threeSum(self, nums):
	        """
	        :type nums: List[int]
	        :rtype: List[List[int]]
	        """
	        res = []
	        nums.sort()
	        for i in range(len(nums)-2):
	            if i>0 and nums[i] == nums[i-1]:
	                continue
	            l, r = i+1, len(nums)-1
	            while l<r:
	                s = nums[i] + nums[l] + nums[r]
	                if s < 0:
	                    l += 1
	                elif s > 0:
	                    r -= 1
	                else:
	                    res.append((nums[i],nums[l],nums[r]))
	                    while l<r and nums[l]==nums[l+1]:
	                        l += 1
	                    while l<r and nums[r]==nums[r-1]:
	                        r -= 1
	                    l += 1; r -= 1
	        return res