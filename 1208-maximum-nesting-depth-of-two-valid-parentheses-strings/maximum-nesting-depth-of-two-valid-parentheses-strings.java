class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = i & 1;
            if (seq.charAt(i) == '(') {
                res[i] = (i % 2) ^ 1; 
            } else {
                res[i] = i % 2;
            }
        }
        return res;
    }
}
