package com.aakash.dsa.graphs.complete;

import java.util.*;

public class BiggestConnectedComponent {
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
        System.out.println(adjList);
        int biggestConnectedComponentLength = biggestComponent(vertices, adjList);
        System.out.println(biggestConnectedComponentLength);

    }

    public static <T> int biggestComponent(Set<T> vertices, Map<T, List<T>> adjList){
        int biggestComponentLength = 0;
        Set<T> visited = new HashSet<>();
        for (T vertex : vertices){
            if (!visited.contains(vertex)){
                List<T> result = bfsFromSource(vertex, adjList, visited);
                biggestComponentLength = Math.max(biggestComponentLength, result.size());
            }
        }

        return biggestComponentLength;
    }

    public static <T> List<T> bfsFromSource(T source, Map<T, List<T>> adjList, Set<T> visited){
        List<T> result = new ArrayList<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(source);
        queue.add(source);

        while (!queue.isEmpty()){
            T current = queue.poll();
            result.add(current);

            for (T neighbour : adjList.getOrDefault(current, new ArrayList<>())){
                if (!visited.contains(neighbour)){
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        return result;
    }

    public static <T> Map<T, List<T>> buildGraph(List<Edge<T>> edges){
        Map<T, List<T>> adjList = new HashMap<>();

        for (Edge<T> edge: edges){
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
