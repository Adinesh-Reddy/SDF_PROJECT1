package arbitaryarithmetic;

/* AInteger class to implement arbitrary-precision integer arithmetic
 * Using string representation for storing large numbers without overflow
*/

public class AInteger{

    public String value;  // To store the integer in string form

    //Defualt constructor initializes to zero
    public AInteger(){
        this.value="0";}
    
    // Constructor accepting a string value
    public AInteger(String s){
       this.value=s;   
    }

    // Copy constructor to copy value form another insatnce
    public AInteger(AInteger other) {
        this.value = other.value;
    }

    // Static parse to parse a string into AInteger insatance
    public static AInteger parse(String s) {
        return new AInteger(s);
    }

    /*Making methods into single arguemented*/

    /*  Adds two AInteger values one is from this class itself and another from otherclass
      and returns AInteger */
    public AInteger add(AInteger s){
        return new AInteger(Add(this.value,s.value));
    }
    
    // Multiplies two AInteger values
    public AInteger mul(AInteger s){
        return new AInteger(Multiply(this.value,s.value));
    }

    // Divides this AInteger by another
    public AInteger div(AInteger s){
        return new AInteger(Division(this.value,s.value));
    }

    // Subtracts another AInteger from this
    public AInteger sub(AInteger s){
        return new AInteger(Sub(this.value,s.value));
    }

    
    /**
     * Adds two string-represented integers. 
     * @param s1 First integer string
     * @param s2 Second integer string
     * @return Sum as string
     */
    public String Add(String s1,String s2){

    //Handles negative numbers by converting to subtraction.
        if(s1.charAt(0)=='-' && s2.charAt(0)!='-'){return Sub(s2,s1.substring(1));}
        if(s2.charAt(0)=='-' && s1.charAt(0)!='-'){return Sub(s1,s2.substring(1));}
        if(s1.charAt(0)=='-' && s2.charAt(0)=='-'){return (Add(s1.substring(1),s2.substring(1)).equals("0"))? Add(s1.substring(1),s2.substring(1)):("-"+Add(s1.substring(1),s2.substring(1)));}

    //Pads strings to equal length before digit-wise addition from right to left
    // Ex: 999 1 --> 999 001
    
        while (s1.length()<s2.length()){
            s1="0"+s1;}
        while (s2.length()<s1.length()){
            s2="0"+s2;}

    //logic for addition of two strings

        String s=""; //ouput string
        int carry=0; //after adding two digits taking carry
        int n=s1.length(); 

        for(int i=0;i<s1.length();i++){

            carry=((s1.charAt(n-1-i)-'0')+(s2.charAt(n-1-i)-'0'))+carry;
            s=String.valueOf(carry%10)+s;
            carry=carry/10;

        }

    //at the end if carry is not zero we have to add extra digit to the result s
    //Ex 999 001 -->1000 

        if(carry!=0){
            s=String.valueOf(carry)+s;
        }
        return RemoveStartZero(s);
    }


    /**
     * Compares two string-represented numbers.
     * @return 1 if s1 > s2, -1 if s1 < s2, 0 if equal
     */
    public int compare(String s1, String s2) {
        
        s1 = RemoveStartZero(s1);
        s2 = RemoveStartZero(s2);
    
    //after removing zeros comparing
        if (s1.length() > s2.length()) return 1;
        if (s1.length() < s2.length()) return -1;
    
    // for same length checking digit by digit    
        for (int i = 0; i < s1.length(); i++) {
            if ((s1.charAt(i)-'0') > (s2.charAt(i)-'0')) return 1;
            if ((s1.charAt(i)-'0') < (s2.charAt(i)-'0')) return -1;
        }
    
        
        return 0; //which means both are same
    }
    
    
    /**
     * Subtraction of two integers using string-represented integers. 
     * @param s1 First integer string
     * @param s2 Second integer string
     * @return Result as string
     */
    public String Sub(String s1, String s2) {

        //Handles sign 
        if (s1.charAt(0) == '-' && s2.charAt(0) != '-') {
            return "-" + Add(s1.substring(1), s2);
        }
        if (s2.charAt(0) == '-' && s1.charAt(0) != '-') {
            return Add(s1, s2.substring(1));
        }
        if (s1.charAt(0) == '-' && s2.charAt(0) == '-') {
            return Sub(s2.substring(1), s1.substring(1));
        }

        //Pads strings to equal length before digit-wise addition from right to left
        // Ex: 999 1 --> 999 001
          
        while (s1.length() < s2.length()) {
            s1 = '0' + s1;
        }
        while (s2.length() < s1.length()) {
            s2 = '0' + s2;
        }

    
        String a = s1;
        String b = s2;
        boolean isNegative = false; //to use which is greater in s1,s2
    
        if (compare(s1,s2)==-1) {
            a = s2;
            b = s1;
            isNegative = true; //making boolean into true becuase we have to append - sign if s2<s1
        }
    
        String S = ""; //output string

        int borrow = 0; //taking borrow from if diffrence is negative
        
        //subtracting digit by digit 
        for (int j = 0; j < a.length(); j++) {

        
            int D1 = a.charAt(a.length() - j - 1) - '0'; // coming from last digit
            int D2 = b.charAt(a.length() - j - 1) - '0'; 
    
            if ((D1 - borrow) >= D2) {
                S = (D1 - D2 - borrow) + S;
                borrow = 0;
            } else {
                S = (D1 - D2 + 10 - borrow) + S;
                borrow = 1;
            }
        }
    
        S = RemoveStartZero(S); //Removing starting extra zeros
    
        if (isNegative && !S.equals("0")) { // if b>a then - sign gets appended
            S = '-' + S;
        }
    
        return S;
    }

     
    /**
     * Multiplication of two integers using string-represented integers. 
     * @param s1 First integer string
     * @param s2 Second integer string
     * @return Result as string
     */
    public String Multiply(String s1, String s2) {
        // Handle negative signs: if s1 is negative and s2 is not
        if (s1.charAt(0) == '-' && s2.charAt(0) != '-') {
            // Check if result is 0, return without sign; otherwise prepend minus
            return (Multiply(s1.substring(1), s2).equals("0")) ? Multiply(s1.substring(1), s2) : ("-" + Multiply(s1.substring(1), s2));
        }
    
        // Handle negative signs: if s2 is negative and s1 is not
        if (s2.charAt(0) == '-' && s1.charAt(0) != '-') {
            return (Multiply(s1, s2.substring(1)).equals("0")) ? Multiply(s1, s2.substring(1)) : ("-" + Multiply(s1, s2.substring(1)));
        }
    
        // If both s1 and s2 are negative, make both positive
        if (s1.charAt(0) == '-' && s2.charAt(0) == '-') {
            return Multiply(s1.substring(1), s2.substring(1));
        }
    
        // Remove leading zeros from both input strings
        s1 = RemoveStartZero(s1);
        s2 = RemoveStartZero(s2);
    
        // Length of second string (used to loop through each digit)
        int m = s2.length();
    
        // Initialize final result as zero
        String S = "0";
    
        // Outer loop goes through each digit in s2 from right to left
        for (int l = 0; l < m; l++) {
            String result = "0"; // Temporary result for current digit of s2
    
            // Get current digit from s2 (as integer) from right end
            int TEMP = s2.charAt(m - l - 1) - '0';
    
            // Repeat addition of s1, TEMP times to simulate multiplication
            while (TEMP != 0) {
                result = Add(result, s1);
                TEMP--;
            }
    
            // Add required number of zeros at the end (place value shift)
            int L = l;
            while (L != 0) {
                result = result + "0";
                L--;
            }
    
            // Add intermediate result to final result S
            S = Add(S, result);
        }
    
        // Return final result after removing any leading zeros
        return RemoveStartZero(S);
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

    
   /**
     * Division of two integers using string-represented integers. 
     * @param s1 First integer string
     * @param s2 Second integer string
     * @return Result as string
     */
   public String Division(String s1, String s2) {

    // Handle case: s1 is negative and s2 is positive
    if (s1.charAt(0) == '-' && s2.charAt(0) != '-') {
        // If either operand is zero, strip sign and continue
        if (RemoveStartZero(s1.substring(1)).equals("0") || RemoveStartZero(s2.substring(0)).equals("0"))
            return Division(s1.substring(1), s2);
        // Otherwise, prepend minus sign to the result
        return "-" + Division(s1.substring(1), s2);
    }

    // Handle case: s1 is positive and s2 is negative
    if (s2.charAt(0) == '-' && s1.charAt(0) != '-') {
        if (RemoveStartZero(s1.substring(0)).equals("0") || RemoveStartZero(s2.substring(1)).equals("0"))
            return Division(s1, s2.substring(1));
        return "-" + Division(s1, s2.substring(1));
    }

    // Handle case: both s1 and s2 are negative (result is positive)
    if (s1.charAt(0) == '-' && s2.charAt(0) == '-') {
        return Division(s1.substring(1), s2.substring(1));
    }

    // Throw exception if divisor is zero
    if (RemoveStartZero(s2).equals("0")) throw new ArithmeticException("Division by Zero Error");

    // If dividend is zero, result is zero
    if (RemoveStartZero(s1).equals("0")) return "0";

    String Remainder = ""; // Temporary value to hold current remainder
    String Quotient = "";  // Final result of the division

    // Loop through each digit of the dividend
    for (int i = 0; i < s1.length(); i++) {
        // Bring down next digit from dividend into remainder
        Remainder = Remainder + s1.charAt(i);

        // Remove any leading zeros from remainder
        Remainder = RemoveStartZero(Remainder);

        int temp = 0; // Counter to track how many times s2 fits into Remainder

        // While remainder is greater than or equal to divisor
        while (compare(Remainder, s2) >= 0) {
            Remainder = Sub(Remainder, s2); // Subtract divisor from remainder
            temp++;                         // Increment counter
        }

        // Append current quotient digit to final result
        Quotient = Quotient + String.valueOf(temp);
    }

    // Return the final quotient after removing leading zeros
    return RemoveStartZero(Quotient);
}

}












