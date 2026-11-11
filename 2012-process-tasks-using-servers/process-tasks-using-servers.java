class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        PriorityQueue<int[]> free = new PriorityQueue<>((a, b) -> {

            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        for (int i = 0; i < servers.length; i++) {
            free.offer(new int[]{i, servers[i]});
        }

        int[] ans = new int[tasks.length];
        int time = 0;

        for (int i = 0; i < tasks.length; i++) {
            time = Math.max(i, time);
            if (busy.size() == servers.length) {
                time = Math.max(i, busy.peek()[0]);
            }

            
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] currServer = busy.poll();
                free.offer(new int[]{currServer[1], currServer[2]});
            }

            int[] pick = free.poll();
            busy.offer(new int[]{tasks[i] + time, pick[0], pick[1]});
            ans[i] = pick[0];
        }

        return ans;
    }
}