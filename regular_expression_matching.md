## Regular Expression Matching

Implement regular expression matching with support for '.' and '*'.

绝望，深深的绝望

怎么都不对

动态规划

	class Solution {
	    public boolean isMatch(String s, String p) {
	        int m = s.length();
	        int n = p.length();
	        boolean[] res = new boolean[n+1];
	        res[0] = true;
	        for(int i=2; i<=n; i++){
	            res[i] = res[i-2] && p.charAt(i-1)=='*';
	        }
	        boolean pre, cur;
	        char pc, sc, tc;
	        for(int i=1; i<=m; i++){
	            pre = res[0];
	            res[0] = false;
	            sc = s.charAt(i-1);
	            for(int j=1; j<=n; j++){
	                cur = res[j];
	                pc = p.charAt(j-1);
	                if(pc!='*'){
	                    res[j] = pre && (sc==pc || pc=='.');
	                }else{
	                    tc = p.charAt(j-2);
	                    res[j] = res[j-2] || (res[j] && (sc==tc || tc=='.'));
	                }
	                pre = cur;
	            }
	        }
	        return res[n];
	    }
	}