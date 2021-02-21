package week2_graph_decomposition2;

import java.util.*;

//to solve this problem I am using Kosaraju's Algorithms
//time complexity O (V + E) which is asymptotically optimal
public class StronglyConnected {
    private static ArrayList<Integer>[] reverseGraph(ArrayList<Integer>[] adj) {
        int V = adj.length;
        ArrayList<Integer>[] rev = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            rev[i] = new ArrayList<>();
        }
        for (int i = 0; i < adj.length; i++) {
            for (int num : adj[i]) {
                rev[num].add(i);
            }
        }
        return rev;
    }
    private static void dfs1(ArrayList<Integer>[] adj,
                             int i,
                             boolean[] visited,
                             Deque<Integer> st) {
        visited[i] = true;
        for (int adjacents : adj[i]) {
            if (!visited[adjacents]) {
                dfs1(adj, adjacents, visited, st);
            }
        }
        st.push(i);
    }
    private static void dfs2(ArrayList<Integer>[] adj,
                             int i,
                             boolean[] visited) {
        visited[i] = true;
        System.out.print(i + " ");
        for (int adjacents : adj[i]) {
            if (!visited[adjacents]) {
                dfs2(adj, adjacents, visited);
            }
        }
    }
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        Deque<Integer> st = new ArrayDeque<>();
        int V = adj.length;
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs1(adj, i, visited, st);
            }
        }
        System.out.println("Stack: " + st);
        Arrays.fill(visited, false);
        adj = reverseGraph(adj);
        int curr;
        int ans = 0;
        while (!st.isEmpty()) {
            curr = st.poll();
            if (!visited[curr]) {
                dfs2(adj, curr, visited);
                ans++;
                System.out.println();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ArrayList<Integer>[] adj = readGraph();
        //System.out.println(Arrays.toString(reverseGraph(adj)));
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }

    public static ArrayList<Integer>[] readGraph() {
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
        }
        return adj;
    }
}

