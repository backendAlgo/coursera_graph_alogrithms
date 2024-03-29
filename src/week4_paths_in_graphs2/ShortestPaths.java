package week4_paths_in_graphs2;

import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj,
                                      ArrayList<Integer>[] cost,
                                      int s,
                                      long[] distance,
                                      int[] reachable,
                                      int[] shortest) {
        int V = adj.length;
        distance[s] = 0;
        for (int i = 0; i < V - 1; i++) {
            for (int u = 0; u < V; u++) {
                for (int vi = 0; vi < adj[u].size(); vi++) {
                    relaxation(u, adj[u].get(vi), cost[u].get(vi), distance);
                }
            }
        }

        for (int u = 0; u < V; u++) {
            for (int vi = 0; vi < adj[u].size(); vi++) {
                if (relaxation(u, adj[u].get(vi), cost[u].get(vi), distance)) {

                }
            }
        }
    }
    // relaxation method
    private static boolean relaxation(int u, int v, int cost, long[] dist) {
        if (dist[u] != Integer.MAX_VALUE && dist[v] > dist[u] + cost) {
            dist[v] = dist[u] + cost;
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

