package com.iflytek.staff.chao.structure.base.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : wangchaodee
 * @Description: 图 算法
 * @date Date : 2022年07月13日 19:16
 */
public class Graph {


    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }


    public Node cloneGraph(Node node) {
        return cloneGraph(node, new HashMap<Integer, Node>());
    }

    private Node cloneGraph(Node node, HashMap<Integer, Node> visited) {
        if (node == null) {
            return node;
        }
        if (visited.containsKey(node.val)) {
            return visited.get(node.val);
        }

        Node newNode = new Node(node.val);
        visited.put(node.val, newNode);
        for (Node neighbor : node.neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor, visited));
        }

        return newNode;
    }


}


