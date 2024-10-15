package com.aakash.dsa.graphs.complete;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyList {
    /*
            0 -- 1 -- 2
            |  / |  /
            | /  | /
            4 -- 3

     */
    public static void main(String[] args) {
        int vertex = 5;
        List<List<Integer>> adjList = new ArrayList<>(vertex);
        for (int i = 0; i < vertex; i++) {
            adjList.add(new ArrayList<>());
        }

        addEdge(adjList, 0, 1);
        addEdge(adjList, 0, 4);
        addEdge(adjList, 1, 2);
        addEdge(adjList, 1, 3);
        addEdge(adjList, 1, 4);
        addEdge(adjList, 2, 3);
        addEdge(adjList, 3, 4);

        printGraph(adjList);
    }


    public static void addEdge(List<List<Integer>> adjList, int u, int v){
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public static void printGraph(List<List<Integer>> adjList){
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print("Adjacency List of vertex : " + i + "::-->");
//            System.out.print("Head");
            for (int j = 0; j < adjList.get(i).size(); j++) {
                System.out.print(" -> " + adjList.get(i).get(j));
            }
            System.out.println();
        }
    }
}
