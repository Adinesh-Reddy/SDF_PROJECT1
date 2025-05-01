import os
import sys

def compile_java_source():


    #Getting paths of jar file and main file
    jar_file_path = os.path.join("arbitraryarithmetic", "aarithmetic.jar")
    java_source_file_path = os.path.join("Java_files", "MyInfArith.java")
    

    #command to compile the main file
    compile_command = f"javac -cp {jar_file_path} {java_source_file_path}"
    
    #getting compilation result
    compile_result = os.system(compile_command)
    
    #if result is zero means compilation failed
    if compile_result != 0:
        print("Compilation failed")
        sys.exit(1)
    print("Compilation successful")

#function to run the main file
def run_java_program(data_type, operation, first_operand, second_operand):
    
    classpath_separator = ":" if os.name != 'nt' else ";" #for different OS system different seperator

    #getting jar file path
    jar_file_path = os.path.join("arbitraryarithmetic", "aarithmetic.jar")

    #command to run the main file
    run_command = f"java -cp {jar_file_path}{classpath_separator}Java_files MyInfArith {data_type} {operation} {first_operand} {second_operand}"
    
    
    run_result = os.system(run_command)
    
    #if run command fails printing failed
    if run_result != 0:
        print("Execution failed")
        sys.exit(1)



    
if len(sys.argv) != 5: #if arguements given more than 4
    print("Usage: python3 execute_java.py <int|float> <add|sub|mul|div> <num1> <num2>")
    sys.exit(1)

    
data_type, operation, first_operand, second_operand = sys.argv[1:]

    
compile_java_source()

#calling run_java_program function
run_java_program(data_type, operation, first_operand, second_operand)
