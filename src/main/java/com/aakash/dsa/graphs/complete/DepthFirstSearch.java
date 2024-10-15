package com.aakash.dsa.graphs.complete;

import java.util.*;

public class DepthFirstSearch {
    public static void main(String[] args) {
          /*
                0
               / \
              1   2
               \  /
                3

                4
               / \
              5---6

         */

        Set<Integer> vertices = Set.of(0, 1, 2, 3, 4, 5, 6);

        List<Edge<Integer>> edges = List.of(
             new Edge<>(0, 1),
             new Edge<>(0, 2),
             new Edge<>(1, 3),
             new Edge<>(2, 3),
             new Edge<>(4, 5),
             new Edge<>(4, 6),
             new Edge<>(5, 6)
        );

        Map<Integer, List<Integer>> adjList = buildGraph(edges);
        List<Integer> traversal = dfs(vertices, adjList);
        System.out.println(traversal);

    }

    public static <T> List<T> dfs(Set<T> vertices, Map<T, List<T>> adjList){
        List<T> result = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        int count = 0;
        for (T vertex : vertices){
            if (!visited.contains(vertex)){
                dfsRecursive(vertex, visited, adjList, result);
                count++;
            }
        }
        System.out.println(count);
        return result;
    }

    public static <T> void dfsRecursive(T source, Set<T> visited, Map<T, List<T>> adjList, List<T> result){
        visited.add(source);
        result.add(source);

        for (T neighbour : adjList.getOrDefault(source, new ArrayList<>())){
            if (!visited.contains(neighbour)){
                dfsRecursive(neighbour, visited, adjList, result);
            }
        }
    }

    public static <T> Map<T, List<T>> buildGraph(List<Edge<T>> edges){
        Map<T, List<T>> adjList = new HashMap<>();

        for (Edge<T> edge : edges){
            T source = edge.source;
            T destination = edge.destination;

            adjList.putIfAbsent(source, new ArrayList<>());
            adjList.putIfAbsent(destination, new ArrayList<>());

            adjList.get(source).add(destination);
            adjList.get(destination).add(source);
        }

        return adjList;
    }

    public static class Edge<T>{
        private T source;
        private T destination;

        public Edge(T source, T destination) {
            this.source = source;
            this.destination = destination;
        }
    }
}
