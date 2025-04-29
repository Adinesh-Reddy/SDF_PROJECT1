import arbitaryarithmetic.AFloat;
import arbitaryarithmetic.AInteger;
public class MyInfArith {
    public static void main(String[] args) {
        
        String type= args[0];
        String operation=args[1];
        String num1=args[2];
        String num2=args[3];

        switch (type) {
            case "int" ->                 {
                    AInteger Num1= new AInteger(num1);
                    AInteger Num2= new AInteger(num2);
            switch (operation) {
                case "add" -> System.out.println((Num1.add(Num2)).value);
                case "sub" -> System.out.println((Num1.sub(Num2)).value);
                case "div" -> System.out.println((Num1.div(Num2)).value);
                case "mul" -> System.out.println((Num1.mul(Num2)).value);
                default -> System.out.println("Invalid operation");
            }
                }
            case "float" ->                 {
                    AFloat Num1= new AFloat(num1);
                    AFloat Num2= new AFloat(num2);
            switch (operation) {
                case "add" -> System.out.println((Num1.add(Num2)).value);
                case "sub" -> System.out.println((Num1.sub(Num2)).value);
                case "div" -> System.out.println((Num1.div(Num2)).value);
                case "mul" -> System.out.println((Num1.mul(Num2)).value);
                default -> System.out.println("Invalid operation");
            }
                }
            default -> System.out.println("Invalid type");
        }

        
        
    }
}
    

