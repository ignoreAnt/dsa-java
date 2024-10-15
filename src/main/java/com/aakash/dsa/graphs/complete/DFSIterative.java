package com.aakash.dsa.graphs.complete;

import java.util.*;

public class DFSIterative {
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
        List<Integer> vertices = List.of(0, 1, 2, 3, 4, 5, 6);
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
        List<Integer> dfsTraversal = dfs(vertices, adjList);
        System.out.println(dfsTraversal);


        List<String> sVertices = List.of("A", "B", "C", "D", "E", "F", "G");
        List<Edge<String>> edgeList = List.of(
                new Edge<>("A", "B"),
                new Edge<>("A", "C"),
                new Edge<>("B", "D"),
                new Edge<>("C", "D"),
                new Edge<>("E", "F"),
                new Edge<>("E", "G"),
                new Edge<>("F", "G")
        );
        Map<String, List<String>> adjListString = buildGraph(edgeList);
        List<String> sTraversal = dfs(sVertices, adjListString);
        System.out.println(sTraversal);
    }

    public static <T> List<T> dfs(List<T> vertices, Map<T, List<T>> adjList){
        List<T> traversal = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        int count = 0;
        for (T vertex : vertices){
            if (!visited.contains(vertex)){
                traversal.addAll(dfsIterative(vertex, adjList, visited));
                count++;
            }
        }
        System.out.println(count);
        return traversal;
    }

    public static <T> List<T> dfsIterative(T source, Map<T, List<T>> adjList, Set<T> visited){
        List<T> result = new ArrayList<>();
        Deque<T> stack = new ArrayDeque<>();

        visited.add(source);
        stack.push(source);

        while (!stack.isEmpty()){
            T current = stack.pop();
            result.add(current);

            for (T neighbour : adjList.getOrDefault(current, new ArrayList<>())){
                if (!visited.contains(neighbour)){
                    visited.add(neighbour);
                    stack.push(neighbour);
                }
            }
        }

        return result;
    }

    public static <T>Map<T, List<T>> buildGraph(List<Edge<T>> edges){
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
