import java.io.*;

	public class TreeDiagram {
	  public static void main(String[] a)throws IOException{
		  PrintWriter pwriter = new PrintWriter("h://dir_tree.txt");
		  File []paths;
	     try { 
	  /* This section search the entire file system.
	   *   	 paths = File.listRoots();
	    	 
	    	 for(File path : paths){
	    		 pwriter.println();
	    		 System.out.println(path);
	    		 pwriter.println(path);
	    		 File dirs[] = path.listFiles();
	        	 if(dirs != null && paths.length> 0)
	 	            for(File target : dirs){
	 	            		Tree(0, target, pwriter);
	 	         
	 	         */
	    	 File root = new File("F://NCSOFT");
	    	 pwriter.println(root.getParent()); // print drive name. 
	    	 Tree(0, root, pwriter);
			  pwriter.flush(); 
	      } catch(Exception e) {
	         e.printStackTrace();
	      } 
	  }
	  public static void Tree(int indentation, File file, PrintWriter pwriter) throws IOException {
		  if(indentation!=0){
		      pwriter.println();
		  }
	    for (int i = 0; i < indentation; i++){
	    	if(i == 0)
		    	pwriter.print("+");
	    	else if(i == indentation - 4)
			      pwriter.print("+");
	    	else if(i < indentation - 4)
	    	  pwriter.print(" ");
	    	else pwriter.print("-");
	    }
    	  pwriter.print(file.getName());
	    if (file.isDirectory()) {
	      File[] filelist = file.listFiles();
	      if(filelist!=null&&filelist.length>0) 
	      for (File nextFile : filelist)
	   //  print directory only if uncomment
	   // 	 if(nextFile.isDirectory())  
	        Tree(indentation + 4, nextFile, pwriter );
	    }
	  }
	}


