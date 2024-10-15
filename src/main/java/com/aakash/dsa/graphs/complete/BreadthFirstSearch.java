package com.aakash.dsa.graphs.complete;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {
    public static void main(String[] args) {
       /*
                    0
                   / \
                  1   2
                 / \
                3   4

        */
        int vertex = 5;
        int[][] edges = {
               {0, 1},
               {0, 2},
               {1, 3},
               {1, 4}
       };
       int source = 0;
       List<List<Integer>> adjList = buildGraph(vertex, edges);
       printAdjacencyList(adjList);
       List<Integer> result = bfs(vertex, adjList, source);
       System.out.println(result);


    }

    public static List<Integer> bfs(int vertex, List<List<Integer>> adjList, int source){

        // Handle cases for empty graph or invalid sources
        if (vertex == 0 || source < 0 || source > vertex){
            return new ArrayList<>();
        }

        // Result list to store the BFS traversal order
        List<Integer> result = new ArrayList<>();

        // Create an array to track visited vertices so far
        boolean[] visited = new boolean[vertex];

        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();

        // Mark the source vertex as visited and enqueue it
        visited[source] = true;
        queue.add(source);

        // While queue is not empty process each vertex
        while (!queue.isEmpty()){

            // Dequeue a vertex from queue
            int current = queue.poll();
            result.add(current);

            // Get all the adjacent vertex of dequeued vertex
            for (int neighbour: adjList.get(current)){
                if (!visited[neighbour]){
                    // If an adjacent vertex has not been visited, mark it visited
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }

        return result;
    }

    public static List<List<Integer>> buildGraph(int vertex, int[][] edges){
        // Create adjacency List to represent the graph
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < vertex; i++) {
            adjList.add(new ArrayList<>());
        }

        // Add Edges to adjacency list
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u); // Since graph is undirected
        }

        return adjList;
    }
    
    public static void printAdjacencyList(List<List<Integer>> adjList){
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print("[" + i + "] ::==> ");
            List<Integer> neighbors = adjList.get(i);

            // Check if there are any neighbors to print
            for (int j = 0; j < neighbors.size(); j++) {
                System.out.print(neighbors.get(j));
                if (j < neighbors.size() - 1) {
                    System.out.print(" --> ");
                }
            }
            System.out.println();
        }
    }
}
