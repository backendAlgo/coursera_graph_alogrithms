package week4_paths_in_graphs2;

import java.util.*;

public class Dijkstra {
    static class DijkstraNode {
        //  unique id for describing each node
        private int id;
        //  value from source node to given node
        private int val;

        public DijkstraNode(int id, int val) {
            this.id = id;
            this.val = val;
        }
    }

    private static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int V = adj.length;
        int[] dist = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        TreeSet<DijkstraNode> pq = new TreeSet<>((a, b) -> b.val - a.val);
        pq.add(new DijkstraNode(s, 0));
        DijkstraNode curr;
        int currId;
        while (!pq.isEmpty()) {
            curr = pq.pollFirst();
            assert curr != null;
            currId = curr.id;
            for (int i = 0; i < adj[currId].size(); i++) {
                int adjacent = adj[currId].get(i);
                int val = cost[currId].get(i);
                if (dist[adjacent] > dist[currId] + val) {
                    pq.add(new DijkstraNode(adjacent, dist[currId] + val));
                    dist[adjacent] = dist[currId] + val;
                }
            }
        }
        return dist[t];
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}