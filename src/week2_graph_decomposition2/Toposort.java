//package week2_graph_decomposition2;

import java.util.*;

//I am using just dfs to topological sort
//I can use kahn's algorithms
public class Toposort {
    //    using dfs but it causes time limit exceed
    private static LinkedList<Integer> toposort2(ArrayList<Integer>[] adj) {
        int V = adj.length;
        boolean[] visited = new boolean[adj.length];
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                dfs(adj, visited, ans, i);
        }


        return ans;
    }

    private static void dfs(ArrayList<Integer>[] adj, boolean[] visited, LinkedList<Integer> ans, int curr) {
        visited[curr] = true;
        for (int adjacentNodes : adj[curr]) {
            if (!visited[adjacentNodes])
                dfs(adj, visited, ans, adjacentNodes);
        }
        ans.addFirst(curr);
    }

    //    using kahn's algorithms
    private static LinkedList<Integer> toposort(ArrayList<Integer>[] adj) {
        int V = adj.length;
        LinkedList<Integer> ans =
                new LinkedList<>();
        // find indegrees of each vertex
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int adjacent : adj[i]) {
                indegree[adjacent]++;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < indegree.length; i++)
            if (indegree[i] == 0)
                q.offer(i);
        // to check if there is a cycle
        int count = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            ans.add(curr);
            for (int adjacent : adj[curr])
                if (--indegree[adjacent] == 0)
                    q.offer(adjacent);
            count++;
        }
        assert (count != V) : "There's a cycle";

        return ans;
    }

    public static void main(String[] args) {
        ArrayList<Integer>[] adj = readGraph();
        LinkedList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }

    public static ArrayList<Integer>[] readGraph() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        return adj;
    }
}

