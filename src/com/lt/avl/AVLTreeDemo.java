package com.lt.avl;

//平衡二叉树(AVL)
//它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int [] arr={4,3,6,5,7,8};  测试左旋转
        //int [] arr={10,12,8,9,7,6}; 测试右旋转
        int arr[] = {10, 11, 7, 6, 8, 9};
        //创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("在平衡处理");
        System.out.println("树的高度是=" + avlTree.getRoot().height());
        System.out.println("树的左子树的高度=" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度=" + avlTree.getRoot().rightHeight());
        System.out.println("当前的根节点等于" + avlTree.getRoot());
        System.out.println("根节点的左子节点" + avlTree.getRoot().left);
        System.out.println("根节点的右子节点" + avlTree.getRoot().right);
    }
}

//平衡二叉树
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;  //如果root为空则直接让root指向node
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}

//创建Node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 返回当前节点的高度，以该节点为根节点的树的高度
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 添加节点的方法
     * 递归的形式添加节点，注意要满足二叉排序树的要求
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }

        //判断传入的节点的值，和当前子树的根节点的值关系
        if (node.value < this.value) {
            //如果当前节点左子节点位null
            if (this.left == null) {
                this.left = node;
            } else {
                //递归向左子树添加
                this.left.add(node);
            }
        } else {    //添加的节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                //递归的向右子树添加
                this.right.add(node);
            }
        }

        //当添加完一个节点后，如果:(右子树的高度-左子树的高度)>1,左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子树进行左旋转
                right.rightRotate();
                //再对当前节点进行左旋转
                leftHeight();
            } else {
                leftRotate();//左旋转..
            }
            return; //！！！！！！
        }

        //当添加完一个节点后，如果(左子树的高度-右子树的高度)>1,右旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的左子树高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前节点的左节点进行左旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            } else {
                //直接进行右旋转即可
                rightHeight();
            }
        }
    }

    /**
     * 左旋转方法
     */
    private void leftRotate() {
        //创建新的节点,以当前根节点的值
        Node newNode = new Node(value);
        //把新的节点的左子树设置成当前这个节点的左子树
        newNode.left = left;
        //把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值替换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成当前节点右子树的右子树
        right = right.right;
        //把当前节点的左子树(左子节点)设置成新的节点
        left = newNode;
    }

    /**
     * 右旋转
     */
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    /**
     *  中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}

