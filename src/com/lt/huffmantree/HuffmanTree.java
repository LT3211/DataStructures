package com.lt.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//赫夫曼树
//树的带权路径长度最小
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);

        preOrder(huffmanTree); //67 29 38 15 7 8 23 10 4 1 3 6 13
    }

    /**
     * 前序遍历
     *
     * @param root
     */
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("是空树，不能遍历~");
        }
    }

    /**
     * 创建赫夫曼树
     *
     * @param arr 需要创建按成赫夫曼树的数组
     * @return 创建好后的赫夫曼树的root节点
     */
    public static Node createHuffmanTree(int[] arr) {
        //第一步为了操作方便
        //1.遍历arr数组
        //2.将arr的每个元素构建成一各Node
        //3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();

        for (int value : arr) {
            nodes.add(new Node(value));
        }

        //我们处理的过程是一个循环的过程

        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);

            //取出根节点最小的两棵二叉树
            //(1)取出权值最小的结点(二叉树)
            Node leftNode = nodes.get(0);
            //(2)取出权值第二小的节点
            Node rightNode = nodes.get(1);

            //(3)构建一棵新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //(4)从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(5)将parent加入到nodes
            nodes.add(parent);
        }

        //返回赫夫曼树的root节点
        return nodes.get(0);
    }
}

//创建结点类
//为了让Node 对象持续排序Collections集合排序
//让Node实现Comparable接口
class Node implements Comparable<Node> {
    int value;//节点权值
    Node left;//指向左子节点
    Node right;//指向右子节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }
}