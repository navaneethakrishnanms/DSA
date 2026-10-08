class Solution {
    public String removeOuterParentheses(String s) {
        int opened=0;
        StringBuilder sb=new StringBuilder();
        for(char c:s.toCharArray()){
            if(c=='('){
                if(opened>0){
                   sb.append(c);
                
                }opened++;
            }else{
                opened--;
                if(opened>0){
                    sb.append(c);
                }
            }
        }return sb.toString();
        
    }
}