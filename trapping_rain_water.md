## Trapping Rain Water

Given *n* non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example,

Given `[0,1,0,2,1,0,1,3,2,1,2,1]`, return `6`.

![](https://i.imgur.com/5RnZi9Y.png)

#### Java Solution:

	class Solution {
	    public int trap(int[] height) {
	        if(height.length<3)
	    		return 0;
	    	int oldV = 0;
	    	for(int i=0; i<height.length; i++){
	    		oldV += height[i];
	    	}
	    	int newV = 0;
	    	int left = 0;
	    	int right = height.length-1;
	    	while(left<right){
	    		while(left>0 && height[left]==height[left+1] && left<right) left++;
	    		while(right>left && height[right]==height[right-1]) right--;
	    		for(int i=left+1; i<right; i++){
	    			if(height[i]<(height[left]<height[right]?height[left]:height[right]))
	    				height[i]=height[left]<height[right]?height[left]:height[right];
	    		}
	    		if(height[left]<height[right])
	    			left++;
	    		else
	    			right--;
	    	}
	    	for(int i=0; i<height.length; i++){
	    		newV += height[i];
	    	}
	    	return newV-oldV;
	    }
	}

是这道题真难还是我的能力提高了，去洗澡路上就突然有了思路，大概是以前做过类似的原因。一次通过我还以为自己看错了。

#### Java Discussion Solution:

	class Solution {
	    public int trap(int[] height) {
	        int left = 0;
	    	int right = height.length-1;
	    	int leftMax = 0;
	    	int rightMax = 0;
	    	int res = 0;
	    	while(left<right){
	    		leftMax=Math.max(leftMax, height[left]);
	    		rightMax = Math.max(rightMax, height[right]);
	    		if(leftMax<rightMax){
	    			res += leftMax-height[left];
	    			left++;
	    		}else{
	    			res += rightMax-height[right];
	    			right--;
	    		}
	    	}
	    	return res;
	    }
	}

#### Python Solution:

	class Solution:
	    def trap(self, height):
	        """
	        :type height: List[int]
	        :rtype: int
	        """
	        left, right, leftMax, rightMax, res = 0, len(height)-1, 0, 0, 0
	        while left<right:
	            leftMax, rightMax = max(leftMax, height[left]), max(rightMax, height[right])
	            if leftMax<rightMax:
	                res += leftMax-height[left]
	                left += 1
	            else:
	                res += rightMax-height[right]
	                right -= 1
	        return res