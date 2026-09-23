class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int totalSum = 0;
        
        for (int num : nums) {
            totalSum += num;
        }
        
        int target = totalSum - x;
        if (target < 0) return -1;
        if (target == 0) return n;
        
        int maxLen = -1;
        int currentSum = 0;
        int i = 0;
        
        for (int j = 0; j < n; j++) {
            currentSum += nums[j];
            
            while (currentSum > target && i <= j) {
                currentSum -= nums[i];
                i++;
            }
            
            if (currentSum == target) {
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }
        
        return maxLen == -1 ? -1 : n - maxLen;
    }
}
