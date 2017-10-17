public class N_Queens {
	  public static void main(String[] args) {
		  new Queen(2);
		  new Queen(4);
		  new Queen(6);
		  }
	}

class Queen{
	 private int queens;
	 private int[] board;
	 private int solution;
	 	Queen(int qNum){
	 		queens = qNum;
	 		board = new int[qNum];
	 		solution = 0;
	 		findSolution();
	 	}

	  boolean check(int q) {
		    int Other = board[q];
		    for (int i = 1; i <= q; i++) {
		      int test = board[q - i];
		      if (test == Other || test == Other - i || test == Other + i) 
		        return true;
		    }
		    return false;
		  }
	  
	  public void findSolution(){
	    int q = 0;
	    board[0] = -1;
	    while (q >= 0) {
	      do {
	        board[q]++;
	      } while ((board[q] < queens) && check(q));
	      if (board[q] < queens) {
	        if (q < queens - 1) {
	          board[++q] = -1;
	        } 
	        else {
	          solution++;
	  	    System.out.println("Solution " + (solution));
		    for (int i = 0; i < queens; i++) {
		      for (int j = 0; j < queens; j++) {
		    	  if(board[i] == j){
		    	  System.out.print("Q ");
		    	  }
		    	  else{
		    	  System.out.print("_ ");
		      }
		      }
		      System.out.println();
		    }
		  }
	     } 
	      else {
	        q--;
	      }
	  }
	    System.out.println(queens + " Queens has " + solution + " solutions.");
}
}
