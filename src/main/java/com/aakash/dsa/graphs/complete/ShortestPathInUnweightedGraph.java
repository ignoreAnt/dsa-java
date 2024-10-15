package com.aakash.dsa.graphs.complete;

import java.util.*;

public class ShortestPathInUnweightedGraph {
    public static void main(String[] args) {
        /*
                0
               / \
              1---2
               \ /
                3
         */

        Map<Integer, List<Integer>> adjList = Map.of(
                0, List.of(1, 2),
                1, List.of(0, 2, 3),
                2, List.of(0, 1, 3),
                3, List.of(1, 2),
                4, List.of()
        );

        System.out.println(shortestDistanceFromSource(0, adjList));

    }

    /**
     * Find the shortest distance from source to all other vertices in an unweighted graph
     * @param source source vertex
     * @param adjList adjacency list representation of the graph
     * @return Map of vertex to the shortest distance from source
     * @param <T> type of vertex
     */
    public static <T> Map<T, Integer> shortestDistanceFromSource(T source, Map<T, List<T>> adjList){

       // Create a map to store the shortest distance from source
       Map<T, Integer> distances = new HashMap<>();

       // Initialize all distances to -1
       for (T vertex : adjList.keySet()){
           distances.put(vertex, -1);
       }

       // Distance from source to source is 0
       distances.put(source, 0);

       // Create a queue for BFS
       Queue<T> queue = new LinkedList<>();

       // Add source to the queue
       queue.add(source);

       // While queue is not empty process each vertex
       while (!queue.isEmpty()){
           // Dequeue a vertex from queue
           T current = queue.poll();

           // For each neighbour of current vertex
           for (T neighbour : adjList.getOrDefault(current, new ArrayList<>())){
               // If the neighbour is not visited
               if (distances.get(neighbour) == -1){
                   // Update the distance of neighbour from source
                   distances.put(neighbour, distances.get(current) + 1);
                   // Add the neighbour to the queue
                   queue.add(neighbour);
               }
           }
       }

       return distances;

    }
}
