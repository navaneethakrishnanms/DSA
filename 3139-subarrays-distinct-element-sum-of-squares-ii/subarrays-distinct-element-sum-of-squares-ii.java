
class Solution {
    static final int MOD = 1_000_000_007;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        long[] sum = new long[4 * n];
        long[] sumSq = new long[4 * n];
        long[] lazy = new long[4 * n];

        java.util.HashMap<Integer, Integer> last = new java.util.HashMap<>();

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int prev = last.getOrDefault(x, -1);

           
            update(1, 0, n - 1, prev + 1, i,
                   sum, sumSq, lazy);

            ans = (ans + sumSq[1]) % MOD;
            last.put(x, i);
        }

        return (int) ans;
    }

    private void update(int node, int left, int right,
                        int ql, int qr,
                        long[] sum, long[] sumSq, long[] lazy) {

        if (ql > right || qr < left) {
            return;
        }

        if (ql <= left && right <= qr) {
            long len = right - left + 1L;
            long oldSum = sum[node];

            sumSq[node] = (sumSq[node]
                    + 2L * oldSum + len) % MOD;

            sum[node] = (oldSum + len) % MOD;
            lazy[node]++;
            return;
        }

        pushDown(node, left, right, sum, sumSq, lazy);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, ql, qr,
               sum, sumSq, lazy);

        update(node * 2 + 1, mid + 1, right, ql, qr,
               sum, sumSq, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSq[node] = (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
    }

    private void pushDown(int node, int left, int right,
                          long[] sum, long[] sumSq, long[] lazy) {

        long add = lazy[node];

        if (add == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;

        apply(node * 2, mid - left + 1L, add, sum, sumSq, lazy);
        apply(node * 2 + 1, right - mid, add, sum, sumSq, lazy);

        lazy[node] = 0;
    }

    private void apply(int node, long len, long add,
                       long[] sum, long[] sumSq, long[] lazy) {

        long oldSum = sum[node];

        sumSq[node] = (sumSq[node]
                + 2L * add % MOD * oldSum % MOD
                + add * add % MOD * len % MOD) % MOD;

        sum[node] = (oldSum + add * len) % MOD;
        lazy[node] = (lazy[node] + add) % MOD;
    }
}
