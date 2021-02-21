package week3_paths_in_graphs1;

import java.util.*;

public class Bipartite {
    private static int bipartite(ArrayList<Integer>[] adj) {
        var V = adj.length;
        var flag = new int[V];
        //0 for not visited
        //1 for black color
        //2 for red color
        var q = new ArrayDeque<Integer>();
        //0 node is chose as a beginning of bfs
        q.offer(0);
        flag[0] = 1;
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
        System.out.println(bipartite(adj));
    }
}

