import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StudentsInput {
	
	public ArrayList<Student> StudentInfo = new ArrayList<Student>();
	
	/*public static void main(String args[]) {
		ReadStudentInput("Students.csv");
		
		
	}*/
	
	public void ReadStudentInput(String file) {

		File input = new File(file);
		

		try {
			Scanner scan = new Scanner(input);
			
			
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] fields = line.split(" ");
				//System.out.println(fields.length);
				String[] arr1 = Arrays.copyOfRange(fields,0,3); // full name, grade
				String[] arr2 = Arrays.copyOfRange(fields, 3, 11); // 8 choices
				String[] arr3 = Arrays.copyOfRange(fields, 11, 14); // 3 alternate
				
				Student S = new Student(arr1[0],arr1[1],arr1[2],arr2,arr3);
				
				StudentInfo.add(S);				
			}
		

			scan.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found");
			e.printStackTrace();
		}
	}
	
}
