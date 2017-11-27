## Median of Two Sorted Arrays

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

其实不是很懂这个复杂度.

java:

	class Solution {
	    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	        int i = 0;
	        int j = 0;
	        int[] index = new int[nums1.length+nums2.length];
	        int p = 0;
	        while(i<nums1.length && j<nums2.length){
	            if(nums1[i]>nums2[j]){
	                index[p++] = nums2[j++];
	            }else{
	                index[p++] = nums1[i++];
	            }
	        }
	        if(i>=nums1.length){
	            while(j<nums2.length){
	                index[p++] = nums2[j++];
	            }
	        }
	        if(j>=nums2.length){
	            while(i<nums1.length){
	                index[p++] = nums1[i++];
	            }
	        }
	        if(index.length%2==0){
	            return (double)((index[index.length/2]+index[index.length/2-1])/2.0);
	        }else{
	            return (double)(index[index.length/2]);
	        }
	    }
	}

python:

	class Solution:
	    def findMedianSortedArrays(self, nums1, nums2):
	        """
	        :type nums1: List[int]
	        :type nums2: List[int]
	        :rtype: float
	        """
	        i,j = 0,0
	        index = []
	        while i<len(nums1) and j<len(nums2):
	            if nums1[i]<nums2[j]:
	                index.append(nums1[i])
	                i += 1
	            else:
	                index.append(nums2[j])
	                j += 1
	        index += nums1[i:]
	        index += nums2[j:]
	        if len(index)%2==0:
	            return (index[len(index)//2]+index[len(index)//2-1])/2.0
	        else:
	            return index[len(index)//2]*1.0