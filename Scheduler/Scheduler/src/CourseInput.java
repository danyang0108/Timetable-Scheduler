import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseInput {
	
	//public variables
	public ArrayList<String> CourseList = new ArrayList<String>();
	public int courseSize;
	
	/*public static void main(String[] args) {
		ReadInput("Courses.csv");
		for (int i = 0 ; i < CourseList.size(); i++) {
			System.out.println(CourseList.get(i));
		}
	}*/
	
	public void ReadInput(String file) {

		File input = new File(file);
		

		try {
			Scanner scan = new Scanner(input);
			
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				//String[] fields = line.split(",");
				CourseList.add(line);
				courseSize++;
			}
			
			

			scan.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found");
			e.printStackTrace();
		}
	}
	public int getcourseSize() {
		return courseSize;
	}
}
