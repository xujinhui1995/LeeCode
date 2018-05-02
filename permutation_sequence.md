## Permutation Sequence

The set `[1,2,3,...,n]` contains a total of *n*! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for *n*=3:
	
1.`"123"`

2.`"132"`

3.`"213"`

4.`"231"`

5.`"312"`

6.`"321"`

Given *n* and *k*, return the *k*th permutation sequence.

**Note:**

*·* Given *n* will be between 1 and 9 inclusive.

*·* Given *k* will be between 1 and *n*! inclusive.

**Example 1:**

	Input: n = 3, k = 3
	Output: "213"

**Example 2:**

	Input: n = 4, k = 9
	Output: "2314"


#### Java Solution:

	class Solution {
	    public String getPermutation(int n, int k) {
	        int[] factorial = getFactorial(n);
	    	if(n<=0 || k<=0 || k>factorial[n])
	    		return "";
	        if(k==n && n==1)
	            return "1";
	    	int i = 1;
	        int m = k;
	        //System.out.println(factorial[n]);
	    	String res = "";
	    	List<String> num = new ArrayList<String>();
	    	for(int j=0; j<=n; j++){
	    		num.add(j,""+j);
	    	}
	    	while(i<n-1){
	    		int temp = (k/factorial[n-i] + (k%factorial[n-i]==0?0:1));
	    		res = res + num.remove(temp);
	            //System.out.println(k/factorial[n-i] +":"+ (k%factorial[n-i]==0?0:1));
	    		k = (k%factorial[n-i]==0)?factorial[n-i]:(k%factorial[n-i]);
	    		i++;
	    	}
	        //System.out.println(num.size());
	    	if(m%2==0){
	    		return res+num.remove(2)+num.remove(1);
	    	}else{
	    		return res+num.remove(1)+num.remove(1);
	    	}
	    }
	    public int[] getFactorial(int n){
	    	int[] res = new int[n+1];
	    	res[0] = 1;
	    	for (int i=1; i<=n; i++) {
	    		res[i] = res[i-1]*i;
	    	}
	    	return res;
	    }
	}


#### Java Discussion Solution:

	class Solution {
	    public String getPermutation(int n, int k) {
	    	int pos = 0;
		    List<Integer> numbers = new ArrayList<>();
		    int[] factorial = new int[n+1];
		    StringBuilder sb = new StringBuilder();
		    
		    // create an array of factorial lookup
		    int sum = 1;
		    factorial[0] = 1;
		    for(int i=1; i<=n; i++){
		        sum *= i;
		        factorial[i] = sum;
		    }
		    // factorial[] = {1, 1, 2, 6, 24, ... n!}
		    
		    // create a list of numbers to get indices
		    for(int i=1; i<=n; i++){
		        numbers.add(i);
		    }
		    // numbers = {1, 2, 3, 4}
		    
		    k--;
		    
		    for(int i = 1; i <= n; i++){
		        int index = k/factorial[n-i];//好巧妙啊，因为list从0开始，k--后，正好对应。
		        sb.append(String.valueOf(numbers.get(index)));
		        numbers.remove(index);
		        k-=index*factorial[n-i];
		    }
		    
		    return String.valueOf(sb);
	    }
	}

#### Python Discussion Solution:

	class Solution:
	    def getPermutation(self, n, k):
	        """
	        :type n: int
	        :type k: int
	        :rtype: str
	        """
	        table = [str(i) for i in range(1,n+1)] #generate a list to store the number
	        i = 0
	        res = ''
	        while table:
	            i += 1
	            m, k = divmod(k, math.factorial(n-i)) #the number of strings(like "123", "132") start with a certain number(like "1") is (n-1)!
	            if k==0:
	                m -= 1
	                k = math.factorial(n-i)
	            res += table.pop(m)
	        return res