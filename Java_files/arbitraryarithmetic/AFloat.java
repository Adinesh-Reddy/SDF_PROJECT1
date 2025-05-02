package arbitraryarithmetic;
/* AFloat class to implement arbitrary-precision  arithmetic
 * Using string representation for storing large numbers without overflow
*/

public class AFloat{
    public String value;// To store the integer in string form

    //Defualt constructor initializes to zero
    public AFloat(){
        this.value="0.0";
    }

    // Constructor accepting a string value
    public AFloat(String s){
        this.value=s;
    }

    // Copy constructor to copy value form another insatnce
    public AFloat(AFloat other) {
        this.value = other.value;
    }

    // Static parse to parse a string into AInteger insatance
    public static AFloat parse(String s) {
        return new AFloat(s);
    }

    //Making into single arguement
    //Adding another float to this float 

    public AFloat add(AFloat s){
        return new AFloat(AddF(this.value,s.value));
    }

    //multiplying another float to this float
    public AFloat mul(AFloat s){
        return new AFloat(MultiplyF(this.value,s.value));
    }

    //dividing this float by another float
    public AFloat div(AFloat s){
        return new AFloat(DivisionF(this.value,s.value));
    }

    //subtraction of another flaot from this float
    public AFloat sub(AFloat s){
        return new AFloat(SubF(this.value,s.value));
    }

    //Addition of two strings and returns output in string form
    public String Add(String Str1,String Str2){
        
    //adding zeros before numbers to make the lengths equal
        while (Str1.length()<Str2.length()){
            Str1="0"+Str1;

        }
        while (Str2.length()<Str1.length()){
            Str2="0"+Str2;

        }

        String Str="";// output string 
        int carry=0; //initializing carry as zero
        int n=Str1.length(); //length of the string

        //addition for logic
        for(int i=0;i<Str1.length();i++){
            carry=((Str1.charAt(n-1-i)-'0')+(Str2.charAt(n-1-i)-'0'))+carry;
            Str=String.valueOf(carry%10)+Str;
            carry=carry/10;
        }

        if(carry!=0){
            Str=String.valueOf(carry)+Str; //appending carry if it is not zero
        }

        return Str; //here we are not removing zeros at start in order to put decimal point in float addition
    }
    
    
     // Removes all leading zeros from the string representation of a number
     public String RemoveStartZero(String s){
        int i=0; // Start index

        // Move index forward while characters are zero
        while(i<s.length() && s.charAt(i)=='0' ){
            i++;
        }

        // If all characters are zero, return single "0"
        if(i==s.length()) return "0"; 
        
         // Otherwise return substring starting from the first non-zero character
        return s.substring(i);
    }
    

    //Comparing two strings numerically
    public int compare(String Str1, String Str2) {
    
        // Remove leading zeros from both strings
        Str1 = RemoveStartZero(Str1);
        Str2 = RemoveStartZero(Str2);
    
        // If Str1 is longer than Str2 after removing zeros, it is greater
        if (Str1.length() > Str2.length()) return 1;
    
        // If Str1 is shorter than Str2, it is smaller
        if (Str1.length() < Str2.length()) return -1;
    
        // If lengths are equal, compare characters one by one
        for (int i = 0; i < Str1.length(); i++) {
    
            // If current digit of Str1 is greater, return 1
            if ((Str1.charAt(i) - '0') > (Str2.charAt(i) - '0')) return 1;
    
            // If current digit of Str1 is smaller, return -1
            if ((Str1.charAt(i) - '0') < (Str2.charAt(i) - '0')) return -1;
        }
    
        // All digits are equal, so both strings represent the same number
        return 0;
    }
    


    //Multiplication of two strings and giving output as String
    public String Multiply(String Str1,String Str2){
        
        // Remove leading zeros from both input strings
        Str1=RemoveStartZero(Str1); 
        Str2=RemoveStartZero(Str2); 

        // Length of second string (used to loop through each digit)
        int m=Str2.length();

        // Initialize final result as zero
        String s="0"; 
 
        // Outer loop goes through each digit in Str2 from right to left
        for(int l=0;l<m;l++){
            String result="0";// Temporary result for current digit of Str2

            // Get current digit from Str2 (as integer) from right end
            int TEMP=Str2.charAt(m-l-1)-'0';
            
            // Repeat addition of Str1, TEMP times to simulate multiplication
            while(TEMP!=0){
                result=Add(result,Str1);
                TEMP--;
            }
            
            // Add required number of zeros at the end (place value shift)
            int L=l;
            while(L!=0){
                result=result+"0";
                L--;
                
            }
            // Add intermediate result to final result S
            s=Add(s,result);

        }
        // Return final result after removing any leading zeros
        return RemoveStartZero(s);
    }

    //Mutliplication of two floats returning result as output string
    public String MultiplyF(String Str1,String Str2){


        if(Str1.charAt(0)=='-' && Str2.charAt(0)!='-'){return (MultiplyF(Str1.substring(1),Str2).equals("0.0"))? MultiplyF(Str1.substring(1),Str2):("-"+MultiplyF(Str1.substring(1),Str2));  }
        if(Str2.charAt(0)=='-' && Str1.charAt(0)!='-'){return (MultiplyF(Str2.substring(1),Str1).equals("0.0"))? MultiplyF(Str2.substring(1),Str1):("-"+MultiplyF(Str2.substring(1),Str1));}
        if(Str1.charAt(0)=='-' && Str2.charAt(0)=='-'){return MultiplyF(Str1.substring(1),Str2.substring(1));}

        int no_of_decimal_points_Str1=0; //initiallizing no of decimal points as zero
        int no_of_decimal_points_Str2=0;

        int N1=Str1.length();
        int N2=Str2.length();

        //finding no of decimal points in Str1 and Str2
        while(N1!=0){
            if(Str1.charAt(N1-1)=='.'){ no_of_decimal_points_Str1++;}
            N1--;
        }
        while(N2!=0){
            if(Str2.charAt(N2-1)=='.'){ no_of_decimal_points_Str2++;}
            N2--;
        }

        if(no_of_decimal_points_Str1==0){Str1=Str1+".0";} //Appending ".0" if no of decimal points are Zero
        if(no_of_decimal_points_Str2==0){Str2=Str2+".0";}


        //finding index of decimal point Str1 and Str2
        int index_of_decimal_point_Str1=Str1.indexOf('.');
        int index_of_decimal_point_Str2=Str2.indexOf('.');

        //Removing decimal point and making into integers
        Str1=Str1.substring(0,index_of_decimal_point_Str1)+Str1.substring(index_of_decimal_point_Str1+1);
        Str2=Str2.substring(0,index_of_decimal_point_Str2)+Str2.substring(index_of_decimal_point_Str2+1);
        
        

        String Str= Multiply(Str1, Str2);
        

    
        int l1=Str.length();

        //l2 is sum of no of digits after decimal point in str1 and str2

        int l2=(Str1.length()-index_of_decimal_point_Str1+Str2.length()-index_of_decimal_point_Str2);
        
       // if length of Str is less than l2 append zero before str
        while(Str.length()<l2){
            Str="0"+Str;

        }

        l1=Str.length();
        
        //Adding decimal point to the integer result
        Str=RemoveStartZero(RemoveEndZero((Str.substring(0,l1-l2)+"."+Str.substring(l1-l2))));
        
        //if starting char is "." appending "0" ex- .99 --- 0.99
        if(Str.charAt(0)=='.'){ Str='0'+Str;}
        
        //if last char is "." append '0' at last
        if(Str.charAt(Str.length()-1)=='.'){ Str=Str.substring(0,(Str.length()-1))+".0";}

        //rounded of to 30 decimal position if it exceeds 30 digits after decimal point
        return RoundOffTo30(Str);       

    }
    
    public String Division(String Str1,String Str2){

       // Handles exception "Division by Zero Error"
        if(RemoveStartZero(Str2).equals("0")) throw new ArithmeticException("Division by Zero Error");
        if(RemoveStartZero(Str1).equals("0")) return "0";

        String Remainder=""; //Initialises to empty
        String Quotient="";  //Initialises to empty
        
        //loop runs over each digit of dividend
        for(int i=0;i<Str1.length();i++){
         
        //Append current digit to remainder
         Remainder=Remainder+Str1.charAt(i);
         Remainder=RemoveStartZero(Remainder);
 
         int Temp=0; //how many times we can subtract divisor from current remainder

         while(compare(Remainder,Str2)>=0){
             Remainder=Sub(Remainder,Str2);
             Temp++;
         }
         
         //Appending every time temp to Quotient
         Quotient=Quotient+String.valueOf(Temp);
 
        }
 
      return Quotient; //returning without removing zeros
     }
 
    //to remove zeros after a String with decimal point ex-- 0.0990000 -> 0.099
    public String RemoveEndZero(String Str){


        int i=Str.length()-1;// initiales index as last char

        while(i>0 && Str.charAt(i)=='0'){
            i--;
        }
        
        //returns substring
        return Str.substring(0,(i+1));

    }
    
    public String Sub(String Str1, String Str2) {
        
    
        while (Str1.length() < Str2.length()) {
            Str1 = '0' + Str1;
        }
        while (Str2.length() < Str1.length()) {
            Str2 = '0' + Str2;
        }
    
        String a = Str1;
        String b = Str2;
        boolean isNegative = false;
    
        if (compare(Str1,Str2)==-1) {
            a = Str2;
            b = Str1;
            isNegative = true;
        }
    
        String S = "";
        int borrow = 0;
        for (int j = 0; j < a.length(); j++) {
            int D1 = a.charAt(a.length() - j - 1) - '0';
            int D2 = b.charAt(a.length() - j - 1) - '0';
    
            if ((D1 - borrow) >= D2) {
                S = (D1 - D2 - borrow) + S;
                borrow = 0;
            } else {
                S = (D1 - D2 + 10 - borrow) + S;
                borrow = 1;
            }
        }
    
        //S = RemoveStartZero(S);
    
        if (isNegative && !S.equals("0")) {
            S = '-' + S;
        }
    
        return S;
    }
        
    public String AddF(String Str1, String Str2) {

        // Handle case where Str1 is negative and Str2 is positive: convert to subtraction
        if (Str1.charAt(0) == '-' && Str2.charAt(0) != '-') {
            return SubF(Str2, Str1.substring(1));
        }
    
        // Handle case where Str2 is negative and Str1 is positive: convert to subtraction
        if (Str2.charAt(0) == '-' && Str1.charAt(0) != '-') {
            return SubF(Str1, Str2.substring(1));
        }
    
        // Handle case where both numbers are negative
        if (Str1.charAt(0) == '-' && Str2.charAt(0) == '-') {
            return (AddF(Str1.substring(1), Str2.substring(1))).equals("0.0")
                ? AddF(Str1.substring(1), Str2.substring(1))
                : ("-" + AddF(Str1.substring(1), Str2.substring(1)));
        }
    
        int no_of_decimal_points_Str1 = 0;
        int no_of_decimal_points_Str2 = 0;
        int N1 = Str1.length();
        int N2 = Str2.length();
    
        // Count how many decimal points are in Str1
        while (N1 != 0) {
            if (Str1.charAt(N1 - 1) == '.') {
                no_of_decimal_points_Str1++;
            }
            N1--;
        }
    
        // Count how many decimal points are in Str2
        while (N2 != 0) {
            if (Str2.charAt(N2 - 1) == '.') {
                no_of_decimal_points_Str2++;
            }
            N2--;
        }
    
        // Append ".0" if no decimal point is present in Str1
        if (no_of_decimal_points_Str1 == 0) {
            Str1 = Str1 + ".0";
        }
    
        // Append ".0" if no decimal point is present in Str2
        if (no_of_decimal_points_Str2 == 0) {
            Str2 = Str2 + ".0";
        }
    
        // Equalize number of digits after decimal in Str1 and Str2
        int n1 = Str1.indexOf('.');
        int n2 = Str2.indexOf('.');
    
        if ((Str1.length() - 1 - n1) > (Str2.length() - 1 - n2)) {
            int N = (Str1.length() - 1 - n1) - (Str2.length() - 1 - n2);
            while (N != 0) {
                Str2 = Str2 + "0";
                N--;
            }
        } else {
            int N = (Str2.length() - 1 - n2) - (Str1.length() - 1 - n1);
            while (N != 0) {
                Str1 = Str1 + "0";
                N--;
            }
        }
    
        // Remove decimal points before addition
        n1 = Str1.indexOf('.');
        n2 = Str2.indexOf('.');
        Str1 = Str1.substring(0, n1) + Str1.substring(n1 + 1);
        Str2 = Str2.substring(0, n2) + Str2.substring(n2 + 1);
    
        // Perform addition on the integer-like strings
        String pro = Add(Str1, Str2);
    
        int l1 = pro.length();
        int l2;
    
        // Determine max number of decimal digits to restore the decimal point
        if ((Str1.length() - n1) < (Str2.length() - n2)) {
            l2 = (Str2.length() - n2);
        } else {
            l2 = (Str1.length() - n1);
        }
    
        // Re-insert decimal point at the correct position
        pro = RemoveEndZero(pro.substring(0, l1 - l2) + "." + pro.substring(l1 - l2));
    
        // Remove unnecessary leading zeros
        pro = RemoveStartZero(pro);
    
        // Ensure a digit follows the decimal point if it's the last character
        if (pro.charAt(pro.length() - 1) == '.') {
            pro = pro + "0";
        }
    
        // Ensure result has a leading zero if it starts with a decimal point
        if (pro.charAt(0) == '.') {
            return "0" + pro;
        }
    
        // Round result to 30 decimal places and return
        return RoundOffTo30(pro);
    }
    

    public String SubF(String Str1, String Str2) {

        // If both strings are equal, the result is zero
        if (Str1.equals(Str2)) return "0";
    
        // Case 1: Str1 negative, Str2 positive → -(abs(Str1) + Str2)
        if (Str1.charAt(0) == '-' && Str2.charAt(0) != '-') {
            return "-" + AddF(Str1.substring(1), Str2);
        }
    
        // Case 2: Str2 negative, Str1 positive → Str1 + abs(Str2)
        if (Str2.charAt(0) == '-' && Str1.charAt(0) != '-') {
            return AddF(Str1, Str2.substring(1));
        }
    
        // Case 3: both Str1 and Str2 negative → -(Str2 - Str1)
        if (Str1.charAt(0) == '-' && Str2.charAt(0) == '-') {
            return SubF(Str2.substring(1), Str1.substring(1));
        }
    
        // Check if Str1 and Str2 contain decimal points
        int j1 = 0, j2 = 0;
        int N1 = Str1.length(), N2 = Str2.length();
    
        // Count decimal points in Str1
        while (N1 != 0) {
            if (Str1.charAt(N1 - 1) == '.') { j1++; }
            N1--;
        }
    
        // Count decimal points in Str2
        while (N2 != 0) {
            if (Str2.charAt(N2 - 1) == '.') { j2++; }
            N2--;
        }
    
        // If no decimal point in Str1, add ".0" to make it float
        if (j1 == 0) { Str1 = Str1 + ".0"; }
    
        // If no decimal point in Str2, add ".0" to make it float
        if (j2 == 0) { Str2 = Str2 + ".0"; }
    
        // Get position of decimal points
        int n1 = Str1.indexOf('.');
        int n2 = Str2.indexOf('.');
    
        // Making no of digits after a decimal point same by adding zeros
        if ((Str1.length() - 1 - n1) > (Str2.length() - 1 - n2)) {
            int N = (Str1.length() - 1 - n1) - (Str2.length() - 1 - n2);
            while (N != 0) {
                Str2 = Str2 + "0";
                N--;
            }
        } else {
            int N = (Str2.length() - 1 - n2) - (Str1.length() - 1 - n1);
            while (N != 0) {
                Str1 = Str1 + "0";
                N--;
            }
        }
    
        // Remove decimal points to treat numbers as large integers
        n1 = Str1.indexOf('.');
        n2 = Str2.indexOf('.');
        Str1 = Str1.substring(0, n1) + Str1.substring(n1 + 1);
        Str2 = Str2.substring(0, n2) + Str2.substring(n2 + 1);
    
        // Subtract the two numbers as large integers
        String pro = Sub(Str1, Str2);
    
        int l1 = pro.length();  // Length of result string
    
        // Calculate number of digits after decimal
        int l2;
        if ((Str1.length() - n1) < (Str2.length() - n2)) {
            l2 = (Str2.length() - n2);
        } else {
            l2 = (Str1.length() - n1);
        }
    
        int K = (l1 - l2);  // Position to insert the decimal point
    
        // Insert decimal point and remove trailing zeros
        String out_put = RemoveEndZero(pro.substring(0, K) + "." + pro.substring(K));
    
        // If result ends with '.', add '0' to make it a valid float
        if (out_put.charAt(out_put.length() - 1) == '.') {
            out_put = out_put + "0";
        }
    
        // Handle negative result
        if (out_put.charAt(0) == '-') {
            out_put = out_put.substring(1);  // Remove negative sign temporarily
            out_put = RemoveStartZero(out_put);  // Remove leading zeros
    
            //(e.g., "-.5" becomes "-0.5")
            if (out_put.charAt(0) == '.') {
                return "-" + "0" + out_put;
            } else {
                return RoundOffTo30("-" + out_put);
            }
    
        } else {
            // Result is positive
            out_put = RemoveStartZero(out_put);  // Remove leading zeros
    
            //(e.g., ".5" becomes "0.5")
            if (out_put.charAt(0) == '.') {
                return "0" + out_put;
            } else {
                return RoundOffTo30(out_put);
            }
        }
    }
    
    
        
        
        public String DivisionF(String Str1, String Str2) {

            // Handle case where Str1 is negative and Str2 is positive
            if (Str1.charAt(0) == '-' && Str2.charAt(0) != '-') {
                return (DivisionF(Str1.substring(1), Str2).equals("0.0")) ?
                    DivisionF(Str1.substring(1), Str2) :
                    ("-" + DivisionF(Str1.substring(1), Str2));
            }
        
            // Handle case where Str2 is negative and Str1 is positive
            if (Str2.charAt(0) == '-' && Str1.charAt(0) != '-') {
                return (DivisionF(Str1, Str2.substring(1)).equals("0.0")) ?
                    DivisionF(Str1, Str2.substring(1)) :
                    ("-" + DivisionF(Str1, Str2.substring(1)));
            }
        
            // Handle case where both Str1 and Str2 are negative
            if (Str1.charAt(0) == '-' && Str2.charAt(0) == '-') {
                return DivisionF(Str1.substring(1), Str2.substring(1));
            }
        
            // Count how many decimal points are in Str1 and Str2
            int no_of_decimal_point_Str1 = 0;
            int no_of_decimal_point_Str2 = 0;
            int N1 = Str1.length(); //str1 length
            int N2 = Str2.length(); //str2 length
        
            while (N1 != 0) {
                if (Str1.charAt(N1 - 1) == '.') { no_of_decimal_point_Str1++; }
                N1--;
            }
        
            while (N2 != 0) {
                if (Str2.charAt(N2 - 1) == '.') { no_of_decimal_point_Str2++; }
                N2--;
            }
        
            // If Str1 has no decimal point, append ".0" to make it a float
            if (no_of_decimal_point_Str1 == 0) { Str1 = Str1 + ".0"; }
        
            // If Str2 has no decimal point, append ".0" to make it a float
            if (no_of_decimal_point_Str2 == 0) { Str2 = Str2 + ".0"; }
        
            // Get index of decimal points in Str1 and Str2
            int n1 = Str1.indexOf('.');
            int n2 = Str2.indexOf('.');
        
            // Equalize the number of digits after the decimal point by adding zeros
            if ((Str1.length() - 1 - n1) > (Str2.length() - 1 - n2)) {
                int N = (Str1.length() - 1 - n1) - (Str2.length() - 1 - n2);
                while (N != 0) {
                    Str2 = Str2 + "0";
                    N--;
                }
            } else {
                int N = (Str2.length() - 1 - n2) - (Str1.length() - 1 - n1);
                while (N != 0) {
                    Str1 = Str1 + "0";
                    N--;
                }
            }
        
            // Remove decimal points from both numbers for integer division
            n1 = Str1.indexOf('.');
            n2 = Str2.indexOf('.');
            Str1 = Str1.substring(0, n1) + Str1.substring(n1 + 1);
            Str2 = Str2.substring(0, n2) + Str2.substring(n2 + 1);
        
            // Append 1000 zeros to Str1 to allow enough precision after decimal
            int L = 1000;
            while (L != 0) {
                Str1 = Str1 + "0";
                L--;
            }
        
            // Perform division using the helper Division method
            String output_string = Division(Str1, Str2);
        
            // If result is 0, return "0.0"
            if (output_string.equals("0")) return "0.0";
        
            // Insert decimal point 1000 places from the right
            output_string = output_string.substring(0, output_string.length() - 1000) + "." + output_string.substring(output_string.length() - 1000);
        
            // Remove unnecessary leading and trailing zeros
            output_string = RemoveStartZero(RemoveEndZero(output_string));
        
            // If result starts with '.', prefix with '0'
            if (output_string.charAt(0) == '.') { output_string = '0' + output_string; }
        
            // If result ends with '.', append '0'
            if (output_string.charAt(output_string.length() - 1) == '.') { output_string = output_string.substring(0, output_string.length() - 1) + ".0"; }
        
            // Round the result to 30 digits after the decimal point
            return RoundOffTo30(output_string);
        }
        
    
    /**
     * @param Str
     * @return 
     */
    public String RoundOffTo30(String Str) {

        // Find the position of the decimal point in the string
        int index_of_decimal_point = Str.indexOf('.');
    
        // Check if there are more than 30 digits after the decimal point
        if ((Str.length() - 1 - index_of_decimal_point) > 30) {
            // If so, round the string to keep only 30 digits after the decimal
            Str = Str.substring(0, index_of_decimal_point + 31);
        }
    
        // Return the rounded result
        return Str;
    }
    
    
}






        


    





    


