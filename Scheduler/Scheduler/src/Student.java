public class Student {
	
	public String nameFirst;
	public String nameLast;
	public String grade;
	//public String studentNum;
	public String[] choicesMain; // main 8 course choices
	public String[] choicesAlt; // 3 alternate course choices
	
	
	public Student(String nameFirst, String nameLast, String grade, String[] choicesMain, String[] choicesAlt)
	{
		this.nameFirst = nameFirst;
		this.nameLast = nameLast;
		this.grade = grade;
		this.choicesMain = choicesMain;
		this.choicesAlt = choicesAlt;
	}
	
	public String[] getchoiceMain() {
		
		return choicesMain;
	}
	
	public String[] getchoiceAlt() {
		
		return choicesAlt;
	}
	
}
