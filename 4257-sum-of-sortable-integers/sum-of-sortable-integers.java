import java.util.ArrayList;
import java.util.List;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int sumOfK = 0;
        
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i * i != n) {
                    divisors.add(n / i);
                }
            }
        }
        
        for (int k : divisors) {
            if (isValidSortable(nums, n, k)) {
                sumOfK += k;
            }
        }
        
        return sumOfK;
    }
    
    private boolean isValidSortable(int[] nums, int n, int k) {
        int prevMax = Integer.MIN_VALUE;
        
        for (int start = 0; start < n; start += k) {
            int end = start + k;
            
            int currentMin = Integer.MAX_VALUE;
            int currentMax = Integer.MIN_VALUE;
            int dropCount = 0;
            
            for (int i = start; i < end; i++) {
                currentMin = Math.min(currentMin, nums[i]);
                currentMax = Math.max(currentMax, nums[i]);
                
                int nextIndex = start + ((i - start + 1) % k);
                if (nums[i] > nums[nextIndex]) {
                    dropCount++;
                }
            }
            
            if (dropCount > 1) {
                return false;
            }
            
            if (currentMin < prevMax) {
                return false;
            }
            
            prevMax = currentMax;
        }
        
        return true;
    }
}
