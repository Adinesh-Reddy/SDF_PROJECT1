# 1.🛑Introduction
This project is about implementing an arbitrary precision arithmetic library in Java. The main goal is to implement accurate arithmetic operations on
extremely large integers and floating point numbers without encountering overflow or round off errors which occurs generally. In programming, data types
such as int or double have fixed sizes, which restrict the range and precision of
numerical computations. To overcome these limitations, this project represents
numbers as strings and implements arithmetic operations manually, allowing for
complete control over precision and scale.
The library supports basic operations addition, subtraction, multiplication, and division for both integer and floating-point types. All operations
are implemented in such a way that they preserve the full information of the
numbers involved, avoiding any loss of precision due to rounding. For floatingpoint numbers, up to 30 digits of decimal precision are supported. and different
ways to run this library by using python script and Ant make file.

# 2.🛑Project Folder <br>
SDF_PROJECT1/ <br>
arbitraryarithmetic/aarithmetic.jar <br>
build/arbitraryarithmetic/AInteger.class,AFloat.class <br>
build/MyInfArith.class <br>
Java_files/arbitraryarithmetic/AInteger.java,AFloat.java <br>
Java_files/MyInfArith.java <br>
build.xml     <br>
Pythonscript.py <br>
Report.tex   <br>
Report.pdf <br>
README.md  <br>

# 3.🛑Code Overview <br>
  This project implements an arbitrary precision arithmetic library in Java, capable of performing accurate arithmetic operations on extremely large integers and floating-point numbers.AInteger and AFloat classes represent numbers as strings to overcome the limitations of Java’s data types, ensuring operations avoid overflow and rounding errors. The AInteger class supports addition, subtraction, multiplication, and division for large integers using digit-by-digit string manipulation, including sign handling and zero trimming. Similarly, the AFloat class handles floating-point arithmetic with up to 30 digits of decimal precision, carefully managing decimal placement, rounding, and precision control. The main driver class, MyInfArith, provides a command-line interface that takes four arguments—data type (int or float), operation (add, sub, mul, div), and two numeric operands—then uses the appropriate class to compute and print the result.<br>
  
• AInteger.java: This class handles arbitrary-precision integers, providing methods for addition, subtraction, multiplication, and division. <br>
• AFloat.java: This class handles arbitrary-precision floating-point numbers, methods for addition,subraction,mutliplication and division,with accurate precison and give result upto 30 decima places<br>
• MyInfArith.java: This is the main file that runs the code.<br>

# 4.🔍Verificaion <br>
# 1)Using Java: <br>
command: java MyInfArith < type > < operation > < operand1 > < operand2 > <br>
• Ex: Input: java MyInfArith int add 23650078224912949497310933240250
42939783262467113798386384401498 <br>
Output: 66589861487380063295697317641748 <br>
• Ex: Input: java MyInfArith int div 8792726365283060579833950521677211 
493835253617089647454998358 <br>
Output: 17804979 <br>
• Ex: Input: java MyInfArith float div 2.88832837283283 0.00000 <br>
Output: Exception in thread ”main” java.lang.ArithmeticException: Di-
vision by Zero Error <br>
• Ex: Input: java MyInfArith float div 8792726365283060579833950521677211.0
493835253617089647454998358 <br>
Output: 17804979.091469989302961159520087878533 <br>
<br>
# 2)Using Python Script: <br>
command: python3 PythonScript.py < type > < operation > < operand1 >
< operand2 > (ubuntu) <br>
• Ex: Input: python3 PythonScript.py float sub 840196454.51725 712586963.70283
Output: 127609490.81442 <br>
• Ex: Input: python3 PythonScript.py float mul 6400251.9377695 2326541.6827934
Output: 14890452913599.9717457253213 <br>
<br>
# 3)Using Ant: <br>
command: ant run -Darg1=< type > -Darg2=< operation > -Darg3=< operand1 >
-Darg4=< operand2 > <br>
• Ex: Input: ant run -Darg1=float -Darg2=div -Darg3=244727.15202 -
Darg4=75964.3891 <br>
Output: 3.221603634537752111008551505615 <br>
• Ex: Input: ant run -Darg1=int -Darg2=div -Darg3=22 -Darg4=125 <br>
Output: 0 <br>
<br>
# 4)Using Jar file: <br>
command: java -cp Javaf iles : arbitraryarithmetic/aarithmetics.jar MyIn-
fArith < type > < operation > < operand1 > < operand2 > <br>
• Ex: Input: java -cp Javaf iles : arbitraryarithmetic/aarithmetic.jar <br>
MyInfArith int sub 3116511674006599806495512758577 57745 242300346381144446453884008 <br>
Output: -54628730626339781337950941125431 <br>

# 4.🛑IMPLEMENTATION
   # USING JAVA:
   1)Open the project folder <br>
   2)Change the directory to java files(Contains AInteger.java AFloat.java MyInfArith.java) <br>
   3)First Compile MyInfArith.java using command "javac MyInfArith.java" <br>
   4)then run the Mainfile using comman "java MyInfArith Arg1 Arg2 Arg3 Arg4" <br>
   <br>
   # USING ANT:
   1)Open the project folder <br>
   2)Directly run the ant command (default target is compile) <br>
   3)Ant file contains compile,jar,clean,run targets <br>
   4)Make sure every time you make changes run "ant clean" to clean previous compiled files including jar <br>
   5)For compiling run "ant compile" <br>
   6)To run the code use the command " ant run -Darg1=type -Darg2=operation -Darg3=operand1 -Darg4<operand2 <br>
   # USING PYTHON SCRIPT:
   1)Open the project folder <br>
   2)Directly run the command "python3/python PythonScript.py Arg1 Arg2 Arg3 Arg4
   # USING JAR FILE:
   1)Open the project folder <br>
   2) Jar file contains AInteger.class and AFloat .class (compiled files) <br>
   3)To run the code the format is java -cp <class_path>:<jar_path> <main_class> <arg1> <arg2> <arg3> <arg4> <br>
   4)here in this project class-path is "javafiles" <br>
   5)jar path is "arbitraryarithmetic/aarithmetic.jar" <br>
   6)Run the command Ex:"java -cp Java_files:arbitraryarithmetic/aarithmetic.jar MyInfArith int add 123 456" <br>
   7)Make sure that you dont forget to link path of main class along with jarfiles <br>      

# 5.🛑 KEY LEARNINGS 
  1. Oops in java and handleing packages <br>
  2. Make file using Ant <br>
  3. Git Commands and pushing to git repositry <br>
  4. Os module in python to run the code using python script <br>
  5. Making jar file <br>
# 6. 🛑LIMITATIONS
1)For exytremely large numbers the multiplication and division operations
takes time to give result because of manual digit by digit operation in
methods <br>
2)It Supports only for four operations Addition,Subtraction,Division,Multiplication,does
not support for higher operations like suqare,max,min functions <br>
3)Does not support for inputing scientific notation of large numbers and
cannot handle other strings containg alphabets and different characters <br>

