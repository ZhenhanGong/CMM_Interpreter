package TestScripts;

/**
 * Created by marco on 17/12/2016.
 */
public class TestScripts {

    public static final String error1 =
            "/**\n" +
            " * 标志符错误 测试\n" +
            " * 2010-11-13\n" +
            " * 邱忠磊\n" +
            " * \n" +
            " * 测试变量声明是否合法 主要是：\n" +
            " * 1.标识符是否满足 用例规范(以字母、\n" +
            " *   数字、下划线组成，只能以字母开头\n" +
            " *   ，且不能以下划线结束)\n" +
            " * 2.在相同作用域内不允许出现同名变量\n" +
            " */\n" +
            "\n" +
            "\n" +
            "//测试标识符是否合法\n" +
            "int _a;\n" +
            "double 3_a;\t//数字开头\n" +
            "int a_;\t\t//下划线结尾\n" +
            "int a@com;\t//非法字符\n" +
            "\n" +
            "int i=0;\n" +
            "while(i<6)\n" +
            "{\n" +
            "\twrite(i);\n" +
            "\ti=i+1;\n" +
            "}\n" +
            "\n" +
            "//相同作用域内定义同名变量\n" +
            "int i=10;\n" +
            "\n" +
            "/*output\n" +
            "报错：报错信息可能各异\n" +
            "*///:~";

    public static final String error2 =
            "/**\n" +
            " * 数组报错 测试\n" +
            " * 2010-11-13\n" +
            " * 邱忠磊\n" +
            " * \n" +
            " * 数组报错 测试 主要是：\n" +
            " * 1.数组越界\n" +
            " * 2.数组下标\n" +
            " */\n" +
            "\n" +
            "double R[6];\t//声明实数数组\n" +
            "int I[2];\t//声明整数数组\n" +
            "\n" +
            "int i=0;\n" +
            "\n" +
            "//数组越界\n" +
            "while(i<7)\n" +
            "{\n" +
            "\tR[i] = i;\n" +
            "\ti=i+1;\n" +
            "}\n" +
            "\n" +
            "//下标无效\n" +
            "I[-2] = -1;\n" +
            "\n" +
            "/*output:\n" +
            "报错：报错信息可能各异\n" +
            "*///:~\n";

    public static final String error3 =
            "/**\n" +
            " * 注释报错 测试\n" +
            " * 2010-11-13\n" +
            " * 邱忠磊\n" +
            " * \n" +
            " * 注释报错 测试 主要是：\n" +
            " * 1.注释嵌套\n" +
            " * 2.多行注释无结尾\n" +
            " */\n" +
            "\n" +
            "int I[6];\n" +
            "\n" +
            "int i=0;\n" +
            "while(i<6)\n" +
            "{\n" +
            "\tI[i] = i;\n" +
            "\ti=i+1;\n" +
            "}\n" +
            "\n" +
            "/*output:\n" +
            "报错：报错信息可能各异\n" +
            "*///:~\n" +
            "\n" +
            "//注释嵌套\n" +
            "/* ffff /* mmmm */ nnnnn */\n" +
            "//多行注释无结尾\n" +
            "/* 报错...//\n";

    public static final String test1 =
            "/**\n" +
            " * 测试 变量声明\n" +
            " * 2010-11-13\n" +
            " * 邱忠磊\n" +
            " * \n" +
            " * 测试变量声明是否合法 主要是：\n" +
            " * 1.在相同作用域内不允许出现同名变量\n" +
            " * 2.不同作用域内隐藏上级同名变量\n" +
            " */\n" +
            "\n" +
            "//测试标识符是否合法\n" +
            "int a_2;\n" +
            "double r_2_r;\n" +
            "double i,j=23,k;\n" +
            "\n" +
            "\n" +
            "//测试不同作用域内同名变量隐藏\n" +
            "int c = 23;\n" +
            "double b[2];\n" +
            "b[0] = 23;\n" +
            "b[1] = 0;\n" +
            "if(b[1] == 0)\n" +
            "{\n" +
            "\tdouble c = 23.5;\n" +
            "\tif(c == 23.5)\n" +
            "\t\twrite(1);\n" +
            "\telse\n" +
            "\t\twrite(0);\n" +
            "\n" +
            "\tdouble b[1];\n" +
            "\tb[0] = 0.05;\n" +
            "\tif(b[0] == 0.05)\n" +
            "\t\twrite(1);\n" +
            "\telse\n" +
            "\t\twrite(0);\n" +
            "}\n" +
            "/*output:\n" +
            "1\n" +
            "1\n" +
            "*///:~";

    public static final String test2 =
            "/**\n" +
            " * 测试 一般变量赋值\n" +
            " * 2010-11-13\n" +
            " * 邱忠磊\n" +
            " * \n" +
            " * 测试 一般变量赋值 主要是：\n" +
            " * 1.声明时赋值\n" +
            " * 2.算术运算赋值\n" +
            " * 3.read赋值\n" +
            " * 4.变量传递，即把一个变量赋值给另外一个变量\n" +
            " * 这里涉及到类型转换问题\n" +
            " */\n" +
            "\n" +
            "///////////////声明时赋值\n" +
            "int aa = 23,ab = (-4);\n" +
            "double b ,c =2.55;\n" +
            "\n" +
            "\n" +
            "/////////////算术运算赋值\n" +
            "int a = 15 - 3 * ( 150 / 3 / 10);\n" +
            "if(a == 0)\n" +
            "{\n" +
            "\twrite(1);\n" +
            "}\n" +
            "else\n" +
            "\twrite(0);\n" +
            "\n" +
            "/////////////read赋值\n" +
            "double r;\n" +
            "read(r);\n" +
            "write(r);\n" +
            "\n" +
            "//变量传递\n" +
            "double r2 = 5;\n" +
            "int a2 = 3;\n" +
            "r2 = a2;\n" +
            "if(r2 == 3)\n" +
            "{\n" +
            "\twrite(1);\n" +
            "}\n" +
            "else\n" +
            "\twrite(0);\n" +
            "\n" +
            "r2 = a2 ;\n" +
            "write(r2);\n" +
            "/*output:\n" +
            "1\n" +
            "233\t\t\t#这是输入的数字\n" +
            "233.0\n" +
            "1\n" +
            "3.0\n" +
            "*///:~";

    public static final String test3 =
            "/**\n" +
            " * 数组测试\n" +
            " * 2010-11-13\n" +
            " * 邱忠磊\n" +
            " * \n" +
            " * 数组测试 主要是：\n" +
            " * 1.数组下标\n" +
            " * 2.数组赋值\n" +
            " * 其中数组赋值包括 \n" +
            " *\t1)直接赋值; \n" +
            " *\t2)算术运算赋值; \n" +
            " *\t3)变量赋值; \n" +
            " *\t4)read赋值\n" +
            " */\n" +
            "\n" +
            "double doubleArray[6]; //声明实数数组\n" +
            "int  intArray [2]; //声明整形数组\n" +
            "double a = 2.0;\n" +
            "int b = 0;\n" +
            "\n" +
            "//给数组赋值\n" +
            "doubleArray[b] = 2;\n" +
            "doubleArray[1] = a;\n" +
            "doubleArray[2] = intArray[0];\n" +
            "a = 0.9;\n" +
            "doubleArray[3] = a;\n" +
            "doubleArray[4] = 5 - 2 / (4.0 - 3) * 2.5 + 0.01; \n" +
            "\n" +
            "\n" +
            "read(doubleArray[5]);\n" +
            "\n" +
            "\n" +
            "//打印数组\n" +
            "int x = 0;\n" +
            "while(x < 6)\n" +
            "{\n" +
            "\twrite(doubleArray[x]);\n" +
            "\tx = x + 1;\n" +
            "}\n" +
            "\n" +
            "/*output:\n" +
            "23.33\t\t\t#这是输入的数字\n" +
            "2.0\n" +
            "2.0\n" +
            "0.0\n" +
            "0.9\n" +
            "0.01\n" +
            "23.33\n" +
            "*///:~";

    public static final String test4 =
            "/**\n" +
            " * 算术运算测试\n" +
            " * 2010-11-13\n" +
            " * 邱忠磊\n" +
            " * \n" +
            " * 算术运算测试 主要是：\n" +
            " * 1.运算优先级\n" +
            " * 2.运算时类型转换\n" +
            " * 3.算术精度\n" +
            " * 4.算术溢出\n" +
            " */\n" +
            "\n" +
            "int a ; \n" +
            "a = 2 * 4;\t//a = 8\n" +
            "if( a!= 8)\n" +
            "\twrite(0);\n" +
            "else\n" +
            "\twrite(1);\n" +
            "\n" +
            "double r ;\n" +
            "r = 2 * (3.0 - 2.10) - 0.9 * (2.50 / 1.25 );\t//r = 0.0\n" +
            "\n" +
            "if( r != 0)\n" +
            "{\n" +
            "\twrite(0);\n" +
            "}\n" +
            "else\n" +
            "\twrite(1);\n" +
            "\n" +
            "double b = 4.000001;\n" +
            "write(b);\n" +
            "\n" +
            "int x = 60 * 60 * 24 ;\n" +
            "int y = 60 * 60 ;\n" +
            "write(x / y); \n" +
            "\n" +
            "/*output:\n" +
            "1\n" +
            "0\n" +
            "4.000001\n" +
            "24\n" +
            "*///:~";

    public static final String test5 =
            "/**\n" +
            " * IF-ELSE 测试\n" +
            " * 2010-11-13\n" +
            " * 邱忠磊\n" +
            " * \n" +
            " * IF-ELSE 测试 主要是：\n" +
            " * 1.条件判断\n" +
            " * 2.语句执行\n" +
            " * 3.IF-ELSE 嵌套\n" +
            " */\n" +
            "\n" +
            "int a;\n" +
            "a=1;\n" +
            "if(a * 5 == 5)\n" +
            "{\n" +
            "  double r ;\n" +
            "  r = 2.0;\n" +
            "  if(r)\n" +
            "  {\n" +
            "\twrite(r);\n" +
            "  }\n" +
            "}\n" +
            "else\n" +
            "{\n" +
            "   a=5;\n" +
            "   write(a);\n" +
            "} \n" +
            "\n" +
            "//if 嵌套\n" +
            "int aa = 3;\n" +
            "if(aa < 4)\n" +
            "\tif(2 < aa)\n" +
            "\t\tif(aa != 3)\n" +
            "\t\t\twrite(aa);\n" +
            "\t\telse\n" +
            "\t\t\twrite(aa-2);\n" +
            "\n" +
            "\n" +
            "/*output:\n" +
            "2.0\n" +
            "1\n" +
            "*///:~";

    public static final String test6 =
            "/**\n" +
            " * WHILE 测试\n" +
            " * 2010-11-13\n" +
            " * 邱忠磊\n" +
            " * \n" +
            " * WHILE 测试 主要是：\n" +
            " * 1.条件判断\n" +
            " * 2.语句执行\n" +
            " * 3.WHILE嵌套\n" +
            " */\n" +
            "\n" +
            "int a = 4;\n" +
                    "int j;" +
            "while(a != 0)\n" +
            "{\n" +
            "\twrite(a);\n" +
            "\tj=a-1;\n" +
            "\twhile(0 < j)\n" +
            "\t{\n" +
            "\t\twrite(j);\n" +
            "\t\tj=j-1;\n" +
            "\t}\n" +
            "\ta=a-1;\n" +
            "}\n" +
            "/*output:\n" +
            "4\n" +
            "3\n" +
            "2\n" +
            "1\n" +
            "3\n" +
            "2\n" +
            "1\n" +
            "2\n" +
            "1\n" +
            "1\n" +
            "*///:~ \n";

    public static final String test7 =
            "/**\n" +
            " * IF-ELSE 与 WHILE 混合嵌套 测试\n" +
            " * 2010-11-13\n" +
            " * 邱忠磊\n" +
            " * \n" +
            " * IF-ELSE 与 WHILE 混合嵌套 测试 主要是：\n" +
            " * 1.条件判断\n" +
            " * 2.语句执行\n" +
            " * 3.IF-ELSE 与 WHILE 混合 嵌套\n" +
            " */\n" +
            "\n" +
            "int a = 4;\n" +
                    "int j;" +
            "while(a != 0)\n" +
            "{\n" +
            "\tj = a;\n" +
            "\twhile(j != 0)\n" +
            "\t{\n" +
            "\t\tif(j/2 != 1)\n" +
            "\t\t\twrite(j);\n" +
            "\t\tj = j-1;\t\n" +
            "\t}\n" +
            "\tif( a < 2)\n" +
            "\t{\n" +
            "\t\twrite(a);\n" +
            "\t}\n" +
            "\telse\n" +
            "\t\twrite(a+3);\n" +
            "\ta = a -1;\n" +
            "}\n" +
            "/*output:\n" +
            "4\n" +
            "1\n" +
            "7\n" +
            "1\n" +
            "6\n" +
            "1\n" +
            "5\n" +
            "1\n" +
            "1\n" +
            "*///:~";

    public static final String test8 =
            "/**\n" +
            " * 阶乘测试\n" +
            " * 2010-11-13\n" +
            " * 邱忠磊\n" +
            " * \n" +
            " * 阶乘测试 主要是\n" +
            " * a = N!,其中N为整数\n" +
            " */ \n" +
            "\n" +
            "int a = 6;\n" +
            "int factorial = 1;\n" +
            "while( a != 0 )\n" +
            "{\n" +
            "\tfactorial = factorial * a;\n" +
            "\ta = a -1;\n" +
            "}\n" +
            "write( \"factorial  :\" ); " +
            "write( factorial );\n" +
            "\n" +
            "/*output:\n" +
            "factorial :\n" +
            "720\n" +
            "*///:~  ";

    public static final String test9 =
            "\n" +
            "\n" +
            "\n" +
            "        double R[6] ;\n" +
            "\tR[1] = -0.99;\n" +
            "\tR[2] = -1.0;\n" +
            "\tR[3] = 5;\n" +
            "\tR[4] = 4.01;\n" +
            "\tR[5] = 3.0;\n" +
            "\n" +
            "\tint i;\n" +
            "\tint j;\n" +
            "\tint swap;\n" +
            "        i = 6;\n" +
            "        while (i != 1)\n" +
            "        {\n" +
            "\t    swap = 0;\n" +
            "            j = 1;\n" +
            "            while (j < i-1)\n" +
            "            {\n" +
            "\t\t//交换数组元素\n" +
            "                if ( R[j+1] < R[j])\n" +
            "                {\n" +
            "\t\t    swap = 1;\n" +
            "                    R[0] = R[j];\n" +
            "\t\t    R[j] = R[j+1];\n" +
            "\t\t    R[j+1] = R[0];\n" +
            "                }\n" +
            "               j = j + 1;\n" +
            "            }\n" +
            "            i = i - 1;\n" +
            "\t    if(swap != 1)\n" +
            "\t\ti = 1;\n" +
            "        }\n" +
            "\n" +
            "\tint k = 1;\n" +
            "\twhile(k<6)\n" +
            "\t{\n" +
            "\t\twrite(R[k]);\n" +
            "\t\tk = k +1;\n" +
            "\t}\n" +
            "/*output:\n" +
            "-1.0\n" +
            "-0.99\n" +
            "3.0\n" +
            "4.01\n" +
            "5.0 \n" +
            "*///:~";
}
