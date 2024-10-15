package com.aakash.dsa.graphs.complete;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSOnDisconnectedGraphs {
    public static void main(String[] args) {
        int vertex = 5;
        int[][] edges = {
                {0, 1},
                {1, 2},
                {3, 4}
        };

        List<List<Integer>> adjList = buildGraph(vertex, edges);
        List<Integer> traversal = bfs(vertex, adjList);
        System.out.println(traversal);
    }

    public static List<List<Integer>> buildGraph(int vertex, int[][] edges){
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < vertex; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
        
        return adjList;
    }

    public static List<Integer> bfs(int vertex, List<List<Integer>> adjList){
        List<Integer> traversal = new ArrayList<>();
        boolean[] visited = new boolean[vertex];

        // Perform BFS on each vertex
        for (int i = 0; i < vertex; i++) {
            if (!visited[i]){

                List<Integer> subTraversal = bfsFromSource(adjList, i, visited);
                traversal.addAll(subTraversal);
            }
        }

        return traversal;
    }
    public static List<Integer> bfsFromSource(List<List<Integer>> adjList, int source, boolean[] visited){
        
        // Initialize the list to store result
        List<Integer> result = new ArrayList<>();
        
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        
        // mark source as visited and add it to the queue
        visited[source] = true;
        queue.add(source);
        
        while (!queue.isEmpty()){
            // Dequeue a vertex from queue
            int current = queue.poll();
            result.add(current);

            for (int neighbour : adjList.get(current)){
                if (!visited[neighbour]){
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }

        return result;
    }
}
