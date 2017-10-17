import java.io.*;

class Student implements Serializable{
	private static int StudentId;
	private String StudentName;
	
	Student(int StudentId, String StudentName){
		Student.StudentId = StudentId;
		this.StudentName = StudentName;
	}
	
	Student(String StudentName){
		this.StudentName = StudentName;
	}
	
	public void setStudentId(int StudentId){
		Student.StudentId = StudentId;
	}
 
	public int getStudentId(){
		return StudentId;
	}
 
	public void setStudentName(String StudentName){
		this.StudentName = StudentName;
	}
 
	public String getStudentName(){
		return StudentName;
	}
}
 
class VF_SV{
	public static void main(String args[]) throws IOException, ClassNotFoundException{
 
		// create a student object and write to file "student1.tmp"
		Student studen1 = new Student(2530, "David");
		FileOutputStream fos = new FileOutputStream("student1.tmp");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(studen1);
		fos.close();
		oos.close();
		 
		// create another student object and print out the filed.
		Student student2 = new Student(3389, "Tyler");
		System.out.println(student2.getStudentId() + " , " + student2.getStudentName());
		 
		// Read object student from stundent1 and store in student2
		FileInputStream fis = new FileInputStream("student1.tmp");
		ObjectInputStream ois = new ObjectInputStream(fis);
		student2 = (Student)ois.readObject();
		 
		/* if the static field "studentId" was written to the file, then the studentId
		now would become 2530, since it is the Id being written to the student1.tmp
		*/
		System.out.println(student2.getStudentId() + " , " + student2.getStudentName());
		/* The output of the StudentId is 3389 that is id of student2, not student1.
		 * If the static StudentId was written and read then the number is 2530 not 3389.
		 */
}
}
