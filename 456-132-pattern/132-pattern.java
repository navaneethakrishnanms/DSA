class Solution {
    public boolean find132pattern(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        int maxk = Integer.MIN_VALUE;
        boolean ispoped=false;
        for (int i = n - 1; i >= 0; i--) {
            if (!st.isEmpty() && ispoped && nums[i] < st.peek() && nums[i] < maxk)
                return true;

            while (!st.isEmpty() && st.peek() < nums[i]) {
                int popele = st.pop();
                ispoped = true;
                if (popele > maxk) {
                    maxk = popele;
                }
            }

            st.push(nums[i]);
        }
        return false;
    }
}