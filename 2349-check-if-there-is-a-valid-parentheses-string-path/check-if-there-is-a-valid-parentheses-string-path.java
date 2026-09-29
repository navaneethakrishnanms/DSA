class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int len = m + n - 1;

        if (len % 2 != 0) {
            return false;
        }

        if (grid[0][0] == ')') {
            return false;
        }

        if (grid[m - 1][n - 1] == '(') {
            return false;
        }

        boolean[][][] dp =
            new boolean[m][n][len + 1];

        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                int change = (grid[i][j] == '(') ? 1 : -1;

                for (int balance = 0; balance <= len; balance++) {

                    int prevBalance = balance - change;

                    if (prevBalance < 0 || prevBalance > len) {
                        continue;
                    }

                    if (i > 0 &&
                        dp[i - 1][j][prevBalance]) {

                        dp[i][j][balance] = true;
                    }

                    if (j > 0 &&
                        dp[i][j - 1][prevBalance]) {

                        dp[i][j][balance] = true;
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}