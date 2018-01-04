class Solution{
    public int romanToInt(String s){
        if(strs.length==1)
            return strs[0];
        int index = 0;
        boolean flag = true;
        for(int i=0; i<strs[0].length(); i++){
            for(int j=1; j<strs.length; j++){
                if(i<strs[j].length() && strs[j]!="" && strs[0].substring(0,i+1)==strs[j].substring(0,i+1)){
                    index = i;
                }else{
                    flag = false;
                    break;
                }
            }
            if(!flag)
                break;
        }
        if(index==0)
            return "";
        return strs[0].substring(0,index+1);
    }
}