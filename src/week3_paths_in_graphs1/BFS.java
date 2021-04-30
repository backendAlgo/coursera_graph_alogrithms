//package week3_paths_in_graphs1;

import java.util.*;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        int V = adj.length;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        int ans = -1;
        int size = 1;
        int curr;
        boolean[] visited = new boolean[V];
        boolean done = false;
        OUTER: while (!q.isEmpty()) {
            for (int i = 0; i < size; i++) {
                curr = q.poll();
                visited[curr] = true;
                if (curr == t){
                    done = true;
                    break OUTER;
                }
                for (int adjacent : adj[curr]) {
                    if (!visited[adjacent])
                        q.offer(adjacent);
                }
            }
            ans++;
            size = q.size();
        }
        if (done) return ans + 1;
        else return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

