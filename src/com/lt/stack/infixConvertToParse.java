package com.lt.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 完成将一个中缀表达式转成后缀表达式的功能
 */
public class infixConvertToParse {
    public static void main(String[] args) {
        //说明
        //1. 1+((2+3)*4)-5  =>转成  1 2 3 + 4 * + 5 -
        //2.因为直接对str继进行操作，不方便，因此先将"1+((2+3)*4)-5"  =>中缀表达式对应的List
        //  即"1+((2+3)*4)-5"  =>  ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3.将得到的中缀表达式对应的List  =>  后缀表达式对应的List
        //  即ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  => ArrayList [1 2 3 + 4 * + 5 -]

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpression = toInfixExpression(expression);
        System.out.println("中缀表达式对应的List=" + infixExpression);  //ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> parseSuffixExpresionList = parseSuffixEexpreesionList(infixExpression);
        System.out.println("后缀表达式对应的List" + parseSuffixExpresionList); //ArrayList [1 2 3 + 4 * + 5 -]
    }

    //  即ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  => ArrayList [1 2 3 + 4 * + 5 -]
    //方法:将得到的中缀表达式对应的List  =>  后缀表达式对应的List
    public static List<String> parseSuffixEexpreesionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>(); //符号栈
        //说明: 因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用Stack<String>()直接使用List<String> s2
        List<String> s2 = new ArrayList<>();  //存储中间结果的Lists2

        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号")",则依此弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //将(弹出s1栈，消除小括号
            } else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次与s1中新的栈顶运算符想比较
                //问题: 我们缺少一个优先级高低的方法
                while (s1.size() != 0 && Operation2.getValue(s1.peek()) >= Operation2.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈中
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;  //因为是存放到List，因此按顺序输出就是正确的逆波兰表达式
    }


    //方法:将中缀表达式转成对应的List
    public static List<String> toInfixExpression(String s) {
        //将suffixExpression分割
        List<String> ls = new ArrayList<>();
        int i = 0;    //这是一个指针，用于遍历中缀表达式字符串
        String str;     //对多位数的拼接
        char c; //每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++; //i需要后移
            } else {//如果是一个数，需要考虑多位数
                str = "";   //先将str置成"" '0'[48]->'9'[57]
                while (i < s.length() && (c = s.charAt(i)) > 48 && (c = s.charAt(i)) < 57) {
                    str += c; //拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());

        return ls;  //返回
    }


}

//编写一个Operation可以返回一个运算符对应的优先级
class Operation2 {
    private final static int ADD = 1;
    private final static int SUB = 1;
    private final static int MUL = 1;
    private final static int DIV = 1;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
