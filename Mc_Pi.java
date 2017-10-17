
public class Mc_Pi {

	public static void main(String[] args) {
		long pointNum = 0L;
		long dots = 4000000000L;
		double x, y;
		double pi;
		for(long i = 0L ; i < dots; i++ ){
			x = Math.random();
			y = Math.random();
			if(Math.pow(x, 2.0) + Math.pow(y, 2.0) <= 1) pointNum++;
		}
		pi = (double)pointNum/dots*4;
		
		System.out.println("Dots inside the circle: " + pointNum
				+ "\nOutside: " + (dots - pointNum));
		System.out.println("Pi is " + pi);
	}
}
