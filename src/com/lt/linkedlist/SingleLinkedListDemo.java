package com.lt.linkedlist;

import java.util.Stack;

/**
 * 单链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "aa", "及时雨");
        HeroNode hero2 = new HeroNode(2, "bb", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "cc", "智多星");


        //创建一个链表
        SingledLinkedList singledLinkedList = new SingledLinkedList();


        //加入
        singledLinkedList.addByOrder(hero1);
        singledLinkedList.addByOrder(hero2);
        singledLinkedList.addByOrder(hero3);

        //测试一下单链表的反转功能
    /*    System.out.println("反转单链表~");
        reverseList(singledLinkedList.getHead());
        singledLinkedList.list();*/

       /* System.out.println("测试逆序打印单链表,没有改变链表的结构~");
        reversePrint(singledLinkedList.getHead());*/

        /*//加入按照编号的顺序
        singledLinkedList.addByOrder(hero1);
        singledLinkedList.addByOrder(hero4);
        singledLinkedList.addByOrder(hero3);
        singledLinkedList.addByOrder(hero2);
        singledLinkedList.addByOrder(hero3);

        System.out.println();
        //打印
        singledLinkedList.list();

        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~");
        singledLinkedList.update(newHeroNode);

        System.out.println("修改后的链表情况~");
        singledLinkedList.list();

        //删除一个节点
        singledLinkedList.del(1);
        singledLinkedList.del(5);
        System.out.println("删除后链表的情况~");
        singledLinkedList.list();

        //测试一下 求单链表中有效节点的个数
        System.out.println("有效的节点个数=" + getLength(singledLinkedList.getHead()));

        //测试一下看看是否得到了倒数第k个节点
        HeroNode res = findLastIndexNode(singledLinkedList.getHead(), 1);
        System.out.println("res=" + res);*/
    }

    /**
     * 方法:获取到单链表的节点个数(如果是带头节点的链表,需求不统计头节点)
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {   //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助变量,这里我们没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;//遍历
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第k个节点【新浪面试题】
     * 思路
     * 1.编写一个方法，接收head节点，同时接收一个index
     * 2.index 表示是倒数第index个节点
     * 3. 先把链表从头到尾遍历,得到链表的总的长度getLength()
     * 4.得到size后，我们从链表的第一个开始遍历(size-index)个
     * 5.如果找到了，则返回该节点，否则返回null
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一个遍历得到链表的长度(节点个数)
        int size = getLength(head);
        //第二次遍历size-index位置,就是我们倒数的第k个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义一个辅助变量,for循环定位到倒数的index个
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    /**
     * 将单链表进行反转
     */
    public static void reverseList(HeroNode head) {
        //如果当前为空,或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        //定义一个辅助的指针(变量),帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");

        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur != null) {
            next = cur.next; //先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将cur连接到新的链表上
            cur = next; //让cur后移
        }
        //将head.next指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 使用栈，逆序打印链表元素
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;//空链表无法打印
        }

        //创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next; //cur后移,这样就可以压入下一个节点
        }
        //将栈中的节点进行打印，pop出栈
        while (stack.size()>0){
            System.out.println(stack.pop());    //stack的特点是先进后出
        }
    }

}

//定义SingleLinkedList 管理我们的HeroNode
class SingledLinkedList {
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    /**
     * 添加节点到单项链表
     * 思路，当不考虑编号顺序时
     * 1.找到当前链表的最后节点
     * 2.将最后这个节点的next指向新节点
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历节点找到最后
        while (true) {
            //找到链表最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时,temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    /**
     * 第二种方式在添加英雄时，根据排名将英雄插入到指定位置
     * (如果有这个排名，则添加失败，并给出提示)
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针来帮助我们找到添加的位置
        //因为单链表，因此我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) { //说明链表已经在链表最后
                break;
            }
            if (temp.next.no > heroNode.no) { //位置找到，就在temp后面插入
                break;
            } else if (temp.next.no == heroNode.no) { //锁门希望添加的hearNode的编号已存在
                flag = true;  //说明编号存在
                break;
            }
            temp = temp.next;     //后移，遍历当前链表
        }
        //判断flag的值
        if (flag) { //不能添加，说明编号已存在
            System.out.printf("准备插入的英雄编号%d已经存在了，不能加入\n", heroNode.no);
        } else {
            //插入到链表中，temp后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 显示链表[遍历]
     */
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移，否则死循环
            temp = temp.next;
        }
    }

    /**
     * 完成修改节点的信息，根据编号来修改，即no编号不能改
     * 说明
     * 1.根据newHeroNode的no来修改即可
     *
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }

        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;     //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;      //已经遍历完节点
            }
            if (temp.no == newHeroNode.no) {
                flag = true;  //找到节点
                break;
            }
            temp = temp.next;
        }

        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号%d的节点，不能修改\n", newHeroNode);
        }
    }

    /**
     * 删除节点
     * 思路
     * 1.head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
     * 2.说明我们在比较时，是temp.next.no和需要删除的节点的no比较
     *
     * @param no
     */
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; //标志是否找到带删除的节点

        while (true) {
            if (temp.next == null) {   //已到链表最后
                break;
            }
            if (temp.next.no == no) {
                //找到待删除的节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next; //temp后移，遍历
        }

        if (flag) {
            //当前节点指向目标节点的前一个节点
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d节点不存在", no);
        }
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点


    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
