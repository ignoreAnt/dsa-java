package com.aakash.dsa.graphs.complete;

import java.util.*;

public class ConnectComponents {
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
        Node<Integer> node0 = new Node<>(0);
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node7 = new Node<>(7);

        int vertex = 7;
        Set<Node<Integer>> vertices = Set.of(node0, node1, node2, node3, node4, node5, node6, node7);
        List<Edge<Integer>> edges = List.of(
                new Edge<>(node0, node1),
                new Edge<>(node0, node2),
                new Edge<>(node1, node3),
                new Edge<>(node2, node3),
                new Edge<>(node4, node5),
                new Edge<>(node4, node6),
                new Edge<>(node5, node6)

        );

        Map<Node<Integer>, List<Node<Integer>>> adjList = buildGraph(edges);
        int connectedComponents = countConnectedComponents(vertices, adjList);
        System.out.println(connectedComponents);


    }

    public static <T> int countConnectedComponents(Set<Node<T>> vertices, Map<Node<T>, List<Node<T>>> adjList){
        List<Node<T>> traversal = new ArrayList<>();
        Set<Node<T>> visited = new HashSet<>();
        int connectedComponents = 0;
        for (Node<T> vertex : vertices){
            if(!visited.contains(vertex)){
                traversal.addAll(bfsFromSourceNode(vertex, adjList, visited));
                connectedComponents++;
            }
        }


        return connectedComponents;

    }

    public static <T> List<Node<T>> bfsFromSourceNode(Node<T> source, Map<Node<T>, List<Node<T>>> adjList, Set<Node<T>> visited){
        List<Node<T>> result = new ArrayList<>();
        Queue<Node<T>> queue = new LinkedList<>();

        visited.add(source);
        queue.add(source);

        while (!queue.isEmpty()){
            Node<T> current = queue.poll();
            result.add(current);

            for(Node<T> neighbour : adjList.getOrDefault(current, new ArrayList<>())){
                if (!visited.contains(neighbour)){
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        return result;
    }


    public static <T>  Map<Node<T>, List<Node<T>>> buildGraph(List<Edge<T>> edges){
        Map<Node<T>, List<Node<T>>> adjList = new HashMap<>();

        for (Edge<T> edge : edges){
            Node<T> source = edge.source;
            Node<T> destination = edge.destination;

            adjList.putIfAbsent(source, new ArrayList<>());
            adjList.putIfAbsent(destination, new ArrayList<>());

            adjList.get(source).add(destination);
            adjList.get(destination).add(source);
        }

        return adjList;
    }


    public static class Node<T>{
        private T element;

        public Node(T element) {
            this.element = element;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(element, node.element);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(element);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }

    public static class Edge<T>{
       private Node<T> source;
       private Node<T> destination;
       private Double weight;

        public Edge(Node<T> source, Node<T> destination) {
            this.source = source;
            this.destination = destination;
        }

        public Edge(T sourceValue, T destinationValue){
            this.source = new Node<>(sourceValue);
            this.destination = new Node<>(destinationValue);
        }
    }
}
