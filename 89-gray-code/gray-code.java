import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        int totalCodes = 1 << n; 
        
        for (int i = 0; i < totalCodes; ++i) {
            ans.add(i ^ (i >> 1));
        }
        
        return ans;
    }
}
