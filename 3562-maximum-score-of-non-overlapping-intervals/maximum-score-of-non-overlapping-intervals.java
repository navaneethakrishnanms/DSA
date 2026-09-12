class Solution {
    private static class Interval {
        int id, start, end, weight;

        Interval(int id, int start, int end, int weight) {
            this.id = id;
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervalsList) {

        int n = intervalsList.size();

        Interval[] intervals = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> x = intervalsList.get(i);

            intervals[i] = new Interval(
                i,
                x.get(0),
                x.get(1),
                x.get(2)
            );
        }

        // Sort by ending time
        Arrays.sort(intervals, (a, b) -> {
            if (a.end != b.end)
                return Integer.compare(a.end, b.end);

            return Integer.compare(a.start, b.start);
        });

        // prev[i] = last interval before i that does not overlap
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = findPrevious(intervals, i);
        }

        /*
         * dp[i][k] = best solution using first i intervals
         *            and selecting at most k intervals.
         */
        List<Integer>[][] dp = new ArrayList[n + 1][5];

        long[][] weight = new long[n + 1][5];

        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= n; i++) {

            Interval curr = intervals[i - 1];

            for (int k = 1; k <= 4; k++) {

                // Option 1: don't take current interval
                weight[i][k] = weight[i - 1][k];
                dp[i][k] = new ArrayList<>(dp[i - 1][k]);

                // Option 2: take current interval
                long takeWeight =
                    weight[prev[i - 1] + 1][k - 1]
                    + curr.weight;

                List<Integer> takePath =
                    new ArrayList<>(dp[prev[i - 1] + 1][k - 1]);

                takePath.add(curr.id);

                Collections.sort(takePath);

                // Choose better solution
                if (takeWeight > weight[i][k]) {

                    weight[i][k] = takeWeight;
                    dp[i][k] = takePath;

                } else if (takeWeight == weight[i][k]
                           && isLexicographicallySmaller(
                               takePath,
                               dp[i][k])) {

                    dp[i][k] = takePath;
                }
            }
        }

        // Find best among selecting 0,1,2,3,4 intervals
        List<Integer> answer = new ArrayList<>();
        long bestWeight = 0;

        for (int k = 0; k <= 4; k++) {

            if (weight[n][k] > bestWeight) {

                bestWeight = weight[n][k];
                answer = dp[n][k];

            } else if (weight[n][k] == bestWeight
                       && isLexicographicallySmaller(
                           dp[n][k],
                           answer)) {

                answer = dp[n][k];
            }
        }

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private int findPrevious(Interval[] intervals, int i) {

        int low = 0;
        int high = i - 1;

        int answer = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (intervals[mid].end < intervals[i].start) {

                answer = mid;
                low = mid + 1;

            } else {

                high = mid - 1;
            }
        }

        return answer;
    }

    private boolean isLexicographicallySmaller(
        List<Integer> a,
        List<Integer> b
    ) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}