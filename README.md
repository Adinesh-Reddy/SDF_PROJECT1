1.🛑Introduction
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

2.🛑Project Folder <br>
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

3.🛑Code Overview <br>
  This project implements an arbitrary precision arithmetic library in Java, capable of performing accurate arithmetic operations on extremely large integers and floating-point numbers.AInteger and AFloat classes represent numbers as strings to overcome the limitations of Java’s data types, ensuring operations avoid overflow and rounding errors. The AInteger class supports addition, subtraction, multiplication, and division for large integers using digit-by-digit string manipulation, including sign handling and zero trimming. Similarly, the AFloat class handles floating-point arithmetic with up to 30 digits of decimal precision, carefully managing decimal placement, rounding, and precision control. The main driver class, MyInfArith, provides a command-line interface that takes four arguments—data type (int or float), operation (add, sub, mul, div), and two numeric operands—then uses the appropriate class to compute and print the result.<br>
  
• AInteger.java: This class handles arbitrary-precision integers, providing methods for addition, subtraction, multiplication, and division. <br>
• AFloat.java: This class handles arbitrary-precision floating-point numbers, methods for addition,subraction,mutliplication and division,with accurate precison and give result upto 30 decima places<br>
• MyInfArith.java: This is the main file that runs the code.<br>

4.🔍Verificaion <br>
Using Java: <br>
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
Using Python Script: <br>
command: python3 PythonScript.py < type > < operation > < operand1 >
< operand2 > (ubuntu) <br>
• Ex: Input: python3 PythonScript.py float sub 840196454.51725 712586963.70283
Output: 127609490.81442 <br>
• Ex: Input: python3 PythonScript.py float mul 6400251.9377695 2326541.6827934
Output: 14890452913599.9717457253213 <br>
<br>
Using Ant: <br>
command: ant run -Darg1=< type > -Darg2=< operation > -Darg3=< operand1 >
-Darg4=< operand2 > <br>
• Ex: Input: ant run -Darg1=float -Darg2=div -Darg3=244727.15202 -
Darg4=75964.3891 <br>
Output: 3.221603634537752111008551505615 <br>
• Ex: Input: ant run -Darg1=int -Darg2=div -Darg3=22 -Darg4=125 <br>
Output: 0 <br>
<br>
Using Jar file: <br>
command: java -cp Javaf iles : arbitraryarithmetic/aarithmetics.jar MyIn-
fArith < type > < operation > < operand1 > < operand2 > <br>
• Ex: Input: java -cp Javaf iles : arbitraryarithmetic/aarithmetic.jar <br>
MyInfArith int sub 3116511674006599806495512758577 57745 242300346381144446453884008 <br>
Output: -54628730626339781337950941125431 <br>
