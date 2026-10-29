import java.util.*;

class Solution {
    private int n;
    private List<Integer>[] adj;
    private int[] tin, tout;
    private int timer;
    private int[] depth;
    private int[][] up;
    private int LOG;
    private char[] currentChars;
    private FenwickTree bit;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.currentChars = s.toCharArray();
        this.adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        this.tin = new int[n];
        this.tout = new int[n];
        this.depth = new int[n];
        this.timer = 1; 
        this.LOG = 32 - Integer.numberOfLeadingZeros(n);
        this.up = new int[n][LOG];

        dfs(0, 0, 0);

        this.bit = new FenwickTree(2 * n + 2);
        for (int i = 0; i < n; i++) {
            int mask = 1 << (currentChars[i] - 'a');
            bit.update(tin[i], mask);
            bit.update(tout[i] + 1, mask);
        }

        List<Boolean> result = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                
                int oldMask = 1 << (currentChars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int changeMask = oldMask ^ newMask;

                if (changeMask != 0) {
                    bit.update(tin[u], changeMask);
                    bit.update(tout[u] + 1, changeMask);
                    currentChars[u] = c;
                }
            } else { // "query"
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lcaNode = getLCA(u, v);
                int maskU = bit.query(tin[u]);
                int maskV = bit.query(tin[v]);
                int maskLCAChar = 1 << (currentChars[lcaNode] - 'a');

                int pathMask = maskU ^ maskV ^ maskLCAChar;

                result.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return result;
    }

    private void dfs(int u, int p, int d) {
        depth[u] = d;
        up[u][0] = p;
        for (int i = 1; i < LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        tin[u] = timer++;
        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, d + 1);
            }
        }
        tout[u] = timer++;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        for (int i = LOG - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }

    private static class FenwickTree {
        private final int[] tree;
        private final int size;

        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size];
        }

        public void update(int i, int delta) {
            while (i < size) {
                tree[i] ^= delta;
                i += i & (-i);
            }
        }

        public int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum ^= tree[i];
                i -= i & (-i);
            }
            return sum;
        }
    }
}
