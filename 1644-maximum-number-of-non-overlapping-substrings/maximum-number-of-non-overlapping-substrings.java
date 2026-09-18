import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, -1);
        
        for (int i = 0; i < n; i++) {
            int charIdx = s.charAt(i) - 'a';
            if (left[charIdx] == -1) {
                left[charIdx] = i;
            }
            right[charIdx] = i;
        }
        
        List<int[]> intervals = new ArrayList<>();
        
        for (int i = 0; i < 26; i++) {
            if (left[i] == -1) continue;
            
            int l = left[i];
            int r = right[i];
            boolean isValid = true;
            
            for (int j = l; j <= r; j++) {
                int currChar = s.charAt(j) - 'a';
                if (left[currChar] < l) {
                    isValid = false;
                    break;
                }
                r = Math.max(r, right[currChar]);
            }
            
            if (isValid) {
                intervals.add(new int[]{l, r});
            }
        }
        
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));
        
        List<String> result = new ArrayList<>();
        int prevEnd = -1;
        
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
           
            if (start > prevEnd) {
                result.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }
        
        return result;
    }
}
