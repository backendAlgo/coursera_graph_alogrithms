package week2_graph_decomposition2;

import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        int V = adj.length;
        boolean[] visited = new boolean[V];
        boolean[] inProcess = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (acyclicUtil(adj, i, visited, inProcess)) {
                return 1;
            }
        }
        return 0;
    }

    private static boolean acyclicUtil(ArrayList<Integer>[] adj,
                                       int curr,
                                       boolean[] visited,
                                       boolean[] inProcess) {
        if (inProcess[curr])
            return true;
        if (visited[curr])
            return false;
        visited[curr] = true;
        inProcess[curr] = true;

        for (int i : adj[curr])
            if (acyclicUtil(adj, i, visited, inProcess))
                return true;
        inProcess[curr] = false;
        return false;
    }

    public static void main(String[] args) {
        ArrayList<Integer>[] adj = StronglyConnected.readGraph();
        System.out.println(acyclic(adj));
    }
}

