## Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it mush rearrange it as the lowest possible oeder(ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra momery.

Here are some examples. Input are in the left-hand column and its corresponding outputs are in the right -hang column.

读不懂题目系列，大意就是将数组变为比当前数组字典顺序小的中最大的那个，如果已经是最小，则最大。

#### Java Discussion Solution

	class Solution {
	    public void nextPermutation(int[] nums) {
	        int len = nums.length;
	    	int index = len - 1;
	    	while(index > 0){
	    		if(nums[index-1]<nums[index])
	    			break;
	    		index--;
	    	}
	    	if(index==0){
	    		reverseSort(nums,0,len-1);
	    	}else{
	    		int j = len-1;
	    		while(j>=index){
	    			if(nums[j]>nums[index-1])
	    				break;
	    			j--;
	    		}
	    		swap(nums, j, index-1);
	    		reverseSort(nums, index, len-1);
	    	}
	    }
	    public void reverseSort(int[] nums, int begin, int end){
	    	while(begin<end){
	    		int index = nums[begin];
	    		nums[begin] = nums[end];
	    		nums[end] = index;
	    		begin++;
	    		end--;
	    	}
	    }
	    public void swap(int[] nums, int i, int j){
	    	int index = nums[i];
	    	nums[i] = nums[j];
	    	nums[j] = index;
	    }
	}

##### Python Solution

	class Solution:
	    def nextPermutation(self, nums):
	        """
	        :type nums: List[int]
	        :rtype: void Do not return anything, modify nums in-place instead.
	        """
	        length = len(nums)
	        index = length - 1
	        if length<2:
	            return
	        while index>0:
	            if nums[index-1]<nums[index]:
	                break
	            index -= 1
	        if index==0:
	            nums.reverse()
	        else:
	            j = length - 1
	            while j>=index:
	                if nums[j]>nums[index-1]:
	                    break
	                j -= 1
	            temp = nums[j]
	            nums[j],nums[index-1] = nums[index-1],temp
	            m,n = index,length-1
	            while m<n:
	                temp = nums[m]
	                nums[m],nums[n] = nums[n],temp
	                m += 1
	                n -= 1
	                