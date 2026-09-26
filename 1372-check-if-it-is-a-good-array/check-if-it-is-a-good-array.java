class Solution {
    public boolean isGoodArray(int[] nums) {
        int gcd = nums[0];
        for (int i = 1; i < nums.length; i++) {
            gcd = findGCD(gcd, nums[i]);
            if (gcd == 1) {
                return true;
            }
        }
        return gcd == 1;
    }
    public int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}