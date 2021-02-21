package week2_graph_decomposition2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
//I am using just dfs to topological sort
//I can use kahn's algorithms
public class Toposort {
    private static LinkedList<Integer> toposort(ArrayList<Integer>[] adj) {
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

    public static void main(String[] args) {
        ArrayList<Integer>[] adj = StronglyConnected.readGraph();
        LinkedList<Integer> order = toposort(adj);
        for (int x : order) {
            System.out.print((x + 1) + " ");
        }
    }
}

