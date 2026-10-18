import java.util.ArrayList;
import java.util.List;

class Solution {
    private long[][] table;
    private static final int MOD = 1_000_000_007;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        table = new long[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            table[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        return (int) ((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        long root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < nums.size(); ++i) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        long leftSize = left.size();
        long totalSize = left.size() + right.size();

        long waysFromCombinations = table[(int) totalSize][(int) leftSize];

        long ans = (waysFromCombinations * leftWays) % MOD;
        ans = (ans * rightWays) % MOD;

        return ans;
    }
}
