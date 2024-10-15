package com.aakash.dsa.graphs.complete;

import java.util.*;

public class ShortestDistance {
        // Function to find the shortest path between start and end cities
        public static int shortestPath(int n, List<Road> roads, int start, int end) {
            // Step 1: Build the graph using an adjacency list
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            // Convert Road objects to an adjacency list representation
            for (Road road : roads) {
                graph.get(road.source).add(road.destination);
                graph.get(road.destination).add(road.source);  // Since roads are bidirectional
            }

            // Step 2: Initialize BFS structures
            Queue<CityNode> queue = new LinkedList<>(); // BFS queue storing CityNode objects
            boolean[] visited = new boolean[n]; // To track visited cities

            queue.offer(new CityNode(start, 0));  // Start BFS with the start city and distance 0
            visited[start] = true;

            // Step 3: Perform BFS
            while (!queue.isEmpty()) {
                CityNode currentNode = queue.poll();
                int currentCity = currentNode.city;
                int currentDistance = currentNode.distance;

                // If we've reached the destination city, return the distance
                if (currentCity == end) {
                    return currentDistance;
                }

                // Visit all neighboring cities
                for (int neighbor : graph.get(currentCity)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(new CityNode(neighbor, currentDistance + 1));
                    }
                }
            }

            // Step 4: If the destination city is not reachable, return -1
            return -1;
        }

        public static void main(String[] args) {
            int n = 6; // Number of cities
            List<Road> roads = Arrays.asList(
                    new Road(0, 1),
                    new Road(0, 2),
                    new Road(1, 3),
                    new Road(2, 3),
                    new Road(3, 4),
                    new Road(4, 5)
            );
            int start = 0;  // Starting city
            int end = 5;    // Destination city

            int result = shortestPath(n, roads, start, end);
            System.out.println("Shortest path from city " + start + " to city " + end + " is: " + result); // Output: 4
        }

    // Custom class to represent a road between two cities
    public static class Road {
        int source;
        int destination;

        public Road(int source, int destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    // Custom class to store the current city and the distance from the start city
    public static class CityNode {
        int city;
        int distance;

        public CityNode(int city, int distance) {
            this.city = city;
            this.distance = distance;
        }
    }
}
