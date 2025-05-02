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

2.🛑Project Folder
SDF_PROJECT1/ <br>
arbitraryarithmetic/aarithmetic.jar
build/arbitraryarithmetic/AInteger.class,AFloat.class 
build/MyInfArith.class
