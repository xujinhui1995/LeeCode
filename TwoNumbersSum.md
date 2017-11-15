##Two Sum
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

用java写的:

	class Solution {
	    public int[] twoSum(int[] nums, int target) {
	        int[] result = new int[2];
	        int length = nums.length;
	        for(int i=0; i<length; i++){
	            for(int j=i+1; j<length; j++){
	                if(nums[i]+nums[j] == target){
	                    result[0] = i;
	                    result[1] = j;
	                    return result;
	                }
	            }
	        }
	        return result;
	    }
	}

可以通过

用Python3:

	class Solution:
	    def twoSum(self, nums, target):
	        """
	        :type nums: List[int]
	        :type target: int
	        :rtype: List[int]
	        """
	        result = []
	        for i, each in enumerate(nums):
	            if i not in result:
	                try:
	                    tmp = nums.index(target - each)
	                    if tmp != i:
	                        result.append(i)
	                        result.append(tmp)
	                except:
	                    continue
	        return result
		
刚开始也是用的和java一样的逻辑,但是却超时了.然后借鉴了一下,用了Python的index方法,可能这个方法做了优化,所以不超时.

另外一种哈希表的结构,用的是Python中的字典的逻辑:

	class Solution:
	    def twoSum(self, nums, target):
	        """
	        :type nums: List[int]
	        :type target: int
	        :rtype: List[int]
	        """
	        directory = {}
	        for i in range(len(nums)):
	            if nums[i] in directory:
	                return [directory[nums[i]],i]
	            else:
	                directory[target-nums[i]]=i

具体的逻辑就是如果nums[i]不在字典中,就将target-nums[i]的值作为键,i作为值加入字典.
这样,当某一个数nums[i],他在字典中存在,也就是说某一个数nums[j],使得target-nums[j]=nums[i].那么i和j就是要求的值.j=键target-nums[j]在字典中对应的值,也就是directory[nums[i]]