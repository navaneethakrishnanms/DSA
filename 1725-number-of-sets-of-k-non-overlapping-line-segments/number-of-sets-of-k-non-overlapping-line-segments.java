class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        long N = n + k - 1;
        long R = 2 * k;
        
        long res = 1;
        for (int i = 1; i <= R; i++) {
            res = res * (N - R + i) % MOD;
            res = res * modInverse(i, MOD) % MOD;
        }
        return (int) res;
    }
    
    private long modInverse(long a, long m) {
        long m0 = m;
        long y = 0, x = 1;
        if (m == 1) return 0;
        while (a > 1) {
            long q = a / m;
            long t = m;
            m = a % m;
            a = t;
            t = y;
            y = x - q * y;
            x = t;
        }
        if (x < 0) x += m0;
        return x;
    }
}
