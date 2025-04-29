package arbitaryarithmetic;
public class AFloat{
    public String value;
    public AFloat(){
        this.value="0.0";
    }
    public AFloat(String s){
        this.value=s;
    }
    public AFloat add(AFloat s){
        return new AFloat(AddF(this.value,s.value));
    }
    public AFloat mul(AFloat s){
        return new AFloat(MultiplyF(this.value,s.value));
    }
    public AFloat div(AFloat s){
        return new AFloat(DivisionF(this.value,s.value));
    }
    public AFloat sub(AFloat s){
        return new AFloat(SubF(this.value,s.value));
    }

    public String Add(String s1,String s2){
        
    //adding zeros before numbers to make the lengths equal
        while (s1.length()<s2.length()){
            s1="0"+s1;

        }
        while (s2.length()<s1.length()){
            s2="0"+s2;

        }
        String s="";
        int B=0;
        int n=s1.length();
        for(int i=0;i<s1.length();i++){
            B=((s1.charAt(n-1-i)-'0')+(s2.charAt(n-1-i)-'0'))+B;
            s=String.valueOf(B%10)+s;
            B=B/10;



        }
        if(B!=0){
            s=String.valueOf(B)+s;
        }
        return s; //here we are not removing zeros at start in order to put decimal point in float addition
    }
    
    
    public String RemoveStartZero(String s){
        int i=0;
        while(i<s.length() && s.charAt(i)=='0' ){
            i++;
        }
        if(i==s.length()) return "0"; //if all are zero will return 0

        return s.substring(i);
    }
     // comparing numerically
    public int compare(String s1, String s2) {
        
        s1 = RemoveStartZero(s1); 
        s2 = RemoveStartZero(s2);
    
        
        if (s1.length() > s2.length()) return 1;
        if (s1.length() < s2.length()) return -1;
    
        
        for (int i = 0; i < s1.length(); i++) {
            if ((s1.charAt(i)-'0') > (s2.charAt(i)-'0')) return 1;
            if ((s1.charAt(i)-'0') < (s2.charAt(i)-'0')) return -1;
        }
    
        
        return 0;
    }
    
    public String Multiply(String s1,String s2){
        
        s1=RemoveStartZero(s1);
        s2=RemoveStartZero(s2);

        
        int m=s2.length();
        String result1="0";
        for(int l=0;l<m;l++){
            String result="0";

            int TEMP=s2.charAt(m-l-1)-'0';
            
            while(TEMP!=0){
                result=Add(result,s1);
                TEMP--;
            }

            int L=l;
            while(L!=0){
                result=result+"0";
                L--;
                
            }

            result1=Add(result1,result);

        }

        return RemoveStartZero(result1);
    }





    

    public String MultiplyF(String s1,String s2){

        if(s1.charAt(0)=='-' && s2.charAt(0)!='-'){return (MultiplyF(s1.substring(1),s2).equals("0.0"))? MultiplyF(s1.substring(1),s2):("-"+MultiplyF(s1.substring(1),s2));  }
        if(s2.charAt(0)=='-' && s1.charAt(0)!='-'){return (MultiplyF(s2.substring(1),s1).equals("0.0"))? MultiplyF(s2.substring(1),s1):("-"+MultiplyF(s2.substring(1),s1));}
        if(s1.charAt(0)=='-' && s2.charAt(0)=='-'){return MultiplyF(s1.substring(1),s2.substring(1));}

        int j1=0;
        int j2=0;
        int N1=s1.length();
        int N2=s2.length();
        while(N1!=0){
            if(s1.charAt(N1-1)=='.'){ j1++;}
            N1--;
        }
        while(N2!=0){
            if(s2.charAt(N2-1)=='.'){ j2++;}
            N2--;
        }
        if(j1==0){s1=s1+".0";}
        if(j2==0){s2=s2+".0";}
        int n1=s1.indexOf('.');
        int n2=s2.indexOf('.');
        s1=s1.substring(0,n1)+s1.substring(n1+1);
        s2=s2.substring(0,n2)+s2.substring(n2+1);
        //s1=RemoveZero(s1);
        //s2=RemoveZero(s2);
        //System.out.println(s1);
        //System.out.println(s2);

        String pro= Multiply(s1, s2);
        //System.out.println(pro);

    
        int l1=pro.length();
        int l2=(s1.length()-n1+s2.length()-n2);
       // System.err.println(l2);
        //int decimal_point=(l2-11+1);
        while(pro.length()<l2){
            pro="0"+pro;
           // decimal_point++;
        }
        l1=pro.length();
        //System.out.println(pro);
        

        pro=RemoveStartZero(RemoveEndZero((pro.substring(0,l1-l2)+"."+pro.substring(l1-l2))));
        //System.out.println(pro);
        if(pro.charAt(0)=='.'){ pro='0'+pro;}
        

        if(pro.charAt(pro.length()-1)=='.'){ pro=pro.substring(0,(pro.length()-1))+".0";}


        return RoundOffTo30(pro);

        

        

    }
    
    public String Division(String s1,String s2){

       

        
 
 
        
        //s1=RemoveStartZero(s1);
        //s2=RemoveStartZero(s2);
 
          
     
        
        if(RemoveStartZero(s2).equals("0")) throw new ArithmeticException("Division by Zero Error");
        if(RemoveStartZero(s1).equals("0")) return "0";
        String Q="";
        String R="";
        for(int i=0;i<s1.length();i++){
         Q=Q+s1.charAt(i);
         Q=RemoveStartZero(Q);
 
         int dum=0;
         while(compare(Q,s2)>=0){
             Q=Sub(Q,s2);
             dum++;
         }
         //System.out.println(dum);
         R=R+String.valueOf(dum);
 
        }
 
      return R;
     }
 
    public String RemoveEndZero(String s){
        int i=s.length()-1;
        while(i>0 && s.charAt(i)=='0'){
            i--;
        }

        return s.substring(0,(i+1));

    }
    
    public String Sub(String s1, String s2) {
        
    
        while (s1.length() < s2.length()) {
            s1 = '0' + s1;
        }
        while (s2.length() < s1.length()) {
            s2 = '0' + s2;
        }
    
        String a = s1;
        String b = s2;
        boolean isNegative = false;
    
        if (compare(s1,s2)==-1) {
            a = s2;
            b = s1;
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
        
    public String AddF(String s1,String s2){
        if(s1.charAt(0)=='-' && s2.charAt(0)!='-'){return SubF(s2,s1.substring(1));}
        if(s2.charAt(0)=='-' && s1.charAt(0)!='-'){return SubF(s1,s2.substring(1));}
        if(s1.charAt(0)=='-' && s2.charAt(0)=='-'){return (AddF(s1.substring(1),s2.substring(1))).equals("0.0")? AddF(s1.substring(1),s2.substring(1)):("-"+AddF(s1.substring(1),s2.substring(1))) ;}
        
        int j1=0;
        int j2=0;
        int N1=s1.length();
        int N2=s2.length();
        while(N1!=0){
            if(s1.charAt(N1-1)=='.'){ j1++;}
            N1--;
        }
        while(N2!=0){
            if(s2.charAt(N2-1)=='.'){ j2++;}
            N2--;
        }
        if(j1==0){s1=s1+".0";}
        if(j2==0){s2=s2+".0";}


        //making number of digits after decimal equal
        int n1=s1.indexOf('.');
        int n2=s2.indexOf('.');
        if((s1.length()-1-n1) > (s2.length()-1-n2) ){
            int N = (s1.length()-1-n1) - (s2.length()-1-n2);
            while(N!=0){
                s2=s2+"0";
                N--;
            }
        }

        else{
            int N = (s2.length()-1-n2) - (s1.length()-1-n1);
            while(N!=0){
                s1=s1+"0";
                N--;

            }
        }

        n1=s1.indexOf('.');
        n2=s2.indexOf('.');
        s1=s1.substring(0,n1)+s1.substring(n1+1);
        s2=s2.substring(0,n2)+s2.substring(n2+1);
        ///s1=RemoveZero(s1);
        //s2=RemoveZero(s2);
        

        String pro= Add(s1, s2);

    
        int l1=pro.length();
        int l2;
        if((s1.length()-n1)<(s2.length()-n2)){ l2=(s2.length()-n2);}
        else{ l2=(s1.length()-n1);}
        pro=RemoveEndZero(pro.substring(0,l1-l2)+"."+pro.substring(l1-l2));
        pro=RemoveStartZero(pro);
        if(pro.charAt(pro.length()-1)=='.') {pro= pro+"0";}
        
        if(pro.charAt(0)=='.') return "0"+pro;

        return RoundOffTo30(pro);
    }

    public String SubF(String s1,String s2){

        if(s1.equals(s2)) return "0";
        if(s1.charAt(0)=='-' && s2.charAt(0)!='-'){return "-"+AddF(s1.substring(1),s2);  }
        if(s2.charAt(0)=='-' && s1.charAt(0)!='-'){return AddF(s1,s2.substring(1));}
        if(s1.charAt(0)=='-' && s2.charAt(0)=='-'){return SubF(s2.substring(1),s1.substring(1));}
        int j1=0;
        int j2=0;
        int N1=s1.length();
        int N2=s2.length();
        while(N1!=0){
            if(s1.charAt(N1-1)=='.'){ j1++;}
            N1--;
        }
        while(N2!=0){
            if(s2.charAt(N2-1)=='.'){ j2++;}
            N2--;
        }
        if(j1==0){s1=s1+".0";}
        //System.out.println(s1);
    
        if(j2==0){s2=s2+".0";}
        //System.out.println(s2);

        
        int n1=s1.indexOf('.');
        int n2=s2.indexOf('.');
        if((s1.length()-1-n1) > (s2.length()-1-n2) ){
            int N = (s1.length()-1-n1) - (s2.length()-1-n2);
            while(N!=0){
                s2=s2+"0";
                N--;
            }
        }

        else{
            int N = (s2.length()-1-n2) - (s1.length()-1-n1);
            while(N!=0){
                s1=s1+"0";
                N--;

            }
        }
        //System.out.println(s1+s2);
        n1=s1.indexOf('.');
        n2=s2.indexOf('.');
        s1=s1.substring(0,n1)+s1.substring(n1+1);
        s2=s2.substring(0,n2)+s2.substring(n2+1);
        //System.out.println(s1+s2);

        //s1=RemoveZero(s1);
        //s2=RemoveZero(s2);
        

        String pro= Sub(s1,s2);

    
        int l1=pro.length();

        int l2;
        if((s1.length()-n1)<(s2.length()-n2)){ l2=(s2.length()-n2);}
        else{ l2=(s1.length()-n1);}
        int K=(l1-l2);
        
        String out_put = RemoveEndZero(pro.substring(0,K)+"."+pro.substring(K));
        //out_put=RemoveZero(out_put);
        if(out_put.charAt(out_put.length()-1)=='.') {out_put= out_put+"0";}

        if(out_put.charAt(0)=='-'){
            out_put=out_put.substring(1);
            out_put=RemoveStartZero(out_put);
            if(out_put.charAt(0)=='.') { return "-"+"0"+out_put;}
            else{ return RoundOffTo30("-"+out_put);}
        }
        else{
            out_put=RemoveStartZero(out_put);
            if(out_put.charAt(0)=='.') { return "0"+out_put;}
            else{ return RoundOffTo30(out_put);}


        }

        }
    
        
        //if(out_put.charAt(out_put.length()-1)=='.') {return out_put.substring(0,out_put.length()-1);}
    public String DivisionF(String s1,String s2){
         

        


        if(s1.charAt(0)=='-' && s2.charAt(0)!='-'){return (DivisionF(s1.substring(1),s2).equals("0.0"))? DivisionF(s1.substring(1),s2):("-"+DivisionF(s1.substring(1),s2));  }
        if(s2.charAt(0)=='-' && s1.charAt(0)!='-'){return (DivisionF(s1,s2.substring(1)).equals("0.0"))? DivisionF(s1,s2.substring(1)):("-"+DivisionF(s1,s2.substring(1)));}
        if(s1.charAt(0)=='-' && s2.charAt(0)=='-'){return DivisionF(s1.substring(1),s2.substring(1));}
 
        //adding .0 at end if integers are passed as aruguments

        int j1=0;
        int j2=0;
        int N1=s1.length();
        int N2=s2.length();
        while(N1!=0){
            if(s1.charAt(N1-1)=='.'){ j1++;}
            N1--;
        }
        while(N2!=0){
            if(s2.charAt(N2-1)=='.'){ j2++;}
            N2--;
        }

        if(j1==0){s1=s1+".0";} //appending ".0" if s1 is an integer
        if(j2==0){s2=s2+".0";} //appending ".0" if s2 is an integer
        //System.out.println(s1+s2);



        
        int n1=s1.indexOf('.');
        int n2=s2.indexOf('.');
        
        // making same no of digits after decimal point

        if((s1.length()-1-n1) > (s2.length()-1-n2) ){
            int N = (s1.length()-1-n1) - (s2.length()-1-n2);
            while(N!=0){
                s2=s2+"0";
                N--;
            }
        }

        else{
            int N = (s2.length()-1-n2) - (s1.length()-1-n1);
            while(N!=0){
                s1=s1+"0";
                N--;

            }
        }
        
        

        

        n1=s1.indexOf('.');
        n2=s2.indexOf('.');
        s1=s1.substring(0,n1)+s1.substring(n1+1);
        s2=s2.substring(0,n2)+s2.substring(n2+1);
        

        

        
       
        int L=1000;
        while(L!=0){
            s1=s1+"0";
            L--;
        }
        //s1=RemoveStartZero(s1);
        //s2=RemoveStartZero(s2);

        String res=Division(s1,s2);
       
        if(res.equals("0")) return "0.0";
        //if (res.equals("Division by Zero Error")) return res;
        res=res.substring(0,(res.length()-1000))+"."+res.substring((res.length()-1000));

        res=RemoveStartZero(RemoveEndZero(res));
        
        if(res.charAt(0)=='.'){ res='0'+res;}
        

        if(res.charAt(res.length()-1)=='.'){ res=res.substring(0,(res.length()-1))+".0";}


        return RoundOffTo30(res);

    }

    public String RoundOffTo30(String s){
        int index_of_decimal_point= s.indexOf('.');
        if((s.length()-1-index_of_decimal_point)>30){
            s=s.substring(0,index_of_decimal_point+31);
        }
        return s;

    }
    
}






        


    





    


