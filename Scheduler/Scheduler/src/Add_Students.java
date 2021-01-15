import java.util.ArrayList;
import java.util.HashMap;

//Given the courses and their time slots, insert all of the students into the courses

//course input is a hashmap of course code (key), and values are a pair of the time slots and 
//amount of people in that course

public class Add_Students {
	
	// pair is number slots, and number of people currently in each slot
	HashMap<String, ArrayList<Pair>> temp = new HashMap<String, ArrayList<Pair>>(); //3, 5, 7, 8
	
	StudentsInput studentInput = new StudentsInput();
	
	public Add_Students()
	{
		studentInput.ReadStudentInput("Students.csv");
		
		/*ArrayList<Pair> pair = new ArrayList();
		pair.add(new Pair(1, 0));
		temp.put("TDJ3M1", pair);
		
		pair = new ArrayList();
		pair.add(new Pair(2, 0));
		temp.put("FSF3U6", pair);
		
		pair = new ArrayList();
		pair.add(new Pair(3, 0));
		temp.put("THJ3M1", pair);
		
		pair = new ArrayList();
		pair.add(new Pair(4, 0));
		temp.put("TGJ3M1", pair);
		
		pair = new ArrayList();
		pair.add(new Pair(5, 0));
		temp.put("ENG3U1", pair);
		
		pair = new ArrayList();
		pair.add(new Pair(6, 0));
		temp.put("SPH3U6", pair);
		
		pair = new ArrayList();
		pair.add(new Pair(7, 0));
		temp.put("MCR3U1", pair);
		
		pair = new ArrayList();
		pair.add(new Pair(8, 0));
		temp.put("AWQ3M1", pair);*/
		
		
		
	}
	
	// if all 3 alternates dont work, give them a spare, then have them come personally to discuss
	/*returns the score. score is calculated: alternate 1: +1. and so forth for alternate 2 and 3.
	(+2 and +3)*/
	public int insertStudents() {
		
		int score = 0; // score to see how good this timetable is
		
		for (int i = 0; i < studentInput.StudentInfo.size(); i += 1)
		{
			String[] mainCourses = studentInput.StudentInfo.get(i).getchoiceMain();
			
			for (int j = 0; j < 8; j += 1)
			{
				String specificCourse = mainCourses[j];
				ArrayList<Pair> coursePair = temp.get(specificCourse);
				System.out.println(coursePair.size());
				boolean assigned = false; // tracks if the course has been given to student
				for (Pair k: coursePair) // goes through the slots for this specific course
				{
					if (k.b < 32) 
					{
						k.b += 1; // dont need to track the students, we not storing them. just keep 
						assigned = true;	  // track of the size of the classes, and if we use alternates increment the score.
					}
				}
				// if specific course cant be assigned to student, give them an alternate
				if (assigned == false) 
				{
					String[] altCourses = studentInput.StudentInfo.get(i).getchoiceAlt();
					
					for (int x = 0; x < altCourses.length; x += 1)
					{
						String specificAlt = altCourses[x];
						ArrayList<Pair> altSlots = temp.get(specificCourse);
						
						boolean assignedAlt = false; // tracks if the course has been given to student
						for (Pair k: altSlots) // goes through the slots for this specific course
						{
							if (k.b < 32)
							{
								k.b += 1; // dont need to track the students, we not storing them. just keep 
								assignedAlt = true; 
								score += x + 1; // increment the score cuz using alternate
							}
						}
						
						if (assignedAlt == false) // if no alternates can be given, give spare 
						{
							score += 5;
						}
					}
				}
				
			}
		}
		
		return score;
		
	}
	
	public static void main(String[] args) {
		
		Add_Students main = new Add_Students();
		int score = main.insertStudents();
		
		System.out.println(score);
	}

}
