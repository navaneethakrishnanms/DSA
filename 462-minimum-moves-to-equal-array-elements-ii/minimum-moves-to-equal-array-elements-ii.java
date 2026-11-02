import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        
        
        int median = nums[nums.length >> 1];
        
        int moves = 0;
        
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}
