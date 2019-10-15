package com.lt.tree;

/**
 * 二叉树的前序、中序、后序遍历和查找
 * 二叉树子节点的删除
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {

        //创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");

        //先需要创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree(root);

        //说明，我们先手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);


        //测试
       /* System.out.println("前序遍历"); //1，2，3，4
        binaryTree.preOrder();
        System.out.println("中序遍历"); //2，1，3，4
        binaryTree.infixOrder();
        System.out.println("后序遍历"); //2，4，3，1
        binaryTree.postOrder();*/

        //加入关胜后
        HeroNode node5 = new HeroNode(5, "关胜");
        node3.setLeft(node5);
       /* System.out.println("前序遍历"); //1，2，3，5，4
        binaryTree.preOrder();
        System.out.println("中序遍历"); //2，1，5，3，4
        binaryTree.infixOrder();
        System.out.println("后序遍历"); //2，5，4，3，1
        binaryTree.postOrder();*/

        //前序遍历查找的次数 4
       /* System.out.println("前序遍历方式");
        HeroNode resNode=binaryTree.preOrderSearch(5);
        if (resNode!=null){
            System.out.printf("找到了,信息为 no=%d name=%s",resNode.getNo(),resNode.getName());
        }else {
            System.out.printf("没有找到 no=%d的英雄 ",5);
        }*/

        //中序遍历查找次数 3
        /*System.out.println("中序遍历方式");
        HeroNode resNode=binaryTree.infixOrderSearch(5);
        if (resNode!=null){
            System.out.printf("找到了,信息为 no=%d name=%s",resNode.getNo(),resNode.getName());
        }else {
            System.out.printf("没有找到 no=%d的英雄 ",5);
        }*/

        //后序遍历查找次数 2
        /*System.out.println("后序遍历方式");
        HeroNode resNode = binaryTree.postOrderSearch(5);
        if (resNode != null) {
            System.out.printf("找到了,信息为 no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no=%d的英雄 ", 5);
        }*/


        //测试删除节点的代码
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        //binaryTree.delNode(5);
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();
    }
}

//定义BinaryTree二叉树
class BinaryTree {
    private HeroNode root;

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    //删除指定节点
    public void delNode(int no) {
        if (root != null) {
            //如果只有一个root节点，这里就需要判断root是不是删除的节点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除~");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

//先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;  //默认为null
    private HeroNode right; //默认为null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 递归删除节点
     * 如果删除的节点是叶子节点，则删除该节点
     * 如果删除的节点是非叶子节点，则删除该子树
     *
     * @param no
     */
    public void delNode(int no) {
        //如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left=null,结束递归
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        //如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，就将this.right=null,结束递归
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        //向左子树递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }

        //向右子树递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);   //先输出父节点
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no 查找no
     * @return 如果找到就返回该Node，如果没有找到返回null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("前序遍历比较~");
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }

        //判断当前节点的左子节点是否为空,如果不为空,则递归前序查找
        //如果左递归前序查找，找到节点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) { //说明我们左子树找到
            return resNode;
        }

        //当前的节点的右子节点是否为空，如果不为空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no 查找no
     * @return 如果找到就返回该Node，如果没有找到返回null
     */
    public HeroNode infixOrderSearch(int no) {
        //判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("中序遍历比较~");
        //如果没有找到，就和当前节点比较，如果是则返回当前节点
        if (this.no == no) {
            return this;
        }

        //否则继续继续右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        //判断当前节点的左子节点是否为空，如果不为空，则递归后续查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) { //说明在左子树找到
            return resNode;
        }

        //如果左子数没有找到，则向右子数递归进行
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) { //说明在右子树找到
            return resNode;
        }

        System.out.println("后序遍历比较~");
        //如果左右子数都没有找到，就比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}