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
            if (isDigit(exp[i])){
            	if(i > 0 && isDigit(exp[i-1])){
            		throw new LookAtMrAlgebraOverHereException();
            	}
            	postfix.add(exp[i]);
            }
            else if (isOperator(exp[i])){
            	if(exp[i].equals("("))
            		opera.push(exp[i]);
            	else if(exp[i].equals(")")){
                    while (!opera.isEmpty() && !opera.peek().equals("("))
                    	postfix.add(opera.pop());
                     
                    if (!opera.isEmpty() && !opera.peek() .equals("("))
                        throw new LookAtMrAlgebraOverHereException();                
                    else
                        opera.pop();
            	}
                else
                {
                	if(i > 0 && isOperator(exp[i-1]) && !exp[i-1].equals(")"))
                		throw new LookAtMrAlgebraOverHereException();
                    while (!opera.isEmpty() && Prec(exp[i]) <= Prec(opera.peek()))
                    	postfix.add(opera.pop());
                    opera.push(exp[i]);
                }
            }
            else throw new IllegalOperationException();
        }
      
        while (!opera.isEmpty())
        	postfix.add(opera.pop());
        
        if(postfix.isEmpty())
        	throw new UserIsADumbassException();

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
    System.out.println(expression(args));	
}
catch(LookAtMrAlgebraOverHereException e){
	System.out.println(e);
	System.exit(1);
}
catch(IllegalOperationException e){
	System.out.println(e);
	System.exit(1);
}
catch(UserIsADumbassException e){
	System.out.println(e);
	System.exit(1);
}
catch(ArithmeticException e){
	System.out.println("CANNOT DIVIDED BY ZERO");
	System.exit(1);
}
}

}
class LookAtMrAlgebraOverHereException extends IllegalArgumentException{
	public String toString(){
		return new String("Error! Wrong expression! Example ( 3 + 1 ) * 8");
	}
}

class IllegalOperationException extends IllegalArgumentException{
	public String toString(){
		return new String("Error! Unsupprted operator!");
	}
}

class UserIsADumbassException extends IllegalArgumentException{
	public String toString(){
		return new String("Error! Empty/illegal expression.");
	}
}
