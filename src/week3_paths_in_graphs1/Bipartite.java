//package week3_paths_in_graphs1;

import java.util.*;

public class Bipartite {
    private static int bipartite(int toBegin, ArrayList<Integer>[] adj, int[] flag) {
        int V = adj.length;
        //0 for not visited
        //1 for black color
        //2 for red color
        Queue<Integer> q = new ArrayDeque<>();
        //0 node is chosen as a beginning of bfs
        q.offer(toBegin);
        flag[toBegin] = 1;
        int curr;
        while (!q.isEmpty()) {
            curr = q.poll();
            for (int adjacent : adj[curr]) {
                if (flag[adjacent] == flag[curr])
                    return 0;
                if (flag[adjacent] == 0) {
                    flag[adjacent] = flag[curr] == 1 ? 2 : 1;
                    q.offer(adjacent);
                }
            }
        }
        return 1;
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
        int V = adj.length;
        int[] flag = new int[adj.length];
        for (int i = 0; i < V; i++) {
            if (flag[i] == 0) {
                if (bipartite(i, adj, flag) == 0) {
                    System.out.println(0);
                    return;
                }
            }
        }
        System.out.println(1);
    }
}

