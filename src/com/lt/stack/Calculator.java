package com.lt.stack;

/**
 * 栈模拟计算器,中缀表达式
 */
public class Calculator {
    public static void main(String[] args) {
        //根据前面老师的思路，完成表达式的运算
        String experssion = "70+3+20*6-2";      // 如何处理多位数的问题？
        //创建两个栈，数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义相关变量
        int index = 0;    //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';    //将每次扫描得到的char保存到ch
        String keepNum = "";  //用于拼接多位数

        //开始while循环的扫描experssion
        while (true) {
            //依此得到experssion的每一个字符
            ch = experssion.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做想响应的处理
            if (operStack.isOper(ch)) {//如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较，如果当前操作符的优先级小于或者等于栈中的操作符，就需要从数栈中pop两个数，
                    //再从符号栈中pop出一个符号，进行运算，将得到的结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算的结果入栈
                        numStack.push(res);
                        //然后把当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //如果为空就直接入符号栈
                    operStack.push(ch);
                }
            } else {//如果是数，则直接入数栈

                //numStack.push(ch-48);     没有解决多位数入栈问题
                //分析思路
                //1.当处理多位数时，不能发现时一个数就立即入栈，英因为它可能时多位数
                //2.在处理数，需要向experssion的表达式index后再看一位，如果是数就继续扫描，如果是符号才入栈
                //3.异常我们需要定义一个变量字符串，用于拼接

                //处理多位数
                keepNum += ch;

                //如果ch已经时experssion的最后一个，就直接入栈
                if (index == experssion.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意看最后一位，不是index++
                    if (operStack.isOper(experssion.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是运算符，则入栈keepNum="1"或者"123"
                        numStack.push(Integer.parseInt(keepNum));
                        //keepNum清空
                        keepNum = "";
                    }
                }
            }
            //让index+1，并判断是否扫描到experssion最后
            index++;
            if (index >= experssion.length()) {
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈那盒符号栈中pop出相应的数和符号，并运行
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字【结果】
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        //将数栈的最后数，pop出，就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式%s = %d", experssion, res2);
    }
}

//定义一个ArrayStack表示栈
class ArrayStack2 {
    private int maxSize;    //栈的大小
    private int[] stack;    //数组，数组模拟栈，数组就放在该数组
    private int top = -1;     //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //返回当前栈顶的值，但是不是出栈
    public int peek() {
        return stack[top];
    }

    //判断是否栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断是否栈满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈-pop，将栈顶的数据返回
    public int pop() {
        //先判断是否为空
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈为空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈，遍历时，需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员来确定，优先级使用数字表示
    //数字越大，则优先级就越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;//假定目前表达式只有+，-,*,/
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;//res用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }
}
