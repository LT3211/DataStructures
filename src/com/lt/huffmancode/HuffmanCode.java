package com.lt.huffmancode;

import java.io.*;
import java.util.*;

//哈夫曼编码
public class HuffmanCode {
    //生成赫夫曼树对应的赫夫曼编码
    //思路:
    //1. 将赫夫曼编码存放在Map<byte,String>形式
    //   32->01  97->100
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2. 在生成赫夫曼编码表示，需要拼接路径，定义一个StringBuilder 存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        /*String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();

        //将数据进行压缩
        byte[] huffmanCodesBytes = huffmanZip(contentBytes);

        //将数进行解压
        byte[] sourcesBytes = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println(new String(sourcesBytes));*/


        //测试压缩文件的路径
        /*String srcFile = "D:\\BathBach_ZH-CN4601637280_1920x1080.jpg";
        String dstFile = "D:\\dst.zip";

        zipFile(srcFile, dstFile);
        System.out.println("压缩文件成功");*/

        //测试解压文件
        String zipFile = "D:\\dst.zip";
        String dstFile = "D:\\DathBach_ZH-CN4601637280_1920x1080.jpg";
        unZipFile(zipFile, dstFile);
        System.out.println("解压成功！");
    }

    /**
     * 使用一个方法，将前面的方法封装起来，便于我们调用
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的赫夫曼编码(根据赫夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;
    }

    /**
     * 将字符串对应的byte[]数组，通过生成的赫夫曼树编码表，返回一个赫夫曼编码压缩后的byte[]
     *
     * @param bytes        这时原始的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码
     * @return 返回赫夫曼编码处理后的byte[]
     * 举例：String content="i like like like java do you like a java";
     * 返回的是 字符串"1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * =>对应的byte[] huffmanCodeBytes ,即8位对应一个byte，放入到huffmanCodesBytes
     * huffmanCodeBytes[0]=10101000(补码) => byte [推导 => 10101000 -1 =>10100111(反码) => 11011000 = -88]
     * huffmanCodeBytes[1]=-88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        //1.利用huffmanCodes 将bytes 转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();

        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //System.out.println(stringBuilder.toString());

        //将"10101000101111111100100..."转成byte[]
        //统计返回byte[]huffmanCodes长度
        //一句话 int len=(stringBuilder.length()+7)/8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        //创建存储压缩后的byte数组
        byte[] huffmanCodesBytes = new byte[len];

        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {    //不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            //将strByte转成一个byte，放入到huffmanCodesBytes
            huffmanCodesBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodesBytes;
    }


    /**
     * 重载getCodes
     */
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能:将传入的Node节点的所有叶子节点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node          传入节点
     * @param code          路径:左子节点是0，右子节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);

        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {    //如果node==null不处理
            //判断当前节点是叶子节点还是非叶子节点
            if (node.data == null) {   //非叶子节点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {//说明是个叶子节点
                //就表示找到某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * 前序遍历
     *
     * @param root
     */
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }


    /**
     * @param bytes 接收一个list数组
     * @return 返回的就是List形式[Node[data=97,weight=5],Ndode[..]]
     */
    private static List<Node> getNodes(byte[] bytes) {
        //1.创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        //2.遍历bytes,统计每一个byte出现的次数->map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {   //Map还没有这个字符数据，第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        //3.把每一个键值对转成一个Node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    /**
     * 创建赫夫曼树
     *
     * @param nodes
     * @return
     */
    private static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            //排序,从小到大
            Collections.sort(nodes);
            //取出第一棵最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二棵最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树，它的根节点没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理的两棵二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树，加入到nodes
            nodes.add(parent);
        }
        //nodes 最后的节点就是赫夫曼树的根节点
        return nodes.get(0);
    }


    //完成数据的解压
    //思路
    //1.将huffmanCodeBytes[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //    重新先转成 赫夫曼编码对应的二进制的字符串"10101000..."
    //2.将赫夫曼编码对应的二进制字符串，=> 对照 赫夫曼编码 => "i like like like java do you like a java"

    /**
     * 将一个byte转成一个二进制的字符串
     *
     * @param flag 标志是否需要补高位如果是true，表示需要补高位，如果是false表示不补
     * @param b    传入的byte
     * @return 是该b 对应的二进制的字符串(注意是按补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b; //将b转成int
        //如果是正数我们还存在补高位
        if (flag) {
            temp |= 256;  //按位与256  1 0000 0000 | 0000 00001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);    //返回的是temp对应的二进制的补码

        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 完成对压缩数据的解码
     *
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的字符数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到huffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //2.将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }


        //把字符串按照指定的赫夫曼编码进行解码
        //把赫夫曼编码进行调换，因为反向查询a->100 100->a
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;    //小计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                //1010100010111...
                //地址的取出key1
                String key = stringBuilder.substring(i, i + count);//i 不动，让count移动，指定匹配一个字符
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到count
        }
        //当for循环结束后，list中就存放了所有的字符"i like like like java do you like a java"
        //把list中的数据放入到byte[]并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    //编写一个方法，完成对压缩文件的解压

    /**
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {

        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } finally {

            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println(e2.getMessage());
            }

        }
    }

    //编写方法，将一个文件进行压缩

    /**
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {

        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes); //我们是把
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(huffmanCodes);


        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
            }
        }

    }
}

//创建Node，待数据和权值
class Node implements Comparable<Node> {
    Byte data;  //存放数据本身，比如'a'=>97  ' '=>32
    int weight; //权值，表示字符出现的位置
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

}