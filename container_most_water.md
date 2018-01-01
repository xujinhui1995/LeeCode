## Container With Most Water

Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate(i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.

主要思想是从两边向中间靠拢，左右两边的垂线，较短的那条组成的最大的面积，就是和最右边的那条，所以，在这一次比较之后，左边的短线就可以被抛弃。这样一直循环到相遇。

### java

	class Solution {
	    public int maxArea(int[] height) {
	        int size = height.length;
	        int left = 0;
	        int right = size - 1;
	        int maxArea = 0;
	        while(left < right){
	            maxArea = Math.max(maxArea,Math.min(height[left], height[right]) * (right - left));
	            if(height[left] < height[right])
	                left++;
	            else
	                right--;
	        }
	        return maxArea;
	    }
	}

### python

	class Solution:
	    def maxArea(self, height):
	        """
	        :type height: List[int]
	        :rtype: int
	        """
	        left, right = 0, len(height)-1
	        maxArea = 0
	        while left < right:
	            maxArea = maxArea if maxArea > ((height[left] if height[left] < height[right] \
	            else height[right]) * (right - left)) else ((height[left] if height[left] < height[right] \
	            else height[right]) * (right - left))
	            if height[left] < height[right]:
	                left += 1
	            else:
	                right -= 1
	        return maxArea
### 
	class Solution:
	    def maxArea(self, height):
	        """
	        :type height: List[int]
	        :rtype: int
	        """
	        left, right, maxArea = 0, len(height)-1, 0
	        while left < right:
	            minNum = min(height[left], height[right])
	            maxArea, left, right = max(maxArea,minNum * (right - left)), left + (height[left] == minNum), right - (height[right] == minNum)
	        return maxArea