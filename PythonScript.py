import os
import sys
import subprocess

def compile_java_source():

    Java_source_files="Java_files"   #folder containing java files
    package_path=os.path.join(Java_source_files,"arbitraryarithmetic") #initializing path of package(containing AInteger aFloat)
    main_file_path=os.path.join(Java_source_files,"MyInfArith.java")   #initializing main file path
    AInteger_path=os.path.join(package_path,"AInteger.java")           #getting Ainteger java path
     
    AFloat_path=os.path.join(package_path,"AFloat.java")               #getting Afloat java path    
    Compilation_command=["javac",main_file_path,AInteger_path,AFloat_path]  #command to compile all the files

    Compile_result=subprocess.run(Compilation_command,capture_output=True,text=True)


    
    if Compile_result.returncode != 0: #checking compiled or not
        print("Compilation failed")
        sys.exit(1)                   # exit if compilation failed
    print("Compilation successful")

#function to run the main file
def run_java_program(data_type, operation, first_operand, second_operand):
    
    
    

    #command to run the main file
    run_command = f"java -cp Java_files MyInfArith {data_type} {operation} {first_operand} {second_operand}"
    
    
    run_result = os.system(run_command)
    
    #if run command fails printing failed
    if run_result!= 0:
        print("Execution failed")
        sys.exit(1)



    
if len(sys.argv) != 5: #if arguements given more than 4
    print("Usage: python3 execute_java.py <int|float> <add|sub|mul|div> <num1> <num2>")

    sys.exit(1)
    

data_type, operation, first_operand, second_operand = sys.argv[1:]

    
compile_java_source()


#calling run_java_program function
run_java_program(data_type, operation, first_operand, second_operand)
