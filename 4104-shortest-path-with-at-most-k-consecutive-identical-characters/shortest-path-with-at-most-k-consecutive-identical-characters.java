import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]}); 
        }
        
       
        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        dist[0][1] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 0, 1});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];
            int curCount = curr[2];
            
            if (u == n - 1) {
                return d;
            }
            
            if (d > dist[u][curCount]) {
                continue;
            }
            
            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];
                
                int nextCount = (labels.charAt(v) == labels.charAt(u)) ? curCount + 1 : 1;
                
                if (nextCount <= k) {
                    int nextDist = d + weight;
                    
                    if (nextDist < dist[v][nextCount]) {
                        dist[v][nextCount] = nextDist;
                        pq.offer(new int[]{nextDist, v, nextCount});
                    }
                }
            }
        }
        
        return -1;
    }
}
