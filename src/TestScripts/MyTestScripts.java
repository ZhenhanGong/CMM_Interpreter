package TestScripts;

/**
 * Created by marco on 21/12/2016.
 */
public class MyTestScripts {


    public static String test1 =
            " double a[10]; double b[10], c, d = 1.0; int e = 2, f = 3;" +
                    "\n int g = -1; double h = 2-0.5*4; double i = h+1; double j = 6.0/3; int k = 13%5;" +
                    "\n int l = (1+7)*(6+14/(9-2%8))-(-6+42)/12%13-57; int m = -g+4; a[0] = 0; //int n = a[0]; n = a[0];" +
                    "\n a[-g] = 1; a[e] = 2; a[3] = a[2]+1; a[1+3]=4; a[e+3]=5; a[2*(1+2)]=2*3-4%2; a[2*(e+1)+1]=2*3+1;" +
                    "\n a[(1+7)*(6+14/(9-2%8))-(-6+42)/12%13-57+4]=a[-g]+e-2+a[2*(e+1)+1]; " +
                    "\n write(a[(1+7)*(6+14/(9-2%8))-(-6+42)/12%13-57+4]);" +
                    "\n read(e); read(b[0]); read(b[-g]);" +
                    "\n read(b[e]); read(b[3]); read(b[1+3]); read(b[e+3]); read(b[2*(1+2)]); read(b[2*(e+1)+1]);" +
                    "\n read(b[(1+7)*(6+14/(9-2%8))-(-6+42)/12%13-57+4]);";


    public static String whileLoop =
            "int i = 0;" +
                    "while(i<5) {" +
                    "i=i+1;" +
                    "break;\n" +
                    "}" +
                    "i=i+5;" +
                    "while(i<10){" +
                    "i=i+1;" +
                    "write(i);" +
                    "}";


    public static String ifElse =
            "\n if(2>1)" +
                    "\n write(1);" +
                    "\n else" +
                    "\n write(0);" +
                    "\n if(2<1){" +
                    "\n write(0);" +
                    "\n write(0);" +
                    "\n } else {" +
                    "\n write(2);" +
                    "\n write(2);" +
                    "\n }" +
                    "\n if(2>1){" +
                    "\n write(3);" +
                    "\n write(3);" +
                    "\n }else" +
                    "\n write(0);" +
                    "\n if(2<1)" +
                    "\n write(0);" +
                    "\n else{" +
                    "\n write(4);" +
                    "\n write(4);" +
                    "\n }" +
                    "\n ";



    // TODO BUG1 initialize variable with array element
    public static String bug1 = "int a[5]; a[0] = 666; int b = a[0]; write(b);";

    //TODO: BUG2 repeat declare variable in while loop
    public static String bug2 =
            "int i = 0; " +
                    "\n while(i<2) {" +
                    "\n int j = 0;" +
                    "\n while(j<3) {" +
                    "\n j=j+1;" +
                    "\n write(j);" +
                    "\n }" +
                    "\n i=i+1;" +
                    "\n }";

}
