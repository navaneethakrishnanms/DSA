class Solution {
    public int maxDepth(String s) {
        
        int count=0,max=0;
        char[] c=s.toCharArray();
        int l=c.length;
        for(int i=0;i<l;i++){
            if(c[i]=='('){
                count++;
            }else if(c[i]==')'){
                count--;
            }else{
                continue;
            }if(count>max){
                max=count;
            }
        }return max;
        
    }
}