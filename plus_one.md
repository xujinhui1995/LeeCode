## Plus One

Given a **non-empty** array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that most significant digits is that head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

**Example 1:**

	Input: [1,2,3]
	Output: [1,2,4]
	Explanation: The array represents the integer 123.

**Example 2:**

	Input: [4,3,2,1]
	Output: [4,3,2,2]
	Explanation: The array represents the integer 4321.

#### Java Solution :

	class Solution {
	    public int[] plusOne(int[] digits) {
	        if(digits[digits.length-1]<9){
	            digits[digits.length-1] += 1;
	            return digits;
	        }
	        boolean all_9 = true;
	        for(int i=0; i<digits.length; i++){
	            if(digits[i]!=9)
	                all_9 = false;
	        }
	        if(all_9){
	            int[] res = new int[digits.length+1];
	            res[0]=1;
	            for(int i=1; i<res.length; i++){
	                res[i]=0;
	            }
	            return res;
	        }
	        for(int i=digits.length-2; i>=0; i--){
	            if(digits[i]<9){
	                digits[i+1]=0;
	                digits[i]++;
	                break;
	            }else{
	                digits[i+1]=0;
	            }
	        }
	        return digits;
	    }
	}

#### Python Solution :

	class Solution:
	    def plusOne(self, digits):
	        """
	        :type digits: List[int]
	        :rtype: List[int]
	        """
	        if digits[-1]<9:
	            digits[-1] += 1
	            return digits
	        all_9 = True
	        for i in range(len(digits)):
	            if digits[i]!=9:
	                all_9 = False
	                break
	        if all_9:
	            return [1]+[0]*len(digits)
	        for i in range(len(digits)-2, -1, -1):
	            if digits[i]<9:
	                digits[i] += 1
	                digits[i+1] = 0
	                break
	            else:
	                digits[i+1] = 0
	        return digits