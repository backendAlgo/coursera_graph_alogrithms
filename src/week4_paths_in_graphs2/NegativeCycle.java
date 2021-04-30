//package week4_paths_in_graphs2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        int V = adj.length;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        for (int i = 0; i < V - 1; i++) {
            for (int u = 0; u < V; u++) {
                for (int j = 0; j < adj[u].size(); j++) {
                    relaxation(u,adj[u].get(j),cost[u].get(j), dist);
                }
            }
        }
        for (int u = 0; u < V; u++) {
            for (int j = 0; j < adj[u].size(); j++) {
                if(relaxation(u,adj[u].get(j),cost[u].get(j), dist))
                    return 1;
            }
        }
        return 0;
    }
    private static boolean relaxation(int u, int v, int cost, int[] dist) {
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
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
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
        System.out.println(negativeCycle(adj, cost));
    }
}

