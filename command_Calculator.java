import java.util.*;

public class command_Calculator {
	static int Prec(String ch) {
		if(ch.equals("+") || ch.equals("-"))
			return 1;
		else if(ch.equals("*") || ch.equals("/") || ch.equals("%"))
			return 2;
		else return -1;
    }
	
    static double calculate(String ch, double num1, double num2) {
    	if(ch.equals("+"))
    		return add(num1, num2);
    	else if(ch.equals("-"))
    		return subtract(num1, num2);
    	else if(ch.equals("*"))
    		return multiply(num1, num2);
    	else if(ch.equals("/"))
    		return divide(num1, num2);
    	else if(ch.equals("%"))
    		return modulo(num1, num2);
    	else throw new IllegalOperationException();

    }
    
	  public static double add(double num1, double num2) {
		  return num1 + num2;
	}

	  public static double subtract(double num1, double num2) {
		  return num1 - num2;
	  }

	  public static double multiply(double num1, double num2) {
		  return num1 * num2;
	  }

	  public static double divide(double num1, double num2) {
		  if(num2 == 0) throw new ArithmeticException();
		  return num1 / num2;
	  }
	  
	  public static double modulo(double num1, double num2) {
		  return num1 % num2;
	  }	 
	  
	  public static boolean isDigit(String digit){
		  for(int i = 0; i < digit.length(); i++){
			  if(!Character.isDigit(digit.charAt(i)) && digit.charAt(i) != '.')
				  return false;
		  }
		  return true;
	  }
	  public static boolean isOperator(String operator){
		  if(!(operator.equals("+")) && !(operator.equals("-")) && !(operator.equals("*")) 
				  && !(operator.equals("/")) && !(operator.equals("%")) && !(operator.equals("(")) && !(operator.equals(")")))
				  return false;
		  return true;
	  }
static double expression(String[] exp)
{
        Stack<String> opera = new Stack<>();
        ArrayList<String> postfix = new ArrayList<>();
        
        for (int i = 0; i<exp.length; ++i)
        {
            if (isDigit(exp[i]))
            	postfix.add(exp[i]);

            else if (isOperator(exp[i])){
            	if(exp[i].equals("("))
            		opera.push(exp[i]);
            	else if(exp[i].equals(")")){
                    while (!opera.isEmpty() && !opera.peek().equals("("))
                    	postfix.add(opera.pop());
                     
                    if (!opera.isEmpty() && !opera.peek() .equals("("))
                        throw new IllegalOperationException();                
                    else
                        opera.pop();
            	}
                else
                {
                    while (!opera.isEmpty() && Prec(exp[i]) <= Prec(opera.peek()))
                    	postfix.add(opera.pop());
                    opera.push(exp[i]);
                }
            }
            else throw new IllegalOperationException();
        }
      
        while (!opera.isEmpty())
        	postfix.add(opera.pop());

        return evaluate(postfix);
}

public static double evaluate(ArrayList<String> postfix){
	double num1 = 0, num2 = 0;
	Stack<String> nums = new Stack<>();
	for(String s: postfix){
		if(isDigit(s))
			nums.push(s);
		else {
			num2 = Double.parseDouble(nums.pop());
			num1 = Double.parseDouble(nums.pop());
			nums.push(Double.toString(calculate(s, num1, num2)));
		}
	}
	return Double.parseDouble(nums.pop());
}

public static void main(String[] args) {
try{
	for(String s : args){
		System.out.println(s);
	}
    System.out.println(expression(args));	
}
catch(LookAtMrAlgebraOverHereException e){
	System.out.println("Wrong expression!");
	System.exit(1);
}
catch(IllegalOperationException e){
	System.out.println("Wrong operator!");
	System.exit(1);
}
catch(UserIsADumbassException e){
	System.out.println("Error!");
	System.exit(1);
}
catch(ArithmeticException e){
	System.out.println("CANNOT DIVIDED BY 0");
	System.exit(1);
}
}

}
class LookAtMrAlgebraOverHereException extends IllegalArgumentException{

}

class IllegalOperationException extends IllegalArgumentException{

}

class UserIsADumbassException extends IllegalArgumentException{

}


